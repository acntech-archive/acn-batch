package acnbatch.job;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Iterator;

import acnbatch.job.domain.EmployeeInputRecord;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

@Named
public class EmployeeReader extends AbstractItemReader {


    public static final int CELL_EMPLOYEE_NAME = 0;
    public static final int CELL_EMPLOYEE_EMAIL = 2;
    public static final int CELL_EMPLOYEE_PERSONAL_NUMBER = 1;
    public static final int CELL_EMPLOYEE_PHONE = 3;

    private final InputStream inputStream;
    private Iterator<Row> rowIterator;

    @Inject
    public EmployeeReader(InputStream workbookInputStream) {
        this.inputStream = workbookInputStream;
    }

    @Override
    public void open(Serializable checkpoint) throws Exception {
        Workbook workbook = WorkbookFactory.create(inputStream);

        rowIterator = workbook.getSheetAt(0).rowIterator();
        rowIterator.next(); // Skip header
    }

    @Override
    public EmployeeInputRecord readItem() throws Exception {
        Row row = rowIterator.next();

        EmployeeInputRecord employeeInputRecord = new EmployeeInputRecord();
        employeeInputRecord.setName(row.getCell(CELL_EMPLOYEE_NAME).getStringCellValue());
        employeeInputRecord.setPersonalNumber(Integer.toString((int) row.getCell(CELL_EMPLOYEE_PERSONAL_NUMBER).getNumericCellValue()));
        employeeInputRecord.setEmail(row.getCell(CELL_EMPLOYEE_EMAIL).getStringCellValue());
        employeeInputRecord.setPhone(row.getCell(CELL_EMPLOYEE_PHONE).getStringCellValue());

        return employeeInputRecord;
    }

    @Override
    public void close() throws Exception {
        inputStream.close();
    }
}
