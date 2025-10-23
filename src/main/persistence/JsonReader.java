package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.*;

/*
 * SOURCE: JsonSerializationDemo
 */
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Dealership read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDealership(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Dealership from JSON object and returns it
    private Dealership parseDealership(JSONObject jsonObject) {
        Dealership ds = new Dealership(null, null, null, null);
        addCars(ds, jsonObject);
        addBrokers(ds, jsonObject);
        addCustomers(ds, jsonObject);
        addSales(ds, jsonObject);
        return ds;
    }

    // MODIFIES: ds
    // EFFECTS: parses Car from JSON object and adds them to Dealership
    @SuppressWarnings("methodlength")
    private void addCars(Dealership ds, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("carList");
        if (jsonArray.length() == 0) {
            return;
        }

        for (Object json : jsonArray) {
            JSONObject nextCar = (JSONObject) json;
            int carID = nextCar.getInt("carID");
            String model = nextCar.getString("model");
            int year = nextCar.getInt("year");
            String color = nextCar.getString("color");
            double price = nextCar.getDouble("price");
            int status = nextCar.getInt("status");

            JSONArray jsonPackages = nextCar.getJSONArray("packages");
            ArrayList<String> packages = new ArrayList<>();
            if (jsonPackages.length() > 0) {
                for (Object p : jsonPackages) {
                    packages.add((String) p);
                }
            }

            Car car = new Car(carID, model, year, color, packages, price, status);
            double promotion = nextCar.getDouble("promotion");
            car.setPromotion(promotion);
            ds.addCar(car);
        }
    }

    // MODIFIES: ds
    // EFFECTS: parses Broker from JSON object array and adds them to Dealership
    private void addBrokers(Dealership ds, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("brokerList");
        if (jsonArray.length() == 0) {
            return;
        }

        for (Object json : jsonArray) {
            JSONObject nextBroker = (JSONObject) json;
            int brokerID = nextBroker.getInt("brokerID");
            String name = nextBroker.getString("name");

            JSONArray jsonCustomers = nextBroker.getJSONArray("customers");
            ArrayList<Integer> customers = new ArrayList<>();
            if (jsonCustomers.length() > 0) {
                for (Object c : jsonCustomers) {
                    customers.add(((Number) c).intValue());
                }
            }
            Broker broker = new Broker(brokerID, name, customers);
            ds.addBroker(broker);
        }
    }

    // MODIFIES: ds
    // EFFECTS: parses Customer from JSON object array and adds them to Dealership
    private void addCustomers(Dealership ds, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("customerList");
        if (jsonArray.length() == 0) {
            return;
        }

        for (Object json : jsonArray) {
            JSONObject nextCustomer = (JSONObject) json;
            int customerID = nextCustomer.getInt("customerID");
            String name = nextCustomer.getString("name");
            String phone = nextCustomer.getString("phoneNumber");
            Customer customer = new Customer(customerID, name, phone);
            ds.addCustomer(customer);
        }
    }

    // MODIFIES: ds
    // EFFECTS: parses Sale from JSON object array and adds them to Dealership
    private void addSales(Dealership ds, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("saleList");
        if (jsonArray.length() == 0) {
            return;
        }

        for (Object json : jsonArray) {
            JSONObject nextSale = (JSONObject) json;
            int carID = nextSale.getInt("carID");
            int brokerID = nextSale.getInt("brokerID");
            int customerID = nextSale.getInt("customerID");
            double salePrice = nextSale.getDouble("salePrice");

            Sale sale = new Sale(carID, brokerID, customerID, salePrice);
            ds.addSale(sale);
        }
    }
}
