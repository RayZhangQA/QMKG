package practice.AppiumFramwork;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.generalStore.CheckoutPage;
import pageObjects.generalStore.ProductListPage;

public class Utilities {
	
	AndroidDriver<AndroidElement> driver;
	
	public Utilities(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}

	public void scrollToText(String text) {
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));");		
	}
	
	public void tapElement(WebElement element) {
		TouchAction t = new TouchAction(driver);
		t.tap(tapOptions().withElement(element(element))).perform();
	}
	
	public void longPressElement(WebElement element) {
		TouchAction t = new TouchAction(driver);
		t.longPress(longPressOptions().withElement(element(element)).withDuration(ofSeconds(2))).release().perform();
	}


	public void addProduct(String product) {

		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"" + product + "\").instance(0))"));		
	
		//Click "ADD TO CART" of product and get the corresponding info
		int count = driver.findElementsById("com.androidsample.generalstore:id/productName").size();
		System.out.println("The count is: " + count);
		String prodName = "";
		String prodPrice = "";
		for (int i=0; i<count; i++) {
			ProductListPage prodList = new ProductListPage(driver);
			prodName = prodList.productNames.get(i).getText();
			prodPrice = prodList.productPrices.get(i).getText();
		    System.out.println("The Product " + i + " Name is: " + prodName + ", Price is: " + prodPrice);
		    if (prodName.equalsIgnoreCase(product)) {
				System.out.println("The selected Product Name is: " + prodName);
				prodList.addProdBtns.get(i).click();
				break;
			}
		}
	}

	public double sumOfProducts() {	
		CheckoutPage checkout = new CheckoutPage(driver);
		int prodCount = checkout.productPrices.size();
		double sumOfProducts = 0;
	
		//calculate the sum of products
		for(int i=0; i<prodCount; i++) {
		    //String prodPrice = driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(i).getText();
		    String prodPrice = checkout.productPrices.get(i).getText();
		    System.out.println("The Price of Product " + i + " is: " + prodPrice);
		    sumOfProducts = sumOfProducts + convertAmount(prodPrice);	    
		}
		return sumOfProducts;
	}

    public double convertAmount(String strAmount) {
		double doubleAmount = Double.parseDouble(strAmount.substring(1));
		return doubleAmount;	
    }
	
}

