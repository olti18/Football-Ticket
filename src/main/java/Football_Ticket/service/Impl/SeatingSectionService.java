package Football_Ticket.service.Impl;

import Football_Ticket.Dto.CreateSeatingSectionDTO;
import Football_Ticket.Dto.SeatingSectionDTO;
import Football_Ticket.mapper.SeatingSectionMapper;
import Football_Ticket.model.SeatingSection;
import Football_Ticket.model.Stadium;
import Football_Ticket.repository.SeatingSectionRepository;
import Football_Ticket.repository.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatingSectionService {


    @Autowired
    private SeatingSectionRepository seatingSectionRepository;

    @Autowired
    private StadiumRepository stadiumRepository;

    @Autowired
    private SeatingSectionMapper seatingSectionMapper;


    // Create Seating Section
    public SeatingSectionDTO createSeatingSection(CreateSeatingSectionDTO dto) {
        //String userId = jwtUtil.getCurrentUserId(); // Get logged-in user ID

        Stadium stadium = stadiumRepository.findById(dto.getStadiumId())
                .orElseThrow(() -> new RuntimeException("Stadium not found"));
        dto.setCreatedBy(getCurrentUserId());
        SeatingSection section = seatingSectionMapper.toEntity(dto, stadium);
        seatingSectionRepository.save(section);
        return seatingSectionMapper.toDTO(section);
    }

    // Get all sections
    public List<SeatingSectionDTO> getAllSections() {
        return seatingSectionRepository.findAll().stream()
                .map(seatingSectionMapper::toDTO)
                .collect(Collectors.toList());
    }


    private String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            return jwt.getClaim("sub"); // ID-ja e përdoruesit nga Keycloak
        }
        return null;
    }
}
