package pl.jablonski.jooqpj.jooq;

import java.sql.SQLException;
import java.time.LocalDate;

public interface JooqRepository {

    String getBestMechanicForOrder(String carBrand,
                                   LocalDate from,
                                   int minOrdersCount,
                                   String problemDescription) throws SQLException;
}
