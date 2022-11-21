package pl.jablonski.jooqpj.service;

import pl.jablonski.jooqpj.dto.CarDto;
import pl.jablonski.jooqpj.dto.CustomerDto;
import pl.jablonski.jooqpj.dto.MechanicDto;
import pl.jablonski.jooqpj.dto.OrderDto;

import java.time.LocalDate;

public interface WorkshopService {

    String getBestMechanic(String carBrand,
                           LocalDate from,
                           int minOrdersCount,
                           String problemDescription);

    String getMoneySpendByCustomer(Long customerId, LocalDate from);

    Long addCustomer(CustomerDto customerDto);

    Long addCar(CarDto carDto);

    Long addOrder(OrderDto orderDto);

    Long addMechanic(MechanicDto mechanicDto);

    void addCarToCustomer(Long carId, Long customerId);
}
