package acnbatch.job;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Inject;
import javax.inject.Named;

import acnbatch.jms.NotificationSender;

@Named
public class EmployeeReader extends AbstractItemReader {

	private BufferedReader reader;

	@Inject
	private NotificationSender notificationSender;

	@Override
	public void open(Serializable checkpoint) throws Exception {
		reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader()
				.getResourceAsStream("/META-INF/mydata.csv")));
	}

	@Override
	public String readItem() {
		String line;
		try {
			line = reader.readLine();
			notificationSender.send("EmployeeReader", "Employee read: " + line);
			return line;
		} catch (IOException e) {
			Logger.getLogger(EmployeeReader.class.getName()).log(Level.SEVERE, null, e);
		}
		return null;
	}
}
