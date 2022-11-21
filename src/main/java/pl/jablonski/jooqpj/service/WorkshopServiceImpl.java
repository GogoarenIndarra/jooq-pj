package pl.jablonski.jooqpj.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jablonski.jooqpj.dto.CarDto;
import pl.jablonski.jooqpj.dto.CustomerDto;
import pl.jablonski.jooqpj.dto.MechanicDto;
import pl.jablonski.jooqpj.dto.OrderDto;
import pl.jablonski.jooqpj.exception.EntityNotFoundException;
import pl.jablonski.jooqpj.jooq.JooqRepository;
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

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkshopServiceImpl implements WorkshopService {

    private final CarRepo carRepo;
    private final CustomerRepo customerRepo;
    private final MechanicRepo mechanicRepo;
    private final OrderRepo orderRepo;

    private final CarMapper carMapper;
    private final CustomerMapper customerMapper;
    private final MechanicMapper mechanicMapper;
    private final OrderMapper orderMapper;

    private final JooqRepository jooqRepository;

    @SneakyThrows
    @Override
    public String getBestMechanic(final String carBrand,
                                  final LocalDate from,
                                  final int minOrdersCount,
                                  final String problemDescription) {
        return jooqRepository.getBestMechanicForOrder(carBrand, from, minOrdersCount, problemDescription);
    }

    @Override
    public String getMoneySpendByCustomer(final Long customerId, final LocalDate from) {
        return jooqRepository.getMoneySpendByCustomer(customerId, from);
    }

    @Override
    public Long addCustomer(final CustomerDto customerDto) {
        final Customer customer = customerMapper.toCustomer(customerDto);
        return customerRepo.save(customer).getId();
    }

    @Transactional
    @Override
    public Long addCar(final CarDto carDto) {
        final Car car = carMapper.toCar(carDto);
        final Car savedCar = carRepo.save(car);
        final Customer customer = customerRepo.findById(carDto.getCustomerId()).orElseThrow(() -> {
            log.error("Customer not found for id: {}", carDto.getCustomerId());
            throw EntityNotFoundException.customer();
        });
        customer.getCars().add(car);
        customerRepo.save(customer);

        return savedCar.getId();
    }

    @Override
    public Long addOrder(final OrderDto orderDto) {
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
        return orderRepo.save(order).getId();
    }

    @Override
    public Long addMechanic(final MechanicDto mechanicDto) {
        return mechanicRepo.save(mechanicMapper.toMechanic(mechanicDto)).getId();
    }

    @Override
    public void addCarToCustomer(final Long carId, final Long customerId) {
        final Customer customer = customerRepo.findById(customerId).orElseThrow(() -> {
            log.error("Customer not found for id: {}", customerId);
            throw EntityNotFoundException.customer();
        });
        final Car car = carRepo.findById(carId).orElseThrow(() -> {
            log.error("Car not found for id: {}", carId);
            throw EntityNotFoundException.car();
        });

        customer.getCars().add(car);
        customerRepo.save(customer);
    }
}
