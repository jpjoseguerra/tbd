package cl.tbd.ejemplo1.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import cl.tbd.ejemplo1.models.Vol_habilidad;

import java.util.List;

@Repository
public class Vol_habilidadRepositoryImp implements Vol_habilidadRepository {
    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Vol_habilidad> getAllVol_habilidades() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM vol_habilidad")
                    .executeAndFetch(Vol_habilidad.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Vol_habilidad createVol_habilidad(Vol_habilidad vol_habilidad) {
        try(Connection conn = sql2o.open()){
            long insertedId = (long) conn.createQuery("INSERT INTO vol_habilidad (id_voluntario, id_habilidad) VALUES (:vh_id_vol, :vh_id_hab)", true)                    
                    .addParameter("vh_id_vol", vol_habilidad.getId_voluntario())
                    .addParameter("vh_id_hab", vol_habilidad.getId_habilidad())
                    .executeUpdate().getKey();
                    vol_habilidad.setId(insertedId);
            return vol_habilidad;        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }        
    }

    @Override
    public Vol_habilidad updateVol_habilidad(Vol_habilidad vol_habilidad, long id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("UPDATE vol_habilidad SET id_voluntario = :idVol, id_habilidad = :idHab  WHERE id = :updateId")
                .addParameter("updateId", id)
                .addParameter("idVol", vol_habilidad.getId_voluntario())
                .addParameter("idHab", vol_habilidad.getId_habilidad())
                .executeUpdate();
            vol_habilidad.setId(id);
            return vol_habilidad;        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }        
    }

    @Override
    public List<Vol_habilidad> deleteVol_habilidad(long id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM vol_habilidad WHERE id = :deleteId")
                .addParameter("deleteId", id)
                .executeUpdate();
            return getAllVol_habilidades();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
