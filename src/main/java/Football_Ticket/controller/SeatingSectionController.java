package Football_Ticket.controller;

import Football_Ticket.Dto.CreateSeatingSectionDTO;
import Football_Ticket.Dto.SeatingSectionDTO;
import Football_Ticket.service.Impl.SeatingSectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/seating-sections")
public class SeatingSectionController {



    @Autowired
    private SeatingSectionServiceImpl seatingSectionService;

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
