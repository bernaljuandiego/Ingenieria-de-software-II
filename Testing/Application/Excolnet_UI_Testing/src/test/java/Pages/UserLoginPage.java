package Pages;


import Drivers.DriverSetUp;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class UserLoginPage {

     //private AndroidDriver driver = DriverSetUp.initDriver();

    public static void main(String[] args){
        //Driver creation
        AndroidDriver driver = DriverSetUp.initDriver();
        driver.manage().timeouts().implicitlyWait(5 , TimeUnit.SECONDS);
        MobileElement el1 = (MobileElement) driver.findElementById("co.edu.konradlorenz.excolnet:id/email");
        el1.sendKeys("juan.paramo@cocomobile.co");
        MobileElement el2 = (MobileElement) driver.findElementById("co.edu.konradlorenz.excolnet:id/password");
        el2.sendKeys("Juanp2019*");
        MobileElement el3 = (MobileElement) driver.findElementById("co.edu.konradlorenz.excolnet:id/email_sign_in_button");
        el3.click();
    }


}
