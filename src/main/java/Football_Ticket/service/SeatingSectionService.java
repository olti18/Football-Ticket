package Football_Ticket.service;

import Football_Ticket.Dto.CreateSeatingSectionDTO;
import Football_Ticket.Dto.SeatingSectionDTO;
import Football_Ticket.Dto.StadiumDTO;
import Football_Ticket.model.SeatingSection;

import java.util.List;

public interface SeatingSectionService {
    SeatingSectionDTO createSeatingSection(CreateSeatingSectionDTO dto);
    List<SeatingSectionDTO> getAllSections();

}
