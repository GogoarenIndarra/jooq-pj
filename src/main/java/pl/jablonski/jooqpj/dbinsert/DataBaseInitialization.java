package pl.jablonski.jooqpj.dbinsert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;
import pl.jablonski.jooqpj.dto.CarDto;
import pl.jablonski.jooqpj.dto.CustomerDto;
import pl.jablonski.jooqpj.dto.MechanicDto;
import pl.jablonski.jooqpj.dto.OrderDto;
import pl.jablonski.jooqpj.exception.EntityNotFoundException;
import pl.jablonski.jooqpj.mapper.CarMapper;
import pl.jablonski.jooqpj.mapper.CustomerMapper;
import pl.jablonski.jooqpj.mapper.MechanicMapper;
import pl.jablonski.jooqpj.mapper.OrderMapper;
import pl.jablonski.jooqpj.model.Car;
import pl.jablonski.jooqpj.model.Customer;
import pl.jablonski.jooqpj.model.Mechanic;
import pl.jablonski.jooqpj.model.Order;
import pl.jablonski.jooqpj.repository.CarRepo;
import pl.jablonski.jooqpj.repository.CustomerRepo;
import pl.jablonski.jooqpj.repository.MechanicRepo;
import pl.jablonski.jooqpj.repository.OrderRepo;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataBaseInitialization {

    private final CarRepo carRepo;
    private final CustomerRepo customerRepo;
    private final MechanicRepo mechanicRepo;
    private final OrderRepo orderRepo;

    private final CarMapper carMapper;
    private final CustomerMapper customerMapper;
    private final MechanicMapper mechanicMapper;
    private final OrderMapper orderMapper;

    private final Faker dataFaker;
    private final Random rand;

    private static final List<Long> mechanicsIds = new ArrayList<>();
    private static final List<Long> customersIds = new ArrayList<>();
    private static final List<Long> carsIds = new ArrayList<>();
    private static final List<String> orderDescription = new ArrayList<>();

    static {
        orderDescription.add("Engine oil change including filter");
        orderDescription.add("Suspension replacement");
        orderDescription.add("Replacement of brake pads and discs");
        orderDescription.add("Timing belt replacement");
        orderDescription.add("Air conditioning inspection");
        orderDescription.add("Clutch replacement");
        orderDescription.add("Deleting errors from the car's computer");
        orderDescription.add("Replacing light bulbs");
        orderDescription.add("Replacing battery");
        orderDescription.add("Brake fluid replacement");
        orderDescription.add("Replacement of gaskets in the engine");
    }


    public void initDataBaseWithMechanics(final int numOfMechanics) {
        for (int i = 0; i < numOfMechanics; i++) {
            final MechanicDto mechanicDto = MechanicDto.builder()
                    .name(dataFaker.name().firstName())
                    .surname(dataFaker.name().lastName())
                    .build();

            final var mechanic = mechanicRepo.save(mechanicMapper.toMechanic(mechanicDto));
            mechanicsIds.add(mechanic.getId());
        }
    }

    public void initDataBaseWithCustomers(final int numOfCustomers) {
        for (int i = 0; i < numOfCustomers; i++) {
            final CustomerDto customerDto = CustomerDto.builder()
                    .name(dataFaker.name().firstName())
                    .surname(dataFaker.name().lastName())
                    .email(dataFaker.internet().safeEmailAddress())
                    .phone(dataFaker.phoneNumber().phoneNumber())
                    .build();

            final var customer = customerRepo.save(customerMapper.toCustomer(customerDto));
            customersIds.add(customer.getId());
        }
    }

    public void initDataBaseAddCars() {
        for (final Long customerId : customersIds) {
            final var brand = dataFaker.vehicle().make();
            final CarDto carDto = CarDto.builder()
                    .customerId(customerId)
                    .brand(brand)
                    .model(dataFaker.vehicle().model(brand))
                    .build();

            final Car car = carMapper.toCar(carDto);
            final Car savedCar = carRepo.save(car);
            final Customer customer = customerRepo.findById(carDto.getCustomerId()).orElseThrow(() -> {
                log.error("Customer not found for id: {}", carDto.getCustomerId());
                throw EntityNotFoundException.customer();
            });

            customer.cars.add(savedCar);
            customerRepo.save(customer);
            carsIds.add(savedCar.getId());
        }
    }

    public void initDataBaseAddOrders(final int numOfOrders) {
        int ordersCounter = 0;
        while (ordersCounter < numOfOrders) {
            for (final Long carId : carsIds) {

                final var randomIndex = rand.nextInt(mechanicsIds.size());
                final var randomMechanicId = mechanicsIds.get(rand.nextInt(randomIndex + 1));

                final var randomDescriptionIndex = rand.nextInt(orderDescription.size());
                final var randomDescription = orderDescription.get(rand.nextInt(randomDescriptionIndex + 1));

                final var date = dataFaker.date().birthday(1, 10).toLocalDateTime().toLocalDate();
                final var orderDto = OrderDto.builder()
                        .price(BigDecimal.valueOf(dataFaker.number().numberBetween(100, 2000)))
                        .startDate(date)
                        .finishDate(date.plus(rand.nextInt(5) + 1L, ChronoUnit.DAYS))
                        .description(randomDescription)
                        .carId(carId)
                        .mechanicId(randomMechanicId)
                        .build();

                final Car car = carRepo.findById(orderDto.getCarId()).orElseThrow(() -> {
                    log.error("Car not found for id: {}", orderDto.getCarId());
                    throw EntityNotFoundException.car();
                });
                final Mechanic mechanic = mechanicRepo.findById(orderDto.getMechanicId()).orElseThrow(() -> {
                    log.error("Mechanic not found for id: {}", orderDto.getMechanicId());
                    throw EntityNotFoundException.mechanic();
                });
                final Order order = orderMapper.toOrder(orderDto);
                order.setCar(car);
                order.setMechanic(mechanic);
                orderRepo.save(order);
                ordersCounter++;
            }
        }
    }
}
