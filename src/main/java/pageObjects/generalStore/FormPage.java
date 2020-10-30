package pageObjects.generalStore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage{
    //All the objects belongs to one page will be defined in a java class
    //1. Call the driver object from testcase to PageObject file

    //Concatenate driver
    public FormPage(AppiumDriver driver) {
		// TODO Auto-generated constructor stub
		//AppiumFieldDecorator give the ability to test across iOS and Android platforms
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    public WebElement yourName;
    
    @AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
    public WebElement femaleCheckBox;

    @AndroidFindBy(id="android:id/text1")
    public WebElement dropDown;
    
    @AndroidFindBy(uiAutomator="new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));")
    public WebElement findCountry;
    
    @AndroidFindBy(xpath="//*[@text='Argentina']")
    public WebElement selectCountry;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    public WebElement letsShopBtn;
    
    @AndroidFindBy(xpath="//android.widget.Toast[1]")
    public WebElement toastMessage;

}
