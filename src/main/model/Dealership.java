package model;

import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;

import persistence.Writable;

// Represents a dealership having list of cars, brokers, customers, and sale records.
public class Dealership implements Writable {
    private ArrayList<Car> carList;
    private ArrayList<Broker> brokerList;
    private ArrayList<Customer> customerList;
    private ArrayList<Sale> saleList;

    /*
     * EFFECTS: each list can be null; cars is set to carList; brokers is set to brokerList;
     *          customers is set to customerList; sale records is set to saleList.
     */
    public Dealership(ArrayList<Car> carList, ArrayList<Broker> brokerList, 
                      ArrayList<Customer> customerList, ArrayList<Sale> saleList) {
        this.carList = new ArrayList<>();
        this.brokerList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.saleList = new ArrayList<>();

        if (carList != null) {
            setCarList(carList);
        }
        if (brokerList != null) {
            setBrokerList(brokerList);
        }
        if (customerList != null) {
            setCustomerList(customerList);
        }
        if (saleList != null) {
            setSaleList(saleList);
        }
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
        (this.carList).clear();
        for (Car newCar: carList) {
            addCar(newCar);
        }
    }

    public void setBrokerList(ArrayList<Broker> brokerList) {
        (this.brokerList).clear();
        for (Broker newBroker: brokerList) {
            addBroker(newBroker);
        }    
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        (this.customerList).clear();
        for (Customer newCustomer: customerList) {
            addCustomer(newCustomer);
        }    
    }

    public void setSaleList(ArrayList<Sale> saleList) {
        (this.saleList).clear();
        for (Sale newSale: saleList) {
            addSale(newSale);
        }    
    }

    /*
     * REQUIRES: newCar must not be null.
     * MODIFIES: this
     * EFFECTS: check the car if its already in the car list; if not, add it into the car list.
     */
    public void addCar(Car newCar) {
        boolean found = false;
        for (Car currenCar : carList) {
            if (currenCar.getCarID() == newCar.getCarID()) {
                found = true;
                break;
            }
        }

        if (!found) {
            carList.add(newCar);
        }
        EventLog.getInstance().logEvent(new Event("New car #" + newCar.getCarID() 
                                        + " added into dealership."));
    }

    /*
     * REQUIRES: broker must not be null.
     * MODIFIES: this
     * EFFECTS: check the broker if its already in the broker list; if not, add it into the broker list.
     */
    public void addBroker(Broker newBroker) {
        boolean found = false;
        for (Broker currenBroker : brokerList) {
            if (currenBroker.getBrokerID() == newBroker.getBrokerID()) {
                found = true;
                break;
            }
        }

        if (!found) {
            brokerList.add(newBroker);
            EventLog.getInstance().logEvent(new Event("New broker #" + newBroker.getBrokerID() 
                                        + " added into dealership."));
        }
    }

    /*
     * REQUIRES: customer must not be null.
     * MODIFIES: this
     * EFFECTS: check the customer if its already in the customer list; if not, add it into the customer list;
     *          return true when successed added, return false otherwise.
     */
    public void addCustomer(Customer newCustomer) {
        boolean found = false;
        for (Customer currenCustomer : customerList) {
            if (currenCustomer.getCustomerID() == newCustomer.getCustomerID()) {
                found = true;
                break;
            }
        }

        if (!found) {
            customerList.add(newCustomer);
            EventLog.getInstance().logEvent(new Event("New customer #" + newCustomer.getCustomerID() 
                                        + " added into dealership."));
        }
    }

    /*
     * REQUIRES: sale must not be null.
     * MODIFIES: this
     * EFFECTS: check the record if its already in sale history; if not, add it into the sale history;
     *          mark the car as sold.
     */
    public void addSale(Sale newSale) {
        boolean found = false;
        for (Sale currenSale : saleList) {
            if (currenSale.getCarID() == newSale.getCarID()) {
                found = true;
                break;
            }
        }

        if (!found) {
            saleList.add(newSale);
            // Mark as sold
            Car soldCar = getCarByCarID(newSale.getCarID());
            soldCar.updateStatus(5);

            Broker b = getBrokerByID(newSale.getBrokerID());
            b.assignCustomer(newSale.getCustomerID());

            EventLog.getInstance().logEvent(new Event("New sale record of car #" + newSale.getCarID()
                                            +  " added into dealership."));
        }
    }

    // [[[              ]]]
    // [[[ JSON METHODS ]]]
    // [[[              ]]]

