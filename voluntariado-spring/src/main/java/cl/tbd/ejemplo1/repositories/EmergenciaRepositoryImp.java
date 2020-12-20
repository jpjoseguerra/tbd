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
            return conn.createQuery("SELECT * FROM emergencia")
                    .executeAndFetch(Emergencia.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Emergencia createEmergencia(Emergencia emergencia) {
        try(Connection conn = sql2o.open()){
            long insertedId = (long) conn.createQuery("INSERT INTO emergencia (nombre, descrip, finicio, ffin, id_institucion) values (:eNombre, :eDescrip, :eInicio, :eFin, :idIns)", true)
                    .addParameter("eNombre", emergencia.getNombre())
                    .addParameter("eDescrip", emergencia.getDescrip())
                    .addParameter("eInicio", emergencia.getFinicio())
                    .addParameter("eFin", emergencia.getFfin())
                    .addParameter("idIns", emergencia.getId_institucion())
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
            conn.createQuery("UPDATE emergencia SET nombre = :newNombre, descrip = :newDescrip, finicio = :newFinicio, ffin = :newFfin, id_institucion = :newInstitucion WHERE id = :updateId")
                .addParameter("updateId", id)
                .addParameter("newNombre", emergencia.getNombre())
                .addParameter("newDescrip", emergencia.getDescrip())
                .addParameter("newFinicio", emergencia.getFinicio())
                .addParameter("newFfin", emergencia.getFfin())
                .addParameter("newInstitucion", emergencia.getId_institucion())
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
