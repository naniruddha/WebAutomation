package WebAutomation.GenericUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReporterNG {
	public static ExtentReports getReportObject() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyyHHmm");  
	    Date date = new Date();  
	    //System.out.println(formatter.format(date));  
		String path = System.getProperty("user.dir")+"//reports//index_"+formatter.format(date)+".html";
		//String path = System.getProperty("user.dir")+"//reports//report_"+formatter.format(date)+"//index.html";
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Resutls");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Nihar Aniruddha");
		return extent;
	}
}
