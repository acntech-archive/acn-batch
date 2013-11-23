package acnbatch.job;

import acnbatch.job.domain.EmployeeInputRecord;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

/**
 * Test to check that the EmployeeReader class can read from a xlsx file and return
 * EmployeeInputRecord objects with the information stored in the xlsx file.
 *
 * Created by blystad on 11/23/13.
 */
public class EmployeeReaderUnitTest {

    private String[] remaining_names = {"Navn 2", "Navn 3", "Navn 4"};
    
    @Test
    public void testReadEmployeesFromXlsx() throws Exception {
        InputStream employeeData = this.getClass().getClassLoader().getResourceAsStream("test_data.xlsx");

        EmployeeReader reader = new EmployeeReader(employeeData);
        reader.open(null);

        EmployeeInputRecord record = reader.readItem();

        Assert.assertEquals("Navn 1", record.getName());
        Assert.assertEquals("251244", record.getPersonalNumber());
        Assert.assertEquals("navn1@accenture.com", record.getEmail());
        Assert.assertEquals("AD/navn1", record.getPhone()); // TODO: Where to obtain phone number? Not part of XLSX

        for (String name : remaining_names) {
            record = reader.readItem();

            Assert.assertEquals(name, record.getName());
        }

        reader.close();
    }

}
