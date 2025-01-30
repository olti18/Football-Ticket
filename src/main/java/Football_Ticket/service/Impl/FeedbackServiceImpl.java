package Football_Ticket.service.Impl;

import Football_Ticket.Dto.CreateDiscountDTO;
import Football_Ticket.Dto.CreateFeedbackDTO;
import Football_Ticket.Dto.FeedbackDTO;
import Football_Ticket.config.KeycloakUtils;
import Football_Ticket.mapper.FeedbackMapper;
import Football_Ticket.model.Feedback;
import Football_Ticket.model.Match;
import Football_Ticket.repository.FeedbackRepository;
import Football_Ticket.repository.MatchRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl {

    private final FeedbackRepository feedbackRepository;
    private final MatchRepository matchRepository;
    private final FeedbackMapper feedbackMapper;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository,MatchRepository matchRepository,FeedbackMapper feedbackMapper){
        this.feedbackMapper = feedbackMapper;
        this.feedbackRepository = feedbackRepository;
        this.matchRepository = matchRepository;
    }

    public FeedbackDTO createFeedback(CreateFeedbackDTO dto){
        Match match = matchRepository.findById(dto.getMatchId()) // Use dto.getMatchId() directly
                .orElseThrow(() -> new RuntimeException("match not found"));

        dto.setUserId(getCurrentUserId());

        Feedback feedback = feedbackMapper.toEntity(dto,match);
        feedbackRepository.save(feedback);
        return feedbackMapper.toDTO(feedback);
    }

    public List<Feedback> getFeedbacksByCurrentUser(){
        String currentUserId = getCurrentUserId();
        List<Feedback> allFeedbacks = feedbackRepository.findAll();

        // Filter feedbacks for the current user
        return allFeedbacks.stream()
                .filter(feedback -> feedback.getUserId().equals(currentUserId))
                .collect(Collectors.toList());
    }

    private String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            return jwt.getClaim("sub"); // ID-ja e përdoruesit nga Keycloak
        }
        return null;
    }


}
