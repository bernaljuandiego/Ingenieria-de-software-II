package co.edu.konradlorenz.excolnet.Entities;

import java.util.List;

/*Entity for manage Groups
   Author : Leonardo Ruiz
   Modificado por Enrique Suarez y Bryan Pinzon :6/03/2019
 */
public class Grupo {

    //Group identifier
    private long codigoGrupo;

    // Group name
    private String nombreGrupo;


    //Group users
    private List<Usuario> integrantes;
    //Group publications
    private List<Publicacion> publicaciones;

    public Grupo() {

    }

    public Grupo(Long codGrupo, String nombreGrupo, List<Usuario> integrantes, List<Publicacion> publicaciones) {
        this.codigoGrupo = codGrupo;
        this.nombreGrupo = nombreGrupo;
        this.publicaciones = publicaciones;
        this.integrantes = integrantes;
    }

    /*
    GETTERS  & SETTERS
     */
    public long getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(long codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public List<Usuario> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<Usuario> integrantes) {
        this.integrantes = integrantes;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

}
