package Football_Ticket.controller;

import Football_Ticket.Dto.StadiumCreateDTO;
import Football_Ticket.Dto.StadiumDTO;
import Football_Ticket.service.StadiumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stadiums")
public class StadiumController {

    private final StadiumService stadiumService;

    public StadiumController(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    // **Create Stadium**
    @PostMapping
    public ResponseEntity<StadiumDTO> createStadium(@RequestBody StadiumDTO stadiumDTO) {

        try {
            String createdBy = getCurrentUserId(); // Get Keycloak user ID
            StadiumDTO createdStadium = stadiumService.createStadium(stadiumDTO, createdBy);
            return new ResponseEntity<>(createdStadium, HttpStatus.CREATED);
            // logic to handle stadium creation
//            return ResponseEntity.ok(Stadium);
        }  catch (Exception ex) {
            // Log the general exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    // **Get All Stadiums**
    @GetMapping
    public ResponseEntity<List<StadiumDTO>> getAllStadiums() {
        List<StadiumDTO> stadiums = stadiumService.getAllStadiums();
        return ResponseEntity.ok(stadiums);
    }

    // **Get Stadium By ID**
    @GetMapping("/{id}")
    public ResponseEntity<StadiumDTO> getStadiumById(@PathVariable String id) {
        StadiumDTO stadium = stadiumService.getStadiumById(id);
        return ResponseEntity.ok(stadium);
    }

    // ️ **Update Stadium**
    @PutMapping("/{id}")
    public ResponseEntity<StadiumDTO> updateStadium(
            @PathVariable String id,
            @RequestBody StadiumDTO stadiumDTO
    ) {
        StadiumDTO updatedStadium = stadiumService.updateStadium(id, stadiumDTO);
        return ResponseEntity.ok(updatedStadium);
    }

    //  **Delete Stadium**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStadium(@PathVariable String id) {
        stadiumService.deleteStadium(id);
        return ResponseEntity.noContent().build();
    }

    // **Helper Method: Get Keycloak User ID**
//    private String getCurrentUserId() {
//        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return jwt.getClaim("sub"); // 'sub' contains the user ID from Keycloak
//    }
    private String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            return jwt.getClaim("sub"); // ID-ja e përdoruesit nga Keycloak
        }
        return null;
    }

}

