package cms.prugift.amitech.vn.project.user.pages;

import cms.prugift.amitech.vn.helpers.ExcelHelper;
import cms.prugift.amitech.vn.common.WebUI;
import cms.prugift.amitech.vn.utils.LogUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class OderProductPage {
    private WebDriver driver;
    private WebUI webUI;
    private WebDriverWait wait;
    private ExcelHelper excelHelper = new ExcelHelper();
    private JavascriptExecutor jsExecutor;


    public OderProductPage(WebDriver driver) {
        this.driver = driver;
        webUI = new WebUI(driver);
        jsExecutor = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By changeAddress = By.cssSelector(".custom-address-change.cursor-pointer");
    private By addNewAddress = By.linkText("Thêm địa chỉ mới");
    private By inputName = By.xpath("//input[@name='name']");
    private By verifyName = By.xpath("/html[1]/body[1]/main[1]/section[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/span[1]");
    private By inputPhone = By.xpath("//input[@name='phone']");
    private By verifyPhone = By.xpath("/html[1]/body[1]/main[1]/section[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/span[1]");
    private By inputEmail = By.xpath("//input[@name='email']");
    private By verifyEmail = By.xpath("/html[1]/body[1]/main[1]/section[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/span[1]");
    private By selectCity = By.xpath("(//div[@class=' css-8bddq7'])[1]");
    private By intputCity = By.id("react-select-2-input");
    private By verifyCity = By.xpath("/html[1]/body[1]/main[1]/section[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/span[1]");
    private By selectDistrict = By.xpath("(//div[contains(@class,'css-8bddq7')])[2]");
    private By intputDistrict = By.id("react-select-3-input");
    private By verifyDistrict = By.xpath("/html[1]/body[1]/main[1]/section[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[1]/div[2]/span[1]");
    private By selectWard = By.xpath("(//div[contains(@class,'css-8bddq7')])[3]");
    private By intputWard = By.id("react-select-4-input");
    private By verifyWard = By.xpath("/html[1]/body[1]/main[1]/section[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[1]/div[3]/span[1]");
    private By inputDetailAddress = By.xpath("//input[@name='address']");
    private By verifyDetailAddress = By.xpath("/html[1]/body[1]/main[1]/section[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[1]/div[4]/span[1]");
    private By btnAddNewAddress = By.cssSelector("button[class='btn btn-default']");
    private By rdDelivery = By.xpath("//input[@name='prCode']");
    private By inputPoint = By.xpath("//input[@type='number']");
    private By btnDecreasePoint = By.className("product-amount-group-decrease");
    private By btnIncreasePoint = By.className("product-amount-group-increase");
    private By inputPrCode = By.xpath("//input[@name='prCode']");
    private By verifyPrCode = By.xpath("/html[1]/body[1]/main[1]/section[3]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/span[1]");
    private By inputPrFile = By.xpath("//input[@name='prFile']");
    private By verifyPrFile = By.xpath("/html[1]/body[1]/main[1]/section[3]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[2]/span[1]");
    private By btnNextPage = By.cssSelector(".btn.btn-next.btn-default");
    private By verifyNextPage = By.cssSelector(".product-breadcrumb.active");
    private By verifyOrder = By.cssSelector("div[class='modal-success__body'] h2");
    private By verifyAddAddress = By.cssSelector("div[class='card card-customer-address'] h5");

    public void createOrder(String name, String phone, String email, String province, String district, String wards,
                            String detailAddress, String poNumber, String poFile, String element, String msg) {
        webUI.waitForPageLoaded();
        webUI.clickElement(changeAddress);
        webUI.clickElement(addNewAddress);
        webUI.setText(inputName, name);
        webUI.setText(inputPhone, phone);
        webUI.setText(inputEmail, email);
        if (province.length() > 0) {
            webUI.selectElement(selectCity, intputCity, getFirstWord(province));
            webUI.selectElement(selectDistrict, intputDistrict, getFirstWord(district));
            webUI.sleep(0.2);
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ENTER).build().perform();
            webUI.selectElement(selectWard, intputWard, getFirstWord(wards));
        }
        webUI.setText(inputDetailAddress, detailAddress);
        webUI.clickElement(btnAddNewAddress);
        if (poNumber.length() > 0 || poFile.length() > 0) {
            webUI.setText(inputPrCode, poNumber);
            webUI.setText(inputPrFile, poFile);
            webUI.clickElement(btnNextPage);
        }
        getErrorMsg(element, msg);
//        webUI.clickElement(btnNextPage);
//        webUI.verifyElemenText(verifyNextPage, "Xác nhận");
    }

    void getErrorMsg(String element, String msg) {
        switch (element) {
            case "verifyName":
                webUI.getErrorMsg(verifyName, msg);
                break;
            case "verifyDetailAddress":
                webUI.getErrorMsg(verifyDetailAddress, msg);
                break;
            case "verifyWard":
                webUI.getErrorMsg(verifyWard, msg);
                break;
            case "verifyDistrict":
                webUI.getErrorMsg(verifyDistrict, msg);
                break;
            case "verifyCity":
                webUI.getErrorMsg(verifyCity, msg);
                break;
            case "verifyPhone":
                webUI.getErrorMsg(verifyPhone, msg);
                break;
            case "verifyEmail":
                webUI.getErrorMsg(verifyEmail, msg);
                break;
            case "verifyPrCode":
                webUI.getErrorMsg(verifyPrCode, msg);
                break;
            case "verifyPrFile":
                webUI.getErrorMsg(verifyPrFile, msg);
                break;
            default:
                LogUtils.error("Element not found");
        }

    }

    public void selectElement(WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform();
        webUI.sleep(0.2);
        actions.sendKeys("a").perform();
        webUI.sleep(0.2);
        actions.sendKeys(Keys.ENTER).perform();
    }

    public void verifyAlertMessage(String text) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".modal-success-contain.show")));
        WebElement toastrElement = driver.findElement(By.xpath("//h2[contains(text(),'Đặt hàng thành công')]"));
        String toastrMessage = toastrElement.getText();
        Assert.assertEquals(text, toastrMessage);
        LogUtils.info("Verify message of an alert: " + text);
    }

    public static String getFirstWord(String input) {
        if (input != null && !input.isEmpty()) {
            return input.split(" ")[0];
        } else {
            return "";
        }
    }
}
