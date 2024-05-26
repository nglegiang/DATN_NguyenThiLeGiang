package cms.prugift.amitech.vn.project.user.testcase;

import cms.prugift.amitech.vn.helpers.ExcelHelper;
import cms.prugift.amitech.vn.common.WebUI;
import cms.prugift.amitech.vn.commons.BaseSetup;
import cms.prugift.amitech.vn.project.user.pages.HomePage;
import cms.prugift.amitech.vn.project.user.pages.DetailProductPage;
import cms.prugift.amitech.vn.project.user.pages.EditCartPage;
import cms.prugift.amitech.vn.project.user.pages.LoginUserPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateOrderTest {
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
        driver = new BaseSetup().setupDriver("chrome");
        webUI = new WebUI(driver);
        excelHelper.setExcelFile("src/test/resources/testData/DataTest.xlsx", "CreateOrder");
    }

    @Test
    public void verifyNewAddress() throws Exception {

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
