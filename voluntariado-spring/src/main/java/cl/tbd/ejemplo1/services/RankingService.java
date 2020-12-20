package cl.tbd.ejemplo1.services;

import java.util.List;

import org.json.JSONArray;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.tbd.ejemplo1.models.Ranking;
import cl.tbd.ejemplo1.repositories.RankingRepository;

@CrossOrigin
@RestController
public class RankingService {

    private final RankingRepository rankingRepository;

    RankingService(RankingRepository rankingRepository){
        this.rankingRepository = rankingRepository;
    }

    @GetMapping("/rankings")
    public List<Ranking> getAllRankings() {
        return rankingRepository.getAllRankings();
    }

    @PostMapping("/rankings")
    @ResponseBody
    public Ranking createRanking(@RequestBody Ranking ranking){
        Ranking result = rankingRepository.createRanking(ranking);
        return result;
    }

    @PostMapping("/rankings/rankByTask")
    public String getRankByTask(long id_tarea, int quantity){
        JSONArray result = rankingRepository.getRankByTask(id_tarea, quantity);
        return result.toString();
    }

    @PutMapping("/rankings/{id}")
    @ResponseBody
    public Ranking updateRanking(@RequestBody Ranking ranking, @PathVariable long id){
        Ranking result = rankingRepository.updateRanking(ranking, id);
        return result;
    }

    @DeleteMapping("/rankings/{id}")
    public List<Ranking> deleteRanking(@PathVariable long id){
        return rankingRepository.deleteRanking(id);
    }
}