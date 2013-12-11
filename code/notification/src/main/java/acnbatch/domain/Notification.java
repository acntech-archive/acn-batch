package acnbatch.domain;

import java.io.Serializable;

public class Notification implements Serializable {

	private static final long serialVersionUID = 1L;

	private String source;
	private Type type;
	private String subject;
	private String details;

	public Notification() {
	}

	public Notification(String source, String subject) {
		this(source, Type.INFO, subject);
	}

	public Notification(String source, Type type, String subject) {
		this(source, type, subject, null);
	}

	public Notification(String source, String subject, String details) {
		this(source, Type.INFO, subject, details);
	}

	public Notification(String source, Type type, String subject, String details) {
		this.source = source;
		this.type = type;
		this.subject = subject;
		this.details = details;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
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
