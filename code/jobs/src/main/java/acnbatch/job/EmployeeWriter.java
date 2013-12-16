package acnbatch.job;

import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import acnbatch.entities.EmployeeOutputRecord;
import acnbatch.jms.NotificationSender;

@Named
public class EmployeeWriter extends AbstractItemWriter {

	private static final String NOTIFICATION_SOURCE = EmployeeWriter.class.getSimpleName();

	@PersistenceContext
	private EntityManager em;

	@Inject
	private NotificationSender notificationSender;

	@Override
	public void writeItems(@SuppressWarnings("rawtypes") List list) {
		System.out.println("writeItems: " + list);
		try {
			for (Object employee : list) {
				em.persist(employee);
				notificationSender.send(NOTIFICATION_SOURCE, "Employee inserted. Id: "
						+ ((EmployeeOutputRecord) employee).getId());
			}
		} catch (Exception e) {
			notificationSender.error(NOTIFICATION_SOURCE, e);
		}
	}
}
