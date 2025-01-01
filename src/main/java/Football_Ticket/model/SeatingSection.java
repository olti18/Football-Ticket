package Football_Ticket.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "seating_sections")
public class SeatingSection {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "stadium_id", nullable = false)
    private Stadium stadium;

    @Column(nullable = false, length = 50)
    private String sectionName;

    @Column(nullable = false)
    private int capacity;

    @Column(name = "price_multiplier", nullable = false)
    private BigDecimal priceMultiplier = BigDecimal.valueOf(1.0);

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

