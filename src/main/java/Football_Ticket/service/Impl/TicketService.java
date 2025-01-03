package Football_Ticket.service.Impl;

import Football_Ticket.Dto.TicketDTO;
import Football_Ticket.mapper.TicketMapper;
import Football_Ticket.model.Match;
import Football_Ticket.model.Ticket;
import Football_Ticket.repository.MatchRepository;
import Football_Ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TicketMapper ticketMapper;

    public List<TicketDTO> getAllTickets() {
        return ticketRepository.findAll()
                .stream()
                .map(ticketMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TicketDTO getTicketById(String id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        return ticketMapper.toDTO(ticket);
    }

    public TicketDTO createTicket(TicketDTO dto) {
        Ticket ticket = ticketMapper.toEntity(dto);

        // Verify match exists
        Match match = matchRepository.findById(dto.getMatchId())
                .orElseThrow(() -> new RuntimeException("Match not found"));
        ticket.setMatch(match);

        // Set createdBy using Keycloak
//        ticket.setCreatedBy(KeycloakUtils.getCurrentUserId());
        ticket.setCreatedBy(getCurrentUserId());
        ticket = ticketRepository.save(ticket);
        return ticketMapper.toDTO(ticket);
    }

    public TicketDTO updateTicket(String id, TicketDTO dto) {
        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        existingTicket.setSeatNumber(dto.getSeatNumber());
        existingTicket.setPrice(dto.getPrice());
        existingTicket.setPaid(dto.isPaid());
        existingTicket.setQrCode(dto.getQrCode());
        existingTicket.setStatus(dto.getStatus());

        ticketRepository.save(existingTicket);
        return ticketMapper.toDTO(existingTicket);
    }

    public void deleteTicket(String id) {
        if (!ticketRepository.existsById(id)) {
            throw new RuntimeException("Ticket not found");
        }
        ticketRepository.deleteById(id);
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
