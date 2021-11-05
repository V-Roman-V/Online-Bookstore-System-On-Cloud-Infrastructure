package implementation.database.entity;

import abstraction.database.entity.EntityInterface;
import java.sql.Date;

public class Order implements EntityInterface {
    /**
     * Coefficient which makes id unique for each Orders
     */
    private static int ID_coefficient;

    /**
     * Property of Order
     * 
     */
    private final Book       book;
    private final Reader     reader;
    private final Date       date_booking;
    private Date             date_return;
    private final Integer    order_number;

    /**
     * Creates a Order
     */
    public Order(Book book, Reader reader) {
        order_number = ++ID_coefficient;
        date_booking = new Date(System.currentTimeMillis());
        date_return = null;

        this.book = book;
        this.reader = reader;
    }

    public Order date_return(Date date_return) {
        this.date_return = date_return;
        return this;
    }

    public Book getBook() {
        return book;
    }

    public Reader getReader() {
        return reader;
    }

    public Date getDateBooking() {
        return date_booking;
    }

    public Date getDateReturn() {
        return date_return;
    }

    public Integer getOrderNumber() {
        return order_number;
    }

    @Override
    public String getKey() {
        return order_number.toString();
    }
}
