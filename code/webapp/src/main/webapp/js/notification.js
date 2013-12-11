var websocketSession;

function f_onmessage(evt) {
	notifications = document.getElementById('notifications');
	notifications.innerHTML = notifications.innerHTML + evt.data + '<br />';
}

function open() {
	if (!websocketSession) {
		pathArray = document.location.pathname.split('/');
		websocketSession = new WebSocket('ws://' + document.location.host + '/' + pathArray[1] + '/api/notifications');
		websocketSession.onmessage = f_onmessage;
	}
}

function close() {
	if (websocketSession) {
		websocketSession.close();
	}
}

function sendMessage(msg) {
	websocketSession.send(msg);
}