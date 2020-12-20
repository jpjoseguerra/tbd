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

import cl.tbd.ejemplo1.models.Tarea_habilidad;
import cl.tbd.ejemplo1.repositories.Tarea_habilidadRepository;

@CrossOrigin
@RestController
public class Tarea_habilidadService {

    private final Tarea_habilidadRepository tarea_habilidadRepository;

    Tarea_habilidadService(Tarea_habilidadRepository tarea_habilidadRepository){
        this.tarea_habilidadRepository = tarea_habilidadRepository;
    }

    @GetMapping("/tarea_habilidades")
    public List<Tarea_habilidad> getAllTarea_habilidades() {
        return tarea_habilidadRepository.getAllTarea_habilidades();
    }

    @PostMapping("/tarea_habilidades")
    @ResponseBody
    public Tarea_habilidad createTarea_habilidad(@RequestBody Tarea_habilidad tarea_habilidad){
        Tarea_habilidad result = tarea_habilidadRepository.createTarea_habilidad(tarea_habilidad);
        return result;
    }   
    
    @PutMapping("/tarea_habilidades/{id}")
    @ResponseBody
    public Tarea_habilidad updateRanking(@RequestBody Tarea_habilidad tarea_habilidad, @PathVariable long id){
        Tarea_habilidad result = tarea_habilidadRepository.updateTarea_habilidad(tarea_habilidad, id);
        return result;
    }

    @DeleteMapping("/tarea_habilidades/{id}")
    public List<Tarea_habilidad> deleteTarea_habilidad(@PathVariable long id){
        return tarea_habilidadRepository.deleteTarea_habilidad(id);
    }
}
