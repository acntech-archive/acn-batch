package acnbatch.job;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Named;

@Named
public class EmployeeReader extends AbstractItemReader {

    @Override
    public Object readItem() throws Exception {
        return null;
    }
}
