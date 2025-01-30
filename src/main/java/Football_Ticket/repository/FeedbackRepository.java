package Football_Ticket.repository;

import Football_Ticket.model.Feedback;
import Football_Ticket.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
