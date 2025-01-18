package Football_Ticket.repository;

import Football_Ticket.model.Discount;
import com.google.common.base.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
    Optional<Discount> findByDiscountCode(String discountCode);

}
