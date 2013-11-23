package acnbatch.scheduling;

import javax.ejb.ScheduleExpression;
import javax.ejb.TimerConfig;

public abstract class Schedulable {

	public abstract String name();

	public abstract void run();

	public abstract ScheduleExpression expression();

	public abstract TimerConfig config();
}
