package TestCases;

import Commons.DriverCommons;
import Drivers.DriverSetUp;
import Pages.ChatPage;
import Pages.UserLoginPage;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChatTest {
    private AndroidDriver driver;
    private DriverCommons driverCommons;
    private ChatPage chatPage;

    @Before
    public void initComponents() {
        this.driver = DriverSetUp.initDriver();
        this.driverCommons = new DriverCommons(this.driver);
    }


    @Test
    public void initLogin() {

        driverCommons.addImplicitlyWait(10);

        chatPage = new ChatPage(driver);

        chatPage.clickChat();
        driverCommons.addImplicitlyWait(2);
        for (int i=1; i<10;i++){
            chatPage.escribirChat();
            chatPage.enviarMensaje();
            chatPage.enviarImagen();
            driverCommons.addImplicitlyWait(5);
            chatPage.getRandomImage();
        }


    }

    @After
    public void finalize() {
        driver.quit();
    }
}
