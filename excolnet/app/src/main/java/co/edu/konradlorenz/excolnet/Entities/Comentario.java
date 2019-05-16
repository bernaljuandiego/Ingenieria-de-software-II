package co.edu.konradlorenz.excolnet.Entities;

/*
Entity for manage Comments
Author: Leonardo Ruiz
Modificado por Enrique Suarez:6/03/2019
 */
public class Comentario {

    //User owner of comment
    private Usuario usuario;

    //Body of comment
    private String textComment;
    // Date to post
    private String fechaComentario;

    public Comentario() {

    }

    public Comentario(Usuario usuario, String comentario, String fechaComentario) {
        this.usuario = usuario;
        this.textComment = comentario;
        this.fechaComentario = fechaComentario;

    }
    /*
    GETTERS & SETTERS
     */

    public co.edu.konradlorenz.excolnet.Entities.Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(co.edu.konradlorenz.excolnet.Entities.Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTextComment() {
        return textComment;
    }

    public void setTextComment(String textComment) {
        this.textComment = textComment;
    }

    public String getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(String fechaComentario) {
        this.fechaComentario = fechaComentario;
    }
}
