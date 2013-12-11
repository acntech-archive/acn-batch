package acnbatch.websocket;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.event.Observes;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import acnbatch.events.Notify;
import acnbatch.jms.Notification;

@ServerEndpoint(Endpoints.NOTIFICATIONS)
public class NotificationEndpoint implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(NotificationEndpoint.class);

    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(final Session session) {
        try {
            session.getBasicRemote().sendText("Websocket session opened");
            sessions.add(session);
            LOG.info("Websocket session opened");
        } catch (IOException e) {
            LOG.error("An error occured while opening websocket session", e);
        }
    }

    @OnMessage
    public void onMessage(final String message, final Session client) {
        LOG.info("Message revieved from websocket '" + message + "'");
    }

    @OnClose
    public void onClose(final Session session) {
        try {
            session.getBasicRemote().sendText("WebSocket session closed");
            sessions.remove(session);
            LOG.info("Websocket session closed");
        } catch (IOException e) {
            LOG.error("An error occured while closing websocket session", e);
        }
    }

    public void push(@Observes @Notify Notification notification) {

        for (Session session : sessions) {
            try {
                LOG.info("Pushing notifications to websockets");

                session.getBasicRemote().sendText(
                        "Notification from source '" + notification.getSource() + "' with subject '"
                        + notification.getSubject() + "'");

            } catch (IOException e) {
                LOG.error("An error occured while pushing notification to websocket", e);
            }
        }
    }

    public static int sockets() {
        return sessions.size();
    }
}
