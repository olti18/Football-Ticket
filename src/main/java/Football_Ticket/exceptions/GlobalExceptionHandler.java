//package Football_Ticket.exceptions;
//
//import Football_Ticket.Dto.ErrorResponse;
//import jakarta.persistence.EntityNotFoundException;
//import jakarta.ws.rs.BadRequestException;
//import jakarta.ws.rs.InternalServerErrorException;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.time.ZonedDateTime;
//
//@ControllerAdvice
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(EntityNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ErrorResponse handleNotFound(EntityNotFoundException e) {
//        return new ErrorResponse(
//                e.getMessage(),
//                HttpStatus.NOT_FOUND.value(),
//                ZonedDateTime.now().toLocalDateTime().toString()
//        );
//    }
//
//    @ExceptionHandler(InternalServerErrorException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ErrorResponse handleInternalError(InternalServerErrorException e) {
//        return new ErrorResponse(
//                e.getMessage(),
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                ZonedDateTime.now().toLocalDateTime().toString()
//        );
//    }
//
//    @ExceptionHandler(BadRequestException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponse handleBadRequest(BadRequestException e) {
//        return new ErrorResponse(
//                e.getMessage(),
//                HttpStatus.BAD_REQUEST.value(),
//                ZonedDateTime.now().toLocalDateTime().toString()
//        );
//    }
//}
package Football_Ticket.exceptions;

import Football_Ticket.Dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(EntityNotFoundException e) {
        return new ErrorResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                ZonedDateTime.now().toLocalDateTime().toString()
        );
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalError(InternalServerErrorException e) {
        return new ErrorResponse(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ZonedDateTime.now().toLocalDateTime().toString()
        );
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequest(BadRequestException e) {
        return new ErrorResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                ZonedDateTime.now().toLocalDateTime().toString()
        );
    }

    // Optional: Log a warning if unexpected exceptions are caught
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneralException(Exception e) {
        return new ErrorResponse(
                "Unexpected error occurred: " + e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ZonedDateTime.now().toLocalDateTime().toString()
        );
    }

}
