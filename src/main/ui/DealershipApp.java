package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class DealershipApp {
    private Dealership dealership;
    private Scanner scan;

    // EFFECTS: runs the Dealership application
    public DealershipApp() {
        runDealership();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runDealership() {
        boolean running = true;
        String command = null;

        init();
        System.out.println("\n=== CAR DEALERSHIP MANAGER ===");
        while (running) {
            do {
                displayMenu();
                command = scan.next();
                command = command.toLowerCase();

                if (command.equals("0")) {
                    running = false;
                } else {
                    processCommand(command);
                }
            } while (command == null);
        }

        System.out.println("\nGoodbye :-)");
    }

    // MODIFIES: this
    // EFFECTS: initializes dealership with few cars
    private void init() {
        dealership = new Dealership(null, null, null, null);
        dealership.addCar(new Car("BMW M4", 2025, "Black", null, 120000, 3));
        dealership.addCar(new Car("BMW 330i", 2025, "White", null, 70000, 4));
        dealership.addCar(new Car("Audi A5", 2024, "Blue", null, 65000, 4));
        dealership.addCar(new Car("Audi A7", 2020, "White", null, 50000, 5));

        scan = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("1) Add a new car");
        System.out.println("2) View inventory");
        System.out.println("3) Update car status");
        System.out.println("4) Record a sale");
        System.out.println("0) exit");
        System.out.print("Select: ");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "1":
                addCar();
                break;
            case "2":
                viewInventory();
                break;
            case "3":
                updateCarStatus();
                break;
            case "4":
                recordSale();
                break;
            default:
                System.out.println("Selection not valid, please try again.");
        }
    }

    // MODIFIES: dealership -> carList
    // EFFECTS: add a brand new car order
    private void addCar() {
        scan.nextLine();
        System.out.print("Model: ");
        String model = scan.nextLine();
        System.out.print("Year: ");
        int year = scan.nextInt();
        scan.nextLine();
        System.out.print("Color: ");
        String color = scan.nextLine();
        System.out.print("Price: ");
        int price = scan.nextInt();
        scan.nextLine();

        Car car = new Car(model, year, color, null, price, 0);
        dealership.addCar(car);
        System.out.println("New car added!\n");
    }

    // EFFECTS: print the carList
    private void viewInventory() {
        ArrayList<Car> inventory = dealership.getCarList();
        if (inventory.size() != 0) {
            System.out.println("\n=== INVENTORY ===\nID | YEAR | MODEL");
            for (Car car : inventory) {
                System.out.print(car.getCarID() + "  | " + car.getYear() + " | " + car.getModel() + " | ");
                int status = car.getStatus();
                switch (status) {
                    case 0:
                        System.out.println("Car created");
                        break;
                    case 1:
                        System.out.println("Order placed");
                        break;
                    case 2:
                        System.out.println("In factory");
                        break;
                    case 3:
                        System.out.println("Upcoming");
                        break;
                    case 4:
                        System.out.println("In stock");
                        break;
                    case 5:
                        System.out.println("Sold");
                    default:
                        break;
                }
            }
            System.out.println();
        }
    }

    // MODIFIES: dealership -> carList -> Car -> status
    // EFFECTS: update the car status
    private void updateCarStatus() {
        scan.nextLine();
        System.out.print("\n=== UPDATE CAR STATUS ===\nSelect a car #: ");
        int id = scan.nextInt();
        Car car = dealership.getCarByCarID(id);

        System.out.println("Select new status for: " + car.getYear() + " " + car.getModel());
        System.out.println("    0 - Car modification created\n" + //
                "    1 - Order placed\n" + //
                "    2 - In factory\n" + //
                "    3 - Upcoming\n" + //
                "    4 - In stock\n" + //
                "    5 - Sold");
        System.out.print("Select: ");
        int status = scan.nextInt();
        car.updateStatus(status);
        System.out.println("Car status updated!\n");
    }

    // MODIFIES: dealership -> saleList
    // EFFECTS: add a sale record
    private void recordSale() {
        scan.nextLine();
        System.out.print("\n=== RECORD A NEW SALE ===\n" + //
                "Select a car #: ");
        int carID = scan.nextInt();
        scan.nextLine();
        System.out.print("Select a broker #: ");
        int brokerID = scan.nextInt();
        scan.nextLine();
        System.out.print("Select a customer #: ");
        int customerID = scan.nextInt();
        scan.nextLine();
        System.out.print("Select a price #: ");
        double price = scan.nextDouble();
        scan.nextLine();

        dealership.addSale(new Sale(carID, brokerID, customerID, price));
        ;
        System.out.println("A new sale created!\n");
    }
}
