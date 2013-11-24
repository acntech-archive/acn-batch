package acnbatch.scheduling;

import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Created by blystad on 11/24/13.
 */
@Singleton
@Startup
public class EmployeeJobBatchTimer extends BatchTimer {

    private EmployeeJobSchedule employeeJobSchedule  = new EmployeeJobSchedule();

    @Override
    public Schedulable schedulable() {
        return employeeJobSchedule;
    }
}
