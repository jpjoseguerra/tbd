package cl.tbd.ejemplo1.models;

public class Vol_habilidad {

    public long id;
    public long id_voluntario;
    public long id_habilidad;

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId_voluntario(){
        return id_voluntario;
    }

    public void setId_voluntario(long id_voluntario){
        this.id_voluntario = id_voluntario;
    }

    public long getId_habilidad(){
        return id_habilidad;
    }

    public void setId_habilidad(long id_habilidad){
        this.id_habilidad = id_habilidad;
    }     
}
