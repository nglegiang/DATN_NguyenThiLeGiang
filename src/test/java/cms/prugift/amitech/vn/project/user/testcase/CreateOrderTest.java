package cms.prugift.amitech.vn.project.user.testcase;

import cms.prugift.amitech.vn.helpers.CaptureHelpers;
import cms.prugift.amitech.vn.helpers.ExcelHelper;
import cms.prugift.amitech.vn.common.WebUI;
import cms.prugift.amitech.vn.commons.BaseSetup;
import cms.prugift.amitech.vn.project.user.pages.*;
import cms.prugift.amitech.vn.utils.dataprovider.CreateOrderProvider;
import cms.prugift.amitech.vn.utils.enums.Author;
import cms.prugift.amitech.vn.utils.enums.Browser;
import cms.prugift.amitech.vn.utils.enums.Category;
import cms.prugift.amitech.vn.utils.extentreport.ExtentReportListener;
import cms.prugift.amitech.vn.utils.extentreport.TestInfo;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

@Listeners(ExtentReportListener.class)
public class CreateOrderTest {
    private WebDriver driver;
    WebUI webUI;
    private LoginUserPage loginUserPage;
    private HomePage homePage;
    private DetailProductPage detailProductPage;
    private EditCartPage editCartPage;
    private OderProductPage orderProductPage;
    private ExcelHelper excelHelper = new ExcelHelper();
    ;
    private String url = "https://prugift.amitech.vn/";


    @BeforeClass
    public void setUp() {
        CaptureHelpers.startRecord("Record CreateOrderProduct");
        driver = new BaseSetup().setupDriver("edge");
        webUI = new WebUI(driver);
    }

    @Test(dataProviderClass = CreateOrderProvider.class, dataProvider = "create-order-csv")
    @TestInfo(author = Author.DEFAULT, categories = {Category.REGRESSION}, browser = Browser.EDGE)
    public void createOrderTest(String[] data) throws Exception {
        openCart();
        String name = data[1];
        String phone = data[2];
        String email = data[3];
        String province = data[4];
        String district = data[5];
        String wards = data[6];
        String detailAddress = data[7];
        String poNumber = data[8];
        String poFile = data[9];
        String element = data[10];
        String msg = data[11];
        orderProductPage.createOrder(name, phone, email, province, district, wards, detailAddress, poNumber, poFile, element, msg);
    }

    public void openCart() throws Exception {
        excelHelper.setExcelFile("src/test/resources/testData/excel/DataTest.xlsx", "Login");
        driver.get(url);
        if (webUI.verifyTitle("Đăng nhập - PRUGift")) {
            loginUserPage = new LoginUserPage(driver);
            homePage = loginUserPage.loginSuccsess(excelHelper.getCellData("username", 7), excelHelper.getCellData("password", 7));
        } else {
            homePage = new HomePage(driver);
        }
        orderProductPage = homePage.openPaymentPage();
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
