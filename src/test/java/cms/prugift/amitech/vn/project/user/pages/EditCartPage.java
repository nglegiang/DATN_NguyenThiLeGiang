package cms.prugift.amitech.vn.project.user.pages;

import cms.prugift.amitech.vn.helpers.ExcelHelper;
import cms.prugift.amitech.vn.common.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditCartPage {
    private WebDriver driver;
    private WebUI webUI;
    private WebDriverWait wait;
    private ExcelHelper excelHelper = new ExcelHelper();

    private By checkAll = By.cssSelector("label[for='shopping-checkbox-check-all']");
    private By btnDecrease = By.cssSelector("td[class='text-center d-none d-xl-table-cell'] span[class='product-amount-group-decrease']");
    private By btnIncrease = By.cssSelector("td[class='text-center d-none d-xl-table-cell'] span[class='product-amount-group-increase']");
    private By inputQuantity = By.xpath("(//input[@type='number'])[3]");
    private By quantityMax = By.xpath("(//span[@class='product-amount-group-quantity-in-stock'])[3]");
    private By btnPayment = By.cssSelector(".btn.btn-next.btn-default");
    private By title = By.cssSelector(".shopping-card__title");
    private By card = By.cssSelector("div[id='cartCount'] span[class='header-text-color-cart']");

    public EditCartPage(WebDriver driver) {
        this.driver = driver;
        webUI = new WebUI(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void openPaymentPageSuccess() {
        webUI.clickElement(card);
        webUI.clickElement(checkAll);
        webUI.clickElement(btnPayment);
        webUI.sleep(3);
        webUI.verifyTitle("Thanh toán - PRUGift");
    }

    public void openPaymentPageFailWithNotChooseProduct() throws Exception {
        excelHelper.setExcelFile("src/test/resources/testData/excel/DataTest.xlsx", "EditCart");
        webUI.clickElement(btnPayment);
        webUI.verifyAlertMessage(excelHelper.getCellData("msg", 3));
    }

    public void enterQuantityEmpty() throws Exception {
        excelHelper.setExcelFile("src/test/resources/testData/excel/DataTest.xlsx", "EditCart");
        webUI.clickElement(checkAll);
        webUI.clearText(inputQuantity);
        webUI.setText(inputQuantity, " ");
        webUI.clickElement(btnPayment);
//        Assert.assert;
    }

    public void enterQuantityWithOrtherCharacters() throws Exception {
        webUI.clickElement(checkAll);
        webUI.clearText(inputQuantity);
        webUI.setText(inputQuantity, "a");
//        webUI.verifyElemenText(inputQuantity, "1");
        webUI.clickElement(btnPayment);
    }

    public void verifyMinValue() throws Exception {
        excelHelper.setExcelFile("src/test/resources/testData/excel/DataTest.xlsx", "EditCart");
        webUI.clickElement(checkAll);
        webUI.clearText(inputQuantity);
        webUI.setText(inputQuantity, "0");
        webUI.clickElement(btnPayment);
        webUI.verifyAlertMessage(excelHelper.getCellData("msg", 1));
    }

    public void checkEnterNegativeValue() throws Exception {
        excelHelper.setExcelFile("src/test/resources/testData/excel/DataTest.xlsx", "EditCart");
        webUI.clickElement(checkAll);
        webUI.clearText(inputQuantity);
        webUI.setText(inputQuantity, "-1");
        webUI.clickElement(btnPayment);
        webUI.verifyAlertMessage(excelHelper.getCellData("msg", 1));
    }

    public void verifyLimitValue() throws Exception {
        excelHelper.setExcelFile("src/test/resources/testData/excel/DataTest.xlsx", "EditCart");
        webUI.clickElement(checkAll);
        // Xác định số lượng tồn kho
        WebElement quantityElement = driver.findElement(quantityMax);
        String quantityText = quantityElement.getText();
        String[] string = quantityText.split(" ");
        System.out.println(string[1]);
        int stockQuantity = Integer.parseInt(string[1]);
        // Nhập số lượng lớn hơn số lượng tồn kho
        webUI.clearText(inputQuantity);
        webUI.setText(inputQuantity, (String.valueOf(stockQuantity)));
        webUI.clickElement(btnIncrease);
        System.out.println("entered text");
        webUI.sleep(1);
        webUI.verifyAlertMessage(excelHelper.getCellData("msg", 2) + stockQuantity);
    }

    private int extractQuantity(String text) {
        // Giả sử văn bản có dạng "(Còn 450 sản phẩm)"
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }
        throw new IllegalArgumentException("Không tìm thấy số lượng tồn kho trong văn bản: " + text);
    }
}
