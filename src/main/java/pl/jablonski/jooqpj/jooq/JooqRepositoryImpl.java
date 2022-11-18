package pl.jablonski.jooqpj.jooq;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static org.jooq.impl.DSL.count;
import static pl.jablonski.jooqpj.tables.Cars.CARS;
import static pl.jablonski.jooqpj.tables.Mechanics.MECHANICS;
import static pl.jablonski.jooqpj.tables.Orders.ORDERS;

@Service
@RequiredArgsConstructor
public class JooqRepositoryImpl implements JooqRepository {

    @Override
    public String getBestMechanicForOrder(final String carBrand,
                                          final LocalDate from,
                                          final int minOrdersCount,
                                          final String problemDescription) {

        return ConnectionManager.getConnection()
                .select(MECHANICS.NAME, MECHANICS.SURNAME, count())
                .from(MECHANICS)
                .leftJoin(ORDERS).onKey()
                .leftJoin(CARS).onKey()
                .where(CARS.BRAND.eq(carBrand))
                .and(ORDERS.FINISH_DATE.gt(from))
                .and(ORDERS.DESCRIPTION.contains(problemDescription))
                .groupBy(MECHANICS.NAME, MECHANICS.SURNAME)
                .having(count().greaterThan(minOrdersCount))
                .orderBy(MECHANICS.SURNAME)
                .fetch().toString();
    }
}
