package co.edu.konradlorenz.excolnet.Entities;

import java.util.List;

/*
Entity for manage Publications
Author: Leonardo Ruiz
Modificado por Enrique Suarez:6/03/2019
 */
public class Publicacion {

    // User owner of publication
    private Usuario usuario;

    // Text description of the publication
    private String texto;

    // Date to post
    private String fechaPublicacion;

    // Comments list
    private List<Comentario> comentarios;

    //List of interactions of publication
    private List<Accion> interacciones;

    //Image of publication
    private String imagen;

    public Publicacion() {

    }

    public Publicacion(Usuario usuario, String textPub, List<Comentario> comentarios, String imagen, List<Accion> interacciones, String fechaPublicacion) {
        this.usuario = usuario;
        this.texto = textPub;
        this.comentarios = comentarios;
        this.interacciones = interacciones;
        this.imagen = imagen;
        this.fechaPublicacion = fechaPublicacion;
    }
    /*
    GETTERS & SETTERS
     */

    public co.edu.konradlorenz.excolnet.Entities.Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(co.edu.konradlorenz.excolnet.Entities.Usuario usuario) {
        usuario = usuario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        texto = texto;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        comentarios = comentarios;
    }

    public List<Accion> getInteracciones() {
        return interacciones;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getFechaPublicacion() {
        return this.fechaPublicacion;
    }

    public void setInteracciones(List<Accion> interacciones) {
        interacciones = interacciones;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        imagen = imagen;
    }
}
