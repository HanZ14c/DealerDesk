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
    }

    // EFFECTS: creates and return row with button included
    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(b);
        return p;
    }

    // EFFECTS: returns the DealershipUI controller for this tab
    public DealershipUI getController() {
        return contoller;
    }
}
