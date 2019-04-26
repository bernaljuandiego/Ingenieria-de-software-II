package Pages;

import java.util.List;
import java.util.Random;
import com.github.javafaker.Faker;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class UserRegisterPage {

    private AndroidDriver pageDriver;

    private MobileElement userNameField;

    private MobileElement userLastNameField;

    private MobileElement emailField;

    private MobileElement passwordField;

    private MobileElement imageButton;

    private MobileElement signInButton;

    private String snackBarReference;

    private Faker faker;

    private List<MobileElement> images;


    public UserRegisterPage(AndroidDriver androidDriver) {
        this.pageDriver = androidDriver;
        this.faker = Faker.instance();
        initComponents();
    }


    public void initComponents() {
        this.userNameField = (MobileElement) pageDriver.findElementById("co.edu.konradlorenz.excolnet:id/username_text_input");
        this.userLastNameField = (MobileElement) pageDriver.findElementById("co.edu.konradlorenz.excolnet:id/lastname_text_input");
        this.emailField = (MobileElement) pageDriver.findElementById("co.edu.konradlorenz.excolnet:id/email_text_input");
        this.passwordField = (MobileElement) pageDriver.findElementById("co.edu.konradlorenz.excolnet:id/password_text_input");
        this.signInButton = (MobileElement) pageDriver.findElementById("co.edu.konradlorenz.excolnet:id/sign_in_button");
        this.imageButton = (MobileElement) pageDriver.findElementById("co.edu.konradlorenz.excolnet:id/select_image_button");
        this.snackBarReference = "co.edu.konradlorenz.excolnet:id/snackbar_text";
    }


    public MobileElement getUserNameField() {
        return userNameField;
    }

    public void typeuserNameField() {
        this.userNameField.sendKeys(faker.name().firstName());
    }

    public MobileElement getUserLastNameField() {
        return userLastNameField;
    }

    public void typeUserLastNameField() {
        this.userLastNameField.sendKeys(faker.name().lastName());
    }

    public MobileElement getEmailField() {
        return emailField;
    }

    public void typeEmailField() {
        this.emailField.sendKeys(faker.internet().emailAddress());
    }

    public MobileElement getPasswordField() {
        return passwordField;
    }

    public void typePasswordField() {
        this.passwordField.sendKeys(faker.internet().password(8, 12, true, true));
    }

    public MobileElement getImageButton() {
        return imageButton;
    }

    public void clickImageButton() {
        this.imageButton.click();
    }

    public MobileElement getSignInButton() {
        return signInButton;
    }

    public void clickSignInButton() {
        this.signInButton.click();
    }


    public String getSnackBarReference() {
        return snackBarReference;
    }

    public void setSnackBarReference(String snackBarReference) {
        this.snackBarReference = snackBarReference;
    }

    public List<MobileElement> getImages() {
        return images;
    }

    public void getRandomImage() {
        this.images = (List<MobileElement>) pageDriver.findElementsById("com.android.documentsui:id/icon_thumb");
        System.out.println("images size : " + images.size());
        Random random = new Random();
        this.images.get(random.nextInt(this.images.size())).click();
    }

}
