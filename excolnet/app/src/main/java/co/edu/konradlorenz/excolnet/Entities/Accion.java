package co.edu.konradlorenz.excolnet.Entities;

/*
Entity for manage interactions in publications , comments
Author: Leonardo Ruiz
 */
public class Accion {
    //User Owner of the interaction
    private Usuario Usuario;

    //If interaction is like
    private boolean isLike;


    public Accion() {

    }

    public Accion(Usuario usuario, boolean Like) {
        this.Usuario = usuario;
        this.isLike = Like;
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

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

}
