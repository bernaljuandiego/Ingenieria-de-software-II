package co.edu.konradlorenz.excolnet.Entities;

public class Agencia {

    private String nombre ;
    private String PhotoLogo;
    private String telefono;


    public Agencia() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPhotoLogo() {
        return PhotoLogo;
    }

    public void setPhotoLogo(String photoLogo) {
        PhotoLogo = photoLogo;
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }



}
