package Football_Ticket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {



//    @ExceptionHandler(EntityNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ErrorResponse handleNotFound(Exception e) {
//        return new ErrorResponse(
//                e.getMessage(),
//                HttpStatus.NOT_FOUND.value(),
//                ZonedDateTime.now().toLocalDateTime().toString()
//        );
//    }
//
//
//    @ExceptionHandler(InternalServerErrorException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ErrorResponse handleInternalError(Exception e) {
//        return new ErrorResponse(
//                e.getMessage(),
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                ZonedDateTime.now().toLocalDateTime().toString()
//        );
//    }
//
//
//    @ExceptionHandler(BadRequestException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponse handleBadRequest(Exception e) {
//        return new ErrorResponse(
//                e.getMessage(),
//                HttpStatus.BAD_REQUEST.value(),
//                ZonedDateTime.now().toLocalDateTime().toString()
//        );
//    }



}


