package acnbatch.websocket;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Named;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@Named
@ServerEndpoint(Endpoints.NOTIFICATIONS)
public class NotificationEndpoint implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

	@OnOpen
	public void onOpen(final Session session) {
		sessions.add(session);
	}

	@OnMessage
	public void onMessage(final String message, final Session client) {

	}

	@OnClose
	public void onClose(final Session session) {
		sessions.remove(session);
	}
}
