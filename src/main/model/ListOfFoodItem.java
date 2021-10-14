package model;

import java.util.ArrayList;
import java.util.List;

// represents the list of food items in the fridge.
public class ListOfFoodItem {
    private List<FoodItem> listOfFoodItem = new ArrayList<>();
    private List<String> listOfFoodItemNames = new ArrayList<>();


    public void addFoodItem(FoodItem foodItem) {
        listOfFoodItem.add(foodItem);
        listOfFoodItemNames.add(foodItem.getName());
    }

    public void removeFoodItem(FoodItem foodItem) {
        listOfFoodItem.remove(foodItem);
    }

    public boolean checkItem(String foodItem) {
        return listOfFoodItemNames.contains(foodItem);
    }
}

