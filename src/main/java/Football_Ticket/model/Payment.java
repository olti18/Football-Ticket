package Football_Ticket.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "created_by", nullable = false)
    private String createdBy; // Keycloak User ID

    @Column(nullable = false)
    private String paymentMethod; // Credit Card, PayPal, etc.

    @Column(nullable = false)
    private String paymentStatus; // Success, Failed

    private String transactionId;

    @CreationTimestamp
    private LocalDateTime paymentDate;


}
