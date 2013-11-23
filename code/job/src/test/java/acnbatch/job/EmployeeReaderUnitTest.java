package acnbatch.job;

import org.apache.poi.ss.usermodel.Row;

import java.io.InputStream;

/**
 * Created by blystad on 11/23/13.
 */
public class EmployeeReaderUnitTest {

    public void testReadEmployeesFromXlsx() throws Exception {
        InputStream employeeData = this.getClass().getResourceAsStream("test_data.xlsx");

        EmployeeReader reader = new EmployeeReader(employeeData);
        reader.open(null);

    }

}
