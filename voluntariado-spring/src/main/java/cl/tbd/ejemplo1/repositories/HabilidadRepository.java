package cl.tbd.ejemplo1.repositories;

import java.util.List;
import cl.tbd.ejemplo1.models.Habilidad;

public interface HabilidadRepository {
    public int countHabilidades();
    public List<Habilidad> getAllHabilidades();
    public Habilidad createHabilidad(Habilidad habilidad);
    public Habilidad updateHabilidad(Habilidad habilidad, long id);
    public List<Habilidad> deleteHabilidad(long id);
}
