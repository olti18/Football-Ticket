package Football_Ticket.service;

import Football_Ticket.Dto.CreateDiscountDTO;
import Football_Ticket.Dto.DiscountDTO;

import java.util.List;

public interface DiscountService {

    DiscountDTO createDiscount(CreateDiscountDTO dto);
    List<DiscountDTO> getAllDiscounts();

}
