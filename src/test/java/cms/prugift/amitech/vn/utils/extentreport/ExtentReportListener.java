package cms.prugift.amitech.vn.utils.extentreport;

import cms.prugift.amitech.vn.utils.enums.Author;
import cms.prugift.amitech.vn.utils.enums.Browser;
import cms.prugift.amitech.vn.utils.enums.Category;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;

public class ExtentReportListener implements ITestListener {

    private ExtentReports extent;
    private Map<String, ExtentTest> testMap = new HashMap<>();
    private String className;

    @Override
    public void onStart(ITestContext context) {
        className = context.getName(); // Lấy tên của lớp

        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/ExtentReports.html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Extent Reports Demo");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        test.assignAuthor(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestInfo.class).author().toString());
        test.assignCategory(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestInfo.class).categories()[0].toString());
        test.assignDevice(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestInfo.class).browser().toString());

        testMap.put(result.getMethod().getMethodName(), test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testMap.get(result.getMethod().getMethodName()).pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        testMap.get(result.getMethod().getMethodName()).fail(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // Other methods from ITestListener interface can be left unimplemented

    public void assignAuthor(String testName, Author author) {
        if (testMap.containsKey(testName)) {
            testMap.get(testName).assignAuthor(author.toString());
        }
    }

    public void assignCategory(String testName, Category category) {
        if (testMap.containsKey(testName)) {
            testMap.get(testName).assignCategory(category.toString());
        }
    }

    public void assignbrowser(String testName, Browser device) {
        if (testMap.containsKey(testName)) {
            testMap.get(testName).assignDevice(device.toString());
        }
    }
}
