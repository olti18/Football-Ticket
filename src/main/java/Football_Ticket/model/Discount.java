package Football_Ticket.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 50, unique = true)
    private String discountCode; // Unique discount code (e.g., "SAVE10")

    @Column(nullable = false)
    private BigDecimal discountValue; // Discount value (e.g., 10.00 or 0.10)

    @Column(nullable = false)
    private boolean isPercentageBased;

    @Column(nullable = false)
    private LocalDateTime validFrom; // Discount start date

    @Column(nullable = false)
    private LocalDateTime validUntil; // Discount end date

    @Column(nullable = false)
    private boolean isActive = true; // Indicates if the discount is currently active

    @Column(name = "created_by", nullable = false)
    private String createdBy; // Keycloak User ID of the admin who created the discount

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    /**
     * Checks if the discount is valid based on the current date and time.
     */
    public boolean isValid() {
        LocalDateTime now = LocalDateTime.now();
        return isActive && now.isAfter(validFrom) && now.isBefore(validUntil);
    }

}
