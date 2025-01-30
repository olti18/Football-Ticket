package Football_Ticket.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFeedbackDTO {
    private String userId;
    private String matchId;
    private String comment;
    private int rating;
}
