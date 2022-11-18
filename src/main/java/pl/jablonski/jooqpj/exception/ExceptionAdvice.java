package pl.jablonski.jooqpj.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> restExceptionHandler(EntityNotFoundException exception) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.value()).build(),
                HttpStatus.NOT_FOUND);
    }
}
