package cms.prugift.amitech.vn.admin.testcases;

import cms.prugift.amitech.vn.common.WebUI;
import cms.prugift.amitech.vn.helpers.CaptureHelpers;
import cms.prugift.amitech.vn.helpers.ExcelHelper;
import cms.prugift.amitech.vn.commons.BaseSetup;
import cms.prugift.amitech.vn.admin.pages.LoginPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private String url = "https://cms.prugift.amitech.vn/";
    private ExcelHelper excelHelper = new ExcelHelper();
    private WebUI webUI;
    ;

    @BeforeClass
    public void setUp() throws Exception {
        CaptureHelpers.startRecord("RecordLoginTest");
        webUI = new WebUI(driver);
        driver = new BaseSetup().setupDriver("chrome");
        excelHelper.setExcelFile("src/test/resources/testData/excel/DataTest.xlsx", "Login");
    }

    @Test(priority = 1)
    public void loginFailWithUsernameNull() throws Exception {
        loginPage = new LoginPage(driver);
        driver.get(url);
        loginPage.loginFailWithUsernameNull(excelHelper.getCellData("password", 1));
        excelHelper.setCellData("Pass", 1, 3);
        webUI.waitForPageLoaded();
    }

    @Test(priority = 2)
    public void loginFailWithUsernameDoesNotExist() throws Exception {
        loginPage = new LoginPage(driver);
        driver.get(url);
        loginPage.loginFailWithUsernameDoesNotExist(excelHelper.getCellData("username", 2), excelHelper.getCellData("password", 2));
        webUI.waitForPageLoaded();
    }

    @Test(priority = 3)
    public void loginFailWithPasswordNull() throws Exception {
        loginPage = new LoginPage(driver);
        driver.get(url);
        loginPage.loginFailWithPasswordNull(excelHelper.getCellData("username", 3));
        webUI.waitForPageLoaded();
    }

    @Test(priority = 4)
    public void testHideAndUnHidePassword() throws Exception {
        loginPage = new LoginPage(driver);
        driver.get(url);
        loginPage.testHideAndUnHidePassword();
        webUI.waitForPageLoaded();
    }

    @Test(priority = 5)
    public void loginFailWithIncorrectPassword() throws Exception {
        loginPage = new LoginPage(driver);
        driver.get(url);
        loginPage.loginFailWithIncorrectPassword(excelHelper.getCellData("username", 5), excelHelper.getCellData("password", 5));
        webUI.waitForPageLoaded();
    }

    @Test(priority = 6)
    public void loginAdminSuccsess() throws Exception {
        loginPage = new LoginPage(driver);
        driver.get(url);
        loginPage.loginAdminSuccsess(excelHelper.getCellData("username", 6), excelHelper.getCellData("password", 6));
        webUI.waitForPageLoaded();
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
