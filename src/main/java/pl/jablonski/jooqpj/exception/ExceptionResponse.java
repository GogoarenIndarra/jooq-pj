package pl.jablonski.jooqpj.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ExceptionResponse {
    private int status;
    private String message;
}
