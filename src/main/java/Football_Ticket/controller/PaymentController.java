package Football_Ticket.controller;


import Football_Ticket.model.Discount;
import Football_Ticket.service.Impl.PaymentServiceImpl;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentService;


    @PostMapping("/create/{ticketId}")
    public ResponseEntity<String> createPaymentIntent(@PathVariable String ticketId,String discountCode) {
        try {
            PaymentIntent paymentIntent = paymentService.createPayment(ticketId, discountCode);
            return ResponseEntity.ok("Payment Successful: " + paymentIntent.getStatus());
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body("Payment Failed: " + e.getMessage());
        }
    }



}
