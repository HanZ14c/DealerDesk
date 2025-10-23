package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

@ExcludeFromJacocoGeneratedReport 
public class DealershipTest {
    private Dealership testDealership;
    private ArrayList<Car> testCarList;
    private ArrayList<Broker> testBrokerList;
    private ArrayList<Customer> testCustomerList;
    private ArrayList<Sale> testSaleList;

    private Car carA;
    private Car carB;
    private Car carC;
    private Broker brokerA;
    private Broker brokerB;
    private Customer customerA;
    private Customer customerB;
    private Sale sale;

    @BeforeEach
    void setup() {
        carA = new Car("BMW M4", 2025, "Black", null, 120000, 1);
        carB = new Car("BMW 330i", 2025, "White", null, 70000, 0);
        carC = new Car("Audi A5", 2024, "Blue", null, 65000, 0);
        brokerA = new Broker("Broker_A", null);
        brokerB = new Broker("Broker_B", null);
        customerA = new Customer("Customer_A","1001001000");
        customerB = new Customer("Customer_B","2001001000");
        sale = new Sale(carA.getCarID(), brokerA.getBrokerID(), customerA.getCustomerID(), 12000);
        
        testCarList = new ArrayList<>();
        testCarList.add(carA);
        testCarList.add(carB);
        testCarList.add(carC);
        testBrokerList = new ArrayList<>();
        testBrokerList.add(brokerA);
        testBrokerList.add(brokerB);
        testCustomerList = new ArrayList<>();
        testCustomerList.add(customerA);
        testCustomerList.add(customerB);
        testSaleList = new ArrayList<>();
        testSaleList.add(sale);

        testDealership = new Dealership(testCarList, testBrokerList, testCustomerList, testSaleList);
    }

    @Test
    void testConstructor() {
        assertEquals(3, testDealership.getCarList().size());
        assertEquals(2, testDealership.getBrokerList().size());
        assertEquals(2, testDealership.getCustomerList().size());
        assertEquals(1, testDealership.getSaleList().size());
        assertEquals(carA, testDealership.getCarList().get(0));
        assertEquals(carB, testDealership.getCarList().get(1));
        assertEquals(carC, testDealership.getCarList().get(2));
        assertEquals(brokerA,testDealership.getBrokerList().get(0));
        assertEquals(brokerB,testDealership.getBrokerList().get(1));
        assertEquals(customerA, testDealership.getCustomerList().get(0));
        assertEquals(customerB, testDealership.getCustomerList().get(1));
        assertEquals(sale, testDealership.getSaleList().get(0));
        // Empty dealership
        Dealership testEmptyDealership = new Dealership(null, null, null, null);
        assertEquals(0, testEmptyDealership.getCarList().size());
        assertEquals(0, testEmptyDealership.getBrokerList().size());
        assertEquals(0, testEmptyDealership.getCustomerList().size());
        assertEquals(0, testEmptyDealership.getSaleList().size());
    }

    @Test
    void testSetCarList() {
        ArrayList<Car> newCarList = new ArrayList<>();
        newCarList.add(carA);
        newCarList.add(carC);
        testDealership.setCarList(newCarList);
        assertEquals(2, testDealership.getCarList().size());
        assertEquals(carC, testDealership.getCarList().get(1));
    }

    @Test
    void testSetBrokerList() {
        ArrayList<Broker> newBrokerList = new ArrayList<>();
        newBrokerList.add(brokerB);
        testDealership.setBrokerList(newBrokerList);
        assertEquals(1, testDealership.getBrokerList().size());
        assertEquals(brokerB, testDealership.getBrokerList().get(0));
    }

    @Test
    void testSetCustomerList() {
        ArrayList<Customer> newCustomerList = new ArrayList<>();
        newCustomerList.add(customerB);
        testDealership.setCustomerList(newCustomerList);
        assertEquals(1, testDealership.getCustomerList().size());
        assertEquals(customerB, testDealership.getCustomerList().get(0));
    }

    @Test
    void testSetSaleList() {
        ArrayList<Sale> newSaleList = new ArrayList<>();
        Sale newSale = new Sale(carC.getCarID(), brokerB.getBrokerID(), customerB.getCustomerID(), 10000);
        newSaleList.add(newSale);
        testDealership.setSaleList(newSaleList);
        assertEquals(1, testDealership.getSaleList().size());
        assertEquals(newSale, testDealership.getSaleList().get(0));
    }

    @Test
    void testAddCar() {
        Car newCar = new Car("Audi A7", 2020, "White", null, 50000, 0);
        testDealership.addCar(newCar);
        // Add duplicates
        testDealership.addCar(newCar);
        assertEquals(4, testDealership.getCarList().size());
        assertEquals(newCar, testDealership.getCarList().get(3));
    }

    @Test
    void testAddBroker() {
        Broker newBroker = new Broker("Broker_new", null);
        testDealership.addBroker(newBroker);
        // Add Duplicates
        testDealership.addBroker(newBroker);
        assertEquals(3, testDealership.getBrokerList().size());
        assertEquals(newBroker, testDealership.getBrokerList().get(2));
    }

    @Test
    void testAddCustomer() {
        Customer newCustomer = new Customer("Customer_new", "9001001000");
        testDealership.addCustomer(newCustomer);
        // Add Duplicates
        testDealership.addCustomer(newCustomer);
        assertEquals(3, testDealership.getCustomerList().size());
        assertEquals(newCustomer, testDealership.getCustomerList().get(2));
    }

    @Test
    void testAddSale() {
        Sale newSale = new Sale(carC.getCarID(), brokerB.getBrokerID(), customerB.getCustomerID(), 10000);
        testDealership.addSale(newSale);
        // Add Duplicates
        testDealership.addSale(newSale);
        assertEquals(2, testDealership.getSaleList().size());
        assertEquals(newSale, testDealership.getSaleList().get(1));
        assertEquals(5, testDealership.getCarList().get(2).getStatus());
    }

    @Test
    void testGetCarByCarID() {
        assertNull(testDealership.getCarByCarID(0));
        int id = carA.getCarID();
        assertEquals(carA, testDealership.getCarByCarID(id));
    }

    @Test
    void testFilterCarByModel() {
        assertEquals("BMW M4", testDealership.filterCarByModel("BMW M4").get(0).getModel());
        assertEquals(0, testDealership.filterCarByModel("null").size());
    }

    @Test
    void testFilterCarByColor() {
        assertEquals("BMW M4", testDealership.filterCarByColor("Black").get(0).getModel());
        assertEquals(0, testDealership.filterCarByColor("null").size());
    }

    @Test
    void testFilterCarByStatus() {
        assertEquals("BMW M4", testDealership.filterCarByStatus(5).get(0).getModel());
        assertEquals(0, testDealership.filterCarByStatus(2).size());
    }

    @Test
    void testFilterCarByYear() {
        assertEquals("BMW M4", testDealership.filterCarByYear(2024, 2025).get(0).getModel());
        assertEquals(0, testDealership.filterCarByYear(0, 1).size());
        assertEquals(0, testDealership.filterCarByYear(3000, 3001).size());
        assertEquals(0, testDealership.filterCarByYear(1, 0).size());

    }

    @Test
    void testFilterCarByPrice() {
        assertEquals("BMW M4", testDealership.filterCarByPrice(10000, 120000).get(0).getModel());
        assertEquals(0, testDealership.filterCarByPrice(900000, 900001).size());
        assertEquals(0, testDealership.filterCarByPrice(0, 1).size());
        assertEquals(0, testDealership.filterCarByPrice(1, 0).size());
    }
}
