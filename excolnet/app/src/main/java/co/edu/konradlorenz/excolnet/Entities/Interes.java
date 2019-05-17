package co.edu.konradlorenz.excolnet.Entities;

/*
Entity for manage Interest`s
Author: Leonardo Ruiz
Modificado por Enrique Suarez y Bryan Pinzon: 6/03/2019
 */
public class Interes {


    private String topicName;

    private String Description;

    private String PhotoUrl;

    private String topicType;

    private Double latitude;

    private Double longitude;


    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getPhotoUrl() {
        return PhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.PhotoUrl = photoUrl;
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Interes{" +
                "topicName='" + topicName + '\'' +
                ", Description='" + Description + '\'' +
                ", PhotoUrl='" + PhotoUrl + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
