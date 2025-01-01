package Football_Ticket.model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;

//@Getter
//@Setter
//@Entity
//public class Reservation {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "ticket_id", nullable = false)
//    private Ticket ticket;
//
//    @Column(nullable = false)
//    private String userId; // ID e përdoruesit nga Keycloak
//
//    @Column(nullable = false)
//    private LocalDateTime reservationTime;
//
//    @Column(nullable = false)
//    private boolean isConfirmed;
//
//
//}
