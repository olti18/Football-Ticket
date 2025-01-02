package Football_Ticket.repository;

import Football_Ticket.model.SeatingSection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatingSectionRepository extends JpaRepository<SeatingSection, String> {
}

