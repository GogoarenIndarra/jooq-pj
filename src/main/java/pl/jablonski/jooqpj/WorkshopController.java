package pl.jablonski.jooqpj;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.jablonski.jooqpj.dto.CarDto;
import pl.jablonski.jooqpj.dto.CustomerDto;
import pl.jablonski.jooqpj.dto.MechanicDto;
import pl.jablonski.jooqpj.dto.OrderDto;
import pl.jablonski.jooqpj.service.WorkshopService;


@RestController
@RequiredArgsConstructor
@Slf4j
class WorkshopController {

    private final WorkshopService service;

    @PostMapping("/customer")
    Long createCustomer(@RequestBody final CustomerDto customerDto) {
        return service.addCustomer(customerDto);
    }

    @PostMapping("/car")
    Long createCar(@RequestBody final CarDto carDto) {
        return service.addCar(carDto);
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
