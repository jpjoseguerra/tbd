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

import cl.tbd.ejemplo1.models.Tarea;
import cl.tbd.ejemplo1.repositories.TareaRepository;

@CrossOrigin
@RestController
public class TareaService {

    private final TareaRepository tareaRepository;

    TareaService(TareaRepository tareaRepository){
        this.tareaRepository = tareaRepository;
    }

    @GetMapping("/tareas")
    public List<Tarea> getAllTareas() {
        return tareaRepository.getAllTareas();
    }

    @PostMapping("/tareas")
    @ResponseBody
    public Tarea createTarea(@RequestBody Tarea tarea){
        Tarea result = tareaRepository.createTarea(tarea);
        return result;
    }

    @PutMapping("/tareas/{id}")
    @ResponseBody
    public Tarea updateRanking(@RequestBody Tarea tarea, @PathVariable long id){
        Tarea result = tareaRepository.updateTarea(tarea, id);
        return result;
    }

    @DeleteMapping("/tareas/{id}")
    public List<Tarea> deleteTareas(@PathVariable long id){
        return tareaRepository.deleteTarea(id);
    }
    
}
