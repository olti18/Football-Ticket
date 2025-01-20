package Football_Ticket.Dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DiscountDTO {
    private String id; // Unique identifier
    private String discountCode; // Discount code
    private BigDecimal discountValue; // Discount value
    private boolean isPercentageBased; // If the discount is percentage-based
    private LocalDateTime validFrom; // Start of discount validity
    private LocalDateTime validUntil; // End of discount validity
    private boolean active; // If the discount is active
    private String createdBy; // ID of the user who created the discount
    private LocalDateTime createdAt; // Creation timestamp
    private LocalDateTime updatedAt; // Last updated timestamp
}
