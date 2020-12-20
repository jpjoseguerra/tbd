package cl.tbd.ejemplo1.repositories;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.data.Table;

import cl.tbd.ejemplo1.models.Ranking;

@Repository
public class RankingRepositoryImp implements RankingRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Ranking> getAllRankings() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM ranking")
                    .executeAndFetch(Ranking.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Ranking createRanking(Ranking ranking) {
        try(Connection conn = sql2o.open()){
            long insertedId = (long) conn.createQuery("INSERT INTO ranking (id_voluntario, id_tarea, puntaje, flg_invitado, flg_participa) values (:idVol, :idTar, :rPuntaje, :rInv, :rPart)", true)
                    .addParameter("idVol", ranking.getId_voluntario())
                    .addParameter("idTar", ranking.getId_tarea())
                    .addParameter("rPuntaje", ranking.getPuntaje())
                    .addParameter("rInv", ranking.getFlg_invitado())
                    .addParameter("rPart", ranking.getFlg_participa())
                    .executeUpdate().getKey();
                    ranking.setId(insertedId);
            return ranking;        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }        
    }

    @Override
    public JSONArray getRankByTask(long id_tarea, int quantity){
        
        try(Connection conn = sql2o.open()){
            Table table = conn.createQuery("SELECT vol.id, vol.nombre AS vol_nombre, rnk.id_tarea, tr.nombre AS tarea_nombre, tr.descrip, rnk.puntaje FROM voluntario vol JOIN ranking rnk ON rnk.id_voluntario = vol.id JOIN tarea tr ON tr.id = rnk.id_tarea WHERE tr.id = :idTarea ORDER BY rnk.puntaje DESC LIMIT :quantity")
                .addParameter("idTarea", id_tarea)
                .addParameter("quantity", quantity)
                .executeAndFetchTable();
            
                JSONArray final_json = new JSONArray();
                table.rows().forEach(
                    row ->{
                        String vol_id = row.getString("id");
                        String vol_nombre = row.getString("vol_nombre");
                        String id_tarea_ = row.getString("id_tarea");
                        String tarea_nombre = row.getString("tarea_nombre");
                        String descrip = row.getString("descrip");
                        String puntaje = row.getString("puntaje");
                        
                        JSONObject temp = new JSONObject();
                        temp.put("id_voluntario", vol_id);
                        temp.put("vol_nombre", vol_nombre);
                        temp.put("id_tarea", id_tarea_);
                        temp.put("tarea_nombre", tarea_nombre);
                        temp.put("descrip", descrip);
                        temp.put("puntaje", puntaje);
                        
                        final_json.put(temp);
                    }
                );          
        
                
            return final_json;
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    @Override
    public Ranking updateRanking(Ranking ranking, long id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("UPDATE ranking SET id_voluntario = :idVol, id_tarea = :idTar, puntaje = :rPuntaje, flg_invitado = :rInv, flg_participa = :rPart WHERE id = :updateId")
                .addParameter("updateId", id)
                .addParameter("idVol", ranking.getId_voluntario())
                .addParameter("idTar", ranking.getId_tarea())
                .addParameter("rPuntaje", ranking.getPuntaje())
                .addParameter("rInv", ranking.getFlg_invitado())
                .addParameter("rPart", ranking.getFlg_participa())
                .executeUpdate();
            ranking.setId(id);
            return ranking;        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }        
    }

    @Override
    public List<Ranking> deleteRanking(long id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM ranking WHERE id = :deleteId")
                .addParameter("deleteId", id)
                .executeUpdate();
            return getAllRankings();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
