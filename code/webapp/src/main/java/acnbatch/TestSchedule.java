package acnbatch;

import acnbatch.scheduling.Schedulable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ScheduleExpression;
import javax.ejb.TimerConfig;
import javax.inject.Named;

/**
 * Created by blystad on 11/24/13.
 */
@Named
public class TestSchedule extends Schedulable {

    static Logger logger = LoggerFactory.getLogger(TestSchedule.class);

    @Override
    public String name() {
        return TestSchedule.class.getSimpleName();
    }

    @Override
    public void run() {
        logger.info("Hello World");

        logger.info("RandomBean: " + null);
    }

    @Override
    public ScheduleExpression expression() {
        return new ScheduleExpression().second("*/10").minute("*").hour("*");
    }

    @Override
    public TimerConfig config() {
        return new TimerConfig();
    }
}
