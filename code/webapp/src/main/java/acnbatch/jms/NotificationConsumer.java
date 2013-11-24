package acnbatch.jms;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MessageDriven(mappedName = Resources.NOTIFICATION_QUEUE)
public class NotificationConsumer implements MessageListener {

	private static final Logger LOG = LoggerFactory.getLogger(MessageListener.class);

	@Override
	public void onMessage(Message message) {
		try {
			Notification notification = message.getBody(Notification.class);

			LOG.info("Message recieved from source '{}' with message '{}'", notification.getSource(),
					notification.getMessage());
		} catch (JMSException e) {
			LOG.error("An error occured when recieving message", e);
		}
	}
}
