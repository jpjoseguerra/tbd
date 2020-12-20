package cl.tbd.ejemplo1.models;

public class Eme_habilidad {
    public long id;
    public long id_emergencia;
    public long id_habilidad;

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId_emergencia(){
        return id_emergencia;
    }

    public void setId_emergencia(long id_emergencia){
        this.id_emergencia = id_emergencia;
    }

    public long getId_habilidad(){
        return id_habilidad;
    }

    public void setId_habilidad(long id_habilidad){
        this.id_habilidad = id_habilidad;
    }

    
}
