package pl.jablonski.jooqpj.service;

import pl.jablonski.jooqpj.dto.CarDto;
import pl.jablonski.jooqpj.dto.CustomerDto;
import pl.jablonski.jooqpj.dto.MechanicDto;
import pl.jablonski.jooqpj.dto.OrderDto;

public interface WorkshopService {

    Long addCustomer(CustomerDto customerDto);

    Long addCar(CarDto carDto);

    Long addOrder(OrderDto orderDto);

    Long addMechanic(MechanicDto mechanicDto);
}
