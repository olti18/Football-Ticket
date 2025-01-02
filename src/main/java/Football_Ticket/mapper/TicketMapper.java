package Football_Ticket.mapper;

import Football_Ticket.Dto.TicketDTO;
import Football_Ticket.model.Match;
import Football_Ticket.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    // Map Entity to DTO
    public TicketDTO mapToDTO(Ticket ticket) {
        TicketDTO dto = new TicketDTO();
        dto.setId(ticket.getId());
        dto.setMatchId(ticket.getMatch().getId());
        dto.setSeatNumber(ticket.getSeatNumber());
        dto.setPrice(ticket.getPrice());
        dto.setPaid(ticket.isPaid());
        dto.setPurchaseDate(ticket.getPurchaseDate());
        dto.setQrCode(ticket.getQrCode());
        dto.setStatus(ticket.getStatus());
        return dto;
    }

    // Map DTO to Entity
    public Ticket mapToEntity(TicketDTO dto, Match match) {
        Ticket ticket = new Ticket();
        ticket.setMatch(match);
        ticket.setSeatNumber(dto.getSeatNumber());
        ticket.setPrice(dto.getPrice());
        ticket.setPaid(dto.isPaid());
        ticket.setPurchaseDate(dto.getPurchaseDate());
        ticket.setQrCode(dto.getQrCode());
        ticket.setStatus(dto.getStatus());
        return ticket;
    }
}
