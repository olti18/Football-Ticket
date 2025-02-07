package Football_Ticket.mapper;

import Football_Ticket.Dto.CreateTicketDTO;
import Football_Ticket.Dto.TicketDTO;
import Football_Ticket.model.Match;
import Football_Ticket.model.SeatingSection;
import Football_Ticket.model.Ticket;
import org.springframework.stereotype.Component;


@Component
public class TicketMapper {

    public Ticket toEntity(CreateTicketDTO dto, Match match, SeatingSection seatingSection, String userId) {
        Ticket ticket = new Ticket();
        ticket.setMatch(match);
        ticket.setSeatingSection(seatingSection);
        ticket.setCreatedBy(userId);
        ticket.setPrice(match.getTicketPrice().multiply(seatingSection.getPriceMultiplier())); // Calculate price here
        ticket.setPaid(false); // Default value
        return ticket;
    }


    public TicketDTO toDTO(Ticket ticket) {
        TicketDTO dto = new TicketDTO();
        dto.setId(ticket.getId());
        dto.setMatchId(ticket.getMatch().getId());
        dto.setSeatingSectionId(ticket.getSeatingSection().getId());
        dto.setPrice(ticket.getPrice());
        dto.setCreatedBy(ticket.getCreatedBy());
        dto.setPaid(ticket.isPaid());
        return dto;
    }



}
