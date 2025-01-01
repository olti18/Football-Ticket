package Football_Ticket.service;

import Football_Ticket.Dto.StadiumDTO;

import java.util.List;

public interface  StadiumService {
    StadiumDTO createStadium(StadiumDTO stadiumDTO, String createdBy);

    StadiumDTO getStadiumById(String id);

    List<StadiumDTO> getAllStadiums();

    StadiumDTO updateStadium(String id, StadiumDTO stadiumDTO);

    void deleteStadium(String id);
}