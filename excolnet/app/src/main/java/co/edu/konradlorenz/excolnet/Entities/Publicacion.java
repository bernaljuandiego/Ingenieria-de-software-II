package co.edu.konradlorenz.excolnet.Entities;

import java.util.List;

/*
Entity for manage Publications
Author: Leonardo Ruiz
 */
public class Publicacion {

    // User owner of publication
    private Usuario Usuario;

    // Text description of the publication
    private String Texto;

    // Comments list
    private List<Comentario> Comentarios;

    //List of interactions of publication
    private List<Accion> Interacciones;

    //Image of publication (Optional)
    private String Imagen;

    public Publicacion() {

    }

    public Publicacion(Usuario usuario, String textPub, List<Comentario> comentarios, String imagen, List<Accion> interacciones) {
        this.Usuario = usuario;
        this.Texto = textPub;
        this.Comentarios = comentarios;
        this.Interacciones = interacciones;
        this.Imagen = imagen;

    }
    /*
    GETTERS & SETTERS
     */

    public co.edu.konradlorenz.excolnet.Entities.Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(co.edu.konradlorenz.excolnet.Entities.Usuario usuario) {
        Usuario = usuario;
    }

    public String getTexto() {
        return Texto;
    }

    public void setTexto(String texto) {
        Texto = texto;
    }

    public List<Comentario> getComentarios() {
        return Comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        Comentarios = comentarios;
    }

    public List<Accion> getInteracciones() {
        return Interacciones;
    }

    public void setInteracciones(List<Accion> interacciones) {
        Interacciones = interacciones;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }
}
