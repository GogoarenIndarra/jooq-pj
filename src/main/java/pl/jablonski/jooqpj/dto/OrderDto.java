package pl.jablonski.jooqpj.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
public class OrderDto implements Serializable {

    private final BigDecimal price;
    private final LocalDate finishDate;
    private final LocalDate startDate;
    private final String description;
    private final Long carId;
    private final Long mechanicId;
}
