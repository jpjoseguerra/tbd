package cl.tbd.ejemplo1.models;

import java.sql.Date;

public class Voluntario {
    private long id;
    private String nombre;
    private Date fnacimiento;

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

    public Date getFnacimiento(){
        return fnacimiento;
    }

    public void setFnacimiento(Date fnacimiento){
        this.fnacimiento = fnacimiento;
    }
}
