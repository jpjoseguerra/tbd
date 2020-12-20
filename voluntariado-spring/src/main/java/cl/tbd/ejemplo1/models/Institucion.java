package cl.tbd.ejemplo1.models;

public class Institucion {
    private long id;
    private String nombre;
    private String descrip;

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getDescrip(){
        return descrip;
    }

    public void setDescrip(String descrip){
        this.descrip = descrip;
    }    
}
