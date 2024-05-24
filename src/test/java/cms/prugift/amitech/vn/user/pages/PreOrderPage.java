package cms.prugift.amitech.vn.user.pages;

import cms.prugift.amitech.vn.common.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PreOrderPage {
    private WebDriver driver;
    private WebUI webUI;
    private WebDriverWait wait;


    private By inventor = By.xpath("//label[contains(text(),'Kho Hà Nội')]");
    private By btnAddToCart = By.xpath("button[class='btn btn-buy btn-outline-default']");
    private By btnBuyNow = By.xpath("button[class='btn btn-default']");
    private By btnPreOrder = By.xpath("button[class='btn btn-pre-order']");
    private By btnIncrease = By.cssSelector(".product-amount-group-increase");
    private By btnDecrease = By.cssSelector(".product-amount-group-decrease");
    private By inputNumber = By.xpath("//input[@type='number']");

    public PreOrderPage(WebDriver driver) {
        this.driver = driver;
        webUI = new WebUI(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
