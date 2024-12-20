package Football_Ticket.repository;

import Football_Ticket.model.FootballEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import Football_Ticket.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

