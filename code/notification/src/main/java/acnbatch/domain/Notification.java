package acnbatch.domain;

import java.io.Serializable;

public class Notification implements Serializable {

	private static final long serialVersionUID = 1L;

	private Type type;
	private String source;
	private String subject;
	private String details;

	public Notification() {
	}

	public Notification(String source, String subject) {
		this(Type.INFO, source, subject);
	}

	public Notification(Type type, String source, String subject) {
		this(type, source, subject, null);
	}

	public Notification(String source, String subject, String details) {
		this(Type.INFO, source, subject, details);
	}

	public Notification(Type type, String source, String subject, String details) {
		this.type = type;
		this.source = source;
		this.subject = subject;
		this.details = details;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
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

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
