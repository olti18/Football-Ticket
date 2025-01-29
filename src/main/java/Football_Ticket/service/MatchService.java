package Football_Ticket.service;

import Football_Ticket.Dto.MatchDTO;

import java.util.List;

public interface MatchService {
    List<MatchDTO> getAllMatches();


    MatchDTO getMatchById(String id);


    MatchDTO createMatch(MatchDTO dto);


    MatchDTO updateMatch(String id, MatchDTO dto);


    void deleteMatch(String id);
}
