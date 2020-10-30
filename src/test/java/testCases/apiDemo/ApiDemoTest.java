package testCases.apiDemo;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.apiDemo.DependenciesPage;
import pageObjects.apiDemo.HomePage;
import pageObjects.apiDemo.PreferencesPage;
import practice.AppiumFramwork.BaseCaps;
import practice.AppiumFramwork.TestData;


public class ApiDemoTest extends BaseCaps{

    @BeforeTest
    public void killAllNodes() throws IOException, InterruptedException {
	//taskkill /F /IM node.exe
	Runtime.getRuntime().exec("taskkill /F /IM node.exe");
	Thread.sleep(3000);
    }
    
    @Test(dataProvider="InputData", dataProviderClass=TestData.class)
    public void apiDemoTest(String input) throws IOException, InterruptedException {
	// TODO Auto-generated method stub
		
	startServer();
		
	AndroidDriver<AndroidElement> driver = Capabilities("apiDemo");
	
	HomePage home = new HomePage(driver);
	home.preferences.click();
	//driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
	
	PreferencesPage preferences = new PreferencesPage(driver);
	preferences.dependencies.click();
	//driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
	
	DependenciesPage dependencies = new DependenciesPage(driver);
	dependencies.wifiCheckBox.click();
	dependencies.wifiSettings.click();
	dependencies.editText.sendKeys(input);
	dependencies.editTextBtn.click();
			
	stopServer();
    }
	
}
