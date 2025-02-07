package Football_Ticket.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TicketResponseDTO {
    private String id;
    private String matchName;
    private String seatingSectionId;
    private String seatNumber;
    private BigDecimal price;
    private LocalDateTime purchaseDate;
    private String qrCode;
    private String status;
    private String createdBy;
    private LocalDateTime createdAt;
    private boolean isPaid;
}

