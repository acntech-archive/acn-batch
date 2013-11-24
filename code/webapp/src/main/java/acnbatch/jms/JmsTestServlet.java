package acnbatch.jms;

import java.io.IOException;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Queue;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/TestServlet" })
public class JmsTestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Resource(lookup = Resources.NOTIFICATION_QUEUE)
	private Queue queue;

	@Inject
	private JMSContext jmsContext;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendMessage();
		response.getWriter().println("Message sent!");
	}

	private void sendMessage() {
		Message message = jmsContext.createObjectMessage(new Notification("Servlet", "JMS Rocks!"));
		jmsContext.createProducer().send(queue, message);
	}
}
