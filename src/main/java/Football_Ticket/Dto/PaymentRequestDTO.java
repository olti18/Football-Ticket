package Football_Ticket.Dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequestDTO {
    private String ticketId;
    private BigDecimal amount;
    private String currency; // e.g., "usd"
    private String paymentMethodId;
}
