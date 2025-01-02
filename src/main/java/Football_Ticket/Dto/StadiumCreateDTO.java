package Football_Ticket.Dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StadiumCreateDTO {
    private String name;
    private String location;
    private int capacity;
    private LocalDateTime createdAt;
}