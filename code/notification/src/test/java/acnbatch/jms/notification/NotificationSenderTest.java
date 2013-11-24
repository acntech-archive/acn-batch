package acnbatch.jms.notification;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class NotificationSenderTest {

    @Mock
    ConnectionFactory connectionFactory;

    @Mock
    Queue queue;
    
    @Mock
    JMSContext jmsContext;
   
    @Mock
    JMSProducer jmsProducer;
    
    
    
    @InjectMocks
    NotificationSender notificationSender;
    
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testSendMessage() {
        
        Mockito.when(connectionFactory.createContext()).thenReturn(jmsContext);
        Mockito.when(jmsContext.createProducer()).thenReturn(jmsProducer);
        
        notificationSender.sendMessage("TestMessage");
        
        Mockito.verify(connectionFactory).createContext();
        Mockito.verify(jmsContext).createProducer();
        Mockito.verify(jmsProducer).send(queue, "TestMessage");  
    }          
}
