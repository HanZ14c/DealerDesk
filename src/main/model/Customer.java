package model;

public class Customer {
    private static int nextCustomerID = 1;
    private int customerID;
    private String name;
    private String phoneNumber;

    /*
     * REQUIRES: customer name, phoneNumber have non-zero length
     * EFFECTS: customer id is a positive integer not assigned to any other customer;
     *          customer name is set to name; customer phone number is set to phoneNUmber.
     */
    public Customer(String name, String phoneNumber) {
        this.customerID = nextCustomerID++;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    // [[[         ]]]
    // [[[ GETTERS ]]]
    // [[[         ]]]

    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
