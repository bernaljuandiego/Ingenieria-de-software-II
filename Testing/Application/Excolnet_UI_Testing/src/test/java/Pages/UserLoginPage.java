package Pages;


import Drivers.DriverSetUp;
import com.github.javafaker.Faker;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class UserLoginPage {


    private AndroidDriver pageDriver;

    private MobileElement emailField;

    private MobileElement passwordField;

    private MobileElement logInButton;

    private MobileElement signUpLabel;


    private Faker faker;


    public UserLoginPage(AndroidDriver androidDriver) {
        this.pageDriver = androidDriver;
        this.faker = Faker.instance();
        initComponents();
    }

    public void initComponents() {
        this.emailField = (MobileElement) pageDriver.findElementById("co.edu.konradlorenz.excolnet:id/email");
        this.passwordField = (MobileElement) pageDriver.findElementById("co.edu.konradlorenz.excolnet:id/password");
        this.logInButton = (MobileElement) pageDriver.findElementById("co.edu.konradlorenz.excolnet:id/email_sign_in_button");
        this.signUpLabel = (MobileElement) pageDriver.findElementById("co.edu.konradlorenz.excolnet:id/sign_up_button");
    }


    public MobileElement getEmailField() {
        return emailField;
    }

    public void typeEmailField() {
        this.emailField.sendKeys(faker.internet().safeEmailAddress());
    }

    public MobileElement getPasswordField() {
        return passwordField;
    }

    public void typePasswordField() {
        this.passwordField.sendKeys(faker.internet().password(6, 10, true, true));
    }

    public MobileElement getLogInButton() {
        return logInButton;
    }

    public void clickLogInButton() {
        this.logInButton.click();
    }

    public MobileElement getSignUpLabel() {
        return signUpLabel;
    }

    public void clickSignUp() {
        this.signUpLabel.click();
    }


}
