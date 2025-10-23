package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// Source: JsonSerializationDemo
@ExcludeFromJacocoGeneratedReport
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Dealership ds = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDealership() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDealership.json");
        try {
            Dealership emptyDS = reader.read();
            assertEquals(0, emptyDS.getCarList().size());
            assertEquals(0, emptyDS.getBrokerList().size());
            assertEquals(0, emptyDS.getCustomerList().size());
            assertEquals(0, emptyDS.getSaleList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @SuppressWarnings("methodlength")
    @Test
    void testReaderGeneralDealership() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDealership.json");
        try {
            Dealership generalDS = reader.read();

            ArrayList<Car> carList = generalDS.getCarList();
            assertEquals(2, carList.size());
            ArrayList<String> packages1 = new ArrayList<>();
            packages1.add("Tech Package");
            packages1.add("Sport Package");
            checkCar(1, "BMW M4", 2025, "Blue", packages1, 120000, 5000, 5, carList.get(0));
            checkCar(2, "BMW 340i", 2026, "White", new ArrayList<>(), 80000, 0, 2, carList.get(1));

            ArrayList<Broker> brokerList = generalDS.getBrokerList();
            assertEquals(2, brokerList.size());
            ArrayList<Integer> customers1 = new ArrayList<>();
            customers1.add(1);
            customers1.add(2);
            checkBroker(1, "Alijah", customers1, brokerList.get(0));

            ArrayList<Customer> customerList = generalDS.getCustomerList();
            assertEquals(2, customerList.size());
            checkCustomer(1, "Alex", "100-100-0001", customerList.get(0));
            checkCustomer(2, "Bruno", "100-100-0002", customerList.get(1));

            ArrayList<Sale> saleList = generalDS.getSaleList();
            assertEquals(1, saleList.size());
            checkSale(1, 1, 1, 115000, saleList.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderMissingArrays() {
        JsonReader reader = new JsonReader("./data/testReaderMissingData.json");
        try {
            Dealership ds = reader.read();
        } catch (Exception e) {
            // pass
        }
    }
}
