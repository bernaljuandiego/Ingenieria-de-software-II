package co.edu.konradlorenz.excolnet.Entities;

import java.util.List;

/*Entity for manage Groups
   Author : Leonardo Ruiz
 */
public class Grupo {

    //Group identifier
    private long CodigoGrupo;

    // Group name
    private String nombreGrupo;

    // Group interest`s
    private List<Interes> Intereses;

    //Group users
    private List<Usuario> Integrantes;


    public Grupo() {

    }

    public Grupo(Long codGrupo, String nombreGrupo, List<Interes> intereses, List<Usuario> integrantes) {
        this.CodigoGrupo = codGrupo;
        this.nombreGrupo = nombreGrupo;
        this.Intereses = intereses;
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

    public List<Interes> getIntereses() {
        return Intereses;
    }

    public void setIntereses(List<Interes> intereses) {
        Intereses = intereses;
    }

    public List<Usuario> getIntegrantes() {
        return Integrantes;
    }

    public void setIntegrantes(List<Usuario> integrantes) {
        Integrantes = integrantes;
    }
}
