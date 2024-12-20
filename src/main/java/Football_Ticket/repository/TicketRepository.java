package Football_Ticket.repository;

import Football_Ticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // Metoda shtesë për kërkime specifike nëse nevojitet
}

