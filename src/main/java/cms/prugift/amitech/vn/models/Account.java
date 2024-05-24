package cms.prugift.amitech.vn.models;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class Account {
    @CsvBindByName
    private String username;
    @CsvBindByName
    private String password;
    @CsvBindByName
    private String expectedErrorMessage;

    public Account(String username, String password, String expectedErrorMessage) {
        this.username = username;
        this.password = password;
        this.expectedErrorMessage = expectedErrorMessage;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getExpectedErrorMessage() {
        return expectedErrorMessage;
    }
}
