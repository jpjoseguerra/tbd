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

import cl.tbd.ejemplo1.models.Vol_habilidad;

import cl.tbd.ejemplo1.repositories.Vol_habilidadRepository;

@CrossOrigin
@RestController
public class Vol_habilidadService {

    private final Vol_habilidadRepository vol_habilidadRepository;

    Vol_habilidadService(Vol_habilidadRepository vol_habilidadRepository){
        this.vol_habilidadRepository = vol_habilidadRepository;
    }

    @GetMapping("/vol_habilidades")
    public List<Vol_habilidad> getAllVol_habilidades() {
        return vol_habilidadRepository.getAllVol_habilidades();
    }

    @PostMapping("/vol_habilidades")
    @ResponseBody
    public Vol_habilidad createTarea(@RequestBody Vol_habilidad vol_habilidad){
        Vol_habilidad result = vol_habilidadRepository.createVol_habilidad(vol_habilidad);
        return result;
    }

    @PutMapping("/vol_habilidades/{id}")
    @ResponseBody
    public Vol_habilidad updateVol_habilidad(@RequestBody Vol_habilidad vol_habilidad, @PathVariable long id){
        Vol_habilidad result = vol_habilidadRepository.updateVol_habilidad(vol_habilidad, id);
        return result;
    }

    @DeleteMapping("/vol_habilidades/{id}")
    public List<Vol_habilidad> deleteVol_habilidad(@PathVariable long id){
        return vol_habilidadRepository.deleteVol_habilidad(id);
    }

    
}
