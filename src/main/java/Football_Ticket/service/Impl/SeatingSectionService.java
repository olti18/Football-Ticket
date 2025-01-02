package Football_Ticket.service.Impl;

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

    // 🔍 Get All Sections
    public List<SeatingSectionDTO> getAllSeatingSections() {
        return seatingSectionRepository.findAll().stream()
                .map(seatingSectionMapper::toDTO)
                .collect(Collectors.toList());
    }

    // 🔍 Get Section by ID
    public SeatingSectionDTO getSeatingSectionById(String id) {
        SeatingSection section = seatingSectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seating Section not found"));
        return seatingSectionMapper.toDTO(section);
    }

    // ➕ Create Section
    public SeatingSectionDTO createSeatingSection(SeatingSectionDTO dto) {
        // Check if stadium exists
        Stadium stadium = stadiumRepository.findById(dto.getStadiumId())
                .orElseThrow(() -> new RuntimeException("Stadium not found"));

        // Map DTO to Entity
        SeatingSection section = seatingSectionMapper.mapToEntity(dto, stadium);

        // Get user ID from Keycloak token
        section.setCreatedBy(getCurrentUserId());

        // Save to database
        SeatingSection savedSection = seatingSectionRepository.save(section);

        // Map to DTO and return
        return seatingSectionMapper.toDTO(savedSection);
    }

    // ✏️ Update Section
    public SeatingSectionDTO updateSeatingSection(String id, SeatingSectionDTO dto) {
        SeatingSection section = seatingSectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seating Section not found"));

        // Update fields
        section.setSectionName(dto.getSectionName());
        section.setCapacity(dto.getCapacity());
        section.setPriceMultiplier(dto.getPriceMultiplier());

        // Save to database
        SeatingSection updatedSection = seatingSectionRepository.save(section);

        // Map to DTO and return
        return seatingSectionMapper.toDTO(updatedSection);
    }

    // 🗑️ Delete Section
    public void deleteSeatingSection(String id) {
        SeatingSection section = seatingSectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seating Section not found"));
        seatingSectionRepository.delete(section);
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
