package ui.tabs;

import model.Car;
import ui.ButtonNames;
import ui.DealershipUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.awt.*;
import java.util.ArrayList;

/*
 * Presents the Inventory Tab
 */
@ExcludeFromJacocoGeneratedReport 
public class InventoryTab extends Tab {
    private JTable table;
    private DefaultTableModel tableModel;

    private JTextField modelField;
    private JTextField yearField;
    private JTextField colorField;
    private JTextField priceField;
    private JComboBox<String> statusComboBox;
    
    public InventoryTab(DealershipUI controller) {
        super(controller);
        setLayout(new BorderLayout());

        placeTable();
        placeButtonCol();
    }

    // [[[       ]]]
    // [[[ TABLE ]]]
    // [[[       ]]]

    /*
     * EFFECTS: inner class
     */
    @ExcludeFromJacocoGeneratedReport 
    private static class ReadOnlyTableModel extends DefaultTableModel {
        ReadOnlyTableModel(Object[] columnNames) {
            super(columnNames, 0);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    /*
     * EFFECTS: creates a table from dealership carList
     */
    private void placeTable() {
        String[] columns = new String[] {"Car ID", "Year", "Model", "Color", "Price", "Status"};
        tableModel = new ReadOnlyTableModel(columns);

        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);

        refreshTableData();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    /*
     * MODIFIES: this
     * EFFECTS: reloads data from dealership carList into the table model
     */
    private void refreshTableData() {
        tableModel.setRowCount(0);

        ArrayList<Car> cars = getController().getDealership().getCarList();
        for (Car c : cars) {
            Object[] row = new Object[] {
                c.getCarID(),
                c.getYear(),
                c.getModel(),
                c.getColor(),
                priceLabel(c.getPrice()),
                statusLabel(c.getStatus())
            };
            tableModel.addRow(row);
        }
    }

    /*
     * EFFECTS: returns price data with $
     */
    private String priceLabel(double p) {
        String price = "$" + p;
        return price;
    }

    /*
     * EFFECTS: returns readable status data in String
     */
    private String statusLabel(int status) {
        switch (status) {
            case 0: return "Car created";
            case 1: return "Order placed";
            case 2: return "In factory";
            case 3: return "Upcoming";
            case 4: return "In stock";
            case 5: return "Sold";
            default: return String.valueOf(status);
        }
    }

    // [[[         ]]]
    // [[[ BUTTONS ]]]
    // [[[         ]]]

    /*
     * EFFECTS: adds a column panel contains Refresh and Add New Car buttons
     */
    private void placeButtonCol() {
        JButton refreshButton = new JButton(ButtonNames.REFRESH.getValue());
        JButton addNewCarButton = new JButton(ButtonNames.ADD_NEW_CAR.getValue());
        JButton editStatusButton = new JButton(ButtonNames.EDIT_STATUS.getValue());

        refreshButton.addActionListener(e -> refreshTableData());
        addNewCarButton.addActionListener(e -> addNewCar());
        editStatusButton.addActionListener(e -> editCarStatus());

        JPanel refreshRow = formatButtonRow(refreshButton);
        JPanel editRow = formatButtonRow(addNewCarButton);
        editRow.add(editStatusButton); // place Edit status to the right of Add new car
        refreshRow.setSize(WIDTH, HEIGHT / 6);
        editRow.setSize(WIDTH, HEIGHT / 6);

        JPanel buttonCol = new JPanel(new GridLayout(2, 1));
        buttonCol.add(refreshRow);
        buttonCol.add(editRow);

        this.add(buttonCol, BorderLayout.SOUTH);
    }


    // [[[                ]]]
    // [[[ CREATE NEW CAR ]]]
    // [[[                ]]]

    /*
     * EFFECTS: opens a dialog panel to add a new car into dealership car list
     */
    private void addNewCar() {
        JDialog dialog = createModalDialog("Add New Car");
        createCarForm();

        JPanel form = buildFormPanel();
        JPanel buttonRow = placeSaveCancelButtons(dialog);

        JPanel container = new JPanel(new BorderLayout());
        container.add(form, BorderLayout.CENTER);
        container.add(buttonRow, BorderLayout.SOUTH);

        dialog.setContentPane(container);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    /*
     * EFFECTS: opems a dialog
     */
    private JDialog createModalDialog(String title) {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), title,
                Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        return dialog;
    }

    /*
     * EFFECTS: input fields for the new car
     */
    private void createCarForm() {
        modelField = new JTextField(16);
        yearField = new JTextField(8);
        colorField = new JTextField(12);
        priceField = new JTextField(10);

        String[] statusOptions = new String[] {
            "Car created", "Order placed", "In factory", "Upcoming", "In stock", "Sold"
        };
        statusComboBox = new JComboBox<>(statusOptions);
        statusComboBox.setSelectedIndex(0);
    }

    /*
     * EFFECTS: builds the form panel
     */
    private JPanel buildFormPanel() {
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 8, 4, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addLabelAndTextField(form, gbc, "Model:", modelField, 0, 0);
        addLabelAndTextField(form, gbc, "Year:", yearField, 0, 1);
        addLabelAndTextField(form, gbc, "Color:", colorField, 0, 2);
        addLabelAndTextField(form, gbc, "Price:", priceField, 0, 3);
        addLabelAndTextField(form, gbc, "Status:", statusComboBox, 0, 4);

        return form;
    }

    /*
     * EFFECTS: helper method for buildFormPanel()
     */
    private void addLabelAndTextField(JPanel form, GridBagConstraints gbc, String labelText,
                                    JTextField testField, int gridX, int gridY) {
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        form.add(new JLabel(labelText), gbc);
        gbc.gridx = 1;
        form.add(testField, gbc);
    }

    // Overload
    private void addLabelAndTextField(JPanel form, GridBagConstraints gbc, String labelText,
                                    JComboBox<String> comboBox, int gridX, int gridY) {
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        form.add(new JLabel(labelText), gbc);
        gbc.gridx = 1;
        form.add(comboBox, gbc);
    }


    /*
     * EFFECTS: builds Save/Cancel buttons and actions
     */
    private JPanel placeSaveCancelButtons(JDialog dialog) {
        JButton saveButton = new JButton(ButtonNames.SAVE_NEW_CAR.getValue());
        JButton cancelButton = new JButton(ButtonNames.CANCEL.getValue());

        JPanel buttonRow = formatButtonRow(saveButton);
        buttonRow.add(cancelButton);

        cancelButton.addActionListener(e -> dialog.dispose());
        saveButton.addActionListener(e -> saveNewCar(dialog));
        return buttonRow;
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates a new car from the form, update saving status and closes dialog
     */
    private void saveNewCar(JDialog dialog) {
        String model = modelField.getText().trim();
        String color = colorField.getText().trim();
        int year = Integer.parseInt(yearField.getText().trim());
        double price = Double.parseDouble(priceField.getText().trim());
        int status = statusComboBox.getSelectedIndex();
        Car newCar = new Car(model, year, color, null, price, status);
        getController().getDealership().addCar(newCar);
        getController().updateSavingStatus(false);
        // refreshTableData();
        dialog.dispose();
    }

    // [[[                 ]]]
    // [[[ EDIT CAR STATUS ]]]
    // [[[                 ]]]

    /*
     * MODIFIES: dealership
     * EFFECTS: opens a dialog to set a car's status by car ID
     */
    private void editCarStatus() {
        JDialog dialog = createModalDialog("Edit Status");

        JTextField carIdField = new JTextField(10);
        JComboBox<String> statusBox = createStatusComboBox();

        JPanel form = buildEditStatusForm(carIdField, statusBox);
        JPanel buttons = buildEditStatusButtons(dialog, carIdField, statusBox);

        JPanel container = new JPanel(new BorderLayout());
        container.add(form, BorderLayout.CENTER);
        container.add(buttons, BorderLayout.SOUTH);

        dialog.setContentPane(container);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    /*
     * EFFECTS: returns a dropdown of status labels
     */
    private JComboBox<String> createStatusComboBox() {
        String[] statusChoices = new String[] {
            "Car created", "Order placed", "In factory", "Upcoming", "In stock", "Sold"
        };
        return new JComboBox<>(statusChoices);
    }

    /*
     * EFFECTS: builds the edit status form panel
     */
    private JPanel buildEditStatusForm(JTextField carIdField, JComboBox<String> statusBox) {
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 8, 4, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addLabelAndTextField(form, gbc, "Car ID:", carIdField, 0, 0);
        addLabelAndTextField(form, gbc, "New Status:", statusBox, 0, 1);
        return form;
    }

    /*
     * EFFECTS: builds Save/Cancel buttons
     */
    private JPanel buildEditStatusButtons(JDialog dialog, JTextField carIdField, JComboBox<String> statusBox) {
        JButton saveButton = new JButton(ButtonNames.SAVE_STATUS.getValue());
        JButton cancelButton = new JButton(ButtonNames.CANCEL.getValue());
        JPanel buttonRow = formatButtonRow(saveButton);
        buttonRow.add(cancelButton);

        cancelButton.addActionListener(e -> dialog.dispose());
        saveButton.addActionListener(e -> updateStatus(carIdField, statusBox, dialog));
        return buttonRow;
    }

    /*
     * MODIDIES: dealership
     * EFFECTS: updates the car status and closesdialog
     */
    private void updateStatus(JTextField carIdField, JComboBox<String> statusBox, JDialog dialog) {
        int carId = Integer.parseInt(carIdField.getText());
        int status = statusBox.getSelectedIndex();
        Car car = getController().getDealership().getCarByCarID(carId);
        car.updateStatus(status);
        getController().updateSavingStatus(false);
        // refreshTableData();
        dialog.dispose();
    }
}
