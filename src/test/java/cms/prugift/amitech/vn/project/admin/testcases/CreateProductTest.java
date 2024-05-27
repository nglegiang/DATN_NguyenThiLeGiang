package cms.prugift.amitech.vn.project.admin.testcases;

import cms.prugift.amitech.vn.helpers.ExcelHelper;
import cms.prugift.amitech.vn.common.WebUI;
import cms.prugift.amitech.vn.commons.BaseSetup;
import cms.prugift.amitech.vn.project.admin.pages.CreateProductPage;
import cms.prugift.amitech.vn.project.admin.pages.DashboardPage;
import cms.prugift.amitech.vn.project.admin.pages.LoginPage;
import cms.prugift.amitech.vn.project.admin.pages.ProductManagementPage;
import cms.prugift.amitech.vn.utils.enums.Author;
import cms.prugift.amitech.vn.utils.enums.Browser;
import cms.prugift.amitech.vn.utils.enums.Category;
import cms.prugift.amitech.vn.utils.extentreport.ExtentReportListener;
import cms.prugift.amitech.vn.utils.extentreport.TestInfo;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExtentReportListener.class)
public class CreateProductTest {
    private WebDriver driver;
    WebUI webUI;
    private LoginPage loginPage;
    private ExcelHelper excelHelper = new ExcelHelper();
    private ProductManagementPage productManagementPage;
    private DashboardPage dashboardPage;
    private CreateProductPage createProductPage;
    private String url = "https://cms.prugift.amitech.vn/";


    @BeforeClass
    public void setUp() throws Exception {
        driver = new BaseSetup().setupDriver("chrome");
        webUI = new WebUI(driver);
        excelHelper.setExcelFile("src/test/resources/testData/excel/DataTest.xlsx", "Login");
    }

    @Test(priority = 1)
    @TestInfo(author = Author.LEGIANG, categories = {Category.REGRESSION}, browser = Browser.CHROME)
    public void verifyRequiredFields() throws Exception {
        openAddProductPage();
        createProductPage.verifyRequiredFields();
    }

    @Test(priority = 2)
    @TestInfo(author = Author.LEGIANG, categories = {Category.REGRESSION}, browser = Browser.CHROME)
    public void verifyUsesName() throws Exception {
        openAddProductPage();
        createProductPage.verifyUsesName();
    }

    @Test(priority = 3)
    @TestInfo(author = Author.LEGIANG, categories = {Category.REGRESSION}, browser = Browser.CHROME)
    public void addUses() throws Exception {
        openAddProductPage();
        createProductPage.addUses();
    }

    @Test(priority = 4)
    @TestInfo(author = Author.LEGIANG, categories = {Category.REGRESSION}, browser = Browser.CHROME)
    public void addUsesAlreadyExist() throws Exception {
        openAddProductPage();
        createProductPage.addUsesAlreadyExist();
    }

    @Test(priority = 5)
    @TestInfo(author = Author.LEGIANG, categories = {Category.REGRESSION}, browser = Browser.CHROME)
    public void deleteUses() throws Exception {
        openAddProductPage();
        createProductPage.deleteUses();
    }

    @Test(priority = 6)
    @TestInfo(author = Author.LEGIANG, categories = {Category.REGRESSION}, browser = Browser.CHROME)
    public void verifyCatogeryName() throws Exception {
        openAddProductPage();
        createProductPage.verifyCategoryName();
    }

    @Test(priority = 7)
    @TestInfo(author = Author.LEGIANG, categories = {Category.REGRESSION}, browser = Browser.CHROME)
    public void addCategory() throws Exception {
        openAddProductPage();
        createProductPage.addCategory();
    }

    @Test(priority = 8)
    @TestInfo(author = Author.LEGIANG, categories = {Category.REGRESSION}, browser = Browser.CHROME)
    public void addCategoryAlreadyExist() throws Exception {
        openAddProductPage();
        createProductPage.addCategoryAlreadyExist();
    }

    @Test(priority = 9)
    @TestInfo(author = Author.LEGIANG, categories = {Category.REGRESSION}, browser = Browser.CHROME)
    public void createProduct() throws Exception {
        openAddProductPage();
        createProductPage.createProduct();
    }

    @Test(priority = 10)
    @TestInfo(author = Author.LEGIANG, categories = {Category.REGRESSION}, browser = Browser.CHROME)
    public void createProductFailWithIdAlreadyExist() throws Exception {
        openAddProductPage();
        createProductPage.createProductFailWithIdAlreadyExist();
    }

    public void openAddProductPage() throws Exception {
        driver.get(url);
        if (webUI.verifyTitle("Đăng nhập | Quản trị PruGift")) {
            loginPage = new LoginPage(driver);
            dashboardPage = loginPage.loginAdminSuccsess(excelHelper.getCellData("username", 6), excelHelper.getCellData("password", 6));
        } else {
            dashboardPage = new DashboardPage(driver);
        }
        productManagementPage = dashboardPage.openProductManagementPage();
        createProductPage = productManagementPage.openAddProductPage();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
