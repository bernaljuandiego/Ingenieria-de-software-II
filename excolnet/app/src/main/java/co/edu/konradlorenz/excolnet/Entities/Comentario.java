package co.edu.konradlorenz.excolnet.Entities;

import java.util.List;

/*
Entity for manage Comments
Author: Leonardo Ruiz
Modificado por Enrique Suarez:6/03/2019
 */
public class Comentario {

    //User owner of comment
    private Usuario Usuario;

    //Body of comment
    private String textComment;
        // Date to post
        private String fechaComentario;

    public Comentario() {

    }

    public Comentario(Usuario usuario, String comentario, String fechaComentario) {
        this.Usuario = usuario;
        this.textComment = comentario;
        this.fechaComentario = fechaComentario;

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

    public String getTextComment() {
        return textComment;
    }

    public void setTextComment(String textComment) {
        this.textComment = textComment;
    }
    public void setFechaComentario( String fechaComentario){
        this.fechaComentario= fechaComentario;
    }
    public String getFechaComentario(){
        return fechaComentario;
    }
}
