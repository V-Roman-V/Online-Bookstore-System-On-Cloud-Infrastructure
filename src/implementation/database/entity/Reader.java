package implementation.database.entity;


public class Reader extends ReadOnlyReader {

    
    public Reader(String first_name, String last_name) {
        super(first_name, last_name);
    }

    public Reader address(String address) {
        this.address = address;
        return this;
    }

    public Reader phone_number(String phone_number) {
        this.phone_number = phone_number;
        return this;
    }
    
}
