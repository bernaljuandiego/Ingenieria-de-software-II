package co.edu.konradlorenz.excolnet.Entities;

import java.util.List;

/*
Entity for manage Interest`s
Author: Leonardo Ruiz
Modificado por Enrique Suarez y Bryan Pinzon: 6/03/2019
 */
public class Interes {

    //Interest Identifier
    private Long codigoInteres;

    //Interest name;
    private String nombreInteres;

    //Short Interest Description
    private String description;
    /*
    private String imagenInteres;

    private List<Usuario> listaUsuariosInteresados;
    */
    public Interes() {

    }

    public Interes(Long codInteres, String nombreInteres, String desc, String imagenInteres, List<Usuario> listaUsuariosInteresados) {
        this.codigoInteres = codInteres;
        this.nombreInteres = nombreInteres;
        this.description = desc;
        /*
        this.imagenInteres=imagenInteres;
        this.listaUsuariosInteresados=listaUsuariosInteresados;
        */
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
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    /*
    public void setImagenInteres( String imagenInteres){
        this.imagenInteres= imagenInteres;
    }

    public String getimagenInteres(){
        return this.imagenInteres;
    }

    public void setlistaUsuariosInteresados(List<Usuario> listaUsuariosInteresados){
        this.listaUsuariosInteresados=listaUsuariosInteresados;
    }
    public List<Usuario> getListaUsuariosInteresados(){
        return this.listaUsuariosInteresados;
    }
    */

}   
