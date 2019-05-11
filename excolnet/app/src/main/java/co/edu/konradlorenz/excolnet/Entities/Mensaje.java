package co.edu.konradlorenz.excolnet.Entities;

public class Mensaje {

    private String SenderUID;

    private String DestinyUUID;

    private String senderDisplayName;

    private String senderImage;

    private String senderTime;

    private String message;

    private String photoUrl;

    private String message_type;

    public Mensaje() {

    }

    //Simple message
    public Mensaje(String senderUID, String destinyUUID, String senderDisplayName, String senderImage, String senderTime, String message, String message_type) {
        SenderUID = senderUID;
        DestinyUUID = destinyUUID;
        this.senderDisplayName = senderDisplayName;
        this.senderImage = senderImage;
        this.senderTime = senderTime;
        this.message = message;
        this.message_type = message_type;
    }

    //Image message
    public Mensaje(String senderUID, String destinyUUID, String senderDisplayName, String senderImage, String senderTime, String message, String photoUrl, String message_type) {
        SenderUID = senderUID;
        DestinyUUID = destinyUUID;
        this.senderDisplayName = senderDisplayName;
        this.senderImage = senderImage;
        this.senderTime = senderTime;
        this.message = message;
        this.photoUrl = photoUrl;
        this.message_type = message_type;
    }

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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }
}
