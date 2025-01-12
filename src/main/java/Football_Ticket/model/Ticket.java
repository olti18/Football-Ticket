package Football_Ticket.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @ManyToOne
    @JoinColumn(name = "seating_section_id", nullable = false)
    private SeatingSection seatingSection;

    @Column(name = "seat_number", nullable = false, length = 10)
    private String seatNumber;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "is_paid", nullable = false)
    private boolean isPaid = false;

    @Column(name = "purchase_date", nullable = false)
    private LocalDateTime purchaseDate = LocalDateTime.now();

    @Column(name = "qr_code")
    private String qrCode;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "bought_by", nullable = true)
    private String boughtBy; // Keycloak User ID of the buyer


    @Column(name = "created_by", nullable = false)
    private String createdBy; // Keycloak User ID

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}









//@Getter
//@Setter
//@Entity
//public class Ticket {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String userId; // ID e përdoruesit nga Keycloak (UUID)
//
//    @ManyToOne
//    @JoinColumn(name = "event_id", nullable = false)
//    private FootballEvent event;
//
//    @Column(nullable = false)
//    private BigDecimal price;
//
//    @Column(nullable = false)
//    private boolean isValid;
//
//}

