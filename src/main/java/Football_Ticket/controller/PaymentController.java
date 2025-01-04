package Football_Ticket.controller;

import Football_Ticket.Dto.PaymentRequestDTO;
import Football_Ticket.service.Impl.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentRequestDTO request) {
        try {
            PaymentIntent paymentIntent = paymentService.createPayment(request);
            return ResponseEntity.ok(paymentIntent.getClientSecret());
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/success")
    public ResponseEntity<String> paymentSuccess() {
        return ResponseEntity.ok("Payment Successful");
    }

    @GetMapping("/cancel")
    public ResponseEntity<String> paymentCancel() {
        return ResponseEntity.ok("Payment Canceled");
    }
}
