package Football_Ticket.service;

import Football_Ticket.Dto.CreateTicketDTO;
import Football_Ticket.Dto.TicketDTO;

import java.util.List;

public interface TicketService {
    TicketDTO CreateTicket(CreateTicketDTO dto);
    //List<TicketDTO> getAllTickets();
    TicketDTO getTicketById(String id);
    void deleteTicket(String id);
}
