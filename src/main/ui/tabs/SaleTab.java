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
    private JDialog saleDialog;

    public SaleTab(DealershipUI controller) {
        super(controller);

        placeTable();
        JButton addSale = new JButton("New sale");
        addSale.addActionListener(e -> placeNewSaleForm());
        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(e -> refreshSaleTableData());
        placeHeader("Sales", refresh, addSale);
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
        styleTable(saleTable);
        javax.swing.table.DefaultTableCellRenderer money = new javax.swing.table.DefaultTableCellRenderer();
        money.setHorizontalAlignment(SwingConstants.RIGHT);
        saleTable.getColumnModel().getColumn(3).setCellRenderer(money);

        refreshSaleTableData();

        JScrollPane scrollPane = new JScrollPane(saleTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    /*
     * MODIFIES: this
     * EFFECTS: refresh sale table
     */
    @Override
    public void refresh() {
        refreshSaleTableData();
    }

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
    }

    /*
     * EFFECTS: helper for refreshSaleTableData()
     */
    private String priceLabel(double p) {
        return java.text.NumberFormat.getCurrencyInstance(java.util.Locale.CANADA).format(p);
    }

    // [[[      ]]]
    // [[[ FORM ]]]
    // [[[      ]]]

    /*
     * MODIFIES: this
     * EFFECTS: opens a dialog for creating a new sale
     */
    private void placeNewSaleForm() {
        saleDialog = new JDialog(SwingUtilities.getWindowAncestor(this), "New sale",
                Dialog.ModalityType.APPLICATION_MODAL);
        JPanel container = new JPanel(new BorderLayout(0, 16));
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 12, 20));

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
        JButton cancelButton = new JButton("Cancel");
        JPanel buttonRow = formatButtonRow(saveButton);
        buttonRow.add(cancelButton);

        cancelButton.addActionListener(e -> saleDialog.dispose());
        saveButton.addActionListener(e -> onSave());

        container.add(form, BorderLayout.CENTER);
        container.add(buttonRow, BorderLayout.SOUTH);

        saleDialog.setContentPane(container);
        saleDialog.pack();
        saleDialog.setLocationRelativeTo(this);
        saleDialog.setVisible(true);
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
        refreshSaleTableData();
        saleDialog.dispose();
    }

}
