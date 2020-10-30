package testCases.generalStore;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.generalStore.CheckoutPage;
import pageObjects.generalStore.FormPage;
import pageObjects.generalStore.ProductListPage;
import practice.AppiumFramwork.BaseCaps;
import practice.AppiumFramwork.Utilities;

public class TC3_VerifyShoppingCart extends BaseCaps{
      
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

		String prodName = "Jordan 6 Rings";
		Utilities util = new Utilities(driver);
		util.addProduct(prodName);		//Add product of "Jordan 6 Rings"
	
		ProductListPage prodList = new ProductListPage(driver);
		prodList.shoppingCartBtn.click();	//Click "Shopping Cart" button to check out the selected products
		
		//Check the selected product is showing up in cart correctly.
		CheckoutPage checkout = new CheckoutPage(driver);
		String lastProdName = checkout.productNames.get(0).getText();
		
		//String lastProdName = driver.findElementsById("com.androidsample.generalstore:id/productName").get(0).getText();
		Assert.assertEquals(lastProdName, prodName);
		System.out.println("The selected product \"" + lastProdName + "\" is showing up in cart!");
		
		stopServer();
	}

}
