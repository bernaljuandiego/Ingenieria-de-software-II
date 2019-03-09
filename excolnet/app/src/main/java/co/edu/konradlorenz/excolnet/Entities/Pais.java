package co.edu.konradlorenz.excolnet.Entities;

/*
Entity For manage Countries
Author: Leonardo Ruiz
 */
public class Pais {
    //Country Identifier
    private Long codigo_pais;

    //Country name
    private String nombre_Pais;

    public Pais() {

    }

    public Pais(Long codPais, String nomPais) {
        this.codigo_pais = codPais;
        this.nombre_Pais = nomPais;
    }
    /*
    GETTERS & SETTERS
     */

    public String getNombre_Pais() {
        return nombre_Pais;
    }

    public void setNombre_Pais(String nombre_Pais) {
        this.nombre_Pais = nombre_Pais;
    }

    public Long getCodigo_pais() {
        return codigo_pais;
    }

    public void setCodigo_pais(Long codigo_pais) {
        this.codigo_pais = codigo_pais;
    }
}
