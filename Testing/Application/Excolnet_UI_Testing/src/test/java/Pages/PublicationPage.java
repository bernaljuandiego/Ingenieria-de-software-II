package Pages;

import Drivers.DriverSetUp;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class PublicationPage {


    public static void main(String[] args) {

        AndroidDriver driver = DriverSetUp.initDriver("Android", "Nokia 6");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


            MobileElement fab = (MobileElement) driver.findElementById("co.edu.konradlorenz.excolnet:id/fab_publications");
            fab.click();


//Managing wait action until fragment is opened
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        MobileElement editText = (MobileElement) driver.findElementById("co.edu.konradlorenz.excolnet:id/text_publication");
        editText.sendKeys("prueba");




        MobileElement sendPublication = (MobileElement) driver.findElementById("co.edu.konradlorenz.excolnet:id/send_button");
        sendPublication.click();

        System.out.println("Released Successfully");


    }
}
