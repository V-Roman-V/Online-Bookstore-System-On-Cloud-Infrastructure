package implementation.database.entity;

import abstraction.database.entity.EntityInterface;

import java.sql.Date;

public class ReadOnlyAuthor implements EntityInterface {

    /**
     * Property of ReadOnlyAuthor
     * 
     * final means key fuild
     */
    protected final String first_name;
    protected final String last_name;
    protected String note;
    protected Date birth_date;

    /**
     * Creates a ReadOnlyAuthor
     */
    public ReadOnlyAuthor(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getFullName() {
        return first_name + " " + last_name;
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
