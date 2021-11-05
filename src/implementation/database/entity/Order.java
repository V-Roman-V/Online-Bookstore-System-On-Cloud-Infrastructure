package implementation.database.entity;

import abstraction.database.entity.EntityInterface;
import java.sql.Date;

public class Order implements EntityInterface {
    /**
     * Coefficient which makes id unique for each Orders
     */
    private static int ID_coefficient;

    /**
     * fields of Order
     */
    public Integer order_number;
    public Book book;
    public Reader reader;
    public Date date_booking;
    public Date date_return;

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

    @Override
    public String getKey() {
        return order_number.toString();
    }

}
