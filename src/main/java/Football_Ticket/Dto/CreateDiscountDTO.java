package Football_Ticket.Dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateDiscountDTO {

    private String discountCode; // Unique discount code
    private BigDecimal discountValue; // Discount value
    private boolean isPercentageBased; // If the discount is percentage-based
    private LocalDateTime validFrom; // Valid from date
    private LocalDateTime validUntil; // Valid until date
    //private String createdBy;
}
