package cl.tbd.ejemplo1.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import cl.tbd.ejemplo1.models.Estado_tarea;

@Repository
public class Estado_tareaRepositoryImp implements Estado_tareaRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Estado_tarea> getAllEstado_tareas() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM estado_tarea")
                    .executeAndFetch(Estado_tarea.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Estado_tarea createEstado_tarea(Estado_tarea estado_tarea) {
        try(Connection conn = sql2o.open()){
            long insertedId = (long) conn.createQuery("INSERT INTO estado_tarea (descrip) values (:etDescrip)", true)
                    .addParameter("etDescrip", estado_tarea.getDescrip())
                    .executeUpdate().getKey();
                    estado_tarea.setId(insertedId);
            return estado_tarea;        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }        
    }

    @Override
    public Estado_tarea updateEstado_tarea(Estado_tarea estado_tarea, long id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("UPDATE estado_tarea SET descrip = :newDescrip WHERE id = :updateId")
                .addParameter("updateId", id)
                .addParameter("newDescrip", estado_tarea.getDescrip())
                .executeUpdate();
            estado_tarea.setId(id);
            return estado_tarea;        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }        
    }

    @Override
    public List<Estado_tarea> deleteEstado_tarea(long id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM estado_tarea WHERE id = :deleteId")
                .addParameter("deleteId", id)
                .executeUpdate();
            return getAllEstado_tareas();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
 
     
}
