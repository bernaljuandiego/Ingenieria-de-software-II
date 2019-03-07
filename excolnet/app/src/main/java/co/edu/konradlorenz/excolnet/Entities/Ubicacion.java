package co.edu.konradlorenz.excolnet.Entities;

/*
Entity for manage Ubications  (Optional Class)
Author: Leonardo Ruiz
 */
public class Ubicacion {

    //The institution property of ubication
    private Institucion Institucion;

    //Latitude
    private Long Latitud;

    //Longitude
    private Long Longitud;

    //Precisition
    private Long Precision;

    //PlaceId (Provided by google Maps API)
    private Long placeId;


    public Ubicacion() {

    }

    public Ubicacion(Institucion institucion, Long latitud, Long longitud, Long precision, Long placeId) {
        this.Institucion = institucion;
        this.Latitud = latitud;
        this.Longitud = longitud;
        this.Precision = precision;
        this.placeId = placeId;
    }

    /*
    GETTERS & SETTERS
     */

    public Institucion getInstitucion() {
        return Institucion;
    }

    public void setInstitucion(Institucion institucion) {
        Institucion = institucion;
    }

    public Long getLatitud() {
        return Latitud;
    }

    public void setLatitud(Long latitud) {
        Latitud = latitud;
    }

    public Long getLongitud() {
        return Longitud;
    }

    public void setLongitud(Long longitud) {
        Longitud = longitud;
    }

    public Long getPrecision() {
        return Precision;
    }

    public void setPrecision(Long precision) {
        Precision = precision;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }
}
