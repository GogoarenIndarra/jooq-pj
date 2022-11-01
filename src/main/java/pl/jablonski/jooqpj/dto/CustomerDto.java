package pl.jablonski.jooqpj.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class CustomerDto implements Serializable {

    private final String name;
    private final String surname;
    private final String email;
    private final String phone;
}
