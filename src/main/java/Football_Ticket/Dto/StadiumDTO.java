package Football_Ticket.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StadiumDTO {

    private String id;
    private String name;
    private String location;
    private int capacity;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

