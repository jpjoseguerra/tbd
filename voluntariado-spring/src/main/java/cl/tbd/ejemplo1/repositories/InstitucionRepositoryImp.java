package cl.tbd.ejemplo1.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import cl.tbd.ejemplo1.models.Institucion;

@Repository
public class InstitucionRepositoryImp implements InstitucionRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Institucion> getAllInstituciones() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM institucion")
                    .executeAndFetch(Institucion.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Institucion createInstitucion(Institucion institucion) {
        try(Connection conn = sql2o.open()){
            long insertedId = (long) conn.createQuery("INSERT INTO institucion (nombre, descrip) values (:iNombre, :iDescrip)", true)
                    .addParameter("iNombre", institucion.getNombre())
                    .addParameter("iDescrip", institucion.getDescrip())
                    .executeUpdate().getKey();
                    institucion.setId(insertedId);
            return institucion;        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }        
    }

    @Override
    public Institucion updateInstitucion(Institucion institucion, long id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("UPDATE institucion SET nombre = :iNombre, descrip = :iDescrip WHERE id = :updateId")
                .addParameter("updateId", id)
                .addParameter("iNombre", institucion.getNombre())
                .addParameter("iDescrip", institucion.getDescrip())
                .executeUpdate();
            institucion.setId(id);
            return institucion;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Institucion> deleteInstitucion(long id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM institucion WHERE id = :deleteId")
                .addParameter("deleteId", id)
                .executeUpdate();
            return getAllInstituciones();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
