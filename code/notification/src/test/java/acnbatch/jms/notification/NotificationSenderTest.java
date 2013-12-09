package acnbatch.jms.notification;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import acnbatch.jms.Notification;
import acnbatch.jms.NotificationSender;

public class NotificationSenderTest {

	@InjectMocks
	private NotificationSender notificationSender;

	@Mock
	private Queue queue;

	@Mock
	private JMSContext jmsContext;

	@Mock
	private JMSProducer jmsProducer;

	@Mock
	private ObjectMessage message;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSendMessage() {
		Mockito.when(jmsContext.createProducer()).thenReturn(jmsProducer);
		Mockito.when(jmsContext.createObjectMessage(Mockito.any(Notification.class))).thenReturn(message);

		notificationSender.send("Test", "TestMessage");

		Mockito.verify(jmsContext).createObjectMessage(Mockito.any(Notification.class));
		Mockito.verify(jmsContext).createProducer();
		Mockito.verifyNoMoreInteractions(jmsContext);
		Mockito.verify(jmsProducer).send(queue, message);
		Mockito.verifyNoMoreInteractions(jmsContext);
	}
}
