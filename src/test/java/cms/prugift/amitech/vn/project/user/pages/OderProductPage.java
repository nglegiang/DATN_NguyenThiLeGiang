package cms.prugift.amitech.vn.project.user.pages;

import cms.prugift.amitech.vn.helpers.ExcelHelper;
import cms.prugift.amitech.vn.common.WebUI;
import cms.prugift.amitech.vn.utils.LogUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    private By verifyName = By.xpath("(//span[contains(@class, 'text-danger')])[1]");
    private By inputPhone = By.xpath("//input[@name='phone']");
    private By verifyPhone = By.xpath("(//span[contains(@class, 'text-danger')])[2]");
    private By inputEmail = By.xpath("//input[@name='email']");
    private By verifyEmail = By.xpath("(//span[contains(@class, 'text-danger')])[3]");
    private By selectCity = By.xpath("(//div[@class=' css-8bddq7'])[1]");
    private By intputCity = By.id("react-select-2-input");
    private By verifyCity = By.xpath("(//span[contains(@class, 'text-danger')])[4]");
    private By selectDistrict = By.xpath("(//div[contains(@class,'css-8bddq7')])[2]");
    private By intputDistrict = By.id("react-select-3-input");
    private By verifyDistrict = By.xpath("(//span[contains(@class, 'text-danger')])[5]");
    private By selectWard = By.xpath("(//div[contains(@class,'css-8bddq7')])[3]");
    private By intputWard = By.id("react-select-4-input");
    private By verifyWard = By.xpath("(//span[contains(@class, 'text-danger')])[6]");
    private By inputDetailAddress = By.xpath("//input[@name='address']");
    private By verifyDetailAddress = By.xpath("(//span[contains(@class, 'text-danger')])[7]");
    private By btnAddNewAddress = By.cssSelector("button[class='btn btn-default']");
    private By rdDelivery = By.xpath("//input[@name='prCode']");
    private By inputPoint = By.xpath("//input[@type='number']");
    private By btnDecreasePoint = By.className("product-amount-group-decrease");
    private By btnIncreasePoint = By.className("product-amount-group-increase");
    private By inputPrCode = By.xpath("//input[@name='prCode']");
    private By verifyPrCode = By.xpath("(//span[contains(@class, 'text-danger')])[8]");
    private By inputPrFile = By.xpath("//input[@name='prFile']");
    private By verifyPrFile = By.xpath("(//span[contains(@class, 'text-danger')])[9]");
    private By btnNextPage = By.cssSelector(".btn.btn-next.btn-default");
    private By verifyNextPage = By.cssSelector(".product-breadcrumb.active");
    private By verifyOrder = By.cssSelector("div[class='modal-success__body'] h2");

    public void createOrder(String name, String phone, String email, String province, String district, String wards,
                            String detailAddress, String poNumber, String poFile, String element, String msg) {
        webUI.waitForPageLoaded();
        webUI.clickElement(changeAddress);
        webUI.clickElement(addNewAddress);
        webUI.setText(inputName, name);
        webUI.setText(inputPhone, phone);
        webUI.setText(inputEmail, email);
        webUI.selectCbb(selectCity);
        selectElement(driver);
        selectElement(driver);
        webUI.setText(inputDetailAddress, detailAddress);
        webUI.clickElement(btnAddNewAddress);
        jsExecutor.executeScript("window.scrollTo(0,2200)");
        webUI.clickElement(rdDelivery);
        webUI.setText(inputPrCode, poNumber);
        webUI.setText(inputPrFile, poFile);
        webUI.clickElement(btnAddNewAddress);
        webUI.clickElement(btnNextPage);
        if (element != "") {
            verifyMgs(element, msg);
        } else {
            webUI.verifyElemenText(verifyNextPage, "Xác nhận");
            jsExecutor.executeScript("window.scrollTo(0,1500)");
            webUI.clickElement(btnNextPage);
            webUI.verifyElemenText(verifyOrder, "Đặt hàng thành công");
        }
    }


    public void verifyNewAddress() throws Exception {
        excelHelper.setExcelFile("src/test/resources/testData/csv/CreateOrder.csv", "CreateOrder");
        webUI.waitForPageLoaded();
        webUI.clickElement(changeAddress);
        webUI.clickElement(addNewAddress);
        webUI.clickElement(btnAddNewAddress);
        webUI.verifyElemenText(verifyName, excelHelper.getCellData("name", 1));
        webUI.verifyElemenText(verifyName, excelHelper.getCellData("phone", 1));
        webUI.verifyElemenText(verifyName, excelHelper.getCellData("email", 1));
        webUI.verifyElemenText(verifyName, excelHelper.getCellData("province", 1));
        webUI.verifyElemenText(verifyName, excelHelper.getCellData("district", 1));
        webUI.verifyElemenText(verifyName, excelHelper.getCellData("ward", 1));
    }

    void verifyMgs(String element, String msg) {
        By[] elementMsg = {verifyName, verifyDetailAddress, verifyWard, verifyDistrict, verifyCity,
                verifyPhone, verifyEmail, verifyPrCode, verifyPrFile};
        for (By e : elementMsg) {
            if (e.toString().contains(element)) {
                webUI.verifyElemenText(e, msg);
            }
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
}
