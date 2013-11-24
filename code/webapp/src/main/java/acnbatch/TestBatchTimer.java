package acnbatch;

import acnbatch.job.EmployeeWriter;
import acnbatch.scheduling.BatchTimer;
import acnbatch.scheduling.Schedulable;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * Created by blystad on 11/24/13.
 */
@Singleton
@Startup
public class TestBatchTimer extends BatchTimer {

    private TestSchedule testSchedule = new TestSchedule();

    @Inject
    private EmployeeWriter randombean;

    @Override
    public Schedulable schedulable() {
        return testSchedule;
    }
}
