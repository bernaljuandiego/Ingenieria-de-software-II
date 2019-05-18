package co.edu.konradlorenz.excolnet.Entities;

public class Plan {
    private String agencia;
    private String nombrePlan;
    private String photoPlan;
    private String description;
    private String precio;


    public Plan() {
    }


    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public String getPhotoPlan() {
        return photoPlan;
    }

    public void setPhotoPlan(String photoPlan) {
        this.photoPlan = photoPlan;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}


