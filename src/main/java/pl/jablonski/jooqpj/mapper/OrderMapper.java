package pl.jablonski.jooqpj.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import pl.jablonski.jooqpj.dto.OrderDto;
import pl.jablonski.jooqpj.model.Order;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderMapper {

    Order toOrder(final OrderDto orderDto);

    OrderDto toOrderDto(final Order order);
}
