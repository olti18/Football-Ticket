package Football_Ticket.mapper;

import Football_Ticket.Dto.SeatingSectionDTO;
import Football_Ticket.Dto.SeatingSectionDTOSave;
import Football_Ticket.model.SeatingSection;
import Football_Ticket.model.Stadium;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

//@Mapper
@Component
public class SeatingSectionMapper {

    // Map Entity → DTO
    public SeatingSectionDTO toDTO(SeatingSection section) {
        return SeatingSectionDTO.builder()
                .id(section.getId())
                .stadiumId(section.getStadium().getId()) // Map Stadium ID
                .sectionName(section.getSectionName())
                .capacity(section.getCapacity())
                .priceMultiplier(section.getPriceMultiplier())
                .createdBy(section.getCreatedBy())
                .createdAt(section.getCreatedAt())
                .updatedAt(section.getUpdatedAt())
                .build();
    }

    // Map DTO → Entity
    public SeatingSection mapToEntity(SeatingSectionDTO dto, Stadium stadium) {
        SeatingSection section = new SeatingSection();
        section.setStadium(stadium);
        section.setSectionName(dto.getSectionName());
        section.setCapacity(dto.getCapacity());
        section.setPriceMultiplier(dto.getPriceMultiplier());
        section.setCreatedBy(dto.getCreatedBy());
        return section;
    }

}
