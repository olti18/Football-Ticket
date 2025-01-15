package Football_Ticket.service;

import Football_Ticket.model.Ticket;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import java.util.List;

public interface PaymentService{

    List<Ticket> getPaidTicketsByCurrentUser();
    PaymentIntent createPayment(String ticketId) throws StripeException;

}
