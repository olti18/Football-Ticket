package Football_Ticket.repository;

import Football_Ticket.Dto.TicketResponseDTO;
import Football_Ticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
    @Query("SELECT new Football_Ticket.Dto.TicketResponseDTO(" +
            "t.id, CONCAT(m.homeTeam, ' vs ', m.awayTeam), " +
            "t.seatingSection.id, t.seatNumber, t.price, " +
            "t.purchaseDate, t.qrCode, t.status, " +
            "t.createdBy, t.createdAt, t.isPaid) " +
            "FROM Ticket t " +
            "JOIN t.match m")
    List<TicketResponseDTO> getTicketsWithMatchDetails();

    List<Ticket> findByBoughtByAndIsPaid(String boughtBy, boolean isPaid);

}
