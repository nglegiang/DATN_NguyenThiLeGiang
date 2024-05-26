package cms.prugift.amitech.vn.project.admin.pages;


import cms.prugift.amitech.vn.common.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ProductManagementPage {

    private WebDriver driver;
    private WebUI webUI;

    private By addProductButton = By.xpath("//a[contains(text(),'Thêm mới')]");

    public ProductManagementPage(WebDriver driver) {
        this.driver = driver;
        webUI = new WebUI(driver);
    }


    public CreateProductPage openAddProductPage() {
        webUI.waitForPageLoaded();
        webUI.verifyTitle("Quản lý sản phẩm");
        Assert.assertTrue(webUI.verifyElemenText(addProductButton, "Thêm mới"), "Không phải trang Quản lý sản phẩm");
        webUI.clickElement(addProductButton);
        return new CreateProductPage(driver);
    }
}
