package acnbatch.job;

import acnbatch.jms.NotificationSender;
import acnbatch.job.domain.EmployeeOutputRecord;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EmployeeProcessor implements ItemProcessor {

    SimpleDateFormat format = new SimpleDateFormat("M/dd/yy");

    @Inject
    private NotificationSender notificationSender;

    @Override
    public EmployeeOutputRecord processItem(Object t) {
        System.out.println("processItem: " + t);

        StringTokenizer tokens = new StringTokenizer((String) t, ";");

        EmployeeOutputRecord output = new EmployeeOutputRecord();
        output.setName(tokens.nextToken());
        output.setPersonalNumber(tokens.nextToken());
        output.setEmail(tokens.nextToken());
        output.setEnterpriseId(tokens.nextToken());
        output.setPhone(tokens.nextToken());
        
        notificationSender.send("EmployeeProcessor", "Employee created. PersonalNumber: " + output.getPersonalNumber());

        return output;
    }
}
