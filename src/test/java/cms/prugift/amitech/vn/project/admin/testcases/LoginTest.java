package cms.prugift.amitech.vn.project.admin.testcases;

import cms.prugift.amitech.vn.common.WebUI;
import cms.prugift.amitech.vn.helpers.CaptureHelpers;
import cms.prugift.amitech.vn.helpers.ExcelHelper;
import cms.prugift.amitech.vn.commons.BaseSetup;
import cms.prugift.amitech.vn.project.admin.pages.LoginPage;
import cms.prugift.amitech.vn.utils.enums.Author;
import cms.prugift.amitech.vn.utils.enums.Browser;
import cms.prugift.amitech.vn.utils.enums.Category;
import cms.prugift.amitech.vn.utils.extentreport.ExtentReportListener;
import cms.prugift.amitech.vn.utils.extentreport.TestInfo;
import cms.prugift.amitech.vn.utils.LogUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

@Listeners(ExtentReportListener.class)
public class LoginTest {
    private static WebDriver driver;
    private LoginPage loginPage;
    private String url = "https://cms.prugift.amitech.vn/";
    private ExcelHelper excelHelper = new ExcelHelper();
    private WebUI webUI;
    private static ExtentReports extent;
    private ExtentTest extentTest;

    @BeforeClass
    public void setUp() throws Exception {
        driver = new BaseSetup().setupDriver("chrome");
        CaptureHelpers.startRecord("Login");
        webUI = new WebUI(driver);
        excelHelper.setExcelFile("src/test/resources/testData/excel/DataTest.xlsx", "Login");
    }

    @Test(priority = 1)
    @TestInfo(author = Author.LEGIANG, categories = {Category.REGRESSION}, browser = Browser.CHROME)
    public void loginFailWithUsernameNull() throws Exception {
        loginPage = new LoginPage(driver);
        driver.get(url);
        loginPage.loginFailWithUsernameNull(excelHelper.getCellData("password", 1));
        excelHelper.setCellData("Pass", 1, 3);
        webUI.waitForPageLoaded();
    }

    @Test(priority = 2)
    @TestInfo(author = Author.LEGIANG, categories = {Category.REGRESSION}, browser = Browser.CHROME)
    public void loginFailWithUsernameDoesNotExist() throws Exception {
        loginPage = new LoginPage(driver);
        driver.get(url);
        loginPage.loginFailWithUsernameDoesNotExist(excelHelper.getCellData("username", 2), excelHelper.getCellData("password", 2));
        webUI.waitForPageLoaded();
    }

    @Test(priority = 3)
    @TestInfo(author = Author.LEGIANG, categories = {Category.REGRESSION}, browser = Browser.CHROME)
    public void loginFailWithPasswordNull() throws Exception {
        loginPage = new LoginPage(driver);
        driver.get(url);
        loginPage.loginFailWithPasswordNull(excelHelper.getCellData("username", 3));
        webUI.waitForPageLoaded();
    }

    @Test(priority = 4)
    @TestInfo(author = Author.LEGIANG, categories = {Category.REGRESSION}, browser = Browser.CHROME)
    public void testHideAndUnHidePassword() throws Exception {
        loginPage = new LoginPage(driver);
        driver.get(url);
        loginPage.testHideAndUnHidePassword();
        webUI.waitForPageLoaded();
    }

    @Test(priority = 5)
    @TestInfo(author = Author.LEGIANG, categories = {Category.REGRESSION}, browser = Browser.CHROME)
    public void loginFailWithIncorrectPassword() throws Exception {
        loginPage = new LoginPage(driver);
        driver.get(url);
        loginPage.loginFailWithIncorrectPassword(excelHelper.getCellData("username", 5), excelHelper.getCellData("password", 5));
        webUI.waitForPageLoaded();
    }

    @Test(priority = 6)
    @TestInfo(author = Author.LEGIANG, categories = {Category.REGRESSION}, browser = Browser.CHROME)
    public void loginAdminSuccsess() throws Exception {
        LogUtils.info("Run test case: loginAdminSuccsess");
        loginPage = new LoginPage(driver);
        driver.get(url);
        loginPage.loginAdminSuccsess(excelHelper.getCellData("username", 6), excelHelper.getCellData("password", 6));
        webUI.waitForPageLoaded();
    }


    @AfterMethod
    public void takeScreenshot(ITestResult result) {
        // passed = SUCCESS v� failed = FAILURE
        if (ITestResult.FAILURE == result.getStatus()) {
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
