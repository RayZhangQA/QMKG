package pageObjects.generalStore;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductListPage{

	public ProductListPage(AppiumDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
 
    @AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
    public WebElement shoppingCartBtn;
    
    @AndroidFindBy(xpath="//*[@text='ADD TO CART']")
    public List<WebElement> addProdBtns;
    
    @AndroidFindBy(id="com.androidsample.generalstore:id/productName")
    public List<WebElement> productNames;
    
    @AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
    public List<WebElement> productPrices;
   
}
