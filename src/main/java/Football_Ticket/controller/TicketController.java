package Football_Ticket.controller;


import Football_Ticket.Dto.TicketDTO;
import Football_Ticket.Dto.TicketResponseDTO;
import Football_Ticket.model.Ticket;
import Football_Ticket.service.Impl.PaymentServiceImpl;
import Football_Ticket.service.Impl.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import Football_Ticket.Dto.CreateTicketDTO;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class TicketController {
    @Autowired
    private TicketServiceImpl ticketService;

    @Autowired
    private PaymentServiceImpl paymentService;

    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> getAllTicketss() {
        List<TicketResponseDTO> tickets = ticketService.getAllTicketsWithMatchName();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/purchased")
    public ResponseEntity<List<Ticket>> getPurchasedTickets() {
        try {
            List<Ticket> tickets = paymentService.getPaidTicketsByCurrentUser();
            return ResponseEntity.ok(tickets);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(null); // Handle errors gracefully
        }
    }

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

//    @GetMapping
//    public ResponseEntity<List<TicketDTO>> getAllTickets() {
//        return ResponseEntity.ok(ticketService.getAllTickets());
//    }

//    // Get ticket by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<TicketDTO> getTicketById(@PathVariable String id) {
//        return ResponseEntity.ok(ticketService.getTicketById(id));
//    }


//
//    // Delete ticket by ID
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTicket(@PathVariable String id) {
//        ticketService.deleteTicket(id);
//        return ResponseEntity.noContent().build();
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
