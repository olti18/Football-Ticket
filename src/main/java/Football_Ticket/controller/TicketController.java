package Football_Ticket.controller;


import Football_Ticket.Dto.TicketDTO;
import Football_Ticket.model.Ticket;
import Football_Ticket.service.Impl.TicketService;
import com.nimbusds.oauth2.sdk.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import Football_Ticket.Dto.CreateTicketDTO;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

//    @GetMapping("/my-tickets")
//    public ResponseEntity<List<Ticket>> getMyTickets() {
//        List<Ticket> tickets = ticketService.getTicketsByCurrentUser();
//        return ResponseEntity.ok(tickets);
//    }

    @PostMapping("/create")
    public ResponseEntity<TicketDTO> createTicket(@RequestBody CreateTicketDTO dto) {
        try {
            TicketDTO ticketDTO = ticketService.CreateTicket(dto);
            return new ResponseEntity<>(ticketDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
   }

    // Get all tickets
    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    // Get ticket by ID
    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable String id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    // Delete ticket by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable String id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/tickets/my")
//    public ResponseEntity<List<Ticket>> getMyTickets() {
//        String userId = getCurrentUserId();
//        if (userId == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // No valid user ID found
//        }
//        List<Ticket> tickets = ticketService.getTicketsByUserId(userId);
//        return ResponseEntity.ok(tickets);
//    }


    private String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal(); // Cast the principal to a Jwt object
            return jwt.getClaim("sub"); // Retrieve the "sub" claim, which typically contains the user ID
        }
        return null; // Return null if no authentication or if the principal is not a JWT
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
//        "id": 1,// remove this pls
//        "name": "Test Event",
//        "dateTime": "2024-12-20T09:43:16",
//        "stadium": "Test Stadium",
//        "availableTickets": 100
//        }