package acnbatch.job;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

@Named
public class EmployeeReader extends AbstractItemReader {


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
    public Row readItem() throws Exception {
        return rowIterator.next();
    }

    @Override
    public void close() throws Exception {
        inputStream.close();
    }
}
