package pl.jablonski.jooqpj.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class MechanicDto implements Serializable {

    private final String name;
    private final String surname;
}
