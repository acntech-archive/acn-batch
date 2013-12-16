package acnbatch.jms;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import acnbatch.domain.Notification;
import acnbatch.domain.Type;
import acnbatch.metadata.Resources;

public class NotificationSender {

	private static final Logger LOG = LoggerFactory.getLogger(NotificationSender.class);

	@Resource(lookup = Resources.NOTIFICATION_QUEUE)
	private Queue queue;

	@Inject
	private JMSContext jmsContext;

	public void send(String source, String subject) {
		send(new Notification(source, subject));
	}

	public void send(Notification notification) {
		LOG.info("Sending notification with subject '{}'", notification.getSubject());
		Message message = jmsContext.createObjectMessage(notification);
		jmsContext.createProducer().send(queue, message);
	}

	public void error(String source, Throwable throwable) {
		send(new Notification(Type.ERROR, source, throwable.getMessage()));
	}
}