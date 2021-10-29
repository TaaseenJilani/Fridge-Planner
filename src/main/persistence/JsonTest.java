package persistence;

import model.FoodItem;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkFoodItem(String name, String container, FoodItem foodItem) {
        assertEquals(name, foodItem.getName());
        assertEquals(container, foodItem.getDescription());
    }
}
