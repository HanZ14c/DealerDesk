package ui.tabs;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Customer;
import model.Broker;
import ui.ButtonNames;
import ui.DealershipUI;

/*
 * Presents the Directory Tab
 */
@ExcludeFromJacocoGeneratedReport 
public class DirectoryTab extends Tab {
    private JTable brokerTable;
    private DefaultTableModel brokerTableModel;
    private JTable customerTable;
    private DefaultTableModel customerTableModel;
    private JPanel tables;

    public DirectoryTab(DealershipUI controller) {
        super(controller);
        tables = new JPanel(new GridLayout(2, 1, 0, 24));

        placeBrokerTable();
        placeCustomerTable();
        add(tables, BorderLayout.CENTER);
        placeRefreshButton();
    }

    // [[[       ]]]
    // [[[ TABLE ]]]
    // [[[       ]]]

    /*
     * EFFECTS: inner class, set table read only
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
     * EFFECTS: creates a table for Dealership broker list
     */
    private void placeBrokerTable() {
        String[] columns = new String[] {"Broker ID", "Name", "Assigned customers"};
        brokerTableModel = new ReadOnlyTableModel(columns);

        brokerTable = new JTable(brokerTableModel);
        brokerTable.setSize(WIDTH, HEIGHT / 3);
        styleTable(brokerTable);

        refreshBrokerTableData();

        JScrollPane scrollPane = new JScrollPane(brokerTable);
        JPanel section = new JPanel(new BorderLayout(0, 10));
        section.add(new JLabel("Brokers"), BorderLayout.NORTH);
        section.add(scrollPane, BorderLayout.CENTER);
        tables.add(section);
    }

    /*
     * MODIFIES: this
     * EFFECTS: refresh broker table
     */
    private void refreshBrokerTableData() {
        brokerTableModel.setRowCount(0);

        ArrayList<Broker> brokers = getController().getDealership().getBrokerList();
        for (Broker b : brokers) {
            Object[] row = new Object[] {
                b.getBrokerID(),
                b.getName(),
                customerListLabel(b.getCustomers())
            };
            brokerTableModel.addRow(row);
        }
    }

    /*
     * EFFECTS: helper for print customers label as a list
     */
    private String customerListLabel(List<Integer> customers) {
        String customersString = "";
        for (int i = 0; i < customers.size(); i++) {
            if ((i + 1) != customers.size()) {
                customersString = customersString + customers.get(i) + ", ";
            } else {
                customersString = customersString + customers.get(i);
            }
        }
        return customersString;
    }

    /*
     * EFFECTS: creates a table for Dealership customer list
     */
    private void placeCustomerTable() {
        String[] columns = new String[] {"Customer ID", "Name", "Phone number"};
        customerTableModel = new ReadOnlyTableModel(columns);

        customerTable = new JTable(customerTableModel);
        customerTable.setSize(WIDTH, HEIGHT / 3);
        styleTable(customerTable);

        refreshCustomerTableData();

        JScrollPane scrollPane = new JScrollPane(customerTable);
        JPanel section = new JPanel(new BorderLayout(0, 10));
        section.add(new JLabel("Customers"), BorderLayout.NORTH);
        section.add(scrollPane, BorderLayout.CENTER);
        tables.add(section);
    }

    /*
     * MODIFIES: this
     * EFFECTS: refresh cutomer table
     */
    private void refreshCustomerTableData() {
        customerTableModel.setRowCount(0);

        ArrayList<Customer> customers = getController().getDealership().getCustomerList();
        for (Customer c : customers) {
            Object[] row = new Object[] {
                c.getCustomerID(),
                c.getName(),
                c.getPhoneNumber()
            };
            customerTableModel.addRow(row);
        }
    }

    // [[[         ]]]
    // [[[ BUTTONS ]]]
    // [[[         ]]]

    /*
     * EFFECTS: places a refresh button
     */
    private void placeRefreshButton() {
        JButton refreshButton = new JButton(ButtonNames.REFRESH.getValue());
        refreshButton.addActionListener(e -> refreshTableData());
        placeHeader("Directory", refreshButton);
    }

    /*
     * EFFECTS: helper for placeRefreshButton()
     */
    @Override
    public void refresh() {
        refreshTableData();
    }

    private void refreshTableData() {
        refreshBrokerTableData();
        refreshCustomerTableData();
    }
}
