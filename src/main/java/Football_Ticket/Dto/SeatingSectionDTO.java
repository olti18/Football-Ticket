package Football_Ticket.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Data
@Builder
public class SeatingSectionDTO {
    private String id;
    private String stadiumId;
    private String sectionName;
    private int capacity;
    private BigDecimal priceMultiplier;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
