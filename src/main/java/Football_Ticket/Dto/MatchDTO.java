package Football_Ticket.Dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
public class MatchDTO {

    private String id;
    private String homeTeam;
    private String awayTeam;
    private LocalDateTime matchDate;
    private String stadiumId;
    private BigDecimal ticketPrice;
    private String status;

}
