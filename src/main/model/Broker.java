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
        this.brokerID = nextBrokerID++;
        this.name = name;
        this.customers = new ArrayList<>();

        if (customers != null) {
            for (Customer customer : customers) {
                assignCustomer(customer);
            }            
        }
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
        return customers.size();
    }

    /*
     * REQUIRES: customer must not be null
     * MODIFIES: this
     * EFFECTS: assign a new customer to the broker, ignore the customer who already exist in customers list;
     */
    public void assignCustomer(Customer customer) {
        if (customers.size() == 0) {
            customers.add(customer);
        }

        boolean find = false;
        for (Customer i : customers) {
            if ((i.getCustomerID() == customer.getCustomerID())) {
                find = true;
                break;
            }
        }
        if (!find) {
            customers.add(customer);
        }
    }
}
