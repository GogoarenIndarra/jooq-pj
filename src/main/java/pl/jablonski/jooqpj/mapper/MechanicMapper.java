package pl.jablonski.jooqpj.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import pl.jablonski.jooqpj.dto.MechanicDto;
import pl.jablonski.jooqpj.model.Mechanic;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MechanicMapper {

    Mechanic toMechanic(final MechanicDto mechanicDto);

    MechanicDto toMechanicDto(final Mechanic mechanic);
}
