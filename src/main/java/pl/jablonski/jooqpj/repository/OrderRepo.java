package pl.jablonski.jooqpj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jablonski.jooqpj.model.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
