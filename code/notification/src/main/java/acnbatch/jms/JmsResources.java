package acnbatch.jms;

import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;

@JMSDestinationDefinitions({
    @JMSDestinationDefinition(name = JmsResources.SYNC_APP_MANAGED_QUEUE,
        resourceAdapter = "jmsra",
        interfaceName = "javax.jms.Queue",
        destinationName="syncAppQueue",
        description="My Sync Queue for App-managed JMSContext")       ,
})
public class JmsResources {
    public static final String SYNC_APP_MANAGED_QUEUE = "java:global/jms/mySyncAppQueue";
}