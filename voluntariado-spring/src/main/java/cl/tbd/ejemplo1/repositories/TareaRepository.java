package cl.tbd.ejemplo1.repositories;

import java.util.List;

import cl.tbd.ejemplo1.models.Tarea;

public interface TareaRepository {
    public List<Tarea> getAllTareas();
    public Tarea createTarea(Tarea tarea);
    public Tarea updateTarea(Tarea tarea, long id);
    public List<Tarea> deleteTarea(long id);
    
}
