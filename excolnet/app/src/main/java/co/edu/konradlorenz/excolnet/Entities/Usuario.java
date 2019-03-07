package co.edu.konradlorenz.excolnet.Entities;


import java.util.List;

/*
Entity for manage Users
Author: Leonardo Ruiz
 */
public class Usuario {
    //User identifier
    private long codigo;

    //User real name
    private String nombre;

    //Country of the user
    private Pais pais;

    //Friends List
    private List<Usuario> amigos;

    //Groups List
    private List<Grupo> grupos;

    //Interest`s List
    private List<Interes> intereses;

    //Institution of exchange
    private Institucion institucionIntercambio;

    //Institution of user country
    private Institucion institucionInicial;

    //User publications
    private List<Publicacion> publicaciones;

    //User notifications
    private List<Notificacion> notificaciones;


    public Usuario() {
    }

    public Usuario(Long codigo, String nombre, Pais pais, List<Usuario> amigos, List<Grupo> Grupos, List<Interes> intereses, Institucion institucionIntercambio, Institucion institucionInicial, List<Publicacion> publicaciones , List<Notificacion> notifs) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.pais = pais;
        this.amigos = amigos;
        this.grupos = Grupos;
        this.intereses = intereses;
        this.institucionInicial = institucionInicial;
        this.institucionIntercambio = institucionIntercambio;
        this.publicaciones = publicaciones;
        this.notificaciones = notifs;
    }

    /*
    GETTERS & SETTERS
     */
    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Usuario> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<Usuario> amigos) {
        this.amigos = amigos;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public List<Interes> getIntereses() {
        return intereses;
    }

    public void setIntereses(List<Interes> intereses) {
        this.intereses = intereses;
    }

    public Institucion getInstitucionIntercambio() {
        return institucionIntercambio;
    }

    public void setInstitucionIntercambio(Institucion institucionIntercambio) {
        this.institucionIntercambio = institucionIntercambio;
    }

    public Institucion getInstitucionInicial() {
        return institucionInicial;
    }

    public void setInstitucionInicial(Institucion institucionInicial) {
        this.institucionInicial = institucionInicial;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }
}
