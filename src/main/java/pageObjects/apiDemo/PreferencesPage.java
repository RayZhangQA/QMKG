package pageObjects.apiDemo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PreferencesPage{
    
    public PreferencesPage(AppiumDriver driver) {
	// TODO Auto-generated constructor stub
	//AppiumFieldDecorator give the ability to test across iOS and Android platforms
	PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='3. Preference dependencies']")
    public WebElement dependencies;
}
