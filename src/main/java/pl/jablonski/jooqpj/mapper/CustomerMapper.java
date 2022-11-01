package pl.jablonski.jooqpj.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import pl.jablonski.jooqpj.dto.CustomerDto;
import pl.jablonski.jooqpj.model.Customer;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerMapper {

    Customer toCustomer(final CustomerDto customerDto);

    CustomerDto toCustomerDto(final Customer customer);
}
