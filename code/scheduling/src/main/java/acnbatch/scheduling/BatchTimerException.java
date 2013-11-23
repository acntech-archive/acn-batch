package acnbatch.scheduling;

public class BatchTimerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BatchTimerException() {
		super();
	}

	public BatchTimerException(String message, Throwable cause) {
		super(message, cause);
	}

	public BatchTimerException(String message) {
		super(message);
	}

	public BatchTimerException(Throwable cause) {
		super(cause);
	}
}
