package Football_Ticket.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId; // ID e përdoruesit nga Keycloak (UUID)

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private FootballEvent event;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private boolean isValid;

}

