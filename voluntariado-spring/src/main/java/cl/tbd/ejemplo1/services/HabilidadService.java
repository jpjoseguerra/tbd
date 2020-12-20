package cl.tbd.ejemplo1.services;

import cl.tbd.ejemplo1.models.Habilidad;
import cl.tbd.ejemplo1.repositories.HabilidadRepository;

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

@CrossOrigin
@RestController
public class HabilidadService {
    
    private final HabilidadRepository habilidadRepository;
    
    HabilidadService(HabilidadRepository habilidadRepository){
        this.habilidadRepository = habilidadRepository;
    }

    @GetMapping("/habilidades")
    public List<Habilidad> getAllHabilidades() {
        return habilidadRepository.getAllHabilidades();
    }

    @GetMapping("/habilidades/count")
    public String countHabilidades(){
        int total = habilidadRepository.countHabilidades();
        return String.format("Existen %s habilidades posibles", total);
    }

    @PostMapping("/habilidades")
    @ResponseBody
    public Habilidad createHabilidad(@RequestBody Habilidad habilidad){
        Habilidad result = habilidadRepository.createHabilidad(habilidad);
        return result;
    }
    
    @PutMapping("/habilidades/{id}")
    @ResponseBody
    public Habilidad updateHabilidad(@RequestBody Habilidad habilidad, @PathVariable long id){
        Habilidad result = habilidadRepository.updateHabilidad(habilidad, id);
        return result;
    }

    @DeleteMapping("/habilidades/{id}")
    public List<Habilidad> deleteHabilidad(@PathVariable long id){
        return habilidadRepository.deleteHabilidad(id);
    }

}
