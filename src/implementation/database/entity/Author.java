package implementation.database.entity;

import java.sql.Date;

public class Author extends ReadOnlyAuthor {

    public Author(String first_name, String last_name) {
        super(first_name, last_name);
    }

    //Below is the implementation of Builder pattern

    public Author birth_date(Date birth_date) {
        this.birth_date = birth_date;
        return this;
    }

    public Author note(String note) {
        this.note = note;
        return this;
    }

}
