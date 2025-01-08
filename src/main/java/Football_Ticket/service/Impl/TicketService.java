package Football_Ticket.service.Impl;

import Football_Ticket.Dto.CreateTicketDTO;
import Football_Ticket.Dto.TicketDTO;
import Football_Ticket.mapper.TicketMapper;
import Football_Ticket.model.Match;
import Football_Ticket.model.SeatingSection;
import Football_Ticket.model.Ticket;
import Football_Ticket.repository.MatchRepository;
import Football_Ticket.repository.SeatingSectionRepository;
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
    private SeatingSectionRepository seatingSectionRepository;

    @Autowired
    private TicketMapper ticketMapper;



    // Create Ticket
    public TicketDTO createTicket(CreateTicketDTO dto) {
        String userId = getCurrentUserId(); // Automatically fetch logged-in user ID

        Match match = matchRepository.findById(dto.getMatchId())
                .orElseThrow(() -> new RuntimeException("Match not found"));

        SeatingSection seatingSection = seatingSectionRepository.findById(dto.getSeatingSectionId())
                .orElseThrow(() -> new RuntimeException("Seating Section not found"));

        Ticket ticket = ticketMapper.toEntity(dto, match, seatingSection, userId);
        ticketRepository.save(ticket);
        return ticketMapper.toDTO(ticket);
    }

    // Get all tickets
    public List<TicketDTO> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(ticketMapper::toDTO)
                .collect(Collectors.toList());
    }

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


//    @Autowired
//    private TicketRepository ticketRepository;
//
//    @Autowired
//    private MatchRepository matchRepository;
//
//    @Autowired
//    private TicketMapper ticketMapper;
//
//    // Create ticket
//    public TicketDTO createTicket(CreateTicketDTO dto) {
//        //String userId = getCurrentUserId(); // Get logged-in user ID
//
//        Match match = matchRepository.findById(dto.getMatchId())
//                .orElseThrow(() -> new RuntimeException("Match not found"));
//
//        Ticket ticket = ticketMapper.toEntity(dto, match);
//        dto.setCreatedBy(getCurrentUserId());
//        ticketRepository.save(ticket);
//        return ticketMapper.toDTO(ticket);
//    }
//
//    // Get all tickets
//    public List<TicketDTO> getAllTickets() {
//        return ticketRepository.findAll().stream()
//                .map(ticketMapper::toDTO)
//                .collect(Collectors.toList());
//    }
//
//    // Get ticket by ID
//    public TicketDTO getTicketById(String id) {
//        Ticket ticket = ticketRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Ticket not found"));
//        return ticketMapper.toDTO(ticket);
//    }
//




//    @Autowired
//    private TicketRepository ticketRepository;
//
//    @Autowired
//    private MatchRepository matchRepository;
//
//    @Autowired
//    private TicketMapper ticketMapper;
//
//    public List<TicketDTO> getAllTickets() {
//        return ticketRepository.findAll()
//                .stream()
//                .map(ticketMapper::toDTO)
//                .collect(Collectors.toList());
//    }
//
//    public TicketDTO getTicketById(String id) {
//        Ticket ticket = ticketRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Ticket not found"));
//        return ticketMapper.toDTO(ticket);
//    }
//
//    public TicketDTO createTicket(TicketDTO dto) {
//        Ticket ticket = ticketMapper.toEntity(dto);
//
//        // Verify match exists
//        Match match = matchRepository.findById(dto.getMatchId())
//                .orElseThrow(() -> new RuntimeException("Match not found"));
//        ticket.setMatch(match);
//
//        // Set createdBy using Keycloak
////        ticket.setCreatedBy(KeycloakUtils.getCurrentUserId());
//        ticket.setCreatedBy(getCurrentUserId());
//        ticket = ticketRepository.save(ticket);
//        return ticketMapper.toDTO(ticket);
//    }
//
//    public TicketDTO updateTicket(String id, TicketDTO dto) {
//        Ticket existingTicket = ticketRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Ticket not found"));
//
//        existingTicket.setSeatNumber(dto.getSeatNumber());
//        existingTicket.setPrice(dto.getPrice());
//        existingTicket.setPaid(dto.isPaid());
//        existingTicket.setQrCode(dto.getQrCode());
//        existingTicket.setStatus(dto.getStatus());
//
//        ticketRepository.save(existingTicket);
//        return ticketMapper.toDTO(existingTicket);
//    }
//
//    public void deleteTicket(String id) {
//        if (!ticketRepository.existsById(id)) {
//            throw new RuntimeException("Ticket not found");
//        }
//        ticketRepository.deleteById(id);
//    }

    private String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            return jwt.getClaim("sub"); // ID-ja e përdoruesit nga Keycloak
        }
        return null;
    }

}
