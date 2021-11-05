package implementation.database.entity;

import abstraction.database.entity.EntityInterface;

public class Reader implements EntityInterface {

    /**
     * fields of Reader
     * 
     * final means key field
     */
    private final String first_name;
    private final String last_name;
    private String address;
    private String phone_number;

    /**
     * Creates a Reader
     */
    public Reader(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    //Below is the implementation of Builder pattern

    public Reader address(String address) {
        this.address = address;
        return this;
    }

    public Reader phone_number(String phone_number) {
        this.phone_number = phone_number;
        return this;
    }

    //Below are getters which returns private fields of Reader class

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
        return first_name + last_name;
    }

    public static String getKey(String first_name, String last_name) {
        return first_name + last_name;
    }
}
