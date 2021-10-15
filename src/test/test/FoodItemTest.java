package test;

import model.FoodItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class FoodItemTest {
    private FoodItem testFoodItem;

    @BeforeEach
    void runBefore() {
        testFoodItem = new FoodItem("Chicken Curry", "Red circular box");
    }

    @Test
    void testGetName() {
        assertEquals("Chicken Curry", testFoodItem.getName());
    }

    @Test
    void testGetDescription() {
        assertEquals("Red circular box", testFoodItem.getDescription());
    }

    @Test
    void testGetDate() {
        assertEquals(LocalDate.now(), testFoodItem.getDate());
    }






}
