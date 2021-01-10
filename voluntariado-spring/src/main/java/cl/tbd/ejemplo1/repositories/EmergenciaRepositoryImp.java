package cl.tbd.ejemplo1.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import cl.tbd.ejemplo1.models.Emergencia;

@Repository
public class EmergenciaRepositoryImp implements EmergenciaRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Emergencia> getAllEmergencias() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT id, nombre, descrip, finicio, ffin, id_institucion, ST_X(ST_AsText(ubicacion)) AS longitud, ST_Y(ST_AsText(ubicacion)) AS latitud FROM emergencia")
                    .executeAndFetch(Emergencia.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Emergencia createEmergencia(Emergencia emergencia) {
        try(Connection conn = sql2o.open()){
            String insert = "INSERT INTO emergencia (nombre, descrip, finicio, ffin, id_institucion, ubicacion) values (:eme_nombre, :eme_descrip, :eme_inicio, :eme_fin, :id_ins, ST_GeomFromText(:eme_punto, 4326))";
            String punto = "POINT(" + emergencia.getLongitud() + " " + emergencia.getLatitud() + ")";
            long insertedId = (long) conn.createQuery(insert, true)
                .addParameter("eme_nombre", emergencia.getNombre())
                .addParameter("eme_descrip", emergencia.getDescrip())
                .addParameter("eme_inicio", emergencia.getFinicio())
                .addParameter("eme_fin", emergencia.getFfin())
                .addParameter("id_ins", emergencia.getId_institucion())
                .addParameter("eme_punto", punto)
                .executeUpdate().getKey();
            emergencia.setId(insertedId);
            return emergencia;        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }        
    }


    @Override
    public Emergencia updateEmergencia(Emergencia emergencia, long id) {
        try(Connection conn = sql2o.open()){
            String update = "UPDATE emergencia SET nombre = :eme_nombre, descrip = :eme_descrip, finicio = :eme_inicio, ffin = :eme_fin, id_institucion = :id_ins, location =  ST_GeomFromText(:eme_punto, 4326) WHERE id = :id_update)";
            String punto = "POINT(" + emergencia.getLongitud() + " " + emergencia.getLatitud() + ")";
            conn.createQuery(update, true)
                .addParameter("id_update", id)
                .addParameter("eme_nombre", emergencia.getNombre())
                .addParameter("eme_descrip", emergencia.getDescrip())
                .addParameter("eme_inicio", emergencia.getFinicio())
                .addParameter("eme_fin", emergencia.getFfin())
                .addParameter("id_ins", emergencia.getId_institucion())
                .addParameter("eme_punto", punto)
                .executeUpdate();
            emergencia.setId(id);
            return emergencia;        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }

    @Override
    public List<Emergencia> deleteEmergencia(long id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM emergencia WHERE id = :deleteId")
                .addParameter("deleteId", id)
                .executeUpdate();
            return getAllEmergencias();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
