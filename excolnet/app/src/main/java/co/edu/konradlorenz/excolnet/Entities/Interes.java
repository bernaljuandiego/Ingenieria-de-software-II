package co.edu.konradlorenz.excolnet.Entities;

/*
Entity for manage Interest`s
Author: Leonardo Ruiz
 */
public class Interes {

    //Interest Identifier
    private Long codigoInteres;

    //Interest name;
    private String nombreInteres;

    //Short Interest Description
    private String Description;

    public Interes() {

    }

    public Interes(Long codInteres, String nombreInteres, String desc) {
        this.codigoInteres = codInteres;
        this.nombreInteres = nombreInteres;
        this.Description = desc;
    }

    /*
    GETTERS & SETTERS
     */
    public Long getCodigoInteres() {
        return codigoInteres;
    }

    public void setCodigoInteres(Long codigoInteres) {
        this.codigoInteres = codigoInteres;
    }

    public String getNombreInteres() {
        return nombreInteres;
    }

    public void setNombreInteres(String nombreInteres) {
        this.nombreInteres = nombreInteres;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
