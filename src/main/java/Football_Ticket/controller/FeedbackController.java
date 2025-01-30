package Football_Ticket.controller;

import Football_Ticket.Dto.CreateFeedbackDTO;
import Football_Ticket.Dto.FeedbackDTO;
import Football_Ticket.model.Feedback;
import Football_Ticket.service.Impl.FeedbackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedbackController {
    @Autowired
    private FeedbackServiceImpl feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackDTO> CreateFeedback(@RequestBody CreateFeedbackDTO dto){
        return ResponseEntity.ok(feedbackService.createFeedback(dto));
    }
}
