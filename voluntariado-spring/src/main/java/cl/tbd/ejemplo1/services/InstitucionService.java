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

import cl.tbd.ejemplo1.models.Institucion;
import cl.tbd.ejemplo1.repositories.InstitucionRepository;

@CrossOrigin
@RestController
public class InstitucionService {

    private final InstitucionRepository institucionRepository;

    InstitucionService(InstitucionRepository institucionRepository){
        this.institucionRepository = institucionRepository;
    }

    @GetMapping("/instituciones")
    public List<Institucion> getAllInstituciones() {
        return institucionRepository.getAllInstituciones();
    }

    @PostMapping("/instituciones")
    @ResponseBody
    public Institucion createInstitucion(@RequestBody Institucion institucion){
        Institucion result = institucionRepository.createInstitucion(institucion);
        return result;
    }

    @PutMapping("/instituciones/{id}")
    @ResponseBody
    public Institucion updateInstitucion(@RequestBody Institucion institucion, @PathVariable long id){
        Institucion result = institucionRepository.updateInstitucion(institucion, id);
        return result;
    }

    @DeleteMapping("/instituciones/{id}")
    public List<Institucion> deleteInstitucion(@PathVariable long id){
        return institucionRepository.deleteInstitucion(id);
    }
    
}
