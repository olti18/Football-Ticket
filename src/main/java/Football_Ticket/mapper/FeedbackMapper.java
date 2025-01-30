package Football_Ticket.mapper;

import Football_Ticket.Dto.CreateFeedbackDTO;
import Football_Ticket.Dto.FeedbackDTO;
import Football_Ticket.Dto.MatchDTO;
import Football_Ticket.model.Feedback;
import Football_Ticket.model.Match;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FeedbackMapper {

    public Feedback toEntity(CreateFeedbackDTO dto, Match match) {
        Feedback feedback = new Feedback();
        feedback.setUserId(dto.getUserId());
        feedback.setMatch(match); // Setting the Match entity
        feedback.setComment(dto.getComment());
        feedback.setRating(dto.getRating());
        feedback.setCreatedAt(LocalDateTime.now()); // Default creation time
        return feedback;
    }

    public FeedbackDTO toDTO(Feedback feedback) {
        FeedbackDTO dto = new FeedbackDTO();
        dto.setId(feedback.getId());
        dto.setUserId(feedback.getUserId());
        dto.setMatchId(String.valueOf(feedback.getMatch()));//!= null ? Long.valueOf(feedback.getMatch().getId()) : null); // Avoids NullPointerException
        dto.setComment(feedback.getComment());
        dto.setRating(feedback.getRating());
        dto.setCreatedAt(feedback.getCreatedAt());
        return dto;
    }

}
