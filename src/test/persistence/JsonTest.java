package persistence;

import model.Broker;
import model.Car;
import model.Customer;
import model.Dealership;
import model.Sale;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class JsonTest {
    protected void checkCar(int carID, String model, int year, String color,
                            ArrayList<String> packages, double price, double promotion,
                            int status, Car car) {
        assertEquals(carID, car.getCarID());
        assertEquals(model, car.getModel());
        assertEquals(year, car.getYear());
        assertEquals(color, car.getColor());
        assertEquals(packages, car.getPackages());
        assertEquals(price, car.getPrice());
        assertEquals(promotion, car.getPromotion());
        assertEquals(status, car.getStatus());
    }
    protected void checkBroker(int brokerID, String name, ArrayList<Customer> customers, Broker broker) {
        assertEquals(brokerID, broker.getBrokerID());
        assertEquals(name, broker.getName());
        assertEquals(customers, broker.getCustomers());
    }
    protected void checkCustomer(int customerID, String name, String phoneNumber, Customer customer) {
        assertEquals(customerID, customer.getCustomerID());
        assertEquals(name, customer.getName());
        assertEquals(phoneNumber, customer.getPhoneNumber());
    }
    protected void checkSale(int carID, int brokerID, int customerID, double salePrice, Sale sale) {
        assertEquals(carID, sale.getCarID());
        assertEquals(brokerID, sale.getBrokerID());
        assertEquals(customerID, sale.getCustomerID());
        assertEquals(salePrice, sale.getSalePrice());
    }
}
