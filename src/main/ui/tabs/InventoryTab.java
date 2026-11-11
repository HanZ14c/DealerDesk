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
        styleTable(table);
        javax.swing.table.DefaultTableCellRenderer money = new javax.swing.table.DefaultTableCellRenderer();
        money.setHorizontalAlignment(SwingConstants.RIGHT);
        table.getColumnModel().getColumn(4).setCellRenderer(money);

        refreshTableData();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    /*
     * MODIFIES: this
     * EFFECTS: reloads data from dealership carList into the table model
     */
    @Override
    public void refresh() {
        refreshTableData();
    }

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
        String price = java.text.NumberFormat.getCurrencyInstance(java.util.Locale.CANADA).format(p);
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

        placeHeader("Inventory", refreshButton, editStatusButton, addNewCarButton);
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

        JPanel container = new JPanel(new BorderLayout(0, 16));
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 12, 20));
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

        statusComboBox = createStatusComboBox();
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
                                    JComponent field, int gridX, int gridY) {
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        form.add(new JLabel(labelText), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        form.add(field, gbc);
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
        refreshTableData();
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

        JPanel container = new JPanel(new BorderLayout(0, 16));
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 12, 20));
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
        JComboBox<String> comboBox = new JComboBox<>(statusChoices);
        // Aqua clips padded renderers; Basic sizes the control from the renderer.
        comboBox.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        comboBox.setBackground(Color.WHITE);
        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        comboBox.setRenderer((list, value, index, selected, focused) -> {
            JLabel label = (JLabel) renderer.getListCellRendererComponent(
                    list, value, index, selected, focused);
            label.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
            return label;
        });
        return comboBox;
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
        refreshTableData();
        dialog.dispose();
    }
}
