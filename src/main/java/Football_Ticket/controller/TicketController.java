package Football_Ticket.controller;


import Football_Ticket.Dto.TicketDTO;
import Football_Ticket.service.Impl.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable String id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.createTicket(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> updateTicket(
            @PathVariable String id,
            @RequestBody TicketDTO dto) {
        return ResponseEntity.ok(ticketService.updateTicket(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable String id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }









//    @Autowired
//    private TicketRepository ticketRepository;
//
//    @Autowired
//    private FootballEventRepository footballEventRepository;
//
//    @GetMapping
//    public List<Ticket> getAllTickets() {
//        return ticketRepository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
//        return ticketRepository.findById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public ResponseEntity<Ticket> createTicket(@RequestParam Long eventId, @RequestParam BigDecimal price) {
//        return footballEventRepository.findById(eventId)
//                .map(event -> {
//                    Ticket ticket = new Ticket();
//                    ticket.setUserId(getCurrentUserId()); // Merr ID-n e përdoruesit nga Keycloak
//                    ticket.setEvent(event);
//                    ticket.setPrice(price);
//                    ticket.setValid(true);
//                    return ResponseEntity.ok(ticketRepository.save(ticket));
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
//        if (ticketRepository.existsById(id)) {
//            ticketRepository.deleteById(id);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    private String getCurrentUserId() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
//            Jwt jwt = (Jwt) authentication.getPrincipal();
//            return jwt.getClaim("sub"); // ID-ja e përdoruesit nga Keycloak
//        }
//        return null;
//    }
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