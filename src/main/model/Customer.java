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
        // stub
    }

    // Getters

    public int getCustomerID() {
        return -1; // stub
    }

    public String getName() {
        return null; // stub
    }

    public int getPhoneNumber() {
        return -1; // stub
    }
}
