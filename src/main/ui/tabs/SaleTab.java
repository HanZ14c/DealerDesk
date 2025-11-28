package ui.tabs;

import model.Sale;
import ui.ButtonNames;
import ui.DealershipUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.awt.*;
import java.util.ArrayList;

/*
 * Presents the Sale Tab
 */
@ExcludeFromJacocoGeneratedReport 
public class SaleTab extends Tab {
    private JTable saleTable;
    private DefaultTableModel saleTableModel;

    private JTextField carIdField;
    private JTextField brokerIdField;
    private JTextField customerIdField;
    private JTextField priceField;
    private JLabel congratImageLabel;

    public SaleTab(DealershipUI controller) {
        super(controller);
        setLayout(new BorderLayout());

        placeTable();
        placeNewSaleForm();
    }

    // [[[       ]]]
    // [[[ TABLE ]]]
    // [[[       ]]]

    /*
     * EFFECTS: inner class for read-only table
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
     * EFFECTS: creates a table from dealership sale list
     */
    private void placeTable() {
        String[] columns = new String[] {"Car ID", "Broker ID", "Customer ID", "Sale Price"};
        saleTableModel = new ReadOnlyTableModel(columns);

        saleTable = new JTable(saleTableModel);
        saleTable.setFillsViewportHeight(true);

        refreshSaleTableData();

        JScrollPane scrollPane = new JScrollPane(saleTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    /*
     * MODIFIES: this
     * EFFECTS: refresh sale table
     */
    private void refreshSaleTableData() {
        saleTableModel.setRowCount(0);
        ArrayList<Sale> sales = getController().getDealership().getSaleList();
        for (Sale s : sales) {
            Object[] row = new Object[] {
                s.getCarID(),
                s.getBrokerID(),
                s.getCustomerID(),
                priceLabel(s.getSalePrice())
            };
            saleTableModel.addRow(row);
        }
        if (congratImageLabel != null) {
            congratImageLabel.setVisible(false);
        }
    }

    /*
     * EFFECTS: helper for refreshSaleTableData()
     */
    private String priceLabel(double p) {
        return "$" + p;
    }

    // [[[      ]]]
    // [[[ FORM ]]]
    // [[[      ]]]

    /*
     * MODIFIES: this
     * EFFECTS: places the form for creating a new sale at the bottom
     */
    private void placeNewSaleForm() {
        JPanel container = new JPanel(new BorderLayout());

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 8, 4, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        carIdField = new JTextField(12);
        brokerIdField = new JTextField(12);
        customerIdField = new JTextField(12);
        priceField = new JTextField(12);

        addLabelAndTextField(form, gbc, "Car ID:", carIdField, 0, 0);
        addLabelAndTextField(form, gbc, "Broker ID:", brokerIdField, 0, 1);
        addLabelAndTextField(form, gbc, "Customer ID:", customerIdField, 0, 2);
        addLabelAndTextField(form, gbc, "Sale Price:", priceField, 0, 3);

        JButton saveButton = new JButton(ButtonNames.SAVE_SALE.getValue());
        JButton refreshButton = new JButton(ButtonNames.REFRESH.getValue());
        JPanel buttonRow = formatButtonRow(saveButton);
        buttonRow.add(refreshButton);

        refreshButton.addActionListener(e -> refreshSaleTableData());
        saveButton.addActionListener(e -> onSave());

        container.add(form, BorderLayout.CENTER);
        container.add(buttonRow, BorderLayout.SOUTH);

        placeImage(container);

        add(container, BorderLayout.SOUTH);
    }

    /*
     * EFFECTS: helper method for placeNewSaleForm()
     */
    private void addLabelAndTextField(JPanel form, GridBagConstraints gbc, String labelText, JTextField textField,
                                      int gridX, int gridY) {
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        form.add(new JLabel(labelText), gbc);
        gbc.gridx = 1;
        form.add(textField, gbc);
    }

    /*
     * MODIFIES: dealership
     * EFFECTS: validates inputs, creates a sale, adds it to dealership, and updates UI
     */
    private void onSave() {
        int carId = Integer.parseInt(carIdField.getText().trim());
        int brokerId = Integer.parseInt(brokerIdField.getText().trim());
        int customerId = Integer.parseInt(customerIdField.getText().trim());
        double price = Double.parseDouble(priceField.getText().trim());
        Sale newSale = new Sale(carId, brokerId, customerId, price);
        getController().getDealership().addSale(newSale);
        getController().updateSavingStatus(false);
        congratImageLabel.setVisible(true);
    }

    // [[[       ]]]
    // [[[ IMAGE ]]]
    // [[[       ]]]

    /*
     * EFFECTS: places a image with fixed size left to the form
     */
    private void placeImage(JPanel container) {
        congratImageLabel = new JLabel();
        // Fixed size
        final int w = 120;
        final int h = 120;
        ImageIcon raw = new ImageIcon("images/cheers.png");
        Image scaled = raw.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        congratImageLabel.setIcon(new ImageIcon(scaled));
        Dimension fixed = new Dimension(w, h);
        congratImageLabel.setPreferredSize(fixed);
        congratImageLabel.setMinimumSize(fixed);
        congratImageLabel.setMaximumSize(fixed);
        congratImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        congratImageLabel.setVerticalAlignment(SwingConstants.CENTER);
        congratImageLabel.setVisible(false);
        container.add(congratImageLabel, BorderLayout.WEST);
    }
}
