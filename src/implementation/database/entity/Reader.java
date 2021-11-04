package implementation.database.entity;

import abstraction.database.entity.EntityInterface;

public class Reader implements EntityInterface {

    /**
     * Coefficient which makes id unique for each reader
     */
    private static int ID_coefficient;

    /**
     * fields of readerTable
     */
    public Integer reader_ticket;
    public String first_name;
    public String last_name;
    public String address;
    public String phone_number;

    public Reader(String first_name, String last_name) {
        reader_ticket = ++ID_coefficient;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Reader phoneNumber(String phone_number) {
        this.phone_number = phone_number;
        return this;
    }

    public Reader address(String address) {
        this.address = address;
        return this;
    }

    @Override
    public String getKey() {
        return first_name + last_name;
    }
}
