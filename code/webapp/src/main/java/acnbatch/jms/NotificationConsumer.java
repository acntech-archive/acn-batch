package acnbatch.jms;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = Resources.NOTIFICATION_QUEUE)
public class NotificationConsumer implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			Notification notification = message.getBody(Notification.class);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
