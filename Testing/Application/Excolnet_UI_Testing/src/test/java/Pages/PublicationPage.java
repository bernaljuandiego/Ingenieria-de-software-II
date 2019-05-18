package Pages;

import Commons.DriverCommons;
import Drivers.DriverSetUp;
import com.github.javafaker.Faker;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class PublicationPage {


    private AndroidDriver pageDriver;

    private  MobileElement fab;

    private MobileElement editText;

    private MobileElement createPublicationButton;

    private Faker faker;


    public PublicationPage(AndroidDriver pageDriver) {
        this.pageDriver = pageDriver;
        this.faker = Faker.instance();
        initComponents();
    }

    public void initComponents(){
        this.fab = (MobileElement) pageDriver.findElementById("co.edu.konradlorenz.excolnet:id/fab_publications");

    }

    public MobileElement getFab() {
        return fab;
    }

    public void clickFab() {
        this.fab.click();
    }

    public MobileElement getEditText() {
        return editText;
    }

    public void typeInEditText() {
        this.editText = (MobileElement) pageDriver.findElementById("co.edu.konradlorenz.excolnet:id/text_publication");
        this.editText.sendKeys(faker.internet().avatar());
    }

    public MobileElement getCreatePublicationButton() {
        return createPublicationButton;
    }

    public void clickCreatePublicationButton() {
        this.createPublicationButton = (MobileElement) pageDriver.findElementById("co.edu.konradlorenz.excolnet:id/send_button");
        this.createPublicationButton.click();
    }

}
