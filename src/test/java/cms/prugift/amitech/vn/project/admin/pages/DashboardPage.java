package cms.prugift.amitech.vn.project.admin.pages;

import cms.prugift.amitech.vn.common.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    private WebDriver driver;
    private WebUI webUI;

    private By productMenu = By.linkText("Quản lý sản phẩm");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        webUI = new WebUI(driver);
    }

    public ProductManagementPage openProductManagementPage() {
        webUI.verifyTitle("Trang chủ");
        webUI.clickElement(productMenu);
        return new ProductManagementPage(driver);
    }

}
