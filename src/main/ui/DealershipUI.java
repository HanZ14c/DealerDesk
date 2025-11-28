package ui;

import model.*;
import persistence.*;
import ui.tabs.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

/*
 * EFFECTS: creates a GUI for dealership
 */
@ExcludeFromJacocoGeneratedReport 
public class DealershipUI extends JFrame {
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    public static final int HOME_TAB_INDEX = 0;
    public static final int INVENTORY_TAB_INDEX = 1;
    public static final int SALE_TAB_INDEX = 2;
    public static final int DIRECTORY_TAB_INDEX = 3;

    private JTabbedPane sidebar;

    private static final String JSON_STORE = "./data/dealership.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Dealership dealership;
    private boolean saved;

    public static void main(String[] args) {
        new DealershipUI();
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates DealershipUI
     */
    private DealershipUI() {
        super("Dealership Console");
        saved = true;

        setSize(WIDTH, HEIGHT);
        setMinimumSize(new java.awt.Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new CloseHandler());

        dealership = new Dealership(null, null, null, null);

        try {
            setup();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);
        loadTabs();
        add(sidebar);
        setVisible(true);
    }

    /*
     * EFFECTS: inner class for haldling close event
     */
    @ExcludeFromJacocoGeneratedReport 
    private class CloseHandler extends WindowAdapter {
        @Override public void windowClosing(WindowEvent e) {
            askForSaving();
            // print events
            for (Event next : EventLog.getInstance()) {
                System.out.println(next.toString() + "\n");
            }
        }
    }

    /*
     * EFEFCTS: setup Json Reader & Writer
     */
    private void setup() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds gome tab, settings tab and report tab to this UI
     */
    private void loadTabs() {
        JPanel homeTab = new HomeTab(this);
        JPanel inventoryTab = new InventoryTab(this);
        JPanel saleTab = new SaleTab(this);
        JPanel hrTab = new DirectoryTab(this);

        sidebar.add(homeTab, HOME_TAB_INDEX);
        sidebar.setTitleAt(HOME_TAB_INDEX, "Home");
        sidebar.add(inventoryTab, INVENTORY_TAB_INDEX);
        sidebar.setTitleAt(INVENTORY_TAB_INDEX, "Inventory");
        sidebar.add(saleTab, SALE_TAB_INDEX);
        sidebar.setTitleAt(SALE_TAB_INDEX, "Sale");
        sidebar.add(hrTab, DIRECTORY_TAB_INDEX);
        sidebar.setTitleAt(DIRECTORY_TAB_INDEX, "Directory");
    }

    /*
     * EFFECTS: returns Dealship object controlled by ths UI
     */
    public Dealership getDealership() {
        return dealership;
    }

    // [[[       ]]]
    // [[[ L / S ]]]
    // [[[       ]]]

    /*
     * EFFECTS: saves the workroom to file
     * SOURCE: JsonSerializationDemo
     */
    public String saveFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(dealership);
            jsonWriter.close();
            updateSavingStatus(true);
            return ("Saved dealership to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            return ("Unable to write to file: " + JSON_STORE);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: loads Dealership from file
     * SOURCE: JsonSerializationDemo
     */
    public String loadFile() {
        try {
            dealership = jsonReader.read();
            // updateSavingStatus(true);
            return ("Loaded dealership from " + JSON_STORE);
        } catch (IOException e) {
            return ("Unable to read from file: " + JSON_STORE);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: updates the saving status
     */
    public void updateSavingStatus(Boolean update) {
        saved = update;
    }

    /*
     * EFFECTS: ask for saving if not saved
     */
    private void askForSaving() {
        if (saved) {
            dispose();
        } else {
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "You have unsaved changes. Do you want to save?",
                    "Unsaved Changes",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
            if (choice == JOptionPane.YES_OPTION) {
                saveFile();
            }
            dispose();
        }
    }

    /*
     * EFFECTS: returns the group sizes of dealership (car, broker, customer, sale)
     */
    public String getDealershipInfo() {
        String report = dealership.getInfo();
        return report;
    }
}
