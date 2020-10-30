package testCases.generalStore;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.generalStore.CheckoutPage;
import pageObjects.generalStore.FormPage;
import pageObjects.generalStore.ProductListPage;
import practice.AppiumFramwork.BaseCaps;
import practice.AppiumFramwork.Utilities;

public class TC4_VerifyTotalAmount extends BaseCaps{
    
    
    @Test
    public void totalValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
    	startServer();
    	
		AndroidDriver<AndroidElement> driver = Capabilities("GeneralStoreApp");
	
		//Enter "Your Name" and then click "Let's Shop"
		FormPage form = new FormPage(driver);
		form.yourName.sendKeys("Hello"); 	//enter "Hello" to Your Name field
		driver.hideKeyboard();	//hide keyboard
		form.letsShopBtn.click();		//click "Let's Shop" button			
		System.out.println("Loading Products");
		
		//adding top 2 products to shopping cart and click "shopping cart" button
		ProductListPage prodList = new ProductListPage(driver);
		prodList.addProdBtns.get(0).click();	//add first product
		prodList.addProdBtns.get(0).click();	//add second product
		prodList.shoppingCartBtn.click();		//Click Shopping Cart Button
		
		Thread.sleep(3000);
		System.out.println("Check the shopping cart");
	
		Utilities util = new Utilities(driver);
		double sumOfProducts = util.sumOfProducts();
		
		//get the total purchase amount 
		CheckoutPage checkout = new CheckoutPage(driver);
		String totalAmount = checkout.totalAmount.getText();
		//String totalAmount = driver.findElementById("com.androidsample.generalstore:id/totalAmountLbl").getText();
		double totalAmountValue = util.convertAmount(totalAmount);
		System.out.println("The Sum of Products is: " + sumOfProducts);
		
		System.out.println("The Sum of Products " + sumOfProducts + " is matching with Total Purchase Amount " + totalAmountValue);
		
		//compare the sumOfProducts with total purchase amount. 
		Assert.assertEquals(sumOfProducts, totalAmountValue);
		
		stopServer();
    } 
 
}
