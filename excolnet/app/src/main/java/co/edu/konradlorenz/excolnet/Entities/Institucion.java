package co.edu.konradlorenz.excolnet.Entities;

/*
Entity for manage Institutions
Author: Leonardo Ruiz
 */
public class Institucion {
    //Institution Identifier
    private Long codigoInstitucion;

    //Institution name
    private String nombreInstitucion;

    //Institution direction
    private String Direccion;

    //For ubicate in a map(Optional)
    private Ubicacion Ubicacion;

    public Institucion() {

    }

    public Institucion(long codInstitucion, String nombre, String direccion, Ubicacion ubicacion) {
        this.codigoInstitucion = codInstitucion;
        this.nombreInstitucion = nombre;
        this.Direccion = direccion;
        this.Ubicacion = ubicacion;
    }

    public Long getCodigoInstitucion() {
        return codigoInstitucion;
    }

    public void setCodigoInstitucion(Long codigoInstitucion) {
        this.codigoInstitucion = codigoInstitucion;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public Ubicacion getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        Ubicacion = ubicacion;
    }
}
