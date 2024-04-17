package ctu.cit.se.post_service.exceptions.handlers;

import ctu.cit.se.post_service.dtos.others.ErrorDetails;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomExceptionHandling{
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception {
        ex.printStackTrace();
        return new ResponseEntity<>(ErrorDetails.builder().detail(request.getDescription(false)).createAt(LocalDateTime.now()).message(ex.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }
}