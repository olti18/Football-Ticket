package Football_Ticket.controller;

import Football_Ticket.model.Ticket;
import Football_Ticket.repository.FootballEventRepository;
import Football_Ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FootballEventRepository footballEventRepository;

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        return ticketRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestParam Long eventId, @RequestParam BigDecimal price) {
        return footballEventRepository.findById(eventId)
                .map(event -> {
                    Ticket ticket = new Ticket();
                    ticket.setUserId(getCurrentUserId()); // Merr ID-n e përdoruesit nga Keycloak
                    ticket.setEvent(event);
                    ticket.setPrice(price);
                    ticket.setValid(true);
                    return ResponseEntity.ok(ticketRepository.save(ticket));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
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
// Tickets
//{
//        "id": 1,
//        "userId": "7729cc1c-96f0-452f-926c-8a099cf16761",
//        "event": {
//        "id": 1,
//        "name": "Test Event",
//        "dateTime": "2024-12-20T09:43:16",
//        "stadium": "Test Stadium",
//        "availableTickets": 100
//        },
//        "price": 300,
//        "valid": true
//        }


//{
//        "id": 1,// remve this pls
//        "name": "Test Event",
//        "dateTime": "2024-12-20T09:43:16",
//        "stadium": "Test Stadium",
//        "availableTickets": 100
//        }