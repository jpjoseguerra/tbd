package cl.tbd.ejemplo1.models;

public class Voluntario {
    private long id;
    private String nombre;
    private String email;
    private double longitud;
    private double latitud; 

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public double getLongitud(){
        return longitud;
    }

    public void setLongitud(double longitud){
        this.longitud = longitud;
    }

    public double getLatitud(){
        return latitud;
    }

    public void setLatitud(double latitud){
        this.latitud = latitud;
    }
}
