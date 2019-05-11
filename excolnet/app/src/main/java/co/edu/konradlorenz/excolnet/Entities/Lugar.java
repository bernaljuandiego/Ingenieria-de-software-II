package co.edu.konradlorenz.excolnet.Entities;

import java.io.Serializable;

public class Lugar implements Serializable {

    private String id;

    private String titulo;

    private String descripcion;

    private double latitud;
    private double longitud;

    private String imagen_lugar;

    public Lugar() {
    }

    public Lugar(String id, String titulo, String descripcion, String imagen_lugar, double latitud, double longitud) {

        this.titulo = titulo;
        this.descripcion = descripcion;
        this.id = id;
        this.imagen_lugar = imagen_lugar;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen_lugar() {
        return imagen_lugar;
    }

    public void setImagen_lugar(String imagen_lugar) {
        this.imagen_lugar = imagen_lugar;
    }
}
