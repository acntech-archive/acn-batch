package acnbatch.scheduling;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BatchTimer {

	private static final Logger LOG = LoggerFactory.getLogger(BatchTimer.class);

	@Resource
	private TimerService timerService;

	@PostConstruct
	public void create() {
		if (schedulable() != null && schedulable().expression() != null) {
			ScheduleExpression expression = schedulable().expression();

			LOG.info("Creating timer for scheduer {} with schedule {}M {}D {}h {}m {}s", schedulable().name(),
					expression.getMonth(), expression.getDayOfMonth(), expression.getHour(), expression.getMinute(),
					expression.getSecond());
			try {
				timerService.createCalendarTimer(expression, schedulable().config());
			} catch (Exception e) {
				LOG.error("An error occured when trying to create timer", e);
				throw new BatchTimerException("An error occured when trying to create timer", e);
			}
		} else {
			LOG.error("Schedulable is not set. Unable to continue");
			throw new BatchTimerException("Schedulable is not set");
		}
	}

	@Timeout
	public void fire(Timer timer) {
		if (schedulable() != null) {
			schedulable().run();
		} else {
			LOG.error("Schedulable is not set. Unable to continue");
			throw new BatchTimerException("Schedulable is not set");
		}
	}

	public abstract Schedulable schedulable();
}
