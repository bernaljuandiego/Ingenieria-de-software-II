package TestCases;

import Commons.DriverCommons;
import Drivers.DriverSetUp;
import Pages.UserLoginPage;
import Pages.UserRegisterPage;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Before;
import org.junit.Test;

public class UserRegisterTest {

    private AndroidDriver driver;
    private DriverCommons driverCommons;
    private UserRegisterPage userRegisterPage;
    private UserLoginPage userLoginPage;

    @Before
    public void initComponents() {
        this.driver = DriverSetUp.initDriver();
        this.driverCommons = new DriverCommons(this.driver);
    }

    @Test
    public void doRegistry() {
        driverCommons.addImplicitlyWait(5);
        driverCommons.performSwipeUp();
        userLoginPage = new UserLoginPage(driver);
        userLoginPage.clickSignUp();

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
