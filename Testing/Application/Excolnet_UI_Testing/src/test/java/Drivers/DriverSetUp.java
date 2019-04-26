package Drivers;

import java.net.URL;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverSetUp {

    private static AndroidDriver androidDriver;

    public static AndroidDriver initDriver(){
        try {
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, "ZH33F228LN");
            cap.setCapability("autoGrantPermissions", "true");
            cap.setCapability("noReset", true);
            cap.setCapability("fullReset", false);
            cap.setCapability(MobileCapabilityType.APP, "/Users/juandiegobernalpedroza/Desktop/app-debug.apk");
            String serverUrl = "http://127.0.0.1:4723/wd/hub";
            androidDriver = new AndroidDriver(new URL(serverUrl), cap);
            return androidDriver;
        }
        catch (Exception ex) {
            throw new RuntimeException("Appium driver could not be initialized for device ");
        }
    }
}
