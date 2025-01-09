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



}
//{
//        "stadiumId": "stadium id",
//        "sectionName": "VIP Section",
//        "capacity": 150,
//        "priceMultiplier": 1.25
//        }
