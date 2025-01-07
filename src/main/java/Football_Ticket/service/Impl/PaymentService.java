    package Football_Ticket.service.Impl;

    import Football_Ticket.Dto.PaymentRequestDTO;
    import Football_Ticket.model.Payment;
    import Football_Ticket.model.Ticket;
    import Football_Ticket.repository.PaymentRepository;
    import Football_Ticket.repository.TicketRepository;
    import com.stripe.exception.StripeException;
    import com.stripe.model.PaymentIntent;
    import com.stripe.param.PaymentIntentCreateParams;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.oauth2.jwt.Jwt;
    import org.springframework.stereotype.Service;

    import java.math.BigDecimal;
    import java.time.Instant;
    import java.time.ZoneId;


    import com.stripe.Stripe;

    @Service
    public class PaymentService {
        @Autowired
        private PaymentRepository paymentRepository;

        @Autowired
        private TicketRepository ticketRepository;

        public PaymentIntent createPayment(String ticketId) throws StripeException {
            // Set Stripe API Key
           //Stripe.apiKey = "sk_test_51QdfqtLat6bLARBD25uUKyBFYhOIQU7arw0qCs9KpTSnrGX64OdtwUnq4G2VSbQUOe1A0Lh6H1eKNYHIsw9vmLn900XVP9zZ5j";
            Stripe.apiKey = "sk_test_51QdfqtLat6bLARBD25uUKyBFYhOIQU7arw0qCs9KpTSnrGX64OdtwUnq4G2VSbQUOe1A0Lh6H1eKNYHIsw9vmLn900XVP9zZ5j"; // Replace with your actual API key

            // Validate Ticket
            Ticket ticket = ticketRepository.findById(ticketId)
                    .orElseThrow(() -> new RuntimeException("Ticket not found"));

            if (ticket.isPaid()) {
                throw new RuntimeException("Ticket is already paid!");
            }

            // Create PaymentIntent
            PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                    .setAmount(ticket.getPrice().multiply(BigDecimal.valueOf(100)).longValue()) // Convert to cents
                    .setCurrency("usd") // Replace with your preferred currency
                    .setPaymentMethod("pm_card_visa") // Test Payment Method ID (use real IDs in production)
                    .setConfirm(true) // Auto confirm payment
                    .setReturnUrl("http://localhost:3000/api/payments/success") // Redirect on success
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(createParams);

            // Save Payment Record
            Payment payment = new Payment();
            payment.setTicket(ticket);
            payment.setAmount(ticket.getPrice());
            payment.setPaymentMethod("pm_card_visa"); // Use saved/test method
            payment.setPaymentStatus(paymentIntent.getStatus());
            payment.setTransactionId(paymentIntent.getId());

            // Set Payment Date
            payment.setPaymentDate(
                    Instant.ofEpochSecond(paymentIntent.getCreated())
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime()
            );

            payment.setCreatedBy(getCurrentUserId());
            paymentRepository.save(payment);

            // Update Ticket status if payment successful
            if ("succeeded".equals(paymentIntent.getStatus())) {
                ticket.setPaid(true);
                ticketRepository.save(ticket);
            }

            return paymentIntent;
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







    //
    //@Service
    //public class PaymentService {
    //
    //    @Autowired
    //    private PaymentRepository paymentRepository;
    //
    //    @Autowired
    //    private TicketRepository ticketRepository;
    //
    //    public PaymentIntent createPayment(PaymentRequestDTO request) throws StripeException {
    //        // Validate Ticket
    //        Ticket ticket = ticketRepository.findById(request.getTicketId())
    //                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    //
    //        // Create Stripe PaymentIntent
    //        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
    //                .setAmount(request.getAmount().multiply(BigDecimal.valueOf(100)).longValue()) // Convert to cents
    //                .setCurrency(request.getCurrency())
    //                .setPaymentMethod(request.getPaymentMethodId())
    //                .setConfirm(true)
    //                .setReturnUrl("http://localhost:8080/api/payments/success")
    //                .build();
    //
    //        PaymentIntent paymentIntent = PaymentIntent.create(createParams);
    //
    //        // Save Payment Record
    //        Payment payment = new Payment();
    //        payment.setTicket(ticket);
    //        payment.setAmount(request.getAmount());
    //        payment.setPaymentMethod(request.getPaymentMethodId());
    //        payment.setPaymentStatus(paymentIntent.getStatus());
    //        payment.setTransactionId(paymentIntent.getId());
    //        payment.setPaymentDate(
    //                Instant.ofEpochSecond(paymentIntent.getCreated())
    //                        .atZone(ZoneId.systemDefault())
    //                        .toLocalDateTime()
    //        );
    //        payment.setCreatedBy(getCurrentUserId());
    //
    //        paymentRepository.save(payment);
    //
    //        return paymentIntent;
    //    }
    //    private String getCurrentUserId() {
    //        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
    //            Jwt jwt = (Jwt) authentication.getPrincipal();
    //            return jwt.getClaim("sub"); // ID-ja e përdoruesit nga Keycloak
    //        }
    //        return null;
    //    }
    //}
