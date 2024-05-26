package cms.prugift.amitech.vn.utils.dataprovider;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class LoginDataProvider {
    @DataProvider(name = "login-data-csv")
    public Object[][] readCSVData() throws Exception {
        String[][] testData;
        //Get the workbook
        Reader fileInputStream = new FileReader("src/test/resources/testData/csv/Login.csv");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(fileInputStream);
        int numberOfRecords = 0;
        int numberOfColumns = 0;
        for (CSVRecord record : records
        ) {
            ++numberOfRecords;
            numberOfColumns = record.size();
        }
        testData = new String[numberOfRecords - 1][numberOfColumns];
        int currentRecord = 0;
        fileInputStream = new FileReader("src/test/resources/testData/csv/Login.csv", StandardCharsets.UTF_8);
        records = CSVFormat.EXCEL.parse(fileInputStream);

        for (CSVRecord record : records
        ) {
            if (record.getRecordNumber() == 1) {
                continue;
            }
            for (int i = 0; i < record.size(); i++) {
                testData[currentRecord][i] = record.get(i);
            }
            currentRecord++;
        }
        return testData;
    }

}
