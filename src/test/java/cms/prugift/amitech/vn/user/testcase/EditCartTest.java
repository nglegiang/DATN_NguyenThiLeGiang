package cms.prugift.amitech.vn.user.testcase;

import cms.prugift.amitech.vn.common.WebUI;
import cms.prugift.amitech.vn.commons.BaseSetup;
import cms.prugift.amitech.vn.user.pages.DetailProductPage;
import cms.prugift.amitech.vn.user.pages.EditCartPage;
import cms.prugift.amitech.vn.user.pages.HomePage;
import cms.prugift.amitech.vn.user.pages.LoginUserPage;
import cms.prugift.amitech.vn.helpers.ExcelHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
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
        driver = new BaseSetup().setupDriver("edge");
        webUI = new WebUI(driver);
        excelHelper.setExcelFile("src/test/resources/testData/DataTest.xlsx", "Login");
    }

    @Test(priority = 1)
    public void openPaymentPageFailWithNotChooseProduct() throws Exception {
        openCart();
        editCartPage.openPaymentPageFailWithNotChooseProduct();
    }

    @Test(priority = 2)
    public void enterQuantityEmpty() throws Exception {
        openCart();
        editCartPage.enterQuantityEmpty();
    }

    @Test(priority = 3)
    public void enterQuantityWithOrtherCharacters() throws Exception {
        openCart();
        editCartPage.enterQuantityWithOrtherCharacters();
    }

    @Test(priority = 4)
    public void verifyMinValue() throws Exception {
        openCart();
        editCartPage.verifyMinValue();
    }

    @Test(priority = 5)
    public void verifyLimitValue() throws Exception {
        openCart();
        editCartPage.verifyLimitValue();
    }

    @Test(priority = 6)
    public void checkEnterNegativeValue() throws Exception {
        openCart();
        editCartPage.checkEnterNegativeValue();
    }

    @Test(priority = 7)
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
        detailProductPage = homePage.openDetailProduct("Túi xách thời trang");
        editCartPage = detailProductPage.openEditCart();
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
