package co.edu.konradlorenz.excolnet.Entities;

import java.io.Serializable;

public class Host implements Serializable {
    private String id;

    private String nombreHost;
    private String descripcionHost;
    private String precioHost;
    private String imagenHost;

    private double longitud;
    private double latitud;

    public Host() {
    }

    public Host(String id, String nombreHost, String descripcionHost, String precioHost, String imagenHost, double longitud, double latitud) {
        this.id = id;
        this.nombreHost = nombreHost;
        this.descripcionHost = descripcionHost;
        this.precioHost = precioHost;
        this.imagenHost = imagenHost;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreHost() {
        return nombreHost;
    }

    public void setNombreHost(String nombreHost) {
        this.nombreHost = nombreHost;
    }

    public String getDescripcionHost() {
        return descripcionHost;
    }

    public void setDescripcionHost(String descripcionHost) {
        this.descripcionHost = descripcionHost;
    }

    public String getPrecioHost() {
        return precioHost;
    }

    public void setPrecioHost(String precioHost) {
        this.precioHost = precioHost;
    }

    public String getImagenHost() {
        return imagenHost;
    }

    public void setImagenHost(String imagenHost) {
        this.imagenHost = imagenHost;
    }

}
