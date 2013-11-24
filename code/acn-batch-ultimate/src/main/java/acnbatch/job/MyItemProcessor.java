package acnbatch.job;

import acnbatch.job.domain.EmployeeOutputRecord;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

@Named
public class MyItemProcessor implements ItemProcessor {
    SimpleDateFormat format = new SimpleDateFormat("M/dd/yy");

    @Override
    public EmployeeOutputRecord processItem(Object t) {
        System.out.println("processItem: " + t);
        
        StringTokenizer tokens = new StringTokenizer((String)t, ";");
        
        EmployeeOutputRecord output = new EmployeeOutputRecord();
        output.setName(tokens.nextToken());
        output.setPersonalNumber(tokens.nextToken());
        output.setEmail(tokens.nextToken());
        output.setEnterpriseId(tokens.nextToken());
        output.setPhone(tokens.nextToken());
        
        return output;
    }
}
