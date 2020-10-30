package pageObjects.apiDemo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage{
    //All the objects belongs to one page will be defined in a java class
    //1. Call the driver object from testcase to PageObject file

    //Concatenate driver
    public HomePage(AppiumDriver driver) {
		// TODO Auto-generated constructor stub
		//AppiumFieldDecorator give the ability to test across iOS and Android platforms
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath="//android.widget.TextView[@text='Preference']")
    public WebElement preferences;
    

}
