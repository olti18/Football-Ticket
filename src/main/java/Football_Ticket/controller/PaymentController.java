package Football_Ticket.controller;


import Football_Ticket.model.Ticket;
import Football_Ticket.service.Impl.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @PostMapping("/create/{ticketId}")
    public ResponseEntity<String> createPaymentIntent(@PathVariable String ticketId) {
        try {
            PaymentIntent paymentIntent = paymentService.createPayment(ticketId);
            return ResponseEntity.ok("Payment Successful: " + paymentIntent.getStatus());
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body("Payment Failed: " + e.getMessage());
        }
    }



}
