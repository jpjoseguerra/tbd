package cl.tbd.ejemplo1.models;

public class Tarea_habilidad {

    public long id;
    public long id_emehab;
    public long id_tarea;

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }
    
    public long getId_emehab(){
        return id_emehab;
    }

    public void setId_emehab(long id_emehab){
        this.id_emehab = id_emehab;
    }

    public long getId_tarea(){
        return id_tarea;
    }

    public void setId_tarea(long id_tarea){
        this.id_tarea = id_tarea;
    }


}
