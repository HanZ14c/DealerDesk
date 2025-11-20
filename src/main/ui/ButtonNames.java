package ui;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport 
public enum ButtonNames {
    LOAD("Load"),
    SAVE("Save"),
    REFRESH("Refresh"),
    ADD_NEW_CAR("Add new car"),
    EDIT_STATUS("Edit status"),
    CANCEL("Cancel"),
    SAVE_NEW_CAR("Save new car"),
    SAVE_STATUS("Save Status"),
    SAVE_SALE("Save sale");

    private final String name;

    ButtonNames(String name) {
        this.name = name;
    }

    /*
     * EFFECTS: returns name value of this button
     */
    public String getValue() {
        return name;
    }
}
