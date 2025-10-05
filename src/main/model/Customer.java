package model;

public class Customer {
    private static int nextCustomerID = 1;
    private int customerID;
    private String name;
    private int phoneNumber;

    /*
     * REQUIRES: customer name, phoneNumber have non-zero length
     * EFFECTS: customer id is a positive integer not assigned to any other customer;
     *          customer name is set to name; customer phone number is set to phoneNUmber.
     */
    public Customer(String name, int phoneNumber) {
        this.customerID = nextCustomerID++;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    // Getters

    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
}
