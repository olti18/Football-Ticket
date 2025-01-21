package Football_Ticket.exceptions;

public class InternalServerErrorException extends RuntimeException{

    public InternalServerErrorException(String message) {
        super(message);
    }


    public InternalServerErrorException() {
        super("An internal server error occurred");
    }


}
