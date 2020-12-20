package cl.tbd.ejemplo1.services;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.tbd.ejemplo1.models.Estado_tarea;
import cl.tbd.ejemplo1.repositories.Estado_tareaRepository;

@CrossOrigin
@RestController
public class Estado_tareaService {
    
    private final Estado_tareaRepository estado_tareaRepository;
    
    Estado_tareaService(Estado_tareaRepository estado_tareaRepository){
        this.estado_tareaRepository = estado_tareaRepository;
    }

    @GetMapping("/estado_tareas")
    public List<Estado_tarea> getAllEstado_tareas() {
        return estado_tareaRepository.getAllEstado_tareas();
    }

    @PostMapping("/estado_tareas")
    @ResponseBody
    public Estado_tarea createEstado_tarea(@RequestBody Estado_tarea estado_tarea){
        Estado_tarea result = estado_tareaRepository.createEstado_tarea(estado_tarea);
        return result;
    }

    @PutMapping("/estado_tareas/{id}")
    @ResponseBody
    public Estado_tarea updateEstado_tarea(@RequestBody Estado_tarea estado_tarea, @PathVariable long id){
        Estado_tarea result = estado_tareaRepository.updateEstado_tarea(estado_tarea, id);
        return result;
    }

    @DeleteMapping("/estado_tareas/{id}")
    public List<Estado_tarea> deleteEstado_tarea(@PathVariable long id){
        return estado_tareaRepository.deleteEstado_tarea(id);
    }
    

}
