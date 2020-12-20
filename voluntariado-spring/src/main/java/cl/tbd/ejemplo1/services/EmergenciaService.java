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

import cl.tbd.ejemplo1.models.Emergencia;
import cl.tbd.ejemplo1.repositories.EmergenciaRepository;

@CrossOrigin
@RestController
public class EmergenciaService {

    private final EmergenciaRepository emergenciaRepository;

    EmergenciaService(EmergenciaRepository emergenciaRepository){
        this.emergenciaRepository = emergenciaRepository;
    }

    @GetMapping("/emergencias")
    public List<Emergencia> getAllEmergencias() {
        return emergenciaRepository.getAllEmergencias();
    }

    @PostMapping("/emergencias")
    @ResponseBody
    public Emergencia createEmergencia(@RequestBody Emergencia emergencia){
        Emergencia result = emergenciaRepository.createEmergencia(emergencia);
        return result;
    }

    @PutMapping("/emergencias/{id}")
    @ResponseBody
    public Emergencia updateEmergencia(@RequestBody Emergencia emergencia, @PathVariable long id){
        Emergencia result = emergenciaRepository.updateEmergencia(emergencia, id);
        return result;
    }

    @DeleteMapping("/emergencias/{id}")
    public List<Emergencia> deleteEmergencia(@PathVariable long id){
        return emergenciaRepository.deleteEmergencia(id);
    }
    
}