    /*
     * EFFECTS: convert Dealership data into json
     * SOURCE: JsonSerializationDemo
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("carList", carsToJson());
        json.put("brokerList", brokersToJson());
        json.put("customerList", customersToJson());
        json.put("saleList", salesToJson());
        return json;
    }

    /*
     * EFFECTS: returns carList as JSON array.
     */
    private JSONArray carsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Car c : carList) {
            JSONObject jsonCars = new JSONObject();
            jsonCars.put("carID", c.getCarID());
            jsonCars.put("model", c.getModel());
            jsonCars.put("year", c.getYear());
            jsonCars.put("color", c.getColor());
            JSONArray packages = new JSONArray();
            for (String p : c.getPackages()) {
                packages.put(p);
            }
            jsonCars.put("packages", packages);
            jsonCars.put("price", c.getPrice());
            jsonCars.put("promotion", c.getPromotion());
            jsonCars.put("status", c.getStatus());
            jsonArray.put(jsonCars);
        }
        return jsonArray;
    }

    /*
     * EFFECTS: returns brokerList as JSON array.
     */
    private JSONArray brokersToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Broker b : brokerList) {
            JSONObject jsonBrokers = new JSONObject();
            jsonBrokers.put("brokerID", b.getBrokerID());
            jsonBrokers.put("name", b.getName());
            JSONArray customers = new JSONArray();
            for (Integer id : b.getCustomers()) {
                customers.put(id);
            }
            jsonBrokers.put("customers", customers);
            jsonArray.put(jsonBrokers);
        }
        return jsonArray;
    }

    /*
     * EFFECTS: returns customerList as JSON array.
     */
    private JSONArray customersToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Customer c : customerList) {
            JSONObject jsonCustomers = new JSONObject();
            jsonCustomers.put("customerID", c.getCustomerID());
            jsonCustomers.put("name", c.getName());
            jsonCustomers.put("phoneNumber", c.getPhoneNumber());
            jsonArray.put(jsonCustomers);
        }
        return jsonArray;
    }

    /*
     * EFFECTS: returns saleList as JSON array.
     */
    private JSONArray salesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Sale s : saleList) {
            JSONObject jsonSales = new JSONObject();
            jsonSales.put("carID", s.getCarID());
            jsonSales.put("brokerID", s.getBrokerID());
            jsonSales.put("customerID", s.getCustomerID());
            jsonSales.put("salePrice", s.getSalePrice());
            jsonArray.put(jsonSales);
        }
        return jsonArray;
    }

    // [[[                ]]]
    // [[[ FILTER METHODS ]]]
    // [[[                ]]]

    /*
     * REQUIRES: model must be non-zero length.
     * EFFECTS: create a new car list filtered by selected model; can be a empty list.
     */
    public ArrayList<Car> filterCarByModel(String model) {
        ArrayList<Car> carByModel = new ArrayList<>();
        for (Car currCar : carList) {
            if (currCar.getModel().equals(model)) {
                carByModel.add(currCar);
            }
        }
        return carByModel;
    }

    /*
     * REQUIRES: color must be non-zero length.
     * EFFECTS: create a new car list filtered by selected color; can be a empty list.
     */    
    public ArrayList<Car> filterCarByColor(String color) {
        ArrayList<Car> carByColor = new ArrayList<>();
        for (Car currCar : carList) {
            if (currCar.getColor().equals(color)) {
                carByColor.add(currCar);
            }
        }
        return carByColor;
    }

    /*
     * REQUIRES: status must in [0, 5].
     * EFFECTS: create a new car list filtered by selected status; can be a empty list.
     */
    public ArrayList<Car> filterCarByStatus(int status) {
        ArrayList<Car> carByStatus = new ArrayList<>();
        for (Car currCar : carList) {
            if (currCar.getStatus() == status) {
                carByStatus.add(currCar);
            }
        }
        return carByStatus;
    }

    /*
     * EFFECTS: create a new car list filtered by a interval of year; can be a empty list.
     *          can accepte any integers buy recommend meanningful values.
     */
    public ArrayList<Car> filterCarByYear(int start, int end) {
        ArrayList<Car> carByYear = new ArrayList<>();
        if (start <= end) {
            for (Car currCar : carList) {
                if (currCar.getYear() >= start && currCar.getYear() <= end) {
                    carByYear.add(currCar);
                }
            }
        }
        return carByYear;
    }

    /*
     * REQUIRES: low <= high
     * EFFECTS: create a new car list filtered by a interval of price; can be a empty list.
     *          can accepte any integers buy recommend meanningful values.
     */
    public ArrayList<Car> filterCarByPrice(double low, double high) {
        ArrayList<Car> carByPrice = new ArrayList<>();
        if (low <= high) {
            for (Car currCar : carList) {
                if (currCar.getPrice() >= low && currCar.getPrice() <= high) {
                    carByPrice.add(currCar);
                }
            }
        }
        return carByPrice;
    }

    /*
     * EFFECTS: return Car with specific carID
     */
    public Car getCarByCarID(int id) {
        for (Car car : carList) {
            if (car.getCarID() == id) {
                return car;
            }
        }
        return null;
    }

    public Broker getBrokerByID(int id) {
        for (Broker broker : brokerList) {
            if (broker.getBrokerID() == id) {
                return broker;
            }
        }
        return null;
    }

    /*
     * EFFECTS: returns general infos of dealership as String
     */
    public String getInfo() {
        return ("Inventory: " + carList.size() + " | Sale records: " + saleList.size() + " | Brokers: "
                + brokerList.size() + " | Customers: " + customerList.size());
    }
}
