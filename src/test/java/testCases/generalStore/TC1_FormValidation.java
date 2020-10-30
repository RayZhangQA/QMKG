package testCases.generalStore;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.generalStore.FormPage;
import practice.AppiumFramwork.BaseCaps;
import practice.AppiumFramwork.Utilities;

public class TC1_FormValidation extends BaseCaps{

    @Test
	public void formValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
    	startServer();
		AndroidDriver<AndroidElement> driver = Capabilities("GeneralStoreApp");
	
		FormPage form = new FormPage(driver);
		form.yourName.sendKeys("Hello"); 	//enter "Hello" to Your Name field
		driver.hideKeyboard();	//hide keyboard
		form.femaleCheckBox.click();	//select Female checkbox
		form.dropDown.click();	//open country dropdown menu
		
		Utilities util = new Utilities(driver);
		util.scrollToText("Argentina");		//find country "Argentina"
	
		form.selectCountry.click();		//select the found country
		form.letsShopBtn.click();		//click "Let's Shop" button
		stopServer();
    }

    @Test
    public void toastMessageValidation() throws IOException, InterruptedException {
    	// TODO Auto-generated method stub
    	startServer();
    	AndroidDriver<AndroidElement> driver = Capabilities("GeneralStoreApp");
    	 
		FormPage form = new FormPage(driver);
		form.letsShopBtn.click();		//click "Let's Shop" button

    	String toastMessage = form.toastMessage.getAttribute("name");
    	System.out.println(toastMessage);
    	Assert.assertEquals(toastMessage, "Please enter your name");
    	stopServer();
    }
}
