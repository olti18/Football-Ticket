package Football_Ticket.repository;


import Football_Ticket.model.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StadiumRepository extends JpaRepository<Stadium, String> {

}