package acnbatch.websocket;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.Future;

import javax.enterprise.event.Observes;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Async;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import acnbatch.domain.Notification;
import acnbatch.events.Notify;

@ServerEndpoint(Endpoints.NOTIFICATIONS)
public class NotificationEndpoint implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(NotificationEndpoint.class);

	@OnOpen
	public void onOpen(final Session session) {
		String id = session.getId();
		try {
			sendSynchronousNotification(session, "WebSocket session '" + id + "' opened");
			SessionHandler.add(session);
			LOG.info("Websocket session '{}' opened", id);
		} catch (IOException e) {
			LOG.error("An error occured while opening websocket session '" + id + "'", e);
		}
	}

	@OnMessage
	public void onMessage(final String message, final Session client) {
		LOG.info("Message revieved from websocket '" + message + "'");
	}

	@OnClose
	public void onClose(final Session session) {
		String id = session.getId();
		try {
			sendSynchronousNotification(session, "WebSocket session '" + id + "' closed");
			SessionHandler.remove(session);
			LOG.info("Websocket session '" + id + "' closed");
		} catch (IOException e) {
			LOG.error("An error occured while closing websocket session '" + id + "'", e);
		}
	}

	public void push(@Observes @Notify Notification notification) {
		String notificationMessage = createNotificationMessage(notification);
		Collection<Session> sessions = SessionHandler.all();
		for (Session session : sessions) {
			String id = session.getId();
			try {
				LOG.info("Pushing notifications to websockets");
				if (session.isOpen()) {
					sendSynchronousNotification(session, notificationMessage);
				} else {
					LOG.warn("Websocket session '{}' is closed", id);
				}

			} catch (IOException e) {
				LOG.error("An error occured while pushing notification to websocket session '" + id + "'", e);
			}
		}
	}

	public static int sockets() {
		return SessionHandler.count();
	}

	public void sendSynchronousNotification(Session session, String notificationMessage) throws IOException {
		Basic basic = session.getBasicRemote();
		basic.sendText(notificationMessage);
	}

	public Future<Void> sendAsynchronousNotification(Session session, String notificationMessage) {
		Async async = session.getAsyncRemote();
		return async.sendText(notificationMessage);
	}

	public String createNotificationMessage(Notification notification) {
		String source = notification.getSource();
		String subject = notification.getSubject();
		return "Notification from source '" + source + "' with subject '" + subject + "'";
	}
}
