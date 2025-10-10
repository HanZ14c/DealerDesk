package model;

import java.util.ArrayList;

public class Dealership {
    private ArrayList<Car> carList;
    private ArrayList<Broker> brokerList;
    private ArrayList<Customer> customerList;
    private ArrayList<Sale> saleList;

    /*
     * MODIFIES: this
     * EFFECTS: each list can be null; cars is set to carList; brokers is set to brokerList;
     *          customers is set to customerList; sale records is set to saleList.
     */
    public Dealership(ArrayList<Car> carList, ArrayList<Broker> brokerList, 
                      ArrayList<Customer> customerList, ArrayList<Sale> saleList) {
        // stub
    }

    // [[[         ]]]
    // [[[ GETTERS ]]]
    // [[[         ]]]

    public ArrayList<Car> getCarList() {
        return carList;
    }

    public ArrayList<Broker> getBrokerList() {
        return brokerList;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public ArrayList<Sale> getSaleList() {
        return saleList;
    }

    // [[[         ]]]
    // [[[ SETTERS ]]]
    // [[[         ]]]

    public void setCarList(ArrayList<Car> carList) {
        // stub
    }

    public void setBrokerList(ArrayList<Broker> brokerList) {
        // stub
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        // stub
    }

    public void setSaleList(ArrayList<Sale> saleList) {
        // stub
    }

    /*
     * REQUIRES: newCar must not be null.
     * MODIFIES: this
     * EFFECTS: check the car if its already in the car list; if not, add it into the car list.
     */
    public void addCar(Car newCar) {
        // stub
    }

    /*
     * REQUIRES: broker must not be null.
     * MODIFIES: this
     * EFFECTS: check the broker if its already in the broker list; if not, add it into the broker list.
     */
    public void addBroker(Broker newBroker) {
        // stub
    }

    /*
     * REQUIRES: customer must not be null.
     * MODIFIES: this
     * EFFECTS: check the customer if its already in the customer list; if not, add it into the customer list;
     *          return true when successed added, return false otherwise.
     */
    public void addCustomer(Customer newCustomer) {
        // stub
    }

    /*
     * REQUIRES: sale must not be null.
     * MODIFIES: this
     * EFFECTS: check the record if its already in sale history; if not, add it into the sale history;
     *          mark the car as sold.
     */
    public void addSale(Sale newSale) {
        // stub
    }

    public Car getCarByCarID(int carID) {
        return null; // stub
    }

    // [[[                ]]]
    // [[[ FILTER METHODS ]]]
    // [[[                ]]]

    /*
     * REQUIRES: model must be non-zero length.
     * EFFECTS: create a new car list filtered by selected model; can be a empty list.
     */
    public ArrayList<Car> filterCarByModel(String model) {
        return null; // stub
    }

    /*
     * REQUIRES: color must be non-zero length.
     * EFFECTS: create a new car list filtered by selected color; can be a empty list.
     */    
    public ArrayList<Car> filterCarByColor(String color) {
        return null; // stub
    }

    /*
     * REQUIRES: status must in [0, 5].
     * EFFECTS: create a new car list filtered by selected status; can be a empty list.
     */
    public ArrayList<Car> filterCarByStatus(int status) {
        return null; // stub
    }

    /*
     * EFFECTS: create a new car list filtered by a interval of year; can be a empty list.
     *          can accepte any integers buy recommend meanningful values.
     */
    public ArrayList<Car> filterCarByYear(int start, int end) {
        return null; // stub
    }

    /*
     * REQUIRES: low <= high
     * EFFECTS: create a new car list filtered by a interval of price; can be a empty list.
     *          can accepte any integers buy recommend meanningful values.
     */
    public ArrayList<Car> filterCarByPrice(double low, double high) {
        return null; // stub
    }
}
