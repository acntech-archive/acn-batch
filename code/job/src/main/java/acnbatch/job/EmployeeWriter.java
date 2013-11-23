package acnbatch.job;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;
import java.util.List;

@Named
public class EmployeeWriter extends AbstractItemWriter {

    @Override
    public void writeItems(List<Object> items) throws Exception {

    }
}
