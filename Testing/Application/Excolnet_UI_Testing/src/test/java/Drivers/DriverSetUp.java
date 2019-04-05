package Drivers;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverSetUp {



    public  static AndroidDriver initDriver( String deviceName ){
        AndroidDriver androidDriver  =  null;
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        cap.setCapability("autoGrantPermissions", "true");
        cap.setCapability("noReset", true);
        cap.setCapability("fullReset", false);
        cap.setCapability("autoGrantPermissions" , "true" );
        cap.setCapability(MobileCapabilityType.APP, "/home/leo/Documentos/Ingenieria-de-software-II/excolnet/app/build/outputs/apk/debug/app-debug.apk");

        String serverUrl = "http://127.0.0.1:4723/wd/hub";

        try {

            androidDriver = new AndroidDriver(new URL(serverUrl), cap);
        }
        catch (Exception ex) {
            throw new RuntimeException("Appium driver could not be initialized for device ");
        }
        return androidDriver;
    }
}
