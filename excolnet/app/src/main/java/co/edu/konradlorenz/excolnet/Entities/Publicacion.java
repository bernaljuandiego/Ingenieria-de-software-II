package co.edu.konradlorenz.excolnet.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Publicacion implements Serializable {

    private String id;
    // User owner of publication
    private Usuario usuario;

    // Text description of the publication
    private String texto;

    // Date to post
    private String fechaPublicacion;

    // Comments list
    private ArrayList<Comentario> comentarios;

    //List of interactions of publication
    private List<Usuario> usuariosQueGustan;

    //Image of publication
    private String imagen;

    public Publicacion() {
        this.comentarios = new ArrayList<>();
        this.usuariosQueGustan = new ArrayList<>();
    }

    public Publicacion(String id, Usuario usuario, String texto, String fechaPublicacion, String imagen) {
        this.comentarios = new ArrayList<>();
        this.usuariosQueGustan = new ArrayList<>();
        this.id = id;
        this.usuario = usuario;
        this.texto = texto;
        this.fechaPublicacion = fechaPublicacion;
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getUsuario() {
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

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Usuario> getUsuariosQueGustan() {
        return usuariosQueGustan;
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
}
