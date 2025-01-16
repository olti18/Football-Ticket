
package Football_Ticket.mapper;

import Football_Ticket.Dto.CreateStadiumDTO;
import Football_Ticket.Dto.StadiumDTO;
import Football_Ticket.model.Stadium;

import org.springframework.stereotype.Component;

@Component
public class StadiumMapper {

    public Stadium toEntity(CreateStadiumDTO dto, String createdBy) {
        Stadium stadium = new Stadium();
        stadium.setName(dto.getName());
        stadium.setLocation(dto.getLocation());
        stadium.setCapacity(dto.getCapacity());
        stadium.setCreatedBy(createdBy);
        return stadium;
    }



    public StadiumDTO toDTO(Stadium stadium) {
        StadiumDTO dto = new StadiumDTO();
        dto.setId(stadium.getId());
        dto.setName(stadium.getName());
        dto.setLocation(stadium.getLocation());
        dto.setCapacity(stadium.getCapacity());
        dto.setCreatedBy(stadium.getCreatedBy());
        dto.setCreatedAt(stadium.getCreatedAt());
        dto.setUpdatedAt(stadium.getUpdatedAt());
        return dto;
    }
}
