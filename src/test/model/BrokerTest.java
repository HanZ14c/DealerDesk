package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

@ExcludeFromJacocoGeneratedReport 
class BrokerTest {
    private Broker testBroker;
    private Customer customerA;
    private Customer customerB;
    private ArrayList<Integer> customers;

    @BeforeEach
    void runBefore() {
        customerA = new Customer("Customer_A","1001001000");
        customerB = new Customer("Customer_B","2001001000");
        customers = new ArrayList<>();
        customers.add(customerA.getCustomerID());
        customers.add(customerB.getCustomerID());
        // Null customer
        testBroker = new Broker("Broker_A", customers);
    }

    @Test
    void testConstructor() {
        assertEquals("Broker_A", testBroker.getName());
        assertEquals(2, testBroker.getNumOfCustomers());
        
        Broker testBrokerB = new Broker("Broker_B", null);
        assertEquals(0, testBrokerB.getNumOfCustomers());
    }

    @Test
    void testGetters() {
        assertTrue(testBroker.getBrokerID() >= 1);
        assertNotNull(testBroker.getCustomers());
        Broker testBrokerB = new Broker("Broker_B", null);
        assertTrue(testBrokerB.getBrokerID() > testBroker.getBrokerID());
    }

    @Test
    void testAssignCustomer() {
        Customer customerC = new Customer("Customer_C", "3001001000");
        testBroker.assignCustomer(customerC.getCustomerID());
        // Duplicate
        testBroker.assignCustomer(customerC.getCustomerID());
        assertEquals(3, testBroker.getNumOfCustomers());
    }
}
