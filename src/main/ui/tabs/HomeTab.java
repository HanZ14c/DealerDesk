package ui.tabs;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import ui.DealershipUI;
import ui.ButtonNames;

import java.awt.*;

/*
 * Presents the Home Tab
 */
@ExcludeFromJacocoGeneratedReport 
public class HomeTab extends Tab {
    private static final String INIT_GREETING = "Welcome to Dealership Manager";
    private JLabel greeting;

    private JLabel reportMessage;

    private JScrollPane reportPane;
    private JTextArea reportText;

    public HomeTab(DealershipUI controller) {
        super(controller);
        
        setLayout(new GridLayout(3, 1));

        placeGreeting();
        placeLoadSaveButtons();
        placeStatusBlock();
    }

    /*
     * EFFECTS: creats greeting at top of console
     */
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    /*
     * EFFECTS: creates Load and Save buttons that change greeting message when clicked
     */
    private void placeLoadSaveButtons() {
        JButton loadButton = new JButton(ButtonNames.LOAD.getValue());
        JButton saveButton = new JButton(ButtonNames.SAVE.getValue());

        JPanel buttonRow = formatButtonRow(loadButton);
        buttonRow.add(saveButton);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        loadButton.addActionListener(e -> {
            reportMessage.setText(getController().loadFile());
            reportText.setText(getController().getDealershipInfo());
            reportPane.setViewportView(reportText);
        });

        saveButton.addActionListener(e -> {
            reportMessage.setText(getController().saveFile());
            reportText.setText("");
            reportPane.setViewportView(reportText);
        });

        this.add(buttonRow);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds a report test area that prints the dealership informations
     */
    private void placeStatusBlock() {
        JPanel reportBlock = new JPanel(new GridLayout(2, 1));
        reportMessage = new JLabel("");
        reportPane = new JScrollPane(new JTextArea(1, 20));
        reportText = new JTextArea("", 1, 20);
        reportText.setVisible(true);

        reportBlock.add(reportMessage);
        reportBlock.add(reportPane);
        add(reportBlock);
    }
}
