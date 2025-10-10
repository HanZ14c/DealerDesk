package model;

import java.util.ArrayList;

public class Broker {
    private static int nextBrokerID = 1;
    private int brokerID;
    private String name;
    private ArrayList<Customer> customers;

    /*
     * REQUIRES: broker name, phoneNumber have non-zero length
     * EFFECTS: broker id is a positive integer not assigned to any other broker;
     *          broker name is set to name; broker customer list can be empty.
     */
    public Broker(String name, ArrayList<Customer> customers) {
        // stub
    }

    // [[[         ]]]
    // [[[ GETTERS ]]]
    // [[[         ]]]

    public int getBrokerID() {
        return brokerID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    /*
     * EFFECRS: return the number of customers in brokers list
     */
    public int getNumOfCustomers() {
        return -1; // stub
    }

    /*
     * REQUIRES: customer must not be null
     * MODIFIES: this
     * EFFECTS: assign a new customer to the broker, ignore the customer who already exist in customers list;
     */
    public void assignCustomer(Customer customer) {
        // stub
    }
}
