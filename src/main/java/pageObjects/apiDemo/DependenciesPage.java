package pageObjects.apiDemo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DependenciesPage {
	
    public DependenciesPage(AppiumDriver driver) {
	// TODO Auto-generated constructor stub
	//AppiumFieldDecorator give the ability to test across iOS and Android platforms
	PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    
    @AndroidFindBy(id="android:id/checkbox")
    public WebElement wifiCheckBox;

    @AndroidFindBy(xpath="(//android.widget.RelativeLayout)[2]")
    public WebElement wifiSettings;

    @AndroidFindBy(className="android.widget.EditText")
    public WebElement editText;
    
    @AndroidFindBy(className="android.widget.Button")
    public WebElement editTextBtn;

}
