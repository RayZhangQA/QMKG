package testCases.generalStore;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.generalStore.CheckoutPage;
import pageObjects.generalStore.FormPage;
import pageObjects.generalStore.ProductListPage;
import practice.AppiumFramwork.BaseCaps;
import practice.AppiumFramwork.Utilities;

public class TC5_TermsAndProceedValidation extends BaseCaps{
    
    @Test
    public void  termsValidation() throws IOException, InterruptedException{
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
		util.tapElement(checkout.emailCheckBox);	//tap "Send me e-mails on ...." checkbox
	
		util.longPressElement(checkout.termsBtn);		//long press "Terms of Conditions"
		checkout.termsCloseBtn.click();			//Close the pop up window
		checkout.proceedBtn.click();			//Click "Visit to the website to complete purchase"
		
		stopServer();

    } 
    
}
