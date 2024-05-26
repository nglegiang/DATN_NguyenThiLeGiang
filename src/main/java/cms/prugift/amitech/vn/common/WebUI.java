package cms.prugift.amitech.vn.common;

import cms.prugift.amitech.vn.utils.LogUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Random;


public class WebUI {
    private WebDriver driver;
    private Actions actions;
    private WebDriverWait wait;
    private Alert alert;
    private int timeOutWaitForPageLoaded = 30;

    public WebUI(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void setText(By element, String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).sendKeys(value);
        LogUtils.info("Set text " + value + " on " + element.toString());
    }

    public String getTextElement(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getText();
    }

    public void clearText(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).clear();
        LogUtils.info("Clear text in textbox " + element.toString());
    }

    public void clickElement(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).click();
        LogUtils.info("Clicked on the element " + element.toString());
    }

    public boolean verifyTitle(String title) {
        return driver.getTitle().contains(title);
    }

    public boolean verifyElementExist(By element) {
        List<WebElement> elements = driver.findElements(element);
        int total = elements.size();
        if (total > 0) return true;
        return false;
    }


    public boolean verifyElemenText(By element, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getText().equals(text);
    }

    public void verifyAlertMessage(String text) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#toast-container")));
        WebElement toastrElement = driver.findElement(By.cssSelector(".toast-message"));
        String toastrMessage = toastrElement.getText();
        Assert.assertEquals(text, toastrMessage);
        LogUtils.info("Verify message of an alert: " + text);
    }

    public boolean verifyUrl(String url) {
        return driver.getCurrentUrl().contains(url);
    }

    public void selectElement(By element, By inputElement, String elementValue) {
        clickElement(element);
        setText(inputElement, elementValue);
        actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).build().perform();
        LogUtils.info("Select element: " + inputElement);
    }

    public void checkSearchTableByColumn(int column, String value) {
        //Xác định số dòng của table sau khi search
        List<WebElement> row = driver.findElements(By.xpath("//table//tbody/tr"));
        int rowTotal = row.size(); //Lấy ra số dòng
        LogUtils.info("Number of lines found: " + rowTotal);
        //Duyệt từng dòng
        for (int i = 1; i <= rowTotal; i++) {
            WebElement elementCheck = driver.findElement(By.xpath("//table//tbody/tr[" + i + "]/td[" + column + "]"));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", elementCheck);

            System.out.print(value + " - ");
            System.out.println(elementCheck.getText());
            Assert.assertTrue(elementCheck.getText().toUpperCase().contains(value.toUpperCase()), "Dòng số " + i + " không chứa giá trị tìm kiếm.");
        }

    }

    public void reload(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void sleep(double second) {
        try {
            Thread.sleep((long) (second * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getErrorMsg(By element, String msg) {
        String errorMsg;
        errorMsg = driver.findElement(element).getText();
        Assert.assertEquals(errorMsg, msg);
        LogUtils.info("Get error mesage: " + msg);

    }

    public static String taoChuoiNgauNhien(int doDai) {
        String kyTu = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder chuoiNgauNhien = new StringBuilder(doDai);
        Random random = new Random();

        for (int i = 0; i < doDai; i++) {
            int index = random.nextInt(kyTu.length());
            chuoiNgauNhien.append(kyTu.charAt(index));
        }

        return chuoiNgauNhien.toString();
    }

    public static String taoChuoiKiTuDacBiet(int doDai) {
        String dacBiet = "!@#$%^&*()-+={}[]|\\;:'\",.<>?/~`";
        StringBuilder chuoiKiTuDacBiet = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < doDai; i++) {
            int index = random.nextInt(dacBiet.length());
            chuoiKiTuDacBiet.append(dacBiet.charAt(index));
        }

        return chuoiKiTuDacBiet.toString();
    }

    public void waitForPageLoaded() {

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                } catch (Exception e) {
                    return true;
                }
            }
        };

        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutWaitForPageLoaded));
            wait.until(jsLoad);
            wait.until(jQueryLoad);
        } catch (Throwable error) {
            Assert.fail("Page load time exceeded, waiting for JavaScript.");
        }
    }
}
