package Football_Ticket.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 100)
    private String homeTeam;

    @Column(nullable = false, length = 100)
    private String awayTeam;

    @Column(nullable = false)
    private LocalDateTime matchDate;

    @ManyToOne
    @JoinColumn(name = "stadium_id", nullable = false)
    private Stadium stadium;

    @Column(name = "ticket_price", nullable = false)
    private BigDecimal ticketPrice;

    @Column(nullable = false, length = 20)
    private String status;

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

