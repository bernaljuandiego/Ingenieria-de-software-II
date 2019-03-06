package co.edu.konradlorenz.excolnet.Entities;

import java.util.List;

/*
Entity for manage Comments
Author: Leonardo Ruiz
 */
public class Comentario {

    //User owner of comment
    private Usuario Usuario;

    //Body of comment
    private String textComment;


    public Comentario() {

    }

    public Comentario(Usuario usuario, String comentario) {
        this.Usuario = usuario;
        this.textComment = comentario;

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

}
