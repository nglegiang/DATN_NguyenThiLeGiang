package cms.prugift.amitech.vn.project.admin.pages;


import cms.prugift.amitech.vn.common.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class CategoryProductPage {

    private WebDriver driver;
    private WebUI webUI;

    private By btnSearch = By.cssSelector("button[type='submit']");
    private By inputSearch = By.cssSelector("input[placeholder='Nhập tiêu đề']");

    public CategoryProductPage(WebDriver driver) {
        this.driver = driver;
        webUI = new WebUI(driver);
    }

    public void searchCategory(String categoryName) {
        webUI.waitForPageLoaded();
        webUI.setText(inputSearch, categoryName);
        webUI.clickElement(btnSearch);
        checkSearchTableByColumn(4, categoryName);
    }

    public void checkSearchTableByColumn(int column, String value) {
        //Xác định số dòng của table sau khi search
        List<WebElement> row = driver.findElements(By.xpath("//table//tbody/tr"));
        int rowTotal = row.size(); //Lấy ra số dòng
        System.out.println("Số dòng tìm thấy: " + rowTotal);
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
}
