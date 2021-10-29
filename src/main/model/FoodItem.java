package model;

import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;

// Represents a food item having a name, a description of container,
// and time (in days) for how long it has been in the fridge.
public class FoodItem implements Writable {
    private String name;                  // name of the food item
    private String container;             // Description of the container it was put in the fridge
    private LocalDate date;               // what date the item is put in the fridge

    // REQUIRES: foodItemName has a non-zero length
    //           descriptionOfContainer has a non-zero length
    // EFFECTS: name is set to foodItemName, container is set to descriptionOfContainer,
    //          date is the date in which the food item is inserted into the fridge
    public FoodItem(String foodItemName, String descriptionOfContainer) {
        name = foodItemName;
        container = descriptionOfContainer;
        date = LocalDate.now();

    }

    // EFFECTS: Gets the name of the FoodItem
    public String getName() {
        return name;
    }


    // EFFECTS: Gets the description of the container in which the FoodItem is inserted into the fridge
    public String getDescription() {
        return container;
    }

    // EFFECTS: Gets the date at which the FoodItem is inserted into the fridge
    public LocalDate getDate() {
        return date;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("container", container);
        return json;
    }
}




