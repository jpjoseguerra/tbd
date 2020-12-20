package cl.tbd.ejemplo1.repositories;

import java.util.List;

import cl.tbd.ejemplo1.models.Tarea_habilidad;

public interface Tarea_habilidadRepository {

    public List<Tarea_habilidad> getAllTarea_habilidades();
    public Tarea_habilidad createTarea_habilidad(Tarea_habilidad tarea_habilidad);
    public Tarea_habilidad updateTarea_habilidad(Tarea_habilidad tarea_habilidad, long id);
    public List<Tarea_habilidad> deleteTarea_habilidad(long id);
}
