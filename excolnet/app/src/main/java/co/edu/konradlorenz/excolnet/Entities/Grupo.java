package co.edu.konradlorenz.excolnet.Entities;

import java.util.List;

/*Entity for manage Groups
   Author : Leonardo Ruiz
   Modificado por Enrique Suarez y Bryan Pinzon :6/03/2019
 */
public class Grupo {

    //Group identifier
    private long CodigoGrupo;

    // Group name
    private String nombreGrupo;


    //Group users
    private List<Usuario> Integrantes;

    private List<Publicacion> publicaciones;

    public Grupo() {

    }

    public Grupo(Long codGrupo, String nombreGrupo, List<Usuario> integrantes, List<Publicacion> publicaciones) {
        this.CodigoGrupo = codGrupo;
        this.nombreGrupo = nombreGrupo;
        this.publicaciones = publicaciones;
        this.Integrantes = integrantes;
    }

    /*
    GETTERS  & SETTERS
     */
    public long getCodigoGrupo() {
        return CodigoGrupo;
    }

    public void setCodigoGrupo(long codigoGrupo) {
        CodigoGrupo = codigoGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public List<Usuario> getIntegrantes() {
        return Integrantes;
    }

    public void setIntegrantes(List<Usuario> integrantes) {
        Integrantes = integrantes;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

}
