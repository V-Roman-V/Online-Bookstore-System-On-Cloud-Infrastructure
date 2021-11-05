package implementation.database.entity;

import abstraction.database.entity.EntityInterface;

public class ReadOnlyReader implements EntityInterface {

    /**
     * fields of ReadOnlyReader
     * 
     * final means key fuild
     */
    protected final String first_name;
    protected final String last_name;
    protected String address;
    protected String phone_number;

    /**
     * Creates a ReadOnlyReader
     */
    public ReadOnlyReader(String first_name, String last_name) {
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
        return first_name+" "+last_name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    @Override
    public String getKey() {
        return getKey(first_name, last_name);
    }

    public static String getKey(String first_name, String last_name) {
        return (first_name + last_name).toLowerCase();
    }
}
