package TestCases;

import Commons.DriverCommons;
import Drivers.DriverSetUp;
import Pages.UserLoginPage;
import Pages.UserRegisterPage;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserLoginTest {

    private AndroidDriver driver;
    private DriverCommons driverCommons;
    private UserLoginPage userLoginPage;

    @Before
    public void initComponents() {
        this.driver = DriverSetUp.initDriver();
        this.driverCommons = new DriverCommons(this.driver);
    }


    @Test
    public void initLogin() {

            driverCommons.addImplicitlyWait(5);
            userLoginPage = new UserLoginPage(driver);

            userLoginPage.typeEmailField();
            userLoginPage.typePasswordField();
            userLoginPage.clickLogInButton();
            driverCommons.hideKeyBoard();

            driverCommons.addImplicitlyWait(10);

            String currActivity = driver.currentActivity();

            if (currActivity.contains("LoginActivity")) {
                System.out.println("User not Loged yet , executing registry");
                driverCommons.swipeFromUpToBottom();
                userLoginPage.clickSignUp();
                driverCommons.addImplicitlyWait(5);
                UserRegisterTest test = new UserRegisterTest();
                test.doRegistry();
            }
        }

    @After
    public void finalize() {
        driver.quit();
    }
}
