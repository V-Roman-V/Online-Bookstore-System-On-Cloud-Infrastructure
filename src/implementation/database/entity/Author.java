package implementation.database.entity;

import abstraction.database.entity.EntityInterface;

import java.sql.Date;

public class Author implements EntityInterface {

    // fields
    // Integer author_id;
    public String first_name;
    public String last_name;
    public String notes;
    public Date birth_date;

    public Author(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Author birthDate(Date birth_date) {
        this.birth_date = birth_date;
        return this;
    }

    public Author name(String notes) {
        this.notes = notes;
        return this;
    }

    public String getKey(){
        return first_name+last_name;
    }

}
