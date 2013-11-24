package acnbatch.job;

import acnbatch.job.domain.EmployeeInputRecord;
import acnbatch.job.domain.EmployeeOutputRecord;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.ejb.Singleton;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Singleton
@Named
public class EmployeeWriter extends AbstractItemWriter {


    @Override
    public void writeItems(List<Object> items) throws Exception {
        // HACK
        EntityManagerFactory hackfest = Persistence.createEntityManagerFactory("hackfest");

        EntityManager entityManager = hackfest.createEntityManager();
        entityManager.getTransaction().begin();

        for (Object item : items) {
            EmployeeInputRecord record = (EmployeeInputRecord) item;

            EmployeeOutputRecord outputRecord = new EmployeeOutputRecord(record);
            entityManager.persist(outputRecord);
        }

        entityManager.getTransaction().commit();
    }
}
