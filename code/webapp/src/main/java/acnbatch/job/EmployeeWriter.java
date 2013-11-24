package acnbatch.job;

import acnbatch.job.domain.EmployeeInputRecord;
import acnbatch.job.domain.EmployeeOutputRecord;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Named
public class EmployeeWriter extends AbstractItemWriter {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void writeItems(List<Object> items) throws Exception {

        for (Object item : items) {
            EmployeeInputRecord record = (EmployeeInputRecord) item;

            EmployeeOutputRecord outputRecord = new EmployeeOutputRecord(record);
            entityManager.persist(outputRecord);
        }

    }
}
