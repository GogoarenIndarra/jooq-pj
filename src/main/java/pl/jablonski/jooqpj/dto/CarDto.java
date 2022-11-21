package pl.jablonski.jooqpj.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class CarDto implements Serializable {

    private final String brand;
    private final String model;
    private final Long customerId;
}
