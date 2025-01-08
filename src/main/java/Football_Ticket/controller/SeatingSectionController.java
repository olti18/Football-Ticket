package Football_Ticket.controller;

import Football_Ticket.Dto.CreateSeatingSectionDTO;
import Football_Ticket.Dto.SeatingSectionDTO;
import Football_Ticket.service.Impl.SeatingSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/seating-sections")
public class SeatingSectionController {



    @Autowired
    private SeatingSectionService seatingSectionService;

    @PostMapping
    public ResponseEntity<SeatingSectionDTO> createSection(@RequestBody CreateSeatingSectionDTO dto) {
        return ResponseEntity.ok(seatingSectionService.createSeatingSection(dto)); // Replace with Keycloak user ID
    }

    @GetMapping
    public ResponseEntity<List<SeatingSectionDTO>> getAllSections() {
        return ResponseEntity.ok(seatingSectionService.getAllSections());
    }






//    @Autowired
//    private SeatingSectionService seatingSectionService;
//
//
//    @GetMapping
//    public ResponseEntity<List<SeatingSectionDTO>> getAllSeatingSections() {
//        List<SeatingSectionDTO> sections = seatingSectionService.getAllSeatingSections();
//        return ResponseEntity.ok(sections);
//    }
//
//
//    @GetMapping("/{id}")
//    public ResponseEntity<SeatingSectionDTO> getSeatingSectionById(@PathVariable String id) {
//        SeatingSectionDTO section = seatingSectionService.getSeatingSectionById(id);
//        return ResponseEntity.ok(section);
//    }
//
//
//    @PostMapping
//    public ResponseEntity<SeatingSectionDTO> createSeatingSection(@RequestBody SeatingSectionDTO dto) {
//        SeatingSectionDTO createdSection = seatingSectionService.createSeatingSection(dto);
//        return ResponseEntity.ok(createdSection);
//    }
//
//
//    @PutMapping("/{id}")
//    public ResponseEntity<SeatingSectionDTO> updateSeatingSection(@PathVariable String id, @RequestBody SeatingSectionDTO dto) {
//        SeatingSectionDTO updatedSection = seatingSectionService.updateSeatingSection(id, dto);
//        return ResponseEntity.ok(updatedSection);
//    }
//
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteSeatingSection(@PathVariable String id) {
//        seatingSectionService.deleteSeatingSection(id);
//        return ResponseEntity.noContent().build();
//    }



}
//{
//        "stadiumId": "stadium id",
//        "sectionName": "VIP Section",
//        "capacity": 150,
//        "priceMultiplier": 1.25
//        }
