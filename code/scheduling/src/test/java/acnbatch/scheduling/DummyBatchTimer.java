package acnbatch.scheduling;

import javax.ejb.ScheduleExpression;
import javax.ejb.TimerConfig;

public class DummyBatchTimer extends BatchTimer {

	@Override
	public Schedulable schedulable() {
		return new DummySchedulable();
	}

	public class DummySchedulable extends Schedulable {

		@Override
		public String name() {
			return this.getClass().getSimpleName();
		}

		@Override
		public void run() {
			System.out.println();
		}

		@Override
		public ScheduleExpression expression() {
			return new ScheduleExpression();
		}

		@Override
		public TimerConfig config() {
			return null;
		}
	}
}
