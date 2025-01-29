package Football_Ticket.repository;

import Football_Ticket.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, String> {
}
