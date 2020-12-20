package cl.tbd.ejemplo1.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import cl.tbd.ejemplo1.models.Tarea;

import java.util.List;

@Repository
public class TareaRepositoryImp implements TareaRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Tarea> getAllTareas() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM tarea")
                    .executeAndFetch(Tarea.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Tarea createTarea(Tarea tarea) {
        try(Connection conn = sql2o.open()){
            long insertedId = (long) conn.createQuery("INSERT INTO tarea (nombre, descrip, cant_vol_requeridos, cant_vol_inscritos, id_emergencia, finicio, ffin, id_estado) values (:tNombre, :tDescrip, :tRequeridos, :tInscritos, :idEme, :tInicio, :tFin, :idEst)", true)
                    .addParameter("tNombre", tarea.getNombre())
                    .addParameter("tDescrip", tarea.getDescrip())
                    .addParameter("tRequeridos", tarea.getCant_vol_requeridos())
                    .addParameter("tInscritos", tarea.getCant_vol_inscritos())
                    .addParameter("idEme", tarea.getId_emergencia())
                    .addParameter("tInicio", tarea.getFinicio())
                    .addParameter("tFin", tarea.getFfin())                    
                    .addParameter("idEst", tarea.getId_estado())
                    .executeUpdate().getKey();
                    tarea.setId(insertedId);
            return tarea;        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }        
    }

    @Override
    public Tarea updateTarea(Tarea tarea, long id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("UPDATE tarea SET nombre = :tNombre, descrip = :tDescrip, cant_vol_requeridos = :tRequeridos, cant_vol_inscritos = :tInscritos, id_emergencia = :idEme, id_estado = :idEst, finicio = :tInicio, ffin = :tFin WHERE id = :updateId")
                    .addParameter("updateId", id)
                    .addParameter("tNombre", tarea.getNombre())
                    .addParameter("tDescrip", tarea.getDescrip())
                    .addParameter("tRequeridos", tarea.getCant_vol_requeridos())
                    .addParameter("tInscritos", tarea.getCant_vol_inscritos())
                    .addParameter("idEme", tarea.getId_emergencia())
                    .addParameter("tInicio", tarea.getFinicio())
                    .addParameter("tFin", tarea.getFfin())                    
                    .addParameter("idEst", tarea.getId_estado())
                    .executeUpdate();
                    tarea.setId(id);
            return tarea;        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }        
    }

    @Override
    public List<Tarea> deleteTarea(long id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM tarea WHERE id = :deleteId")
                .addParameter("deleteId", id)
                .executeUpdate();
            return getAllTareas();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}
