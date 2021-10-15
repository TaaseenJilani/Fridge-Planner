package test;

import model.FoodItem;
import model.ListOfFoodItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

public class ListOfFoodItemTest {
    private ListOfFoodItem testListOfFoodItem;
    private FoodItem testFoodItem1;
    private FoodItem testFoodItem2;

    @BeforeEach
    void runBefore() {
        testFoodItem1 = new FoodItem("Smoked Salmon", "Yellow rectangular box");
        testFoodItem2 = new FoodItem("Ribs", "Black plastic container");
        testListOfFoodItem = new ListOfFoodItem();
    }

    @Test
    void testAddFoodItem() {
        testListOfFoodItem.addFoodItem(testFoodItem1);
        assertEquals(1, testListOfFoodItem.size());
        assertTrue(testListOfFoodItem.contains(testFoodItem1));
    }

    @Test
    void testRemoveFoodItem() {
        assertEquals(0, testListOfFoodItem.size());
        testListOfFoodItem.addFoodItem(testFoodItem1);
        testListOfFoodItem.addFoodItem(testFoodItem2);
        assertTrue(testListOfFoodItem.contains(testFoodItem1));
        assertTrue(testListOfFoodItem.contains(testFoodItem2));
        testListOfFoodItem.removeFoodItem(testFoodItem1);
        assertFalse(testListOfFoodItem.contains(testFoodItem1));
        assertTrue(testListOfFoodItem.contains(testFoodItem2));
        assertEquals(1, testListOfFoodItem.size());
    }
}
