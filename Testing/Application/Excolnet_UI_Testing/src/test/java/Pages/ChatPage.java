package Pages;

import com.github.javafaker.Faker;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.util.List;
import java.util.Random;

public class ChatPage {

    private AndroidDriver pageDriver;

    private MobileElement chat;

    private Faker faker;

    private MobileElement message_input;
    private MobileElement imagenChat;

    private MobileElement enviarMensaje;

    private List<MobileElement> images;


    public ChatPage(AndroidDriver pageDriver) {
        this.pageDriver = pageDriver;
        this.faker = Faker.instance();
        initComponents();
    }

    public void initComponents(){
        this.chat = (MobileElement) pageDriver.findElementById("co.edu.konradlorenz.excolnet:id/friend_name");
    }

    public void escribirChat(){
        message_input = (MobileElement) pageDriver.findElementById("message_input");
            this.message_input.sendKeys(faker.random().toString());
    }

    public void clickChat() {
        this.chat.click();
    }

    public void enviarMensaje(){
        enviarMensaje = (MobileElement) pageDriver.findElementById("sendMessage_button");
        this.enviarMensaje.click();
    }


    public void enviarImagen(){
        imagenChat = (MobileElement) pageDriver.findElementById("galleryChatImage");
        this.imagenChat.click();
    }



    public void getRandomImage() {
        this.images = (List<MobileElement>) pageDriver.findElementsById("com.android.documentsui:id/icon_thumb");
        System.out.println("images size : " + images.size());
        Random random = new Random();
        this.images.get(random.nextInt(this.images.size())).click();
    }



}
