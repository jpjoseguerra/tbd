package cl.tbd.ejemplo1.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import cl.tbd.ejemplo1.models.Eme_habilidad;

@Repository
public class Eme_habilidadRepositoryImp implements Eme_habilidadRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Eme_habilidad> getAllEme_habilidades() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM eme_habilidad")
                    .executeAndFetch(Eme_habilidad.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Eme_habilidad createEme_habilidad(Eme_habilidad eme_habilidad) {
        try(Connection conn = sql2o.open()){
            long insertedId = (long) conn.createQuery("INSERT INTO eme_habilidad (id_emergencia, id_habilidad) VALUES (:eh_id_emergencia, :eh_id_habilidad)", true)
                    .addParameter("eh_id_emergencia", eme_habilidad.getId_emergencia())
                    .addParameter("eh_id_habilidad", eme_habilidad.getId_habilidad())
                    .executeUpdate().getKey();
                    eme_habilidad.setId(insertedId);
            return eme_habilidad;        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }        
    }

    @Override
    public Eme_habilidad updateEme_habilidad(Eme_habilidad eme_habilidad, long id){
        try(Connection conn = sql2o.open()){
            conn.createQuery("UPDATE eme_habilidad SET id_emergencia = :new_id_emergencia, id_habilidad = :new_id_habilidad WHERE id = :updateId")
                .addParameter("updateId", id)
                .addParameter("new_id_emergencia", eme_habilidad.getId_emergencia())
                .addParameter("new_id_habilidad", eme_habilidad.getId_habilidad())
                .executeUpdate();
            eme_habilidad.setId(id);
            return eme_habilidad;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }  

    @Override
    public List<Eme_habilidad> deleteEme_habilidad(long id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM eme_habilidad WHERE id = :deleteId")
                .addParameter("deleteId", id)
                .executeUpdate();
            return getAllEme_habilidades();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
        
    





    
}
