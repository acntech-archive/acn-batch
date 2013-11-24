package acnbatch.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.ScheduleExpression;
import javax.ejb.TimerConfig;
import java.util.Properties;

/**
 * Created by blystad on 11/24/13.
 */
public class EmployeeJobSchedule extends Schedulable {

    static Logger logger = LoggerFactory.getLogger(EmployeeJobSchedule.class);

    @Override
    public String name() {
        return EmployeeJobSchedule.class.getSimpleName();
    }

    @Override
    public void run() {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        long jobId = jobOperator.start("job", new Properties());
        logger.info("Started job {}", new Object[]{jobId});
    }

    @Override
    public ScheduleExpression expression() {
        return new ScheduleExpression().minute("*/1").hour("*");
    }

    @Override
    public TimerConfig config() {
        return new TimerConfig();
    }
}
