package Football_Ticket.controller;

import Football_Ticket.Dto.CreateFeedbackDTO;
import Football_Ticket.Dto.FeedbackDTO;
import Football_Ticket.model.Feedback;
import Football_Ticket.service.Impl.FeedbackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeedbackController {
    @Autowired
    private FeedbackServiceImpl feedbackService;

    @GetMapping("/feedbacks/current-user")
    public List<Feedback> getFeedbacksByCurrentUser() {
        return feedbackService.getFeedbacksByCurrentUser();
    }
    @PostMapping
    public ResponseEntity<FeedbackDTO> CreateFeedback(@RequestBody CreateFeedbackDTO dto){
        return ResponseEntity.ok(feedbackService.createFeedback(dto));
    }
}
