package cms.prugift.amitech.vn.project.user.pages;

import cms.prugift.amitech.vn.common.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebUI webUI;

    private By inputSearch = By.cssSelector("input[placeholder='Tìm kiếm sản phẩm']");
    private By iconSearch = By.cssSelector(".fa.fa-search.btn-color-default");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        webUI = new WebUI(driver);
    }

    public DetailProductPage openDetailProduct(String keyword) {
        webUI.setText(inputSearch, keyword);
        webUI.clickElement(iconSearch);
        webUI.waitForPageLoaded();
        List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class, 'product-item-info')]"));
        // Duyệt qua các sản phẩm để kiểm tra tiêu đề hoặc mô tả chứa từ khóa
        for (WebElement product : products) {
            WebElement productName = product.findElement(By.xpath("//h3[contains(@class, 'product-item-title')]"));
            if (productName.getText().toLowerCase().contains(keyword.toLowerCase())) {
                WebElement buyButton = product.findElement(By.xpath("//a[contains(@class, 'btn-buy')]"));
                buyButton.click();
                webUI.verifyUrl("/san-pham/");
                return new DetailProductPage(driver);
            }
        }
        throw new NoSuchElementException("No product found with the keyword: " + keyword);
    }

}
