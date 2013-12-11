package acnbatch.job;

import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import acnbatch.jms.NotificationSender;
import acnbatch.job.domain.EmployeeOutputRecord;

@Named
public class EmployeeWriter extends AbstractItemWriter {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private NotificationSender notificationSender;

    @Override
    public void writeItems(List list) {
        System.out.println("writeItems: " + list);
        for (Object employee : list) {
            em.persist(employee);
            notificationSender.send("EmployeeWriter", "Employee inserted. Id: " + ((EmployeeOutputRecord) employee).getId());
        }
    }
}
