package co.edu.konradlorenz.excolnet.Entities;
/*
Entity for manage notifications
Author: Leonardo Ruiz
 */
public class Notificacion {

    //Entity generator of the notification (Can be an user or a group)
    private Object Generator;

    //Body of the notification
    private String textNotification;

    public Notificacion(){

    }

    public Notificacion(Object generador , String texto){
        this.Generator =  generador;
        this.textNotification = texto;
    }
    /*
    GETTERS & SETTERS
     */

    public Object getGenerator() {
        return Generator;
    }

    public void setGenerator(Object generator) {
        Generator = generator;
    }

    public String getTextNotification() {
        return textNotification;
    }

    public void setTextNotification(String textNotification) {
        this.textNotification = textNotification;
    }
}
