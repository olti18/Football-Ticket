package Football_Ticket.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class CreateTicketDTO {

    private String matchId;
    private String seatingSectionId;
    private String seatNumber;
    private BigDecimal price;
    private String status;

}
