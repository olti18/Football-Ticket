package Football_Ticket.repository;

import Football_Ticket.model.FootballEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FootballEventRepository extends JpaRepository<FootballEvent, Long> {

}

