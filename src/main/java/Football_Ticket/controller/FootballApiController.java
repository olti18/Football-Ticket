package Football_Ticket.controller;

import Football_Ticket.service.Impl.FootballApiServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FootballApiController {

    private final FootballApiServiceImpl footballApiService;

    public  FootballApiController(FootballApiServiceImpl footballApiService){
        this.footballApiService = footballApiService;
    }
    @GetMapping("/premier-league-matches")
    public ResponseEntity<String> getPremierLeagueMatches() {
        String result = footballApiService.getPremierLeagueMatches();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/Friendly-matches")
    public String getFriendlyMatches() {
        return footballApiService.FriendlyMatches();
    }

//    @GetMapping("/friendly-matches")
//    public String getFriendlyMatches() {
//        return footballApiService.getFriendlyMatches();
//    }
//
//    @GetMapping("/Kosovo-matches-Api")
//    public String getKosovomatches(){
//        return footballApiService.getKosovoMatches();
//    }
}
