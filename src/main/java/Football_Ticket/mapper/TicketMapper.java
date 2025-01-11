package Football_Ticket.mapper;

import Football_Ticket.Dto.CreateTicketDTO;
import Football_Ticket.Dto.TicketDTO;
import Football_Ticket.model.Match;
import Football_Ticket.model.SeatingSection;
import Football_Ticket.model.Ticket;
import org.springframework.stereotype.Component;

import static org.springframework.data.jpa.domain.AbstractAuditable_.createdBy;

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



    // Map DTO to Entity
//    public Ticket toEntity(CreateTicketDTO dto, Match match, SeatingSection seatingSection, String userId) {
//        Ticket ticket = new Ticket();
//        ticket.setMatch(match);
//        ticket.setSeatingSection(seatingSection);
//        ticket.setSeatNumber(dto.getSeatNumber());
//        ticket.setPrice(dto.getPrice());
//        ticket.setStatus(dto.getStatus());
//        ticket.setCreatedBy(userId);
//        return ticket;
//    }
//
//    // Map Entity to DTO
//    public TicketDTO toDTO(Ticket ticket) {
//        TicketDTO dto = new TicketDTO();
//        dto.setId(ticket.getId());
//        dto.setMatchId(ticket.getMatch().getId());
//        dto.setSeatingSectionId(ticket.getSeatingSection().getId());
//        dto.setSeatNumber(ticket.getSeatNumber());
//        dto.setPrice(ticket.getPrice());
//        dto.setPaid(ticket.isPaid());
//        dto.setPurchaseDate(ticket.getPurchaseDate());
//        dto.setQrCode(ticket.getQrCode());
//        dto.setStatus(ticket.getStatus());
//        dto.setCreatedBy(ticket.getCreatedBy());
//        dto.setCreatedAt(ticket.getCreatedAt());
//        return dto;
//    }







//    public Ticket toEntity(CreateTicketDTO dto, Match match) {
//        Ticket ticket = new Ticket();
//        ticket.setMatch(match);
//        ticket.setSeatNumber(dto.getSeatNumber());
//        ticket.setPrice(dto.getPrice());
//        ticket.setStatus(dto.getStatus());
//        ticket.setCreatedBy(dto.getCreatedBy());
//        return ticket;
//    }
//
//    public TicketDTO toDTO(Ticket ticket) {
//        TicketDTO dto = new TicketDTO();
//        dto.setId(ticket.getId());
//        dto.setMatchId(ticket.getMatch().getId());
//        dto.setSeatNumber(ticket.getSeatNumber());
//        dto.setPrice(ticket.getPrice());
//        dto.setPaid(ticket.isPaid());
//        dto.setQrCode(ticket.getQrCode());
//        dto.setStatus(ticket.getStatus());
//        dto.setCreatedBy(ticket.getCreatedBy());
//        dto.setCreatedAt(ticket.getCreatedAt());
//        dto.setUpdatedAt(ticket.getUpdatedAt());
//        return dto;
//    }





//    public TicketDTO toDTO(Ticket ticket) {
//        TicketDTO dto = new TicketDTO();
//        dto.setId(ticket.getId());
//        dto.setMatchId(ticket.getMatch().getId());
//        dto.setSeatNumber(ticket.getSeatNumber());
//        dto.setPrice(ticket.getPrice());
//        dto.setPaid(ticket.isPaid());
//        dto.setPurchaseDate(ticket.getPurchaseDate());
//        dto.setQrCode(ticket.getQrCode());
//        dto.setStatus(ticket.getStatus());
//        return dto;
//    }
//
//    public Ticket toEntity(TicketDTO dto) {
//        Ticket ticket = new Ticket();
//        ticket.setSeatNumber(dto.getSeatNumber());
//        ticket.setPrice(dto.getPrice());
//        ticket.setPaid(dto.isPaid());
//        ticket.setPurchaseDate(dto.getPurchaseDate());
//        ticket.setQrCode(dto.getQrCode());
//        ticket.setStatus(dto.getStatus());
//        return ticket;
//    }
}
