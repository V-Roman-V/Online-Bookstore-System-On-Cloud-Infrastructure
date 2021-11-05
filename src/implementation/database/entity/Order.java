package implementation.database.entity;

import java.sql.Date;

public class Order extends ReadOnlyOrder {
    public Order(ReadOnlyBook book, ReadOnlyReader reader) {
        super(book, reader);
    }

    //Below is the implementation of Builder pattern

    public Order date_return(Date date_return) {
        this.date_return = date_return;
        return this;
    }
}
