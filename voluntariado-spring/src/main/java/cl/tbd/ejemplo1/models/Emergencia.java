package cl.tbd.ejemplo1.models;

import java.sql.Date;

public class Emergencia {
    private long id;
    private String nombre;
    private String descrip;
    private Date finicio;
    private Date ffin;
    private long id_institucion;

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

    public Date getFinicio(){
        return finicio;
    }

    public void setFinicio(Date finicio){
        this.finicio = finicio;
    }

    public Date getFfin(){
        return ffin;
    }

    public void setFfin(Date ffin){
        this.ffin = ffin;
    }

    public long getId_institucion(){
        return id_institucion;
    }

    public void setId_institucion(long id_institucion){
        this.id_institucion = id_institucion;
    }
}
