package cl.tbd.ejemplo1.repositories;

import cl.tbd.ejemplo1.models.Habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

@Repository
public class HabilidadRepositoryImp implements HabilidadRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public int countHabilidades() {
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM habilidad").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Habilidad> getAllHabilidades() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from habilidad")
                    .executeAndFetch(Habilidad.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Habilidad createHabilidad(Habilidad habilidad) {
        try(Connection conn = sql2o.open()){
            long insertedId = (long) conn.createQuery("INSERT INTO habilidad (descrip) values (:habilidadDescrip)", true)
                    .addParameter("habilidadDescrip", habilidad.getDescrip())
                    .executeUpdate().getKey();
                    habilidad.setId(insertedId);
            return habilidad;        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }        
    }

    @Override
    public Habilidad updateHabilidad(Habilidad habilidad, long id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("UPDATE habilidad SET descrip = :habilidadDescrip WHERE id = :updateId")
                .addParameter("updateId", id)
                .addParameter("habilidadDescrip", habilidad.getDescrip())
                .executeUpdate();
            habilidad.setId(id);
            return habilidad;        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }        
    }

    @Override
    public List<Habilidad> deleteHabilidad(long id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM habilidad WHERE id = :deleteId")
                .addParameter("deleteId", id)
                .executeUpdate();
            return getAllHabilidades();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
 
     
}
