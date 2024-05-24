package cms.prugift.amitech.vn.user.pages;

import cms.prugift.amitech.vn.common.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class DetailProductPage {
    private WebDriver driver;
    private WebUI webUI;
    private WebDriverWait wait;


    private By inventor = By.xpath("//label[contains(text(),'Kho Hà Nội')]");
    private By btnAddToCart = By.cssSelector("button[class='btn btn-buy btn-outline-default']");
    private By btnBuyNow = By.cssSelector("button[class='btn btn-default']");
    private By btnPreOrder = By.cssSelector("button[class='btn btn-pre-order']");
    private By btnIncrease = By.cssSelector(".product-amount-group-increase");
    private By btnDecrease = By.cssSelector(".product-amount-group-decrease");
    private By inputNumber = By.xpath("//input[@type='number']");

    public DetailProductPage(WebDriver driver) {
        this.driver = driver;
        webUI = new WebUI(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addToCart() {
//        webUI.clickElement(inventor);
        webUI.clickElement(btnAddToCart);
        verifyMessage("Thêm sản phẩm vào giỏ hàng thành công");
    }

    public EditCartPage openEditCart() {
        webUI.waitForPageLoaded();
        webUI.clickElement(inventor);
        webUI.clickElement(btnBuyNow);
        webUI.verifyTitle("Giỏ hàng - PRUGift");
        webUI.verifyUrl("/cart");
        return new EditCartPage(driver);
    }

    public PreOrderPage openPreOrderPage() {
        webUI.clickElement(inventor);
        webUI.clickElement(btnPreOrder);
        webUI.verifyTitle("Đặt hàng trước - PRUGift");
        webUI.verifyUrl("/PreOrders/ReOrder");
        return new PreOrderPage(driver);
    }


    public void verifyMessage(String text) {
        WebElement notificationElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modal-detail-p-content")));
        String expectedMessage = "Thêm sản phẩm vào giỏ hàng thành công";
        String actualMessage = notificationElement.getText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }
}
