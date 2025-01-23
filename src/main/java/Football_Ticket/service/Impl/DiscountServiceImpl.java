package Football_Ticket.service.Impl;

import Football_Ticket.Dto.CreateDiscountDTO;
import Football_Ticket.Dto.DiscountDTO;
import Football_Ticket.mapper.DiscountMapper;
import Football_Ticket.model.Discount;
import Football_Ticket.repository.DiscountRepository;
import Football_Ticket.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.keycloak.util.JsonSerialization.mapper;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private DiscountMapper discountMapper;


    public DiscountDTO createDiscount(CreateDiscountDTO dto){
        Discount discount = discountMapper.toEntity(dto);
        discount.setCreatedBy(getCurrentUserId());
        //discount.setPercentageBased(false);
        discount = discountRepository.save(discount);
        return  discountMapper.toDTO(discount);
    }

    public List<DiscountDTO> getAllDiscounts(){
        return discountRepository.findAll().stream()
                .map(discountMapper::toDTO)
                .collect(Collectors.toList());
    }

    private String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            return jwt.getClaim("sub"); // ID-ja e përdoruesit nga Keycloak
        }
        return null;
    }

}
