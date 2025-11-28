package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

/*
 * Presents the Dealship application
 */
@ExcludeFromJacocoGeneratedReport 
public class DealershipApp {
    private static final String JSON_STORE = "./data/dealership.json";
    private Scanner scan;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Dealership dealership;
    private boolean saved;

    // EFFECTS: runs the Dealership application
    public DealershipApp() throws FileNotFoundException {
        scan = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        dealership = new Dealership(null, null, null, null);
        saved = false;
        runDealership();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runDealership() {
        boolean running = true;
        String command = null;
        System.out.println("\n=== CAR DEALERSHIP MANAGER ===");
        while (running) {
            do {
                displayMenu();
                command = scan.next();
                command = command.toLowerCase();

                if (command.equals("0")) {
                    // ask to save
                    if (!saved) {
                        askToSave();
                    }
                    running = false;
                } else {
                    processCommand(command);
                }
            } while (command == null);
        }
        System.out.println("\n--- Goodbye :-) ---");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("1) Add a new car");
        System.out.println("2) View inventory");
        System.out.println("3) Update car status");
        System.out.println("4) Record a sale");
        System.out.println("l) Load saved dealership");
        System.out.println("s) Save dealership");
        System.out.println("0) exit");
        System.out.print("Select: ");
    }

    /*
     * MODIFIES: saved
     * EFFECTS: ask to save file
     */
    private void askToSave() {
        System.err.print("\nYou have unsaved content, do you want to save? (y / n): ");
        String command = scan.next();
        if (command.equalsIgnoreCase("Y")) {
            saveDealership();
        }
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
            case "l":
                loadDealership();
                break;
            case "s":
                saveDealership();
                break;
            default:
                System.out.println("Selection not valid, please try again.");
        }
    }

    // MODIFIES: dealership -> carList
    // EFFECTS: add a brand new car order
    private void addCar() {
        scan.nextLine();
        System.out.print("\n--- ADD NEW CAR ---\nModel: ");
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
        saved = false;
        System.out.println("New car added!\n");
    }

    @SuppressWarnings("methodlength")
    // EFFECTS: print the carList
    private void viewInventory() {
        ArrayList<Car> inventory = dealership.getCarList();
        if (inventory.size() != 0) {
            System.out.println("\n--- INVENTORY ---\nID | YEAR | MODEL, COLOR, PRICE, PROMOTION, STATUS");
            for (Car car : inventory) {
                System.out.print(car.getCarID() + "  | " + car.getYear() + " | " + car.getModel() + ", ");
                System.out.print(car.getColor() + ", " + car.getPackageSize() + ", $" + car.getPrice() + ", $");
                System.out.print(car.getPromotion() + ", ");
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
        } else {
            System.out.println("\nNo data founded.\n");
        }
    }

    // MODIFIES: dealership -> carList -> Car -> status
    // EFFECTS: update the car status
    private void updateCarStatus() {
        scan.nextLine();
        System.out.print("\n--- UPDATE CAR STATUS ---\nSelect a car #: ");
        int id = scan.nextInt();
        Car car = dealership.getCarByCarID(id);

        System.out.println("Select new status for: " + car.getYear() + " " + car.getModel());
        System.out.println("    0 - Car modification created");
        System.out.println("    1 - Order placed");
        System.out.println("    2 - In factory");
        System.out.println("    3 - Upcoming");
        System.out.println("    4 - In stock");
        System.out.println("    5 - Sold");
        System.out.print("Select: ");
        int status = scan.nextInt();
        car.updateStatus(status);
        saved = false;
        System.out.println("Car status updated!\n");
    }

    // MODIFIES: dealership -> saleList
    // EFFECTS: add a sale record
    private void recordSale() {
        scan.nextLine();
        System.out.print("\n--- RECORD A NEW SALE ---\nSelect a car #: ");
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
        saved = false;
        System.out.println("A new sale created!\n");
    }

    /*
     * EFFECTS: saves the workroom to file
     * SOURCE: JsonSerializationDemo
     */
    private void saveDealership() {
        try {
            jsonWriter.open();
            jsonWriter.write(dealership);
            jsonWriter.close();
            saved = true;
            System.out.println("\nSaved dealership to " + JSON_STORE + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("\nUnable to write to file: " + JSON_STORE + "\n");
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: loads Dealership from file
     * SOURCE: JsonSerializationDemo
     */
    private void loadDealership() {
        try {
            dealership = jsonReader.read();
            System.out.println("\nLoaded dealership from " + JSON_STORE + "\n");
        } catch (IOException e) {
            System.out.println("\nUnable to read from file: " + JSON_STORE + "\n");
        }
    }
}
