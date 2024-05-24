package cms.prugift.amitech.vn.user.pages;

import cms.prugift.amitech.vn.common.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginUserPage {
    private WebDriver driver;
    private WebUI webUI;

    private By buttonLoginPage = By.xpath("//a[@class='btn btn-default w-100 btn-org']");
    private By usernameInput = By.id("username");
    private By passwordInput = By.id("password");
    private By loginButton = By.cssSelector("button[type='submit']");

    public LoginUserPage(WebDriver driver) {
        this.driver = driver;
        webUI = new WebUI(driver);
    }

    public HomePage loginSuccsess(String username, String password) {
        webUI.waitForPageLoaded();
        webUI.clickElement(buttonLoginPage);
        webUI.verifyUrl("/Login/Org");
        webUI.verifyTitle("Đăng nhập dành cho phòng ban - PRUGift");
        webUI.waitForPageLoaded();
        webUI.setText(usernameInput, username);
        webUI.setText(passwordInput, password);
        webUI.clickElement(loginButton);
        webUI.verifyTitle("Trang chủ - PRUGift");
        return new HomePage(driver);
    }

}
