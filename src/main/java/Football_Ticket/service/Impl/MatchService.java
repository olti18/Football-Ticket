package Football_Ticket.service.Impl;

import Football_Ticket.Dto.MatchDTO;
import Football_Ticket.mapper.MatchMapper;
import Football_Ticket.model.Match;
import Football_Ticket.model.Stadium;
import Football_Ticket.repository.MatchRepository;
import Football_Ticket.repository.StadiumRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final StadiumRepository stadiumRepository;
    private final MatchMapper matchMapper;

    public MatchService(MatchRepository matchRepository, StadiumRepository stadiumRepository, MatchMapper matchMapper) {
        this.matchRepository = matchRepository;
        this.stadiumRepository = stadiumRepository;
        this.matchMapper = matchMapper;
    }

    public List<MatchDTO> getAllMatches() {
        List<Match> matches = matchRepository.findAll();
        return matches.stream()
                .map(matchMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MatchDTO getMatchById(String id) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found"));
        return matchMapper.toDTO(match);
    }

    @Transactional
    public MatchDTO createMatch(MatchDTO dto) {
        Match match = matchMapper.toEntity(dto);

        // Fetch stadium by ID
        Stadium stadium = stadiumRepository.findById(dto.getStadiumId())
                .orElseThrow(() -> new RuntimeException("Stadium not found"));
        match.setStadium(stadium);

        // Set createdBy field using Keycloak
//        match.setCreatedBy(KeycloakUtils.getCurrentUserId());
        match.setCreatedBy(getCurrentUserId());

        match = matchRepository.save(match);
        return matchMapper.toDTO(match);
    }

    @Transactional
    public MatchDTO updateMatch(String id, MatchDTO dto) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found"));

        match.setHomeTeam(dto.getHomeTeam());
        match.setAwayTeam(dto.getAwayTeam());
        match.setMatchDate(dto.getMatchDate());
        match.setTicketPrice(dto.getTicketPrice());
        match.setStatus(dto.getStatus());

        match = matchRepository.save(match);
        return matchMapper.toDTO(match);
    }

    @Transactional
    public void deleteMatch(String id) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found"));
        matchRepository.delete(match);
    }

    private String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            return jwt.getClaim("sub"); // ID-ja e përdoruesit nga Keycloak
        }
        return null;
    }
}
