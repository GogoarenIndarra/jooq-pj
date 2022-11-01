package pl.jablonski.jooqpj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jablonski.jooqpj.model.Mechanic;

public interface MechanicRepo extends JpaRepository<Mechanic, Long> {
}
