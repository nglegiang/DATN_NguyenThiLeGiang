package cms.prugift.amitech.vn.admin.testcases;

import cms.prugift.amitech.vn.common.WebUI;
import cms.prugift.amitech.vn.dataprovider.LoginDataProvider;
import cms.prugift.amitech.vn.helpers.CaptureHelpers;
import cms.prugift.amitech.vn.helpers.ExcelHelper;
import cms.prugift.amitech.vn.commons.BaseSetup;
import cms.prugift.amitech.vn.admin.pages.LoginPage;
import cms.prugift.amitech.vn.utils.PropertiesFile;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;

import static cms.prugift.amitech.vn.utils.PropertiesFile.projectPath;

public class LoginCSV {
    private WebDriver driver;
    private LoginPage loginPage;
    private String url = "https://cms.prugift.amitech.vn/";
    private WebUI webUI;
    ;

    @BeforeClass
    public void setUp() throws Exception {
        CaptureHelpers.startRecord("RecordLoginCSV");
        webUI = new WebUI(driver);
        driver = new BaseSetup().setupDriver("chrome");
    }

    @Test(dataProviderClass = LoginDataProvider.class, dataProvider = "login-data-csv")
    public void testLoginData(String[] loginData) {
        String username = loginData[1];
        String password = loginData[2];
        String msgError = loginData[3];
        String expected = loginData[4];
        loginPage = new LoginPage(driver);
        driver.get(url);
        loginPage.testCSV(username, password, msgError, expected);
    }

    @AfterMethod
    public void takeScreenshot(ITestResult result) {
        // passed = SUCCESS v� failed = FAILURE
        if (ITestResult.SUCCESS == result.getStatus()) {
            try {
                CaptureHelpers.captureScreenshot(driver, result.getName());

            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        CaptureHelpers.stopRecord();
    }
}
