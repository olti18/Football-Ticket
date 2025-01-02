package Football_Ticket.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Data
@Builder
public class SeatingSectionDTOSave {
    private String stadiumId; // Reference ID only
    private String sectionName;
    private int capacity;
    private BigDecimal priceMultiplier;
}
