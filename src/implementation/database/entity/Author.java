package implementation.database.entity;

import abstraction.database.entity.EntityInterface;

import java.sql.Date;

import static jdk.javadoc.internal.doclets.toolkit.util.Utils.toLowerCase;

public class Author implements EntityInterface {

    /**
     * Property of Author
     * 
     * final means key field
     */
    private final String first_name;
    private final String last_name;
    private String note;
    private Date birth_date;

    /**
     * Creates an Author
     */
    public Author(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
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

    //Below are getters which returns private fields of Author class

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getFullName() {
        return first_name+" "+last_name;
    }

    public String getNote() {
        return note;
    }

    public Date getBirthDate() {
        return birth_date;
    }

    @Override
    public String getKey() {
        return toLowerCase(first_name + last_name);
    }

    public static String getKey(String first_name, String last_name) {
        return toLowerCase(first_name + last_name);
    }

}
