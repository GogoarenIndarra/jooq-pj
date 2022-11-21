package pl.jablonski.jooqpj.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.jablonski.jooqpj.dbinsert.DataBaseInitialization;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DataBaseInitController {

    private final DataBaseInitialization fakerService;

    @PostMapping("/init/mechanics")
    void initDataBaseMechanics(@RequestParam final int numOfMechanics) {
        fakerService.initDataBaseWithMechanics(numOfMechanics);
    }

    @PostMapping("/init/customers")
    void initDataBaseCustomers(@RequestParam final int numOfCustomers) {
        fakerService.initDataBaseWithCustomers(numOfCustomers);
    }

    @PostMapping("/init/cars")
    void initDataBaseCars() {
        fakerService.initDataBaseAddCars();
    }

    @PostMapping("/init/orders")
    void initDataBaseOrders(@RequestParam final int numOfOrders) {
        fakerService.initDataBaseAddOrders(numOfOrders);
    }
}
