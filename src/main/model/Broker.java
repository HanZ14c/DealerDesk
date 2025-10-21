package model;

import java.util.ArrayList;

// Represents a broker having ID, name, and its customers.
public class Broker {
    private static int nextBrokerID = 1;
    private int brokerID;
    private String name;
    private ArrayList<Integer> customerIDs;

    /*
     * REQUIRES: broker name, phoneNumber have non-zero length
     * EFFECTS: broker id is a positive integer not assigned to any other broker;
     *          broker name is set to name; broker customer list can be empty.
     */
    public Broker(String name, ArrayList<Integer> customerIDs) {
        this.brokerID = nextBrokerID++;
        this.name = name;
        this.customerIDs = new ArrayList<>();

        if (customerIDs != null) {
            for (int customerID : customerIDs) {
                assignCustomer(customerID);
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

    public ArrayList<Integer> getCustomers() {
        return customerIDs;
    }

    /*
     * EFFECRS: return the number of customers in brokers list.
     */
    public int getNumOfCustomers() {
        return customerIDs.size();
    }

    /*
     * REQUIRES: customer must not be null.
     * MODIFIES: this
     * EFFECTS: assign a new customer to the broker, ignore the customer who already exist in customers list.
     */
    public void assignCustomer(int customerID) {
        if (customerIDs.size() == 0) {
            customerIDs.add(customerID);
        }

        boolean find = false;
        for (int i : customerIDs) {
            if ((i == customerID)) {
                find = true;
                break;
            }
        }
        if (!find) {
            customerIDs.add(customerID);
        }
    }
}
