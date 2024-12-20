package Football_Ticket.repository;

import Football_Ticket.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Mund të shtoni metoda të tjera për kërkesa specifike, nëse nevojiten
}
