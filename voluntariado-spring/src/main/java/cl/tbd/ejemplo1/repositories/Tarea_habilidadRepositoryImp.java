package cl.tbd.ejemplo1.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import cl.tbd.ejemplo1.models.Tarea_habilidad;

@Repository
public class Tarea_habilidadRepositoryImp implements Tarea_habilidadRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Tarea_habilidad> getAllTarea_habilidades() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM tarea_habilidad")
                    .executeAndFetch(Tarea_habilidad.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Tarea_habilidad createTarea_habilidad(Tarea_habilidad tarea_habilidad) {
        try(Connection conn = sql2o.open()){
            long insertedId = (long) conn.createQuery("INSERT INTO tarea_habilidad (id_emehab, id_tarea) VALUES (:th_id_emehab, :th_id_tarea)", true)                    
                    .addParameter("th_id_emehab", tarea_habilidad.getId_emehab())
                    .addParameter("th_id_tarea", tarea_habilidad.getId_tarea())
                    .executeUpdate().getKey();
                    tarea_habilidad.setId(insertedId);
            return tarea_habilidad;        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }        
    }



    @Override
    public Tarea_habilidad updateTarea_habilidad(Tarea_habilidad tarea_habilidad, long id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("UPDATE tarea_habilidad SET id_emehab = :new_id_emehab, id_tarea = :new_id_tarea  WHERE id = :updateId")
                .addParameter("updateId", id)
                .addParameter("new_id_emehab", tarea_habilidad.getId_emehab())
                .addParameter("new_id_tarea", tarea_habilidad.getId_tarea())
                .executeUpdate();
            tarea_habilidad.setId(id);
            return tarea_habilidad;        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }        
    }

    @Override
    public List<Tarea_habilidad> deleteTarea_habilidad(long id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM tarea_habilidad WHERE id = :deleteId")
                .addParameter("deleteId", id)
                .executeUpdate();
            return getAllTarea_habilidades();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    
}
