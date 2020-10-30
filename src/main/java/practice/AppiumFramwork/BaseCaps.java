package practice.AppiumFramwork;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseCaps {

    public static AppiumDriverLocalService service;
    public static AndroidDriver<AndroidElement> driver;

//    private AppiumServiceBuilder builder;
//    private DesiredCapabilities cap;

    public AppiumDriverLocalService startServer() {
	boolean flag = checkIfServerIsRunning(4723);
	if (!flag) {
//			cap = new DesiredCapabilities();
//			cap.setCapability("noReset", "false");
//			
//			builder = new AppiumServiceBuilder();
//			builder.withIPAddress("127.0.0.1");
//			builder.usingPort(4723);
//			builder.withCapabilities(cap);
//			builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
//			builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");

	    service = AppiumDriverLocalService.buildDefaultService();
	    service.start();
	}
	return service;
    }

    public static void stopServer() {
	service.stop();
    }

    public void killAllNodes() throws IOException, InterruptedException {
	Runtime.getRuntime().exec("taskkill /F /IM node.exe");
	Thread.sleep(3000);
    }

    public static boolean checkIfServerIsRunning(int port) {
	boolean isServerRunning = false;
	ServerSocket serverSocket;
	try {
	    serverSocket = new ServerSocket(port);
	    serverSocket.close();
	} catch (IOException e) {
	    // if control comes here, then it means that the port is in use
	    isServerRunning = true;
	} finally {
	    serverSocket = null;
	}
	return isServerRunning;
    }

    public static void startEmulator() throws IOException, InterruptedException {
	Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\startEmulator.bat");
	Thread.sleep(6000);
    }

    public static AndroidDriver<AndroidElement> Capabilities(String appName) throws IOException, InterruptedException {

	FileInputStream fis = new FileInputStream(
		System.getProperty("user.dir") + "\\src\\main\\java\\resources\\global.properties");
	Properties prop = new Properties();
	prop.load(fis);

	File appDir = new File("src");
	File app = new File(appDir, (String) prop.get(appName));

	DesiredCapabilities caps = new DesiredCapabilities();

	String device = (String) prop.get("device"); // get the device from global.properties
	// String device = System.getProperty("deviceName"); //get the device from mvn
	// command line. remove the device from global.properties

	if (device.contains("Emulator")) {
	    startEmulator();
	}
	System.out.println("The test device is setup as: " + device);

	// caps.setCapability(MobileCapabilityType.DEVICE_NAME, device);//Virtual Device
	// running with Emulator
	caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");// Real Device connected to your Machine.

	caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
	caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 15);

	caps.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
	caps.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);

	// AndroidDriver<AndroidElement> driver = new AndroidDriver(new
	// URL("http://127.0.0.1:4723/wd/hub"),cap);
	// Session takes 60 seconds to close. Before 60s you can force close (Ctrl+C)
	// the connection.
	driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	return driver;
    }

    public static void getScreenshot(String testCaseName) throws IOException {
	// TODO Auto-generated method stub
	String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
	File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	String scrFileName = System.getProperty("user.dir") + "\\Screenshots\\" + testCaseName + "_" + timestamp
		+ ".png";
	FileUtils.copyFile(scrFile, new File(scrFileName));
	System.out.println("The screenshot of failed testcase has stored as " + scrFileName);
    }

}
