package ui.tabs;

import ui.DealershipUI;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport 
public class InventoryTab extends Tab {
    // stub
    
    public InventoryTab(DealershipUI controller) {
        super(controller);
        // stub
    }

    // [[[       ]]]
    // [[[ TABLE ]]]
    // [[[       ]]]

    /*
     * EFFECTS: creates a table from dealership carList
     */
    private void placeTable() {
        // stub
    }

    /*
     * MODIFIES: this
     * EFFECTS: reloads data from dealership carList into the table model
     */
    private void refreshTableData() {
        // stub
    }

    // [[[         ]]]
    // [[[ BUTTONS ]]]
    // [[[         ]]]

    /*
     * EFFECTS: adds a column panel contains Refresh and Add New Car buttons
     */
    private void placeButtonCol() {
        // stub
    }


    // [[[                ]]]
    // [[[ CREATE NEW CAR ]]]
    // [[[                ]]]

    /*
     * EFFECTS: opens a dialog panel to add a new car into dealership car list
     */
    private void addNewCar() {
        // stub
    }

    /*
     * EFFECTS: opems a dialog
     */
    private JDialog createModalDialog(String title) {
        return null;
    }

    /*
     * EFFECTS: input fields for the new car
     */
    private void createCarForm() {
        // stub
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates a new car from the form, update saving status and closes dialog
     */
    private void saveNewCar(JDialog dialog) {
        // stub
    }

    // [[[                 ]]]
    // [[[ EDIT CAR STATUS ]]]
    // [[[                 ]]]

    /*
     * MODIFIES: dealership
     * EFFECTS: opens a dialog to set a car's status by car ID
     */
    private void editCarStatus() {
        // stub
    }

    /*
     * MODIDIES: dealership
     * EFFECTS: updates the car status and closesdialog
     */
    private void updateStatus(JTextField carIdField, JComboBox<String> statusBox, JDialog dialog) {
        // stub
    }
}
