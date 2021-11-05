package implementation.database.entity;

import abstraction.database.entity.EntityInterface;

import java.sql.Date;

public class Author implements EntityInterface {

    /**
     * Property of Author
     * 
     * final means key fuild
     */
    private final String first_name;
    private final String last_name;
    private String note;
    private Date birth_date;

    /**
     * Creates a Author
     */
    public Author(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Author birth_date(Date birth_date) {
        this.birth_date = birth_date;
        return this;
    }

    public Author note(String note) {
        this.note = note;
        return this;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getNote() {
        return note;
    }

    public Date getBirthDate() {
        return birth_date;
    }

    @Override
    public String getKey() {
        return first_name + last_name;
    }

    public static String getKey(String first_name, String last_name) {
        return first_name + last_name;
    }

}
