package Football_Ticket.controller;

import Football_Ticket.Dto.MatchDTO;
import Football_Ticket.service.Impl.MatchServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchServiceImpl matchServiceImpl;

    public MatchController(MatchServiceImpl matchServiceImpl) {
        this.matchServiceImpl = matchServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<MatchDTO>> getAllMatches() {
        return ResponseEntity.ok(matchServiceImpl.getAllMatches());
    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<MatchDTO> getMatchById(@PathVariable String id) {
//        return ResponseEntity.ok(matchServiceImpl.getMatchById(id));
//    }

    @PostMapping
    public ResponseEntity<MatchDTO> createMatch(@RequestBody MatchDTO matchDTO) {
        return ResponseEntity.ok(matchServiceImpl.createMatch(matchDTO));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<MatchDTO> updateMatch(@PathVariable String id, @RequestBody MatchDTO matchDTO) {
//        return ResponseEntity.ok(matchServiceImpl.updateMatch(id, matchDTO));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteMatch(@PathVariable String id) {
//        matchServiceImpl.deleteMatch(id);
//        return ResponseEntity.noContent().build();
//    }
}
