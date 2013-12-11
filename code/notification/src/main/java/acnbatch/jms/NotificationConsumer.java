package acnbatch.jms;

import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import acnbatch.events.Notify;
import acnbatch.websocket.NotificationEndpoint;

@MessageDriven(mappedName = Resources.NOTIFICATION_QUEUE)
public class NotificationConsumer implements MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationConsumer.class);

    @Inject
    @Notify
    private Event<Notification> notify;

    @Override
    public void onMessage(Message message) {
        try {
            Notification notification = message.getBody(Notification.class);

            LOG.info("Notification recieved from source '{}' with subject '{}'", notification.getSource(),
                    notification.getSubject());

            LOG.info("Active websocket sessions: {}", NotificationEndpoint.sockets());

            notify.fire(notification);
        } catch (JMSException e) {
            LOG.error("An error occured when recieving message", e);
        }
    }
}
