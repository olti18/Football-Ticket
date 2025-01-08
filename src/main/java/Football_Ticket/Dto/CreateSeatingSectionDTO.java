package Football_Ticket.Dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateSeatingSectionDTO {

    private String stadiumId;
    private String sectionName;
    private int capacity;
    private BigDecimal priceMultiplier;
    private String createdBy;

}
