package cl.tbd.ejemplo1.services;

import java.util.List;

import cl.tbd.ejemplo1.models.Eme_habilidad;
import cl.tbd.ejemplo1.repositories.Eme_habilidadRepository;

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
public class Eme_habilidadService {

    private final Eme_habilidadRepository eme_habilidadRepository;

    Eme_habilidadService(Eme_habilidadRepository eme_habilidadRepository){
        this.eme_habilidadRepository = eme_habilidadRepository;
    }

    @GetMapping("/eme_habilidades")
    public List<Eme_habilidad> getAllEme_habilidades() {
        return eme_habilidadRepository.getAllEme_habilidades();
    }

    @PostMapping("/eme_habilidades")
    @ResponseBody
    public Eme_habilidad createEme_habilidad(@RequestBody Eme_habilidad eme_habilidad){
        Eme_habilidad result = eme_habilidadRepository.createEme_habilidad(eme_habilidad);
        return result;
    }    

    @PutMapping("/eme_habilidades/{id}")
    @ResponseBody
    public Eme_habilidad updateEme_habilidad(@RequestBody Eme_habilidad eme_habilidad, @PathVariable long id){
        Eme_habilidad result = eme_habilidadRepository.updateEme_habilidad(eme_habilidad, id);
        return result;
    }

    @DeleteMapping("/eme_habilidades/{id}")
    public List<Eme_habilidad> deleteEme_habilidad(@PathVariable long id){
        return eme_habilidadRepository.deleteEme_habilidad(id);
    }


    

    


    
}
