package resources;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import practice.AppiumFramwork.BaseCaps;

public class ScreenshotListener implements ITestListener{

    public void onTestFailure(ITestResult result) {
		
	System.out.println("****** Error " + result.getName() + " test has failed ******");
	
	String testCaseName = result.getInstanceName() + "." + result.getName();
	System.out.println("The failed method name is: " + result.getName());

	try {
	    BaseCaps.getScreenshot(testCaseName);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
	
}