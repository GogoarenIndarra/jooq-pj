package pl.jablonski.jooqpj.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import pl.jablonski.jooqpj.dto.CarDto;
import pl.jablonski.jooqpj.model.Car;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CarMapper {

    Car toCar(final CarDto carDto);

    CarDto toCarDto(final Car car);
}
