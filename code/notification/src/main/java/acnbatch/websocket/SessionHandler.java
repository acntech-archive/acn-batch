package acnbatch.websocket;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

public final class SessionHandler {

	private static final Map<String, Session> sessions = Collections.synchronizedMap(new HashMap<String, Session>());

	private SessionHandler() {
	}

	public static void add(Session session) {
		if (session != null) {
			sessions.put(session.getId(), session);
		}
	}

	public static void remove(Session session) {
		if (session != null) {
			sessions.remove(session.getId());
		}
	}

	public static Collection<Session> all() {
		return sessions.values();
	}

	public static int count() {
		return sessions.size();
	}
}
