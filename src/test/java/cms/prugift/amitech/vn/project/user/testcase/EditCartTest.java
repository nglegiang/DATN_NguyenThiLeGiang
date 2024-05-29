package cms.prugift.amitech.vn.project.user.testcase;

import cms.prugift.amitech.vn.common.WebUI;
import cms.prugift.amitech.vn.commons.BaseSetup;
import cms.prugift.amitech.vn.helpers.CaptureHelpers;
import cms.prugift.amitech.vn.project.user.pages.DetailProductPage;
import cms.prugift.amitech.vn.project.user.pages.EditCartPage;
import cms.prugift.amitech.vn.project.user.pages.HomePage;
import cms.prugift.amitech.vn.project.user.pages.LoginUserPage;
import cms.prugift.amitech.vn.helpers.ExcelHelper;
import cms.prugift.amitech.vn.utils.enums.Author;
import cms.prugift.amitech.vn.utils.enums.Browser;
import cms.prugift.amitech.vn.utils.enums.Category;
import cms.prugift.amitech.vn.utils.extentreport.TestInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EditCartTest {
    private WebDriver driver;
    WebUI webUI;
    private LoginUserPage loginUserPage;
    private HomePage homePage;
    private DetailProductPage detailProductPage;
    private EditCartPage editCartPage;
    private ExcelHelper excelHelper = new ExcelHelper();
    ;
    private String url = "https://prugift.amitech.vn/";


    @BeforeClass
    public void setUp() throws Exception {
        CaptureHelpers.startRecord("Record EditCart");
        driver = new BaseSetup().setupDriver("edge");
        webUI = new WebUI(driver);
        excelHelper.setExcelFile("src/test/resources/testData/excel/DataTest.xlsx", "Login");
    }

    @Test(priority = 1)
    @TestInfo(author = Author.DEFAULT, categories = {Category.REGRESSION}, browser = Browser.EDGE)
    public void openPaymentPageFailWithNotChooseProduct() throws Exception {
        openCart();
        editCartPage.openPaymentPageFailWithNotChooseProduct();
    }

    @Test(priority = 2)
    @TestInfo(author = Author.DEFAULT, categories = {Category.REGRESSION}, browser = Browser.EDGE)
    public void enterQuantityEmpty() throws Exception {
        openCart();
        editCartPage.enterQuantityEmpty();
    }

    @Test(priority = 3)
    @TestInfo(author = Author.DEFAULT, categories = {Category.REGRESSION}, browser = Browser.EDGE)
    public void enterQuantityWithOrtherCharacters() throws Exception {
        openCart();
        editCartPage.enterQuantityWithOrtherCharacters();
    }

    @Test(priority = 4)
    @TestInfo(author = Author.DEFAULT, categories = {Category.REGRESSION}, browser = Browser.EDGE)
    public void verifyMinValue() throws Exception {
        openCart();
        editCartPage.verifyMinValue();
    }

    @Test(priority = 5)
    @TestInfo(author = Author.DEFAULT, categories = {Category.REGRESSION}, browser = Browser.EDGE)
    public void verifyLimitValue() throws Exception {
        openCart();
        editCartPage.verifyLimitValue();
    }

    @Test(priority = 6)
    @TestInfo(author = Author.DEFAULT, categories = {Category.REGRESSION}, browser = Browser.EDGE)
    public void checkEnterNegativeValue() throws Exception {
        openCart();
        editCartPage.checkEnterNegativeValue();
    }

    @Test(priority = 7)
    @TestInfo(author = Author.DEFAULT, categories = {Category.REGRESSION}, browser = Browser.EDGE)
    public void openPaymentPageSuccess() throws Exception {
        openCart();
        editCartPage.openPaymentPageSuccess();
    }

    public void openCart() throws Exception {
        driver.get(url);
        if (webUI.verifyTitle("Đăng nhập - PRUGift")) {
            loginUserPage = new LoginUserPage(driver);
            homePage = loginUserPage.loginSuccsess(excelHelper.getCellData("username", 7), excelHelper.getCellData("password", 7));
        } else {
            homePage = new HomePage(driver);
        }
        WebElement numberCart = driver.findElement(By.id("cartCount"));
        if (Integer.parseInt(numberCart.getAttribute("data-count")) > 0) {
            editCartPage = homePage.openCart();
        } else {
            detailProductPage = homePage.openDetailProduct("Túi xách thời trang");
            editCartPage = detailProductPage.openEditCart();
        }
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
        CaptureHelpers.stopRecord();
        driver.quit();
    }
}
