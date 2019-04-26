package Pages;

import Commons.DriverCommons;
import Drivers.DriverSetUp;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class SearchPage {

    public static void main( String args[]){
        AndroidDriver driver =DriverSetUp.initDriver();

        DriverCommons dc =  new DriverCommons(driver);

        dc.addImplicitlyWait(5);

        MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("Búsqueda");
        el1.click();
        MobileElement el2 = (MobileElement) driver.findElementById("co.edu.konradlorenz.excolnet:id/search_src_text");
        el2.sendKeys("Bryan");
        el2.clear();
        el2.sendKeys("Leonardo ");
        el2.clear();
        el2.sendKeys("Juan ");
        el2.clear();
        el2.sendKeys("Enrique ");

        MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("Navegar hacia arriba");
        el3.click();
        dc.addImplicitlyWait(5);
        MobileElement el4 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/androidx.appcompat.widget.LinearLayoutCompat[9]/android.widget.CheckedTextView");
        el4.click();

       


    }
 }
