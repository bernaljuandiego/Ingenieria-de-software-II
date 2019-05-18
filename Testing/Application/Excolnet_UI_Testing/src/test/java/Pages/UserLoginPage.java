package Pages;

import com.github.javafaker.Faker;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;

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

    public void clickSignUp() {
        this.signUpLabel = (MobileElement) pageDriver.findElementById("co.edu.konradlorenz.excolnet:id/sign_up_button");
        this.signUpLabel.click();
    }

}
