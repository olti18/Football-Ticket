//package Football_Ticket.controller;
//
////
//import java.math.BigDecimal; // Ensure this import is present
//import Football_Ticket.model.Payment;
//import Football_Ticket.repository.PaymentRepository;
//import Football_Ticket.repository.ReservationRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.util.List; // Ensure this import is present
//
//import java.time.LocalDateTime;
//@RestController
//@RequestMapping("/api/payment")
//public class PaymentController {
//
//    @Autowired
//    private PaymentRepository paymentRepository;
//
//    @Autowired
//    private ReservationRepository reservationRepository;
//
//    // Create payment for a reservation
//    @PostMapping
//    public ResponseEntity<Payment> createPayment(@RequestParam Long reservationId,
//                                                 @RequestParam BigDecimal amount,
//                                                 @RequestParam String paymentMethod) {
//        return reservationRepository.findById(reservationId)
//                .map(reservation -> {
//                    Payment payment = new Payment();
//                    payment.setReservation(reservation);
//                    payment.setAmount(amount);
//                    payment.setPaymentTime(LocalDateTime.now());
//                    payment.setPaymentMethod(paymentMethod);
//                    payment.setSuccessful(true); // You can modify this logic depending on the payment status
//                    return ResponseEntity.ok(paymentRepository.save(payment));
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    // Get all payments
//    @GetMapping
//    public ResponseEntity<List<Payment>> getAllPayments() {
//        return ResponseEntity.ok(paymentRepository.findAll()); // Correct type should be List<Payment>
//    }
//
//    // Get payment by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
//        return paymentRepository.findById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    // Update payment status (successful or not)
//    @PutMapping("/{id}/update-status")
//    public ResponseEntity<Payment> updatePaymentStatus(@PathVariable Long id, @RequestParam boolean isSuccessful) {
//        return paymentRepository.findById(id)
//                .map(payment -> {
//                    payment.setSuccessful(isSuccessful);
//                    return ResponseEntity.ok(paymentRepository.save(payment));
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//}
