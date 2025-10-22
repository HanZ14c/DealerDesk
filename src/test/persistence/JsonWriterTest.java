package persistence;

import model.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class JsonWriterTest extends JsonTest {
    
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDealership() {
        try {
            Dealership emptyDS = new Dealership(null, null, null, null);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDealership.json");
            writer.open();
            writer.write(emptyDS);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDealership.json");
            emptyDS = reader.read();
            assertEquals(0, emptyDS.getCarList().size());
            assertEquals(0, emptyDS.getBrokerList().size());
            assertEquals(0, emptyDS.getCustomerList().size());
            assertEquals(0, emptyDS.getSaleList().size());
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }

    @SuppressWarnings("methodlength")
    @Test
    void testWriterGeneralDealership() {
        try {
            Dealership generalDS = new Dealership(null, null, null, null);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDealership.json");
            writer.open();
            writer.write(generalDS);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDealership.json");
            generalDS = reader.read();

            ArrayList<Car> carList = generalDS.getCarList();
            assertEquals(2, carList.size());
            ArrayList<String> packages1 = new ArrayList<>();
            packages1.add("Tech Package");
            packages1.add("Sport Package");
            checkCar(1, "BMW M4", 2025, "Blue", packages1, 120000, 5000, 5, carList.get(0));
            checkCar(2, "BMW 340i", 20260, "White", null, 800000, 0, 2, carList.get(1));

            ArrayList<Broker> brokerList = generalDS.getBrokerList();
            assertEquals(1, brokerList.size());
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
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }
}
