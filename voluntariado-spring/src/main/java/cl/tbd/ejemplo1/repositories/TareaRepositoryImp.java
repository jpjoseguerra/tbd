package cl.tbd.ejemplo1.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import cl.tbd.ejemplo1.models.Emergencia;
import cl.tbd.ejemplo1.models.Tarea;

import java.util.List;
import java.util.Map;

@Repository
public class TareaRepositoryImp implements TareaRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Tarea> getAllTareas() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT id, nombre, descrip, cant_vol_requeridos, cant_vol_inscritos, id_emergencia, finicio, ffin, id_estado, ST_X(ST_AsText(ubicacion)) AS longitud, ST_Y(ST_AsText(ubicacion)) AS latitud FROM tarea")
                    .executeAndFetch(Tarea.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Tarea createTarea(Tarea tarea) {
        try(Connection conn = sql2o.open()){
            String insert = "INSERT INTO tarea (nombre, descrip, cant_vol_requeridos, cant_vol_inscritos, id_emergencia, finicio, ffin, id_estado, ubicacion) values (:tar_nombre, :tar_descrip, :tar_requeridos, :tar_inscritos, :id_eme, :tar_inicio, :tar_fin, :id_est, ST_GeomFromText(:tar_punto, 4326))";
            String punto = "POINT(" + tarea.getLongitud() + " " + tarea.getLatitud() + ")";
            long insertedId = (long) conn.createQuery(insert, true)
                    .addParameter("tar_nombre", tarea.getNombre())
                    .addParameter("tar_descrip", tarea.getDescrip())
                    .addParameter("tar_requeridos", tarea.getCant_vol_requeridos())
                    .addParameter("tar_inscritos", tarea.getCant_vol_inscritos())
                    .addParameter("id_eme", tarea.getId_emergencia())
                    .addParameter("tar_inicio", tarea.getFinicio())
                    .addParameter("tar_fin", tarea.getFfin())                    
                    .addParameter("id_est", tarea.getId_estado())
                    .addParameter("tar_punto", punto)
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
            String update = "UPDATE tarea SET nombre = :tar_nombre, descrip = :tar_descrip, cant_vol_requeridos = :tar_requeridos, cant_vol_inscritos = :tar_inscritos, id_emergencia = :id_eme, finicio = :tar_inicio, ffin = :tar_fin, id_estado = :id_est, ubicacion = ST_GeomFromText(:tar_punto, 4326) WHERE id = :id_update";
            String punto = "POINT(" + tarea.getLongitud() + " " + tarea.getLatitud() + ")";
            conn.createQuery(update)
                    .addParameter("id_update", id)
                    .addParameter("tar_nombre", tarea.getNombre())
                    .addParameter("tar_descrip", tarea.getDescrip())
                    .addParameter("tar_requeridos", tarea.getCant_vol_requeridos())
                    .addParameter("tar_inscritos", tarea.getCant_vol_inscritos())
                    .addParameter("id_eme", tarea.getId_emergencia())
                    .addParameter("tar_inicio", tarea.getFinicio())
                    .addParameter("tar_fin", tarea.getFfin())                    
                    .addParameter("id_est", tarea.getId_estado())
                    .addParameter("tar_punto", punto)
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

    @Override
    public List<Tarea> tareasPorIdEme(long id_emergencia){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT id, nombre, descrip, cant_vol_requeridos, cant_vol_inscritos, id_emergencia, finicio, ffin, id_estado, ST_X(ST_AsText(ubicacion)) AS longitud, ST_Y(ST_AsText(ubicacion)) AS latitud FROM tarea WHERE id_emergencia = :id_eme")
                    .addParameter("id_eme", id_emergencia)
                    .executeAndFetch(Tarea.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
//tarea por distancia, manejo de las ubicaciones como point
    @Override
    public List<Map<String, Object>> tareasOrdenadasPorDistancia(long id_emergencia){
        try(Connection conn = sql2o.open()){
            List<Emergencia> emergencia = conn.createQuery("SELECT ST_X(ST_AsText(ubicacion)) AS longitud, ST_Y(ST_AsText(ubicacion)) AS latitud FROM emergencia WHERE id = :id_eme")
                .addParameter("id_eme", id_emergencia)
                .executeAndFetch(Emergencia.class);
            String punto = "POINT(" + emergencia.get(0).getLongitud() + " " + emergencia.get(0).getLatitud() + ")";
            return conn.createQuery("SELECT id, nombre, ST_X(ST_AsText(ubicacion)) AS longitud, ST_Y(ST_AsText(ubicacion)) AS latitud, ST_Distance(ST_GeomFromText(:tar_punto, 4326), ubicacion::geography) AS distancia FROM tarea WHERE id_emergencia = :id_eme ORDER BY distancia ASC")
                .addParameter("tar_punto", punto)
                .addParameter("id_eme", id_emergencia)
                .executeAndFetchTable()
                .asList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
