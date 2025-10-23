package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport 
public class SaleTest {
    private Sale testSale;

    @BeforeEach
    void setup() {
        testSale = new Sale(1, 2, 3, 30000);
    }
    
    @Test
    void testConstructor() {
        assertEquals(1, testSale.getCarID());
        assertEquals(2, testSale.getBrokerID());
        assertEquals(3, testSale.getCustomerID());
        assertEquals(30000, testSale.getSalePrice());
    }
}
