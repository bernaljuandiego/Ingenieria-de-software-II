package TestCases;

import Commons.DriverCommons;
import Drivers.DriverSetUp;
import Pages.PublicationPage;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Before;
import org.junit.Test;

public class UserPublicationTest {

    private AndroidDriver driver;

    private DriverCommons driverCommons;

    private PublicationPage publicationPage;


    @Before
    public void initComponents(){
        this.driver = DriverSetUp.initDriver("8350ca1a");
        this.driverCommons = new DriverCommons(this.driver);
    }

    public UserPublicationTest(AndroidDriver driver, DriverCommons driverCommons) {
        this.driver = driver;
        this.driverCommons = driverCommons;
   }



    @Test
    public void doPublication(){
        driverCommons.addImplicitlyWait(5);
        this.publicationPage = new PublicationPage(driver);


        publicationPage.clickFab();

        driverCommons.addImplicitlyWait(5);
        publicationPage.typeInEditText();
        publicationPage.clickCreatePublicationButton();




    }





}
