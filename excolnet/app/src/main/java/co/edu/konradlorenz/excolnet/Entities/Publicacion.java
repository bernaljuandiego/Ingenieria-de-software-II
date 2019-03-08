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
    private List<Usuario> usuariosQueGustan;

    //Image of publication
    private String imagen;

    public Publicacion() {

    }

<<<<<<< Updated upstream
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
=======
    public Usuario getUsuario() {
>>>>>>> Stashed changes
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

<<<<<<< Updated upstream
    public List<Accion> getInteracciones() {
        return interacciones;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getFechaPublicacion() {
        return this.fechaPublicacion;
=======
    public List<Usuario> getUsuariosQueGustan() {
        return usuariosQueGustan;
>>>>>>> Stashed changes
    }

    public void setUsuariosQueGustan(List<Usuario> usuariosQueGustan) {
        this.usuariosQueGustan = usuariosQueGustan;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Publicacion(Usuario usuario, String texto, String fechaPublicacion, List<Comentario> comentarios, List<Usuario> usuariosQueGustan, String imagen) {
        this.usuario = usuario;
        this.texto = texto;
        this.fechaPublicacion = fechaPublicacion;
        this.comentarios = comentarios;
        this.usuariosQueGustan = usuariosQueGustan;
        this.imagen = imagen;
    }
}
