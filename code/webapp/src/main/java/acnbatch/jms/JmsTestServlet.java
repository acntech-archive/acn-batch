package acnbatch.jms;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import acnbatch.websocket.NotificationEndpoint;

@WebServlet(urlPatterns = { "/TestServlet" })
public class JmsTestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private NotificationSender notificationSender;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		outputHtml(response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		notificationSender.send("TestServlet", "Hello JMS!");
		outputHtml(response, "Message sent! (" + NotificationEndpoint.sockets() + " sockets)");
	}

	private void outputHtml(HttpServletResponse response) throws IOException {
		outputHtml(response, null);
	}

	private void outputHtml(HttpServletResponse response, String note) throws IOException {
		PrintWriter writer = response.getWriter();
		writer.println("<html>");
		writer.println("\t<head>");
		writer.println("\t\t<title>JMS Test Servlet</title>");
		writer.println("\t</head>");
		writer.println("\t<body>");
		writer.println("\t\t<h1>JMS Test Servlet</h1>");
		writer.println("\t\t<form method=\"post\">");
		writer.println("\t\t\t<input type=\"submit\" value=\"Send\" />");
		writer.println("\t\t</form>");
		if (note != null) {
			writer.println("\t\t<h4>" + note + "</h4>");
		}
		writer.println("\t</body>");
		writer.println("</html>");
	}
}
