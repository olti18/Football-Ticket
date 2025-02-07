package Football_Ticket.service.Impl;

import Football_Ticket.Dto.CreateTicketDTO;
import Football_Ticket.Dto.TicketDTO;
import Football_Ticket.Dto.TicketResponseDTO;
import Football_Ticket.mapper.TicketMapper;
import Football_Ticket.model.Match;
import Football_Ticket.model.SeatingSection;
import Football_Ticket.model.Ticket;
import Football_Ticket.repository.MatchRepository;
import Football_Ticket.repository.SeatingSectionRepository;
import Football_Ticket.repository.TicketRepository;
import Football_Ticket.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private SeatingSectionRepository seatingSectionRepository;

    @Autowired
    private TicketMapper ticketMapper;


    // ... existing code ...

    public List<Ticket> getTicketsForLoggedInUser() {
        String userId = getCurrentUserId();
        if (userId == null) {
            throw new RuntimeException("User not authenticated");
        }
        return ticketRepository.findByBoughtByAndIsPaid(userId, true);
    }


    public TicketDTO CreateTicket(CreateTicketDTO dto){
        String userId = getCurrentUserId();

        Match match = matchRepository.findById(dto.getMatchId())
                .orElseThrow(() -> new RuntimeException("Match not found"));
        SeatingSection seatingSection = seatingSectionRepository.findById(dto.getSeatingSectionId())
                .orElseThrow(() -> new RuntimeException("Seating section not found"));

        Ticket ticket = ticketMapper.toEntity(dto, match, seatingSection, userId);
        ticket.setPrice(match.getTicketPrice().multiply(seatingSection.getPriceMultiplier()));

        // Ensure the seat number is set from the DTO
        if (dto.getSeatNumber() != null && !dto.getSeatNumber().isEmpty()) {
            ticket.setSeatNumber(dto.getSeatNumber());
        } else {
            throw new IllegalArgumentException("Seat number is required.");
        }
        ticket.setStatus("PENDING");
        ticketRepository.save(ticket);

        return ticketMapper.toDTO(ticket);

    }




     // Get all tickets
    public List<TicketResponseDTO> getAllTicketsWithMatchName() {
        return ticketRepository.getTicketsWithMatchDetails();
    }

//    public List<TicketDTO> getAllTickets() {
//        return ticketRepository.findAll().stream()
//                .map(ticketMapper::toDTO)
//                .collect(Collectors.toList());
//    }

    // Get ticket by ID
    public TicketDTO getTicketById(String id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        return ticketMapper.toDTO(ticket);
    }

    // Delete ticket by ID
    public void deleteTicket(String id) {
        if (!ticketRepository.existsById(id)) {
            throw new RuntimeException("Ticket not found");
        }
        ticketRepository.deleteById(id);
    }
    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
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
