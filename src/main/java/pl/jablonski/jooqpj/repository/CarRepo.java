package pl.jablonski.jooqpj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jablonski.jooqpj.model.Car;

public interface CarRepo extends JpaRepository<Car, Long> {
}
