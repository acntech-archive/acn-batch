package acnbatch.job;


public class EmployeeReaderUnitTest {

    private String[] remaining_names = {"Navn 2", "Navn 3", "Navn 4"};
    
//    @Test
    /*
    public void testReadEmployeesFromXlsx() throws Exception {
        InputStream employeeData = this.getClass().getClassLoader().getResourceAsStream("test_data.xlsx");

        EmployeeReader reader = new EmployeeReader();
        reader.setInputStream(employeeData);
        reader.open(null);

        EmployeeInputRecord record = reader.readItem();

        Assert.assertEquals("Navn 1", record.getName());
        Assert.assertEquals("251244", record.getPersonalNumber());
        Assert.assertEquals("navn1.etternavn@accenture.com", record.getEmail());
        Assert.assertNull(record.getPhone());
        Assert.assertEquals("navn1.etternavn", record.getEnterpriseId());

        for (String name : remaining_names) {
            record = reader.readItem();

            Assert.assertEquals(name, record.getName());
        }

        // By now we should have read all rows, and the next row should be null.
        Assert.assertNull(reader.readItem());

        reader.close();
    }
*/
}
