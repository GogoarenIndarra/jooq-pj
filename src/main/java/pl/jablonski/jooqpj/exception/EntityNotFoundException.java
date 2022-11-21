package pl.jablonski.jooqpj.exception;

public class EntityNotFoundException extends RuntimeException {

    private static final String CUSTOMER_NOT_FOUND_MESSAGE = "Customer not found in database";
    private static final String CAR_NOT_FOUND_MESSAGE = "Car not found in database";
    private static final String ORDER_NOT_FOUND_MESSAGE = "Order not found in database";
    private static final String MECHANIC_NOT_FOUND_MESSAGE = "Mechanic not found in database";

    private EntityNotFoundException(final String message) {
        super(message);
    }

    public static EntityNotFoundException customer() {
        return new EntityNotFoundException(CUSTOMER_NOT_FOUND_MESSAGE);
    }

    public static EntityNotFoundException car() {
        return new EntityNotFoundException(CAR_NOT_FOUND_MESSAGE);
    }

    public static EntityNotFoundException order() {
        return new EntityNotFoundException(ORDER_NOT_FOUND_MESSAGE);
    }

    public static EntityNotFoundException mechanic() {
        return new EntityNotFoundException(MECHANIC_NOT_FOUND_MESSAGE);
    }
}
