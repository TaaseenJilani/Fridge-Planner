package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// represents the list of food items in the fridge.
public class ListOfFoodItem implements Writable {
    private String name;
    private List<FoodItem> listOfFoodItem;

    // EFFECTS: Creates an empty list of FoodItems
    public ListOfFoodItem(String name) {
        this.name = name;
        listOfFoodItem = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: Adds FoodItem to an empty list
    //          Adds the name of FoodItem to an empty list
    public void addFoodItem(FoodItem foodItem) {
        listOfFoodItem.add(foodItem);
        EventLog.getInstance().logEvent(new Event("Added " + foodItem.getName() + " to fridge!"));
    }


    // MODIFIES: this
    // EFFECTS: Removes the name of FoodItem from the list of FoodItem names
    public void removeFoodItem(FoodItem foodItem) {
        listOfFoodItem.remove(foodItem);
        EventLog.getInstance().logEvent(new Event("Removed " + foodItem.getName() + " from fridge!"));
    }

    // EFFECTS: returns the size of the list of FoodItems
    public int size() {
        return listOfFoodItem.size();
    }

    // EFFECTS: returns whether the list of food items contain a certain FoodItem or not
    public boolean contains(FoodItem foodItem) {
        if (listOfFoodItem.contains(foodItem)) {
            return true;
        } else {
            return false;
        }

    }


    public List<FoodItem> getListOfFoodItem() {
        return Collections.unmodifiableList(listOfFoodItem);
    }

    public void removeAllElements(List toDelete) {
        listOfFoodItem.removeAll(toDelete);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("ListOfFoodItem", listOfFoodItemToJson());
        return json;
    }

    // EFFECTS: returns food items in the fridge as a JSON array
    private JSONArray listOfFoodItemToJson() {
        JSONArray jsonArray = new JSONArray();
        for (FoodItem f : listOfFoodItem) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }
}

