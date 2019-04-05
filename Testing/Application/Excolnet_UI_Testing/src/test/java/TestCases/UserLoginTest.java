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

        this.driver = DriverSetUp.initDriver("8350ca1a");
        this.driverCommons = new DriverCommons(this.driver);


    }


    @Test
    public void initLogin() {

        try {


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
                userLoginPage.clickSignUp();
                driverCommons.addImplicitlyWait(5);
                UserRegisterTest test = new UserRegisterTest(this.driver, this.driverCommons);
                test.doRegistry();

            }

        } catch (Exception ex) {
            System.out.println("UserLoginTest.initLogin exception : " + ex.getMessage());
        }


    }

    @After
    public void finalize() {
        driver.quit();
    }
}
