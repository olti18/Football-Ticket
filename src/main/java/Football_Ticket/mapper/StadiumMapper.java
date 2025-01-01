//package Football_Ticket.mapper;
//
//
//import Football_Ticket.Dto.StadiumDTO;
//import Football_Ticket.model.Stadium;
//import org.mapstruct.Mapper;
//
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//
////@Component
//@Mapper(componentModel = "spring")
//@Component
//public interface StadiumMapper {
//
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "name", target = "name")
//    @Mapping(source = "location", target = "location")
//    @Mapping(source = "capacity", target = "capacity")
//    @Mapping(source = "createdBy", target = "createdBy")
//    @Mapping(source = "createdAt", target = "createdAt")
//    @Mapping(source = "updatedAt", target = "updatedAt")
//    StadiumDTO toDTO(Stadium stadium);
//
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "name", target = "name")
//    @Mapping(source = "location", target = "location")
//    @Mapping(source = "capacity", target = "capacity")
//    @Mapping(source = "createdBy", target = "createdBy")
//    @Mapping(source = "createdAt", target = "createdAt")
//    @Mapping(source = "updatedAt", target = "updatedAt")
//    Stadium toEntity(StadiumDTO stadiumDTO);
//}
package Football_Ticket.mapper;

import Football_Ticket.Dto.StadiumDTO;
import Football_Ticket.model.Stadium;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StadiumMapper {


    @Mapping(source = "location", target = "location")
    StadiumDTO toDTO(Stadium stadium);

    @Mapping(source = "location", target = "location")
    Stadium toEntity(StadiumDTO stadiumDTO);

  
//    StadiumDTO toDTO(Stadium stadium);
//
//    // Map StadiumDTO to Stadium
//    Stadium toEntity(StadiumDTO stadiumDTO);
}
