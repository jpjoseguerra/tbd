package cl.tbd.ejemplo1.repositories;

import java.util.List;

import cl.tbd.ejemplo1.models.Institucion;

public interface InstitucionRepository {
    public List<Institucion> getAllInstituciones();
    public Institucion createInstitucion(Institucion institucion);
    public Institucion updateInstitucion(Institucion institucion, long id);
    public List<Institucion> deleteInstitucion(long id);
}
