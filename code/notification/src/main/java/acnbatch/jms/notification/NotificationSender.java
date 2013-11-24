package acnbatch.jms.notification;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Stateless
public class NotificationSender { 

    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/MyQueue")
    private Queue queue;
    
    public void sendMessage(String message) {
        try (JMSContext context = connectionFactory.createContext()) {
            context.createProducer().send(queue, message);
        }
    }
}