package Football_Ticket.mapper;

import Football_Ticket.Dto.CreateDiscountDTO;
import Football_Ticket.Dto.DiscountDTO;
import Football_Ticket.model.Discount;
import org.springframework.stereotype.Component;

import static org.springframework.data.jpa.domain.AbstractAuditable_.createdBy;

@Component
public class DiscountMapper {
    //, String createdBy
    public Discount toEntity(CreateDiscountDTO dto) {
        Discount discount = new Discount();
        discount.setDiscountCode(dto.getDiscountCode());
        discount.setDiscountValue(dto.getDiscountValue());
        discount.setPercentageBased(dto.isPercentageBased());
        discount.setValidFrom(dto.getValidFrom());
        discount.setValidUntil(dto.getValidUntil());
        //discount.setCreatedBy(dto.getCreatedBy());
        discount.setActive(true); // Default to active when created
        return discount;
    }

    /**
     * Maps a Discount entity to a DiscountDTO.
     *
     * @param discount the Discount entity to map.
     * @return a DiscountDTO.
     */
    public DiscountDTO toDTO(Discount discount) {
        DiscountDTO dto = new DiscountDTO();
        dto.setId(discount.getId());
        dto.setDiscountCode(discount.getDiscountCode());
        dto.setDiscountValue(discount.getDiscountValue());
        dto.setPercentageBased(discount.isPercentageBased());
        dto.setValidFrom(discount.getValidFrom());
        dto.setValidUntil(discount.getValidUntil());
        dto.setActive(discount.isActive());
        dto.setCreatedBy(discount.getCreatedBy());
        dto.setCreatedAt(discount.getCreatedAt());
        dto.setUpdatedAt(discount.getUpdatedAt());
        return dto;


    }
}