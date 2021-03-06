package co.edu.konradlorenz.excolnet.Entities;

/*
Entity for manage Ubications  (Optional Class)
Author: Leonardo Ruiz
 */
public class Ubicacion {

    //The institution property of ubication
    private Institucion institucion;

    //Latitude
    private Long latitud;

    //Longitude
    private Long longitud;

    //Precisition
    private Long precision;

    //PlaceId (Provided by google Maps API)
    private Long placeId;


    public Ubicacion() {

    }

    public Ubicacion(Institucion institucion, Long latitud, Long longitud, Long precision, Long placeId) {
        this.institucion = institucion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.precision = precision;
        this.placeId = placeId;
    }

    /*
    GETTERS & SETTERS
     */

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    public Long getLatitud() {
        return latitud;
    }

    public void setLatitud(Long latitud) {
        this.latitud = latitud;
    }

    public Long getLongitud() {
        return longitud;
    }

    public void setLongitud(Long longitud) {
        this.longitud = longitud;
    }

    public Long getPrecision() {
        return precision;
    }

    public void setPrecision(Long precision) {
        this.precision = precision;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }
}
