package co.edu.konradlorenz.excolnet.Entities;

import java.io.Serializable;

public class Lugar implements Serializable {

    private String id;

    private String Titulo;

    private String descripcion;

    private Ubicacion ubicacion;

    private String imagen_lugar;

    public Lugar (){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getImagen_lugar() {
        return imagen_lugar;
    }

    public void setImagen_lugar(String imagen_lugar) {
        this.imagen_lugar = imagen_lugar;
    }

    public Lugar(String id, String titulo, String descripcion, Ubicacion ubicacion, String imagen_lugar) {
        this.id = id;
        Titulo = titulo;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.imagen_lugar = imagen_lugar;
    }
}
