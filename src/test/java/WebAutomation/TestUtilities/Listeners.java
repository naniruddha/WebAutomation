package WebAutomation.TestUtilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import WebAutomation.GenericUtilities.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "SUCCESS: Test Passed Succesfully.");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		String filePath = null;
		try {
			WebDriver driverCurrent = null;
			try {
				driverCurrent = (WebDriver)  result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			filePath = getScreenshot(result.getMethod().getMethodName(),driverCurrent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();

	}

}
