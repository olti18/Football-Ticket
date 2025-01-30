package Football_Ticket.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "feedbacks")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId; // Keycloak User ID

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = true)
    private Match match;

    @Column(nullable = false, length = 500)
    private String comment;

    @Column(nullable = false)
    private int rating; // e.g., 1 to 5 stars

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();


}

