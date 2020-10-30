package pageObjects.generalStore;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckoutPage{

	public CheckoutPage(AppiumDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/productName")
    //String lastProdName = driver.findElementsById("com.androidsample.generalstore:id/productName").get(0).getText();
    public List<WebElement> productNames;
    
    @AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
    public List<WebElement> productPrices;
    
    @AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
    public WebElement totalAmount;

    @AndroidFindBy(className="android.widget.CheckBox")
    public WebElement emailCheckBox;

    @AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
    public WebElement termsBtn;
    
    @AndroidFindBy(id="android:id/button1")
    public WebElement termsCloseBtn;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
    public WebElement proceedBtn;

}
