package api.utilities;

//Listener class used to generate Extent reports
import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager extends TestListenerAdapter
{
	public ExtentSparkReporter sparkReporter;
//	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
		
	public void onStart(ITestContext testContext)
	{
//		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="Test_Report.html";
		
//		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/Reports/"+repName);//specify location of the report
//		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/testng.xml");
		
		sparkReporter=new ExtentSparkReporter(".//Reports/"+repName);
		
		extent=new ExtentReports();
		
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("user","Vinodh");
		
		sparkReporter.config().setDocumentTitle("Pet Store Project"); // Tile of report
		sparkReporter.config().setReportName("API Automation Report"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);
	}
	
	public void onTestSuccess(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN)); // send the passed information to the report with GREEN color highlighted
	}
	
	public void onTestFailure(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED)); // send the passed information to the report with RED color highlighted
		
		String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		
		File f = new File(screenshotPath); 
		
		if(f.exists())
        {
        try {
            logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
            } 
        catch (Exception e) 
                {
                e.printStackTrace();
                }
        }
		
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
	}
	
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
}