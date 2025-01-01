//package Football_Ticket.controller;
//
//import Football_Ticket.model.Reservation;
//import Football_Ticket.repository.ReservationRepository;
//import Football_Ticket.repository.TicketRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/reservations")
//public class ReservationController {
//
//    @Autowired
//    private ReservationRepository reservationRepository;
//
//    @Autowired
//    private TicketRepository ticketRepository;
//
//    @PostMapping
//    public ResponseEntity<Reservation> createReservation(@RequestParam Long ticketId) {
//        return ticketRepository.findById(ticketId)
//                .map(ticket -> {
//                    Reservation reservation = new Reservation();
//                    reservation.setTicket(ticket);
//                    reservation.setUserId(getCurrentUserId()); // Merr ID-në e përdoruesit nga Keycloak
//                    reservation.setReservationTime(LocalDateTime.now());
//                    reservation.setConfirmed(false); // Rezervimi fillimisht nuk është i konfirmuar
//                    return ResponseEntity.ok(reservationRepository.save(reservation));
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @GetMapping
//    public List<Reservation> getAllReservations() {
//        return reservationRepository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
//        return reservationRepository.findById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PutMapping("/{id}/confirm")
//    public ResponseEntity<Reservation> confirmReservation(@PathVariable Long id) {
//        return reservationRepository.findById(id)
//                .map(reservation -> {
//                    reservation.setConfirmed(true);
//                    return ResponseEntity.ok(reservationRepository.save(reservation));
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    private String getCurrentUserId() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
//            Jwt jwt = (Jwt) authentication.getPrincipal();
//            return jwt.getClaim("sub"); // ID-ja e përdoruesit nga Keycloak
//        }
//        return null;
//    }
//}
//
