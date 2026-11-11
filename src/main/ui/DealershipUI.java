package ui;

import model.*;
import persistence.*;
import ui.tabs.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

/*
 * EFFECTS: creates a GUI for dealership
 */
@ExcludeFromJacocoGeneratedReport 
public class DealershipUI extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 680;

    public static final int HOME_TAB_INDEX = 0;
    public static final int INVENTORY_TAB_INDEX = 1;
    public static final int SALE_TAB_INDEX = 2;
    public static final int DIRECTORY_TAB_INDEX = 3;

    private static final String JSON_STORE = "./data/dealership.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Dealership dealership;
    private boolean saved;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            configureAppearance();
            new DealershipUI();
        });
    }

    private static void configureAppearance() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ReflectiveOperationException | UnsupportedLookAndFeelException e) {
            throw new IllegalStateException("Unable to initialize application appearance", e);
        }
        java.awt.Font font = new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 14);
        for (Object key : java.util.Collections.list(UIManager.getDefaults().keys())) {
            if (UIManager.get(key) instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, new javax.swing.plaf.FontUIResource(font));
            }
        }
        UIManager.put("Panel.background", Color.WHITE);
        UIManager.put("Button.gradient", null);
        UIManager.put("Button.background", Color.WHITE);
        UIManager.put("Button.foreground", new Color(35, 70, 110));
        UIManager.put("Button.select", new Color(219, 229, 241));
        UIManager.put("Button.focus", new Color(92, 145, 201));
        UIManager.put("Button.border", new javax.swing.plaf.BorderUIResource(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(193, 209, 226)),
                        BorderFactory.createEmptyBorder(8, 14, 8, 14))));
        UIManager.put("TextField.border", new javax.swing.plaf.BorderUIResource(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(193, 209, 226)),
                        BorderFactory.createEmptyBorder(7, 9, 7, 9))));
        UIManager.put("ScrollPane.border", new javax.swing.plaf.BorderUIResource(
                BorderFactory.createLineBorder(new Color(219, 228, 237))));
        UIManager.put("TableHeader.cellBorder", BorderFactory.createEmptyBorder(0, 8, 0, 8));
        UIManager.put("ComboBox.selectionBackground", new Color(219, 229, 241));
        UIManager.put("ComboBox.selectionForeground", new Color(35, 70, 110));
        UIManager.put("ComboBox.background", Color.WHITE);
        UIManager.put("List.selectionBackground", new Color(219, 229, 241));
        UIManager.put("OptionPane.background", Color.WHITE);
        UIManager.put("TextField.margin", new java.awt.Insets(6, 8, 6, 8));
        UIManager.put("Button.margin", new java.awt.Insets(8, 14, 8, 14));
        UIManager.put("Label.foreground", new java.awt.Color(35, 43, 54));
        UIManager.put("TableHeader.background", new Color(239, 245, 251));
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates DealershipUI
     */
    private DealershipUI() {
        super("DealerDesk");
        saved = true;

        setSize(WIDTH, HEIGHT);
        setMinimumSize(new java.awt.Dimension(800, 560));
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new CloseHandler());

        dealership = new Dealership(null, null, null, null);

        try {
            setup();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }

        loadTabs();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /*
     * EFFECTS: inner class for haldling close event
     */
    @ExcludeFromJacocoGeneratedReport 
    private class CloseHandler extends WindowAdapter {
        @Override public void windowClosing(WindowEvent e) {
            askForSaving();
            // print events
            for (Event next : EventLog.getInstance()) {
                System.out.println(next.toString() + "\n");
            }
        }
    }

    /*
     * EFEFCTS: setup Json Reader & Writer
     */
    private void setup() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: creates the navigation and its corresponding pages.
    private void loadTabs() {
        String[] names = {"Overview", "Inventory", "Sales", "Directory"};
        Tab[] tabs = {new HomeTab(this), new InventoryTab(this), new SaleTab(this), new DirectoryTab(this)};
        CardLayout layout = new CardLayout();
        JPanel pages = new JPanel(layout);
        for (int i = 0; i < names.length; i++) {
            pages.add(tabs[i], names[i]);
        }

        JList<String> navigation = createNavigation(names);
        navigation.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && navigation.getSelectedIndex() >= 0) {
                int index = navigation.getSelectedIndex();
                layout.show(pages, names[index]);
                tabs[index].refresh();
            }
        });
        navigation.setSelectedIndex(HOME_TAB_INDEX);

        JPanel sidebar = new JPanel(new BorderLayout(0, 28));
        sidebar.setPreferredSize(new Dimension(184, 0));
        sidebar.setBackground(new Color(241, 246, 251));
        sidebar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(222, 226, 232)),
                BorderFactory.createEmptyBorder(28, 12, 20, 12)));
        JLabel name = new JLabel("Dealership");
        name.setFont(name.getFont().deriveFont(Font.BOLD, 17f));
        name.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 0));
        sidebar.add(name, BorderLayout.NORTH);
        sidebar.add(navigation, BorderLayout.CENTER);
        add(sidebar, BorderLayout.WEST);
        add(pages, BorderLayout.CENTER);
    }

    private JList<String> createNavigation(String[] names) {
        JList<String> navigation = new JList<>(names);
        navigation.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        navigation.setFixedCellHeight(46);
        navigation.setBackground(new Color(241, 246, 251));
        navigation.getAccessibleContext().setAccessibleName("Main navigation");
        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        navigation.setCellRenderer((list, value, index, selected, focused) -> {
            JLabel label = (JLabel) renderer.getListCellRendererComponent(
                    list, value, index, selected, focused);
            label.setBackground(selected ? new Color(219, 229, 241) : list.getBackground());
            label.setForeground(selected ? new Color(35, 70, 110) : new Color(80, 90, 104));
            label.setFont(list.getFont().deriveFont(selected ? Font.BOLD : Font.PLAIN));
            label.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 3, 0, 0,
                            selected ? new Color(48, 83, 124) : list.getBackground()),
                    BorderFactory.createCompoundBorder(label.getBorder(),
                            BorderFactory.createEmptyBorder(0, 10, 0, 8))));
            return label;
        });
        return navigation;
    }

    /*
     * EFFECTS: returns Dealship object controlled by ths UI
     */
    public Dealership getDealership() {
        return dealership;
    }

    // [[[       ]]]
    // [[[ L / S ]]]
    // [[[       ]]]

    /*
     * EFFECTS: saves the workroom to file
     * SOURCE: JsonSerializationDemo
     */
    public String saveFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(dealership);
            jsonWriter.close();
            updateSavingStatus(true);
            return ("Saved dealership to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            return ("Unable to write to file: " + JSON_STORE);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: loads Dealership from file
     * SOURCE: JsonSerializationDemo
     */
    public String loadFile() {
        try {
            dealership = jsonReader.read();
            updateSavingStatus(true);
            return ("Loaded dealership from " + JSON_STORE);
        } catch (IOException e) {
            return ("Unable to read from file: " + JSON_STORE);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: updates the saving status
     */
    public void updateSavingStatus(Boolean update) {
        saved = update;
    }

    /*
     * EFFECTS: ask for saving if not saved
     */
    private void askForSaving() {
        if (saved) {
            dispose();
        } else {
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "You have unsaved changes. Do you want to save?",
                    "Unsaved Changes",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
            if (choice == JOptionPane.YES_OPTION) {
                saveFile();
            }
            dispose();
        }
    }

    /*
     * EFFECTS: returns the group sizes of dealership (car, broker, customer, sale)
     */
    public String getDealershipInfo() {
        String report = dealership.getInfo();
        return report;
    }
}
