package ui.tabs;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.awt.*;

import ui.DealershipUI;

/*
 * SOURCE: SmartHome
 */
@ExcludeFromJacocoGeneratedReport 
public class Tab extends JPanel {
    private final DealershipUI contoller;

    // EFFECT: DealershipUI controller that holds this tab
    public Tab(DealershipUI controller) {
        this.contoller = controller;
        setLayout(new BorderLayout(0, 20));
        setBorder(BorderFactory.createEmptyBorder(24, 28, 24, 28));
    }

    // EFFECTS: creates and return row with button included
    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        stylePrimaryButton(b);
        p.add(b);
        return p;
    }

    protected void stylePrimaryButton(JButton button) {
        button.setBackground(new Color(43, 91, 145));
        button.setForeground(Color.WHITE);
    }

    protected void placeHeader(String title, JButton... buttons) {
        JPanel header = new JPanel(new BorderLayout());
        JLabel heading = new JLabel(title);
        heading.setFont(heading.getFont().deriveFont(Font.BOLD, 23f));
        header.add(heading, BorderLayout.WEST);
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        for (JButton button : buttons) {
            actions.add(button);
        }
        if (buttons.length > 1) {
            stylePrimaryButton(buttons[buttons.length - 1]);
        }
        header.add(actions, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);
    }

    protected void styleTable(JTable table) {
        table.setRowHeight(36);
        table.setShowVerticalLines(false);
        table.setGridColor(new Color(232, 238, 245));
        table.setIntercellSpacing(new Dimension(12, 1));
        table.setSelectionBackground(new Color(222, 232, 243));
        table.setSelectionForeground(new Color(35, 43, 54));
        table.getTableHeader().setPreferredSize(new Dimension(0, 36));
        table.getTableHeader().setFont(table.getFont().deriveFont(Font.BOLD));
        table.setFillsViewportHeight(true);
    }

    public void refresh() {
        // Tabs with data override this when selected.
    }

    // EFFECTS: returns the DealershipUI controller for this tab
    public DealershipUI getController() {
        return contoller;
    }
}
