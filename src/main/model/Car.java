package model;

import java.util.ArrayList;

public class Car {
    private static int nextCarID = 1;
    private int carID;
    private String model;               // model of the car
    private int year;                   // year of the car
    private String color;               // color of the car
    private ArrayList<String> packages; // custom packages, can be null
    private double price;
    private double promotion;
    private int status;

    /*
     * REQUIRES: model, year, color, price, promotion, ans status have non-zero length.
     * MODIFIES: this
     * EFFECTS: car id is a positive integer not assigned to any other car;
     *          model is set to model; year is set to year; color is set to color, price is set to price;
     *          status is set to status; addOns can be null.
     */
    public Car(String model, int year, String color, ArrayList<String> addOns, double price, int status) {
        // stub
    }

    // [[[         ]]]
    // [[[ GETTERS ]]]
    // [[[         ]]]

    public int getCarID() {
        return carID;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public double getPromotion() {
        return promotion;
    }

    public int getStatus() {
        return status;
    }

    public ArrayList<String> getPackages() {
        return packages;
    }

    public int getPackageSize() {
        return -1; // stub
    }

    /*
     * REQUIRES: 
     * MODIFIES: this
     * EFFECTS: clean current packages settings, and add new selected packages.
     */
    public void setPackages(ArrayList<String> addOns) {
        // stub
    }

    /*
     * REQUIRES: addOn must not be null
     * 
     */
    public void addPackage(String addOn) {
        // stub
    }

    /*
     * REQUIRES: amount <= car price
     * MODIFIES: this
     * EFFECTS: make car price discount at given amount
     */
    public void setPromotion(double amount) {
        // stub
    }

    /*
     * REQUIRES: new status must in [0, 5].
     *           0 - Car modification created
     *           1 - Order placed
     *           2 - In factory
     *           3 - Upcoming
     *           4 - In stock
     *           5 - Sold
     * MODIFIES: this
     * EFFESTS: mark the car into new status.
     */
    public void updateStatus(int newStatus) {
        // stub
    }
}
