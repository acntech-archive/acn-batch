package acnbatch.jms;

import java.io.Serializable;

public class Notification implements Serializable {

	private static final long serialVersionUID = 1L;

	private String source;
	private String subject;

	public Notification() {
	}

	public Notification(String source, String subject) {
		this.source = source;
		this.subject = subject;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
