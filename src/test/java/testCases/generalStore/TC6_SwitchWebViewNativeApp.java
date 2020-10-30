package testCases.generalStore;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pageObjects.generalStore.CheckoutPage;
import pageObjects.generalStore.FormPage;
import pageObjects.generalStore.ProductListPage;
import practice.AppiumFramwork.BaseCaps;
import practice.AppiumFramwork.Utilities;

public class TC6_SwitchWebViewNativeApp extends BaseCaps{
    
    @Test
    public void switchWebviewNativeApp() throws IOException, InterruptedException {
	// TODO Auto-generated method stub
    	startServer();
    	
		AndroidDriver<AndroidElement> driver = Capabilities("GeneralStoreApp");

		//Enter "Your Name" and then click "Let's Shop"
		FormPage form = new FormPage(driver);
		form.yourName.sendKeys("Hello"); 	//enter "Hello" to Your Name field
		driver.hideKeyboard();	//hide keyboard
		form.letsShopBtn.click();		//click "Let's Shop" button			
		System.out.println("Loading Products");
	
		//adding a product to shopping cart and click "shopping cart" button
		ProductListPage prodList = new ProductListPage(driver);
		prodList.addProdBtns.get(0).click();	//add first product
		prodList.shoppingCartBtn.click();		//Click Shopping Cart Button

		Thread.sleep(3000);
		CheckoutPage checkout = new CheckoutPage(driver);
		Utilities util = new Utilities(driver);

		checkout.proceedBtn.click();			//Click "Visit to the website to complete purchase"
		Thread.sleep(5000);
	
		//get context handles
		Set<String> contexts = driver.getContextHandles();
		for (String contextName : contexts) {
		    System.out.println("after open webview, the context name: " + contextName);
		}

		//switch to webview operation and get the title of webpage
		driver.context("WEBVIEW_com.androidsample.generalstore");
		System.out.println("System switched to webview and the page title is " + driver.getTitle());
		driver.findElement(By.name("q")).sendKeys("hello");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		
		//come back to native app from webview
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");
		System.out.println("System switched back to " + driver.getContext());

		stopServer();
    } 
    
}
