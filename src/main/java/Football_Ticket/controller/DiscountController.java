package Football_Ticket.controller;

import Football_Ticket.Dto.CreateDiscountDTO;
import Football_Ticket.Dto.DiscountDTO;
import Football_Ticket.service.DiscountService;
import com.stripe.model.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }
    @PostMapping
    public ResponseEntity<DiscountDTO> createDiscount(
            @RequestBody CreateDiscountDTO dto) {
        DiscountDTO discountDTO = discountService.createDiscount(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(discountDTO);
    }

    @GetMapping
    public ResponseEntity<List<DiscountDTO>> getAllDiscounts() {
        List<DiscountDTO> discounts = discountService.getAllDiscounts();
        return ResponseEntity.ok(discounts);
    }

}
