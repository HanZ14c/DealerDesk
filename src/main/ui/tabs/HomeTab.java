package ui.tabs;

import javax.swing.*;
import java.awt.*;
import model.Dealership;
import ui.DealershipUI;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class HomeTab extends Tab {
    private JLabel[] totals;
    private JLabel status;

    public HomeTab(DealershipUI controller) {
        super(controller);
        JButton load = new JButton("Load");
        JButton save = new JButton("Save");
        placeHeader("Overview", load, save);
        JPanel summary = new JPanel(new GridLayout(4, 2, 24, 0));
        summary.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        String[] labels = {"Vehicles", "Sales", "Customers", "Brokers"};
        totals = new JLabel[labels.length];
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setPreferredSize(new Dimension(180, 56));
            summary.add(label);
            totals[i] = new JLabel("0", SwingConstants.RIGHT);
            totals[i].setFont(totals[i].getFont().deriveFont(Font.BOLD, 24f));
            totals[i].setForeground(new Color(43, 91, 145));
            summary.add(totals[i]);
        }
        JPanel content = new JPanel(new BorderLayout());
        content.add(summary, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);
        status = new JLabel(" ");
        status.setForeground(new Color(90, 100, 112));
        add(status, BorderLayout.SOUTH);
        load.addActionListener(e -> {
            status.setText(getController().loadFile());
            refresh();
        });
        save.addActionListener(e -> status.setText(getController().saveFile()));
        refresh();
    }

    @Override
    public void refresh() {
        Dealership data = getController().getDealership();
        int[] counts = {data.getCarList().size(), data.getSaleList().size(),
                data.getCustomerList().size(), data.getBrokerList().size()};
        for (int i = 0; i < totals.length; i++) {
            totals[i].setText(String.valueOf(counts[i]));
        }
    }
}
