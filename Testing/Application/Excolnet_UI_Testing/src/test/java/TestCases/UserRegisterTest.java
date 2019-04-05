package TestCases;

import Commons.DriverCommons;
import Drivers.DriverSetUp;
import Pages.UserRegisterPage;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Before;
import org.junit.Test;

public class UserRegisterTest {

    private AndroidDriver driver;
    private DriverCommons driverCommons;
    private UserRegisterPage userRegisterPage;


    public UserRegisterTest(AndroidDriver driver, DriverCommons driverCommons) {

        this.driver = driver;
        this.driverCommons = driverCommons;



    }

    @Before
    public void initComponents() {
        if (driver == null && driverCommons == null && userRegisterPage == null) {

            this.driver = DriverSetUp.initDriver("8350ca1a");
            this.driverCommons = new DriverCommons(this.driver);


        } else {
            System.out.println("test comes from another test , skipping .. ");
        }
    }

    @Test
    public void doRegistry() {

        this.userRegisterPage = new UserRegisterPage(driver);
        userRegisterPage.typeuserNameField();
        userRegisterPage.typeUserLastNameField();
        userRegisterPage.typeEmailField();
        userRegisterPage.typePasswordField();
        driverCommons.hideKeyBoard();
        userRegisterPage.clickImageButton();
        driverCommons.addImplicitlyWait(5);
        userRegisterPage.getRandomImage();
        driverCommons.addImplicitlyWait(5);
        userRegisterPage.clickSignInButton();
        boolean isDisplayedComponent = driverCommons.waitForPresence(5, userRegisterPage.getSnackBarReference());

        if (isDisplayedComponent) {
             driverCommons.waitForDissapear(5 , userRegisterPage.getSnackBarReference());

            driverCommons.navigateBack();

        } else {
            System.out.println("not found yet");
        }


    }

}
