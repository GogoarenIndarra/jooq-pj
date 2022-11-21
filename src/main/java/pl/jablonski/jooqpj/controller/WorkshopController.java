package pl.jablonski.jooqpj.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.jablonski.jooqpj.dto.CarDto;
import pl.jablonski.jooqpj.dto.CustomerDto;
import pl.jablonski.jooqpj.dto.MechanicDto;
import pl.jablonski.jooqpj.dto.OrderDto;
import pl.jablonski.jooqpj.service.WorkshopService;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@Slf4j
class WorkshopController {

    private final WorkshopService service;


    @GetMapping("/best")
    String getBestMechanic(@RequestParam final String carBrand,
                           @RequestParam final String from,
                           @RequestParam final int minOrdersCount,
                           @RequestParam final String problemDescription) {
        return service.getBestMechanic(carBrand, LocalDate.parse(from), minOrdersCount, problemDescription);
    }

    @GetMapping("/discount")
    String getBestMechanic(@RequestParam final Long customerId,
                           @RequestParam final String from) {
        return service.getMoneySpendByCustomer(customerId, LocalDate.parse(from));
    }

    @PostMapping("/customer")
    Long createCustomer(@RequestBody final CustomerDto customerDto) {
        return service.addCustomer(customerDto);
    }

    @PostMapping("/car")
    Long createCar(@RequestBody final CarDto carDto) {
        return service.addCar(carDto);
    }

    @PostMapping("/car/{carId}/{customerId}")
    void addCarToCustomer(@PathVariable final Long carId, @PathVariable final Long customerId) {
        service.addCarToCustomer(carId, customerId);
    }

    @PostMapping("/mechanic")
    Long createMechanic(@RequestBody final MechanicDto mechanicDto) {
        return service.addMechanic(mechanicDto);
    }

    @PostMapping("/order")
    Long createOrder(@RequestBody final OrderDto orderDto) {
        return service.addOrder(orderDto);
    }
}
