package Commons;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DriverCommons {

    public AndroidDriver driver;
    public TouchAction touch;


    public DriverCommons(AndroidDriver androidDriver) {
        this.driver = androidDriver;
        this.touch = new TouchAction(androidDriver);
    }


    public void addImplicitlyWait(int timeUnit) {
        this.driver.manage().timeouts().implicitlyWait(timeUnit, TimeUnit.SECONDS);

    }


    public void performSwipeDown() {
        this.touch.waitAction(new WaitOptions().withDuration(Duration.ofMillis(5000)))
                .press(new PointOption().withCoordinates(94, 1991))
                .moveTo(new PointOption().withCoordinates(94, 399))
                .perform();
    }


    public void performSwipeUp() {
        touch.waitAction(new WaitOptions().withDuration(Duration.ofMillis(5000)))
                .press(new PointOption().withCoordinates(375, 1130))
                .moveTo(new PointOption().withCoordinates(375, 375))
                .perform();
    }

    public void hideKeyBoard() {
        this.driver.hideKeyboard();
    }

    public boolean waitForPresence(int LimitTimeSeconds, String resourceTarget) {
        Boolean isElementDisplayed;
        try {
            MobileElement mobileElement = (MobileElement) this.driver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"" + resourceTarget + "\")");
            WebDriverWait wait = new WebDriverWait(this.driver, LimitTimeSeconds);
            wait.until(ExpectedConditions.visibilityOf(mobileElement));
            isElementDisplayed = mobileElement.isDisplayed();
            return isElementDisplayed;

        } catch (Exception e) {
            isElementDisplayed = false;
            System.out.println(e.getMessage());
            return isElementDisplayed;
        }
    }

    public void waitForDissapear(int LimitTimeSeconds, String resourceTarget) {
        try {
            MobileElement mobileElement = (MobileElement) this.driver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"" + resourceTarget + "\")");
            WebDriverWait wait = new WebDriverWait(this.driver, LimitTimeSeconds);
            wait.until(ExpectedConditions.invisibilityOf(mobileElement));
        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
    }

    public void navigateBack() {
        this.driver.navigate().back();
    }

    public void resetApp(){
       this.driver.resetApp();
    }

    public boolean swipeFromBottomToUp()
    {
        try  {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            HashMap<String, String> scrollObject = new HashMap<String,String>();
            scrollObject.put("direction", "down");
            js.executeScript("mobile: scroll", scrollObject);
            System.out.println("Swipe down was Successfully done");
        }
        catch (Exception e)
        {
            System.out.println("swipe down was not successfull");
        }
        return false;
    }

    public boolean swipeFromUpToBottom()
    {

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            HashMap<String, String> scrollObject = new HashMap<String, String>();
            scrollObject.put("direction", "up");
            js.executeScript("mobile: scroll", scrollObject);
            System.out.println("Swipe up was Successfully done.");
        }
        catch (Exception e)
        {
            System.out.println("swipe up was not successfull");
        }
        return false;
    }
}
