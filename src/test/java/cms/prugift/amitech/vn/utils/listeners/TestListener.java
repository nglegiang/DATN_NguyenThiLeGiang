package cms.prugift.amitech.vn.utils.listeners;

import cms.prugift.amitech.vn.commons.BaseSetup;
import cms.prugift.amitech.vn.helpers.CaptureHelpers;
import cms.prugift.amitech.vn.utils.LogUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onFinish(ITestContext result) {
        LogUtils.info("End auto test");
    }

    @Override
    public void onStart(ITestContext result) {
        LogUtils.info("Start auto test");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LogUtils.info("Test case fail: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("Đây là test case bị fail: " + result.getName());
        //Chụp màn hình khi test case bị Fail
        try {
            CaptureHelpers.captureScreenshot(BaseSetup.getDriver(), result.getName());
        } catch (Exception e) {
            LogUtils.error("Exception while taking screenshot " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.info("Test case skipp: " + result.getName());

    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("Test case pass: " + result.getName());

    }
}