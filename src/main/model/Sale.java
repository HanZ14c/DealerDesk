package model;

public class Sale {
    private int carID;
    private int brokerID;
    private int customerID;
    private double salePrice;

    /*
     * REQUIRES: carID, brokerID, and customerID are existing instances; salePrice is not negative
     * EFFECTS: car id is set to carID, broker id is set to brokerID; customer id is set to customerID;
     *          sale price is set to salePrice.
     */
    public Sale(int carID, int brokerID, int customerID, double salePrice) {
        // stub
        this.carID = carID;
        this.brokerID = brokerID;
        this.customerID = customerID;
        this.salePrice = salePrice;
    }

    // [[[         ]]]
    // [[[ GETTERS ]]]
    // [[[         ]]]

    public int getCarID() {
        return carID;
    }

    public int getBrokerID() {
        return brokerID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public double getSalePrice() {
        return salePrice;
    }

}
