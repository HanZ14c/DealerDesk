package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

@ExcludeFromJacocoGeneratedReport 
public class CarTest {
    private Car testCar;
    private ArrayList<String> addOns;
    
    @BeforeEach
    void runBefore() {
        addOns = new ArrayList<>();
        addOns.add("Plus Package");
        addOns.add("Night Package");
        testCar = new Car("BMW M4", 2025, "Black", null, 120000, 0);
    }

    @Test
    void testConstructor() {
        assertEquals("BMW M4", testCar.getModel());
        assertEquals(2025, testCar.getYear());
        assertEquals("Black", testCar.getColor());
        assertEquals(120000, testCar.getPrice());
        assertEquals(0, testCar.getStatus());
        assertEquals(0, testCar.getPromotion());
        assertEquals(0, testCar.getPackageSize());

        Car testCarB = new Car("BMW 330i", 2025, "White", null, 70000, 0);
        assertTrue(testCar.getCarID() < testCarB.getCarID());
    }

    @Test
    void testConstructorWithAddOns() {
        addOns = new ArrayList<>();
        addOns.add("Tech Package");
        addOns.add("Luxury Package");

        testCar = new Car("Audi A5", 2024, "Blue", addOns, 65000, 0);
        assertEquals(2, testCar.getPackageSize());
        // assertTrue(car.getPackages().contains("Tech Package"));
        // assertTrue(car.getPackages().contains("Luxury Package"));
        assertEquals("Tech Package", testCar.getPackages().get(0));
        assertEquals("Luxury Package", testCar.getPackages().get(1));
    }

    @Test
    void testSetPackages() {
        addOns = new ArrayList<>();
        addOns.add("Base Package");
        addOns.add("Winter Package");
        testCar.setPackages(addOns);
        assertEquals(2, testCar.getPackageSize());
        assertEquals("Base Package", testCar.getPackages().get(0));
        assertEquals("Winter Package", testCar.getPackages().get(1));
        // Check null
        addOns = new ArrayList<>();
        testCar.setPackages(addOns);
        assertEquals(0, testCar.getPackageSize());
    }

    @Test
    void testAddPackages() {
        testCar.addPackage("Base Package");
        testCar.addPackage("Base Package");
        assertEquals("Base Package", testCar.getPackages().get(0));;
        assertEquals(1, testCar.getPackageSize());
    }

    @Test
    void testSetPromotion() {
        testCar.setPromotion(5000);
        assertEquals(5000, testCar.getPromotion());
        testCar.setPromotion(8000);
        assertEquals(8000, testCar.getPromotion());
    }

    @Test
    void testUpdateStatus() {
        testCar.updateStatus(1);
        assertEquals(1, testCar.getStatus());
        testCar.updateStatus(0);
        assertEquals(0, testCar.getStatus());
    }

    @Test
    void testJsonIDConfliction() {
        Car currCar = new Car(testCar.getCarID() + 1000, "1", 1, "1", new ArrayList<>(), 1, 0);
        Car newCar = new Car("2", 2, "2", new ArrayList<>(), 2, 0);
        assertEquals(currCar.getCarID() + 1, newCar.getCarID());
    }
}
