package cl.tbd.ejemplo1.repositories;

import java.util.List;

import cl.tbd.ejemplo1.models.Vol_habilidad;


public interface Vol_habilidadRepository {
    public List<Vol_habilidad> getAllVol_habilidades();
    public Vol_habilidad createVol_habilidad(Vol_habilidad vol_bablidad);
    public Vol_habilidad updateVol_habilidad(Vol_habilidad vol_habilidad, long id);
    public List<Vol_habilidad> deleteVol_habilidad(long id);
    
}
