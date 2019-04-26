package co.edu.konradlorenz.excolnet.Entities;

public class Mensaje {

    private String SenderUID;

    private  String DestinyUUID;

    private String senderDisplayName;

    private String senderImage;

    private String senderTime;

    private String message;


    public String getSenderUID() {
        return SenderUID;
    }

    public void setSenderUID(String senderUID) {
        SenderUID = senderUID;
    }

    public String getDestinyUUID() {
        return DestinyUUID;
    }

    public void setDestinyUUID(String destinyUUID) {
        DestinyUUID = destinyUUID;
    }

    public String getSenderDisplayName() {
        return senderDisplayName;
    }

    public void setSenderDisplayName(String senderDisplayName) {
        this.senderDisplayName = senderDisplayName;
    }

    public String getSenderImage() {
        return senderImage;
    }

    public void setSenderImage(String senderImage) {
        this.senderImage = senderImage;
    }

    public String getSenderTime() {
        return senderTime;
    }

    public void setSenderTime(String senderTime) {
        this.senderTime = senderTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
