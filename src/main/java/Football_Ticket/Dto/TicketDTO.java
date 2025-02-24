package Football_Ticket.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class TicketDTO {

    private String id;
    private String matchId;
    private String seatingSectionId;
    private String seatNumber;
    private BigDecimal price;
    private boolean isPaid;
    private LocalDateTime purchaseDate;
    private String qrCode;
    private String status;
    private String createdBy;
    private LocalDateTime createdAt;

    // adedd
//    private String homeTeam;        // Add this field
//    private String awayTeam;        // Add this field
//    private String matchName;       // Add this field
}
