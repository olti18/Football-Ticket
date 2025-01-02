package Football_Ticket.mapper;

import Football_Ticket.Dto.MatchDTO;
import Football_Ticket.model.Match;
import org.springframework.stereotype.Component;

@Component
public class MatchMapper {

    public MatchDTO toDTO(Match match) {
        MatchDTO dto = new MatchDTO();
        dto.setId(match.getId());
        dto.setHomeTeam(match.getHomeTeam());
        dto.setAwayTeam(match.getAwayTeam());
        dto.setMatchDate(match.getMatchDate());
        dto.setStadiumId(match.getStadium().getId());
        dto.setTicketPrice(match.getTicketPrice());
        dto.setStatus(match.getStatus());
        return dto;
    }

    public Match toEntity(MatchDTO dto) {
        Match match = new Match();
        match.setHomeTeam(dto.getHomeTeam());
        match.setAwayTeam(dto.getAwayTeam());
        match.setMatchDate(dto.getMatchDate());
        match.setTicketPrice(dto.getTicketPrice());
        match.setStatus(dto.getStatus());
        return match;
    }

}
