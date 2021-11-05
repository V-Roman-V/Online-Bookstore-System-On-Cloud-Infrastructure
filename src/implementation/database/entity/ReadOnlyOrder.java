package implementation.database.entity;

import abstraction.database.entity.EntityInterface;
import java.sql.Date;

public class ReadOnlyOrder implements EntityInterface {
    /**
     * Coefficient which makes id unique for each ReadOnlyOrders
     */
    private static int ID_coefficient;

    /**
     * Fields of ReadOnlyOrder
     */
    protected final ReadOnlyBook       book;
    protected final ReadOnlyReader     reader;
    protected final Date       date_booking;
    protected Date             date_return;
    protected final Integer    order_number;

    /**
     * Creates a ReadOnlyOrder
     */
    public ReadOnlyOrder(ReadOnlyBook book, ReadOnlyReader reader) {
        order_number = ++ID_coefficient;
        date_booking = new Date(System.currentTimeMillis());
        date_return = null;

        this.book = book;
        this.reader = reader;
    }

    public ReadOnlyBook getBook() {
        return book;
    }

    public ReadOnlyReader getReader() {
        return reader;
    }

    public Date getDateBooking() {
        return date_booking;
    }

    public Date getDateReturn() {
        return date_return;
    }

    public Integer getReadOnlyOrderNumber() {
        return order_number;
    }

    @Override
    public String getKey() {
        return order_number.toString().toLowerCase();
    }
}
