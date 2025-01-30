package Football_Ticket.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FeedbackDTO {
    private Long id;
    private String userId;
    private String matchId; // Only match ID instead of full Match entity
    private String comment;
    private int rating;
    private LocalDateTime createdAt;
}
