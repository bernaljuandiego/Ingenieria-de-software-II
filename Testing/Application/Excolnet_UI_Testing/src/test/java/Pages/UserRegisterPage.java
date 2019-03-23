package Pages;

import Drivers.DriverSetUp;
import com.google.gson.annotations.Since;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class UserRegisterPage {


    public static void main(String[] args){
        //Driver creation
        AndroidDriver driver = DriverSetUp.initDriver("Android" , "Moto E (4) Plus");
        driver.manage().timeouts().implicitlyWait(5 , TimeUnit.SECONDS);
        TouchAction touch =  new TouchAction(driver) ;

            System.out.println("intentando hacer swipe");

        touch.waitAction(new WaitOptions().withDuration(Duration.ofMillis(5000)))
                .press(new PointOption().withCoordinates(586 , 681))
                .moveTo(new PointOption().withCoordinates(597 , 291))

                .perform();




        MobileElement el1 = (MobileElement) driver.findElementById("co.edu.konradlorenz.excolnet:id/sign_up_button");
        el1.click();
        MobileElement el2 = (MobileElement) driver.findElementById("co.edu.konradlorenz.excolnet:id/username_text_input");
        el2.sendKeys("juan");
        MobileElement el3 = (MobileElement) driver.findElementById("co.edu.konradlorenz.excolnet:id/lastname_text_input");
        el3.sendKeys("paramo");
        MobileElement el4 = (MobileElement) driver.findElementById("co.edu.konradlorenz.excolnet:id/email_text_input");
        el4.sendKeys("juan.paramo@cocomobile.co");
        MobileElement el5 = (MobileElement) driver.findElementById("co.edu.konradlorenz.excolnet:id/password_text_input");
        el5.sendKeys("Juanp2019*");
        MobileElement el6 = (MobileElement) driver.findElementById("co.edu.konradlorenz.excolnet:id/sign_in_button");
        el6.click();

        driver.manage().timeouts().implicitlyWait(3 , TimeUnit.SECONDS);

        driver.navigate().back();


    }
}
