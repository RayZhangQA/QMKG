package testCases.generalStore;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.generalStore.FormPage;
import practice.AppiumFramwork.BaseCaps;
import practice.AppiumFramwork.Utilities;

public class TC2_AddingProducts extends BaseCaps{
    
    @Test
    public void addingProducts() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
    	startServer();
		AndroidDriver<AndroidElement> driver = Capabilities("GeneralStoreApp");
	
		//Enter "Your Name" and then click "Let's Shop"
		FormPage form = new FormPage(driver);
		form.yourName.sendKeys("Hello"); 	//enter "Hello" to Your Name field
		driver.hideKeyboard();	//hide keyboard
		form.letsShopBtn.click();		//click "Let's Shop" button
		//form.letsShopBtn.sendKeys("hello");	//fail the test here for negative test
		
		//Add product of "Jordan 6 Rings"
		Utilities util = new Utilities(driver);
		util.addProduct("Jordan 6 Rings");
		//System.out.println(prodName + " is added to shopping cart!");
		
		//Click "Shopping Cart" button to check out the selected products
		driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();
		System.out.println("The selected products are added to cart!");
		
		stopServer();
    }


    
}
