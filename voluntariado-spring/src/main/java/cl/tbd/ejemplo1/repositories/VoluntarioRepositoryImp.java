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
            return conn.createQuery("select * from voluntario")
                    .executeAndFetch(Voluntario.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Voluntario createVoluntario(Voluntario voluntario) {
        try(Connection conn = sql2o.open()){
            long insertedId = (long) conn.createQuery("INSERT INTO voluntario (nombre, fnacimiento) values (:vNombre, :vNacimiento)", true)
                    .addParameter("vNombre", voluntario.getNombre())
                    .addParameter("vNacimiento", voluntario.getFnacimiento())
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
            conn.createQuery("UPDATE voluntario SET nombre = :vNombre, fnacimiento = :vNacimiento WHERE id = :updateId")
                .addParameter("updateId", id)
                .addParameter("vNombre", voluntario.getNombre())
                .addParameter("vNacimiento", voluntario.getFnacimiento())
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
