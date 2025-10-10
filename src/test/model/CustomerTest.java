package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private Customer testCustomer;

    @BeforeEach
    void runBefore() {
        testCustomer = new Customer("Customer_A","1001001000");
    }

    @Test
    void testConstructor() {
        assertEquals("Customer_A", testCustomer.getName());
        assertEquals("1001001000", testCustomer.getPhoneNumber());
        assertTrue(testCustomer.getCustomerID() >= 1);
    }
}
