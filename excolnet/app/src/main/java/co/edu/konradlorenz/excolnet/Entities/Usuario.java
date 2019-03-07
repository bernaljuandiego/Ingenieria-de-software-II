package co.edu.konradlorenz.excolnet.Entities;


import java.util.List;

/*
Entity for manage Users
Author: Leonardo Ruiz
 */
public class Usuario {
    //User identifier
    private long Codigo;

    //User real name
    private String nombre;

    //Country of the user
    private Pais pais;

    //Friends List
    private List<Usuario> Amigos;

    //Groups List
    private List<Grupo> Grupos;

    //Interest`s List
    private List<Interes> Intereses;

    //Institution of exchange
    private Institucion institucionIntercambio;

    //Institution of user country
    private Institucion institucionInicial;

    //User publications
    private List<Publicacion> Publicaciones;

    //User notifications
    private List<Notificacion> Notificaciones;


    public Usuario() {
    }

    public Usuario(Long codigo, String nombre, Pais pais, List<Usuario> amigos, List<Grupo> Grupos, List<Interes> Intereses, Institucion institucionIntercambio, Institucion institucionInicial, List<Publicacion> publicaciones , List<Notificacion> notifs) {
        this.Codigo = codigo;
        this.nombre = nombre;
        this.pais = pais;
        this.Amigos = amigos;
        this.Grupos = Grupos;
        this.Intereses = Intereses;
        this.institucionInicial = institucionInicial;
        this.institucionIntercambio = institucionIntercambio;
        this.Publicaciones = publicaciones;
        this.Notificaciones = notifs;
    }

    /*
    GETTERS & SETTERS
     */
    public long getCodigo() {
        return Codigo;
    }

    public void setCodigo(long codigo) {
        Codigo = codigo;
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
        return Amigos;
    }

    public void setAmigos(List<Usuario> amigos) {
        Amigos = amigos;
    }

    public List<Grupo> getGrupos() {
        return Grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        Grupos = grupos;
    }

    public List<Interes> getIntereses() {
        return Intereses;
    }

    public void setIntereses(List<Interes> intereses) {
        Intereses = intereses;
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
        return Publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        Publicaciones = publicaciones;
    }

    public List<Notificacion> getNotificaciones() {
        return Notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        Notificaciones = notificaciones;
    }
}
