package cms.prugift.amitech.vn.user.pages;

import cms.prugift.amitech.vn.helpers.ExcelHelper;
import cms.prugift.amitech.vn.common.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OderProductPage {
    private WebDriver driver;
    private WebUI webUI;
    private WebDriverWait wait;
    private ExcelHelper excelHelper = new ExcelHelper();

    public OderProductPage(WebDriver driver) throws Exception {
        this.driver = driver;
        webUI = new WebUI(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        excelHelper.setExcelFile("src/test/resources/testData/DataTest.xlsx", "CreateOrder");
    }

    private By changeAddress = By.cssSelector(".custom-address-change.cursor-pointer");
    private By addNewAddress = By.linkText("Thêm địa chỉ mới");
    private By inputName = By.tagName("name");
    private By verifyName = By.xpath("(//font[contains(text(),'This field is required')])[1]");
    private By inputPhone = By.tagName("phone");
    private By verifyPhone = By.xpath("(//font[contains(text(),'This field is required')])[2]");
    private By inputEmail = By.tagName("email");
    private By verifyEmail = By.xpath("(//font[contains(text(),'This field is required')])[3]");
    private By selectCity = By.id("react-select-2-placeholder");
    private By intputCity = By.id("react-select-2-input");
    private By verifyCity = By.xpath("(//font[contains(text(),'This field is required')])[4]");
    private By selectDistrict = By.id("react-select-3-placeholder");
    private By intputDistrict = By.id("react-select-3-input");
    private By verifyDistrict = By.xpath("(//font[contains(text(),'This field is required')])[5]");
    private By selectWard = By.id("react-select-4-placeholder");
    private By intputWard = By.id("react-select-4-input");
    private By verifyWard = By.xpath("(//font[contains(text(),'This field is required')])[6]");
    private By inputDetailAddress = By.tagName("address");
    private By verifyDetailAddress = By.xpath("(//font[contains(text(),'This field is required')])[7]");
    private By btnAddNewAddress = By.cssSelector("button[class='btn btn-default']");

    private By inputPoint = By.xpath("//input[@type='number']");
    private By btnDecreasePoint = By.className("product-amount-group-decrease");
    private By btnIncreasePoint = By.className("product-amount-group-increase");
    private By inputPrCode = By.tagName("prCode");
    private By inputPrFile = By.tagName("prFile");
    private By btnNextPage = By.cssSelector(".btn.btn-next.btn-default");


    public void verifyNewAddress() throws Exception {
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
}
