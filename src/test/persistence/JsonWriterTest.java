package persistence;

import model.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// Source: JsonSerializationDemo
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
            // add carList
            ArrayList<String> packages1 = new ArrayList<>();
            packages1.add("Tech Package");
            packages1.add("Sport Package");
            Car car1 = new Car("BMW M4", 2025, "Blue", packages1, 120000, 5);
            car1.setPromotion(5000);
            Car car2 = new Car("BMW 340i", 2026, "White", null, 80000, 2);
            generalDS.addCar(car1);
            generalDS.addCar(car2);

            // add brokerList
            ArrayList<Integer> broker1Customers = new ArrayList<>();
            broker1Customers.add(1);
            broker1Customers.add(2);
            Broker broker1 = new Broker("Alijah", broker1Customers);
            generalDS.addBroker(broker1);
            Broker broker2 = new Broker("Bozena", new ArrayList<>());
            generalDS.addBroker(broker2);

            // add customerList
            Customer customer1 = new Customer("Alex", "100-100-0001");
            Customer customer2 = new Customer("Bruno", "100-100-0002");
            generalDS.addCustomer(customer1);
            generalDS.addCustomer(customer2);

            // add saleList
            Sale sale1 = new Sale(car1.getCarID(), broker1.getBrokerID(), customer1.getCustomerID(), 115000);
            generalDS.addSale(sale1);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDealership.json");
            writer.open();
            writer.write(generalDS);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDealership.json");
            generalDS = reader.read();

            ArrayList<Car> carList = generalDS.getCarList();
            assertEquals(2, carList.size());
            checkCar(car1.getCarID(), "BMW M4", 2025, "Blue", packages1, 120000, 5000, 5, carList.get(0));
            checkCar(car2.getCarID(), "BMW 340i", 2026, "White", new ArrayList<>(), 80000, 0, 2, carList.get(1));

            ArrayList<Broker> brokerList = generalDS.getBrokerList();
            assertEquals(2, brokerList.size());
            ArrayList<Integer> broker1CustomersAddedBySale = new ArrayList<>(broker1Customers);
            if (!broker1CustomersAddedBySale.contains(customer1.getCustomerID())) {
                broker1CustomersAddedBySale.add(customer1.getCustomerID());
            }
            checkBroker(broker1.getBrokerID(), "Alijah", broker1CustomersAddedBySale, brokerList.get(0));

            ArrayList<Customer> customerList = generalDS.getCustomerList();
            assertEquals(2, customerList.size());
            checkCustomer(customer1.getCustomerID(), "Alex", "100-100-0001", customerList.get(0));
            checkCustomer(customer2.getCustomerID(), "Bruno", "100-100-0002", customerList.get(1));

            ArrayList<Sale> saleList = generalDS.getSaleList();
            assertEquals(1, saleList.size());
            checkSale(car1.getCarID(), broker1.getBrokerID(), customer1.getCustomerID(), 115000, saleList.get(0));
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }
}
