package acnbatch.jms;

import java.io.IOException;
import java.util.Set;

import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import acnbatch.websocket.NotificationEndpoint;
import acnbatch.websocket.NotificationEvent;

@MessageDriven(mappedName = Resources.NOTIFICATION_QUEUE)
public class NotificationConsumer implements MessageListener {

	private static final Logger LOG = LoggerFactory.getLogger(MessageListener.class);

	@Inject
	@NotificationEvent
	private Event<Message> notificationEvent;

	@Override
	public void onMessage(Message message) {
		try {
			Notification notification = message.getBody(Notification.class);

			LOG.info("Notification recieved from source '{}' with message '{}'", notification.getSource(),
					notification.getMessage());

			LOG.info("Active websocket sessions: {}", NotificationEndpoint.sessions.size());

			push(message);
		} catch (JMSException e) {
			LOG.error("An error occured when recieving message", e);
		}
	}

	public void push(Message message) {
		try {
			LOG.info("Pushing notifications to websockets");

			Notification notification = message.getBody(Notification.class);

			final Set<Session> sessions = NotificationEndpoint.sessions;

			for (Session session : sessions) {
				session.getBasicRemote().sendText(
						"Notification from source '" + notification.getSource() + "' with message '"
								+ notification.getMessage() + "'");
			}
		} catch (IOException e) {
			LOG.error("An error occured while pushing notification to websocket", e);
		} catch (JMSException e) {
			LOG.error("An error occured while getting message body", e);
		}
	}
}