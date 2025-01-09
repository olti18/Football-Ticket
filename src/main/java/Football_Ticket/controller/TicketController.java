package Football_Ticket.controller;


import Football_Ticket.Dto.TicketDTO;
import Football_Ticket.service.Impl.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Football_Ticket.Dto.CreateTicketDTO;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // Create Ticket
    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@RequestBody CreateTicketDTO dto) {
        TicketDTO ticket = ticketService.createTicket(dto);
        return ResponseEntity.ok(ticket);
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