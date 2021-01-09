package cl.tbd.ejemplo1.repositories;

import cl.tbd.ejemplo1.models.Voluntario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class VoluntarioRepositoryImp implements VoluntarioRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public int countVoluntarios() {
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM voluntario").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Voluntario> getAllVoluntarios() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT id, nombre, email, ST_X(ST_AsText(ubicacion)) AS longitud, ST_Y(ST_AsText(ubicacion)) AS latitud FROM voluntario")
                    .executeAndFetch(Voluntario.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Voluntario createVoluntario(Voluntario voluntario) {
        try(Connection conn = sql2o.open()){
            String insert = "INSERT INTO voluntario (nombre, email, ubicacion) values (:vol_nombre, :vol_email, ST_GeomFromText(:vol_punto, 4326)))";
            String punto = "POINT(" + voluntario.getLongitud() + " " + voluntario.getLatitud() + ")";
            long insertedId = (long) conn.createQuery(insert, true)
                .addParameter("vol_nombre", voluntario.getNombre())
                .addParameter("vol_punto", punto)
                .addParameter("vol_email", voluntario.getEmail())
                .executeUpdate().getKey();
            voluntario.setId(insertedId);
            return voluntario;        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }

    @Override
    public Voluntario updateVoluntario(Voluntario voluntario, long id) {
        try(Connection conn = sql2o.open()){
            String update = "UPDATE voluntario SET nombre = :vol_nombre, email = :vol_email, location = ST_GeomFromText(:vol_punto, 4326) WHERE id = :id_update";
            String punto = "POINT(" + voluntario.getLongitud() + " " + voluntario.getLatitud() + ")";
            conn.createQuery(update, true)
                .addParameter("id_update", id)
                .addParameter("vol_nombre", voluntario.getNombre())
                .addParameter("vol_punto", punto)
                .addParameter("vol_email", voluntario.getEmail())
                .executeUpdate();
            voluntario.setId(id);
            return voluntario;        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }

    @Override
    public List<Voluntario> deleteVoluntario(long id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM voluntario WHERE id = :deleteId")
                .addParameter("deleteId", id)
                .executeUpdate();
            return getAllVoluntarios();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

