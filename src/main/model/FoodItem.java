package model;

import java.time.LocalDate;

// Represents a food item having a name, a description of container,
// and time (in days) for how long it has been in the fridge.
public class FoodItem {
    private String name;                   // name of the food item
    private String container;             // Description of the container it was put in the fridge
    private LocalDate date;               // what date the item is put in the fridge


    public FoodItem(String foodItemName, String descriptionOfContainer) {
        name = foodItemName;
        container = descriptionOfContainer;
        date = LocalDate.now();

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return container;
    }

    public LocalDate getDate() {
        return date;
    }


}

