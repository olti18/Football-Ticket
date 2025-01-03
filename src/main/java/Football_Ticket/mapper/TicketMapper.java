package Football_Ticket.mapper;

import Football_Ticket.Dto.TicketDTO;
import Football_Ticket.model.Match;
import Football_Ticket.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public TicketDTO toDTO(Ticket ticket) {
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

    public Ticket toEntity(TicketDTO dto) {
        Ticket ticket = new Ticket();
        ticket.setSeatNumber(dto.getSeatNumber());
        ticket.setPrice(dto.getPrice());
        ticket.setPaid(dto.isPaid());
        ticket.setPurchaseDate(dto.getPurchaseDate());
        ticket.setQrCode(dto.getQrCode());
        ticket.setStatus(dto.getStatus());
        return ticket;
    }
}
