package cms.prugift.amitech.vn.admin.pages;

import cms.prugift.amitech.vn.helpers.ExcelHelper;
import cms.prugift.amitech.vn.common.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {
    private WebDriver driver;
    private WebUI webUI;
    ExcelHelper excelHelper;

    private By usernameInput = By.id("username");
    private By passwordInput = By.id("Input_Password");
    private By togglePassword = By.xpath("//span[@toggle='#Input_Password']");
    private By checkboxRemember = By.cssSelector("#Input_RememberMe");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By errorMessage = By.xpath("//span[@class='text-danger span1 field-validation-error']");
    private By errorUsername = By.id("username-error");
    private By errorPassword = By.xpath("//span[@id='Input_Password-error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        webUI = new WebUI(driver);
        excelHelper = new ExcelHelper();
    }

    public DashboardPage loginAdminSuccsess(String username, String password) {
        webUI.waitForPageLoaded();
        webUI.setText(usernameInput, username);
        webUI.setText(passwordInput, password);
        webUI.clickElement(loginButton);
        return new DashboardPage(driver);
    }

    public void loginFailWithUsernameNull(String password) {
        webUI.waitForPageLoaded();
        webUI.setText(passwordInput, password);
        webUI.clickElement(loginButton);
        webUI.getErrorMsg(errorUsername, "Vui lòng nhập tài khoản");
    }

    public void loginFailWithUsernameDoesNotExist(String username, String password) {
        webUI.waitForPageLoaded();
        webUI.setText(usernameInput, username);
        webUI.setText(passwordInput, password);
        webUI.clickElement(loginButton);
        webUI.getErrorMsg(errorMessage, "Tài khoản hoặc mật khẩu không đúng.");
    }

    public void testCSV(String username, String password, String elementMsg, String msg) {
        webUI.waitForPageLoaded();
        webUI.setText(usernameInput, username);
        webUI.setText(passwordInput, password);
        webUI.clickElement(loginButton);
        switch (elementMsg) {
            case "errorMessage":
                webUI.getErrorMsg(errorMessage, msg);
                break;
            case "errorPassword":
                webUI.getErrorMsg(errorPassword, msg);
                break;
            case "errorUsername":
                webUI.getErrorMsg(errorUsername, msg);
                break;
            default:
                System.out.println("không tìm thấy element");
        }
    }

    public void loginFailWithPasswordNull(String username) {
        webUI.waitForPageLoaded();
        webUI.setText(usernameInput, username);
        webUI.clickElement(loginButton);
        webUI.getErrorMsg(errorPassword, "Vui lòng nhập mật khẩu");
    }

    public void loginFailWithIncorrectPassword(String username, String password) {
        webUI.waitForPageLoaded();
        webUI.setText(usernameInput, username);
        webUI.setText(passwordInput, password);
        webUI.clickElement(loginButton);
        webUI.getErrorMsg(errorMessage, "Tài khoản hoặc mật khẩu không đúng.");
    }

    public void testHideAndUnHidePassword() throws Exception {
        excelHelper.setExcelFile("src/test/resources/testData/excel/DataTest.xlsx", "Login");
        webUI.waitForPageLoaded();
        webUI.setText(passwordInput, excelHelper.getCellData("password", 5));
        String title;
        webUI.clickElement(togglePassword);
        title = driver.findElement(passwordInput).getAttribute("type");
        Assert.assertEquals(title, "text");
        webUI.sleep(1);
        webUI.clickElement(togglePassword);
        title = driver.findElement(passwordInput).getAttribute("type");
        Assert.assertEquals(title, "password");
        webUI.sleep(1);
    }
}
