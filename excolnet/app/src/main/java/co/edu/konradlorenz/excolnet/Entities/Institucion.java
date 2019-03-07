package co.edu.konradlorenz.excolnet.Entities;

/*
Entity for manage Institutions
Author: Leonardo Ruiz
   Modificado por Enrique Suarez y Bryan Pinzon :6/03/2019
 */
public class Institucion {
    //Institution Identifier
    private Long codigoInstitucion;

    //Institution name
    private String nombreInstitucion;

    //Institution direction
    private String direccion;

    //For ubicate in a map(Optional)
    private Ubicacion ubicacion;

    public Institucion() {

    }

    public Institucion(long codInstitucion, String nombre, String direccion, Ubicacion ubicacion) {
        this.codigoInstitucion = codInstitucion;
        this.nombreInstitucion = nombre;
        this.direccion = direccion;
        this.ubicacion = ubicacion;
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
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
}
