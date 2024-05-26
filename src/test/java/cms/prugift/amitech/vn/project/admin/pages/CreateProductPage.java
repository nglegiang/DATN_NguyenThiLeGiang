package cms.prugift.amitech.vn.project.admin.pages;


import cms.prugift.amitech.vn.common.WebUI;
import cms.prugift.amitech.vn.helpers.ExcelHelper;
import cms.prugift.amitech.vn.project.user.pages.LoginUserPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class CreateProductPage {
    private WebDriver driver;
    private WebUI webUI;
    private JavascriptExecutor jsExecutor;
    private LoginUserPage loginUserPage;
    private ExcelHelper excelHelper;
    private CategoryProductPage categoryProductPage;

    public CreateProductPage(WebDriver driver) {
        this.driver = driver;
        webUI = new WebUI(driver);
        jsExecutor = (JavascriptExecutor) driver;
        excelHelper = new ExcelHelper();
    }

    private By categories = By.linkText("Quản lý danh mục");
    private By categoryProduct = By.linkText("Danh mục sản phẩm");

    //Add product
    private By titleCreatePrd = By.xpath("//span[@class='card-title namePageText2 pl-3 pt-3']");

    //information
    private By tabInfo = By.linkText("Thông tin cơ bản");
    private By inputIdProduct = By.name("sku");
    private By idProductVerify = By.xpath("//span[contains(text(),'Vui lòng nhập mã hàng')]");
    private By inputNameProduct = By.name("name");
    private By nameProductVerify = By.xpath("//span[contains(text(),'Vui lòng nhập tên sản phẩm')]");
    private By selectUses = By.xpath("//div[@class='rounded-0 col-md-10 col-sm-8 pr-0 css-b62m3t-container']//div[contains(@class,'css-19bb58m')]");
    private By inputSearchUses = By.id("react-select-2-input");
    private By UsesVerify = By.xpath("//p[@class='text-danger col-12']");
    private By buttonAddUses = By.xpath("//div[@id='tab-1']//button[2]");
    private By inputNameUses = By.xpath("//div[@class='modal-body']//input[@name='name']");
    private By verifyNameUses = By.cssSelector("div[class='modal-body'] span[class='text-danger']");
    private By btnSaveName = By.xpath("//button[@class='btn btn-primary']");
    private By deleteUses = By.xpath("//button[i[@class='fal fa-solid fa-trash']]");
    private By cbUses = By.xpath("//div[@role='dialog']//div[4]//div[1]//input[1]");
    private By selectCategory = By.xpath("//div[@class='basic-multi-select rounded-0 col-md-11 col-sm-9 pr-0 css-b62m3t-container']//div[contains(@class,'css-13cymwt-control')]");
    private By inputCategory = By.id("react-select-3-input");
    private By categoryVerify = By.xpath("//p[contains(text(),'Vui lòng nhập danh mục sản phẩm')]");
    private By btnAddCategory = By.xpath("//button[contains(@class,'col-12')]");
    private By verifyNameCategory = By.xpath("//span[contains(text(),'Vui lòng nhập tên danh mục')]");
    private By inputNameCategory = By.cssSelector("div[class='modal-body'] input[name='name']");
    private By uploadSecondaryImg = By.xpath("//button[contains(text(),'Upload ảnh phụ')]");
    private By inputInventoryHN = By.xpath("(//input[contains(@name,'quantity')])[1]");
    private By inputWarehouseCodeHN = By.xpath("(//input[contains(@name,'code')])[1]");
    private By inputInventoryHCM = By.xpath("(//input[contains(@name,'quantity')])[2]");
    private By inputWarehouseCodeHCM = By.xpath("(//input[contains(@name,'code')])[2]");
    private By inputPrice = By.name("price");

    private By buttonAddClassification = By.xpath("//div/button[contains(normalize-space(),'Thêm phân loại hàng')]");
    private By btnDeleteBlockClassification1 = By.xpath("(//button[contains(@type,'button')])[5]");
    private By btnDdeleteBlockClassification2 = By.xpath("(//button[contains(@type,'button')])[6]");
    private By inputGroupName1 = By.xpath("(//input[contains(@placeholder,'Nhập tên phân loại, Ví dụ size ...')])");
    private By inputGroupType1 = By.xpath("(//input[contains(@placeholder,'Nhập phân loại, Ví dụ S,M')])");
    private By inputPriceGroupType1 = By.xpath("//input[@placeholder='giá tăng thêm']");
    private By inputGroupType2 = By.xpath("(//input[contains(@placeholder,'Nhập phân loại, Ví dụ S,M')])[2]");
    private By inputPriceGroupType2 = By.xpath("(//input[@placeholder='giá tăng thêm'])[2]");
    private By buttonAddClassificiation1 = By.xpath("(//button[contains(@type,'button')][contains(text(),'Thêm phân loại hàng')])[2]    ");
    private By deleteGood = By.xpath("(//i)[61]");

    private By inputGroupName2 = By.xpath("(//input[contains(@placeholder,'Nhập tên phân loại, Ví dụ size ...')])[2]");
    private By inputGroupType21 = By.xpath("(//input[contains(@placeholder,'Nhập phân loại, Ví dụ S,M')])[3]");
    private By inputPriceGroupType21 = By.xpath("(//input[contains(@placeholder,'giá tăng thêm')])[3]");
    private By inputGroupType22 = By.xpath("(//input[contains(@placeholder,'Nhập phân loại, Ví dụ S,M')])[4]");
    private By inputPriceGroupType22 = By.xpath("(//input[@placeholder='giá tăng thêm'])[4]");
    private By buttonAddProperties = By.xpath("//button[contains(text(),'Thêm thuộc tính')]");
    private By verifyProperties1 = By.xpath("//div/label[contains(normalize-space(),'Nhóm phân loại 1')]");
    private By verifyProperties2 = By.xpath("//div/label[contains(normalize-space(),'Nhóm phân loại 2')]");

    private By buttonAddPriceRange = By.xpath("//button[contains(text(),'Thêm khoảng giá')]");
    private By inputFrom = By.xpath("//input[contains(@placeholder,'Từ (sản phẩm) *')]");
    private By inputTo = By.xpath("//input[contains(@placeholder,'Đến (sản phẩm)')]");
    private By inputPriceRange = By.xpath("//input[@placeholder='Đơn Giá *']");
    private By verifyPriceRange = By.xpath("//span[contains(text(),'Vui lòng nhập giá')]");
    private By inputPercentRange = By.xpath("//input[@placeholder='Phần trăm giảm giá *']");
    private By deletePriceRange = By.xpath("//tbody/tr[1]/td[1]");

    private By uploadMainImg = By.cssSelector(".imgA");
    private By mainImgVerify = By.xpath("//p[contains(text(),'Vui lòng chọn ảnh')]");
    private By verifyUploadImg = By.xpath("//div[contains(text(),'Cắt ảnh')]");
    private By buttonSaveImg = By.xpath("//button[@type='button'][contains(text(),'Lưu')]");
    private By inputWeight = By.xpath("//input[@placeholder='Nhập cân nặng']");
    private By verifyWeigth = By.xpath("//span[contains(text(),'Cân nặng phải lớn hơn 1')]");
    private By inputCost = By.xpath("//input[@placeholder='Nhập giá bán']");
    private By selectSex = By.xpath("//div[@class=' css-b62m3t-container']//div[@class=' css-19bb58m']");
    private By inputSex = By.id("react-select-4-input");
    private By inputAge = By.xpath("//input[@placeholder='Nhập độ tuổi']");
    private By inputUnit = By.name("unit");
    private By verifyUnit = By.xpath("//span[contains(text(),'Vui lòng nhập đơn vị')]");
    private By checkboxPruReward = By.xpath("(//input[@type='checkbox'])[1]");
    private By checkboxOutstandingProduct = By.xpath("(//input[@type='checkbox'])[2]");
    private By checkboxNewProduct = By.xpath("(//input[@type='checkbox'])[3]");
    private By checkboxSellingProducts = By.xpath("(//input[@type='checkbox'])[4]");
    private By checkboxPromotionalProducts = By.xpath("(//input[@type='checkbox'])[5]");
    private By checkboxActivated = By.xpath("(//input[@type='checkbox'])[6]");
    private By checkboxSendForApproval = By.xpath("(//input[@type='checkbox'])[7]");

    private By tabDetail = By.linkText("Mô tả chi tiết");
    private By verifySumary = By.xpath("//label[contains(text(),'Mô tả ngắn')]");
    private By iframeShortDescription = By.xpath("(//div[@class='tox-edit-area']/iframe)[1]");
    private By iframeDescription = By.xpath("(//div[@class='tox-edit-area']/iframe)[2]");

    private By tabTechnical = By.linkText("Thông số kĩ thuật");
    private By verifyTechnical = By.xpath("//label[contains(text(),'Thông số kĩ thuật')]");
    private By idFrameTechnical = By.xpath("(//div[@class='tox-edit-area']/iframe)[3]");
    private By buttonSave = By.xpath("//button[contains(text(),'Lưu')]");
    private By btnAccept = By.xpath("//button[contains(text(),'Đồng ý')]");


    public void verifyRequiredFields() throws Exception {
        excelHelper.setExcelFile("src/test/resources/testData/DataTest.xlsx", "AddProduct");
        webUI.waitForPageLoaded();
        webUI.clickElement(buttonSave);
        verifyMsgRequiredFields();
        verifyIDhHouseware();
        excelHelper.setCellData("Pass", 1, 30);
    }

    public void verifyCategoryName() throws Exception {
        excelHelper.setExcelFile("src/test/resources/testData/DataTest.xlsx", "AddUsesAndCategory");
        webUI.clickElement(btnAddCategory);
        webUI.clickElement(btnSaveName);
        webUI.verifyElemenText(verifyNameUses, excelHelper.getCellData("category", 1));
    }

    public void addCategory() throws Exception {
        excelHelper.setExcelFile("src/test/resources/testData/DataTest.xlsx", "AddUsesAndCategory");
        webUI.waitForPageLoaded();
        webUI.clickElement(btnAddCategory);
        String name = excelHelper.getCellData("category", 2);
        webUI.setText(inputNameCategory, name);
        webUI.clickElement(btnSaveName);
        webUI.verifyAlertMessage(excelHelper.getCellData("category", 3));
        verifyNewCategory(name);
        webUI.sleep(1);

    }

    public void verifyNewCategory(String name) {
        openCategoryPage();
        categoryProductPage = new CategoryProductPage(driver);
        categoryProductPage.searchCategory(name);
    }

    public void addCategoryAlreadyExist() throws Exception {
        webUI.waitForPageLoaded();
        excelHelper.setExcelFile("src/test/resources/testData/DataTest.xlsx", "AddUsesAndCategory");
        webUI.clickElement(btnAddCategory);
        webUI.setText(inputNameCategory, excelHelper.getCellData("category", 2));
        webUI.clickElement(btnSaveName);
        webUI.verifyAlertMessage(excelHelper.getCellData("category", 4));
        webUI.sleep(1);
    }

    public void verifyUsesName() throws Exception {
        excelHelper.setExcelFile("src/test/resources/testData/DataTest.xlsx", "AddUsesAndCategory");
        webUI.clickElement(buttonAddUses);
        webUI.clickElement(btnSaveName);
        webUI.verifyElemenText(verifyNameUses, excelHelper.getCellData("uses", 1));
        webUI.sleep(1);
    }

    public void addUses() throws Exception {
        excelHelper.setExcelFile("src/test/resources/testData/DataTest.xlsx", "AddUsesAndCategory");
        webUI.waitForPageLoaded();
        String name = excelHelper.getCellData("uses", 2);
        webUI.clickElement(buttonAddUses);
        webUI.setText(inputNameUses, name);
        webUI.clickElement(btnSaveName);
        webUI.selectElement(selectUses, inputSearchUses, name);
        webUI.verifyElemenText(selectUses, name);
        webUI.verifyAlertMessage(excelHelper.getCellData("uses", 3));
        webUI.sleep(1);
    }

    public void addUsesAlreadyExist() throws Exception {
        excelHelper.setExcelFile("src/test/resources/testData/DataTest.xlsx", "AddUsesAndCategory");
        webUI.waitForPageLoaded();
        webUI.clickElement(buttonAddUses);
        webUI.setText(inputNameUses, excelHelper.getCellData("uses", 2));
        webUI.clickElement(btnSaveName);
        webUI.verifyAlertMessage(excelHelper.getCellData("uses", 4));
        webUI.sleep(1);

    }

    public void deleteUses() {
        webUI.waitForPageLoaded();
        webUI.clickElement(deleteUses);
        webUI.clickElement(cbUses);
        webUI.sleep(1);
        webUI.clickElement(btnSaveName);
    }

    public void enterInvalidInventor() {
        webUI.waitForPageLoaded();
        webUI.setText(inputIdProduct, "PRU01");
        webUI.setText(inputNameProduct, "PRU01");
        webUI.selectElement(selectUses, inputSearchUses, "Quà tặng nhân viên");
        webUI.selectElement(selectCategory, inputCategory, "Văn phòng - công sở");
        webUI.setText(inputInventoryHN, "-1");
        webUI.setText(inputWarehouseCodeHN, "KHO-HN");
        String filePath = "C:\\Users\\legia\\Downloads\\DATN_NguyenThiLeGiang\\src\\test\\resources\\testData\\sản phẩm1.jpg";
        uploadImg(uploadMainImg, filePath);
        Assert.assertTrue(webUI.verifyElemenText(verifyUploadImg, "Cắt ảnh"), "Upload img không thành công");
        webUI.clickElement(buttonSaveImg);
        webUI.setText(inputWeight, "500");
        webUI.setText(inputUnit, "Cái");
        jsExecutor.executeScript("window.scrollTo(1500,0)");
        webUI.clickElement(buttonSave);
        webUI.clickElement(btnAccept);
        webUI.verifyAlertMessage("Lưu dữ liệu lỗi");

    }

    public void createProduct() {
        webUI.waitForPageLoaded();
        setInformation();
        jsExecutor.executeScript("window.scrollTo(500,0)");
        setDetail();
        setTechnical();
        webUI.clickElement(buttonSave);
        webUI.clickElement(btnAccept);
        webUI.verifyAlertMessage("Thêm sản phẩm thành công !");
        webUI.verifyUrl("/Product/Details/");

    }

    public void createProductFailWithIdAlreadyExist() {
        webUI.waitForPageLoaded();
        setInformation();
        jsExecutor.executeScript("window.scrollTo(500,0)");
        webUI.clickElement(buttonSave);
        webUI.clickElement(btnAccept);
        webUI.verifyAlertMessage("Mã hàng này đã tồn tại!");
    }


    public void verifyMsgRequiredFields() throws Exception {
        webUI.verifyElemenText(idProductVerify, excelHelper.getCellData("idProduct", 1));
        webUI.verifyElemenText(nameProductVerify, excelHelper.getCellData("nameProduct", 1));
        webUI.verifyElemenText(UsesVerify, excelHelper.getCellData("uses", 1));
        webUI.verifyElemenText(categoryVerify, excelHelper.getCellData("category", 1));
        webUI.verifyElemenText(mainImgVerify, excelHelper.getCellData("mainImg", 1));
        webUI.verifyElemenText(verifyWeigth, excelHelper.getCellData("weight", 1));
        webUI.verifyElemenText(verifyUnit, excelHelper.getCellData("unit", 1));
        jsExecutor.executeScript("window.scrollTo(0,1500)");
        webUI.clickElement(buttonAddPriceRange);
        webUI.verifyElemenText(verifyPriceRange, "Vui lòng nhập giá");
    }

    public void verifyIDhHouseware() {
        webUI.reload(driver);
        webUI.waitForPageLoaded();
        webUI.setText(inputIdProduct, "PRU01");
        webUI.setText(inputNameProduct, "PRU01");
        webUI.selectElement(selectUses, inputSearchUses, "Quà tặng nhân viên");
        webUI.selectElement(selectCategory, inputCategory, "Văn phòng - công sở");
        String filePath = "C:\\Users\\legia\\Downloads\\DATN_NguyenThiLeGiang\\src\\test\\resources\\testData\\sản phẩm1.jpg";
        uploadImg(uploadMainImg, filePath);
        Assert.assertTrue(webUI.verifyElemenText(verifyUploadImg, "Cắt ảnh"), "Upload img không thành công");
        webUI.clickElement(buttonSaveImg);
        webUI.setText(inputWeight, "500");
        webUI.setText(inputUnit, "Cái");
        jsExecutor.executeScript("window.scrollTo(1500,0)");
        webUI.clickElement(buttonSave);
        webUI.clickElement(btnAccept);
        webUI.verifyAlertMessage("Vui lòng nhập mã kho hàng!");
    }

    public void setInformation() {
        webUI.reload(driver);
        Assert.assertTrue(webUI.verifyElemenText(titleCreatePrd, "Thêm mới sản phẩm"), "Không phải trang Thêm sản phẩm");
        webUI.waitForPageLoaded();
        webUI.setText(inputIdProduct, "PRU01");
        webUI.setText(inputNameProduct, "PRU01");
        webUI.selectElement(selectUses, inputSearchUses, "Quà tặng nhân viên");
        webUI.selectElement(selectCategory, inputCategory, "Văn phòng - công sở");

        webUI.setText(inputInventoryHN, "150");
        webUI.setText(inputWarehouseCodeHN, "KHO-HN");
        webUI.setText(inputInventoryHCM, "100");
        webUI.setText(inputWarehouseCodeHCM, "KHO-HCM");
        webUI.setText(inputPrice, "250000");

        //add property 1
        webUI.clickElement(buttonAddProperties);
        Assert.assertTrue(webUI.verifyElemenText(verifyProperties1, "Nhóm phân loại 1"), "Không tìm thấy thuộc tính");
        webUI.setText(inputGroupName1, "Màu Trắng");
        webUI.setText(inputGroupType1, "1000ml");
        webUI.setText(inputPriceGroupType1, "20000");
        webUI.clickElement(buttonAddClassification);
        webUI.setText(inputGroupType2, "600ml");
        webUI.setText(inputPriceGroupType2, "0");

        //add property 2
        webUI.clickElement(buttonAddProperties);
        Assert.assertTrue(webUI.verifyElemenText(verifyProperties2, "Nhóm phân loại 2"), "Không tìm thấy thuộc tính");
        webUI.setText(inputGroupName2, "Màu Xanh");
        webUI.setText(inputGroupType21, "1000ml");
        webUI.setText(inputPriceGroupType21, "20000");
        webUI.clickElement(buttonAddClassificiation1);
        webUI.setText(inputGroupType22, "600ml");
        webUI.setText(inputPriceGroupType22, "0");

        //upload img
        String filePath = "C:\\Users\\legia\\Downloads\\DATN_NguyenThiLeGiang\\src\\test\\resources\\testData\\img\\sản phẩm1.jpg";
        uploadImg(uploadMainImg, filePath);
        Assert.assertTrue(webUI.verifyElemenText(verifyUploadImg, "Cắt ảnh"), "Upload img không thành công");
        webUI.clickElement(buttonSaveImg);

        webUI.setText(inputWeight, "500");
        webUI.setText(inputCost, "100000");
        webUI.selectElement(selectSex, inputSex, "Tẩt cả");
        webUI.setText(inputAge, "25");
        webUI.setText(inputUnit, "Cái");
        webUI.clickElement(checkboxActivated);
    }

    public void setDetail() {
        jsExecutor.executeScript("window.scrollTo(500,0)");
        webUI.waitForPageLoaded();
        Assert.assertTrue(webUI.verifyElemenText(tabDetail, "Mô tả chi tiết"), "Không tìm thấy tab Mô tả chi tiết");
        webUI.clickElement(tabDetail);
        Assert.assertTrue(webUI.verifyElemenText(verifySumary, "Mô tả ngắn"), "Không phải tab Mô tả chi tiết");
        //Short Description
        WebElement iframe = driver.findElement(iframeShortDescription);
        driver.switchTo().frame(iframe);
        WebElement body = driver.findElement(By.id("tinymce"));
        body.clear(); // Xóa nội dung hiện có nếu cần
        body.sendKeys("Đây là nội dung mới được nhập vào TinyMCE Editor bằng Selenium.");
        driver.switchTo().defaultContent();

        //Description
        iframe = driver.findElement(iframeDescription);
        driver.switchTo().frame(iframe);
        body = driver.findElement(By.id("tinymce"));
        body.clear(); // Xóa nội dung hiện có nếu cần
        body.sendKeys("Đây là nội dung mới được nhập vào TinyMCE Editor bằng Selenium.");
        driver.switchTo().defaultContent();
    }

    public void setTechnical() {
        jsExecutor.executeScript("window.scrollTo(800,0)");
        webUI.waitForPageLoaded();
        Assert.assertTrue(webUI.verifyElemenText(tabTechnical, "Thông số kĩ thuật"), "Không tìm thấy tab Thông số kĩ thuật");
        webUI.clickElement(tabTechnical);
        Assert.assertTrue(webUI.verifyElemenText(verifyTechnical, "Thông số kĩ thuật"), "Không phải tab thông tin kĩ thuật");
        WebElement iframe = driver.findElement(idFrameTechnical);
        driver.switchTo().frame(iframe);
        WebElement body = driver.findElement(By.id("tinymce"));
        body.clear(); // Xóa nội dung hiện có nếu cần
        body.sendKeys("Đây là nội dung mới được nhập vào TinyMCE Editor bằng Selenium.");
        driver.switchTo().defaultContent();
    }


    public void uploadImg(By element, String filePath) {
        webUI.clickElement(element);
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        StringSelection str = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
//        robot.keyPress(KeyEvent.VK_CONTROL);
//        robot.keyPress(KeyEvent.VK_V);
//        robot.keyRelease(KeyEvent.VK_CONTROL);
//        robot.keyRelease(KeyEvent.VK_V);
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);

        // Nhấn phím Enter để mở hộp thoại chọn tệp
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(1000);

        // Nhấn phím Ctrl+V để dán đường dẫn tệp từ clipboard
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(1000);

        // Nhấn phím Enter để xác nhận việc chọn tệp
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }

    public CategoryProductPage openCategoryPage() {
        webUI.clickElement(categories);
        webUI.clickElement(categoryProduct);
        return new CategoryProductPage(driver);
    }

}
