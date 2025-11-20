package ui;

import model.*;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

/*
 * EFFECTS: creates a GUI for dealership
 */
@ExcludeFromJacocoGeneratedReport 
public class DealershipUI extends JFrame {
    // stub
    public static void main(String[] args) {
        // stub
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates DealershipUI
     */
    private DealershipUI() {
        // stub
    }

    /*
     * EFEFCTS: setup Json Reader & Writer
     */
    private void setup() {
        // stub
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds gome tab, settings tab and report tab to this UI
     */
    private void loadTabs() {
        // stub
    }

    /*
     * EFFECTS: returns Dealship object controlled by ths UI
     */
    public Dealership getDealership() {
        return null;
    }

    // [[[       ]]]
    // [[[ L / S ]]]
    // [[[       ]]]

    /*
     * EFFECTS: saves the workroom to file
     * SOURCE: JsonSerializationDemo
     */
    public String saveFile() {
        return null;
    }

    /*
     * MODIFIES: this
     * EFFECTS: loads Dealership from file
     * SOURCE: JsonSerializationDemo
     */
    public String loadFile() {
        return null;
    }

    /*
     * MODIFIES: this
     * EFFECTS: updates the saving status
     */
    public void updateSavingStatus(Boolean update) {
        // stub
    }

    /*
     * EFFECTS: ask for saving if not saved
     */
    private void askForSaving() {
        // stub
    }

    /*
     * EFFECTS: returns the group sizes of dealership (car, broker, customer, sale)
     */
    public String getDealershipInfo() {
        return null;
    }
}
