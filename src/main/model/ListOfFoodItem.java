package model;

import java.util.ArrayList;
import java.util.List;


// represents the list of food items in the fridge.
public class ListOfFoodItem {
    protected List<FoodItem> listOfFoodItem;

    // EFFECTS: Creates an empty list of FoodItems
    public ListOfFoodItem() {
        listOfFoodItem = new ArrayList<FoodItem>();
    }

    // MODIFIES: this
    // EFFECTS: Adds FoodItem to an empty list
    //          Adds the name of FoodItem to an empty list
    public void addFoodItem(FoodItem foodItem) {
        listOfFoodItem.add(foodItem);
    }

    // MODIFIES: this
    // EFFECTS: Removes the name of FoodItem from the list of FoodItem names
    public void removeFoodItem(FoodItem foodItem) {
        listOfFoodItem.remove(foodItem);
    }

    // EFFECTS: returns the size of the list of FoodItems
    public int size() {
        return listOfFoodItem.size();
    }

    // EFFECTS: returns whether the list of food items contain a certain FoodItem or not
    public boolean contains(FoodItem foodItem) {
        return listOfFoodItem.contains(foodItem);
    }
}

