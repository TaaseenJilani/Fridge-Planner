package persistence;

import model.FoodItem;
import model.ListOfFoodItem;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {


    @Test
    void testWriterInvalidFile() {
        try {
            ListOfFoodItem lofi = new ListOfFoodItem("My Fridge");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ListOfFoodItem lofi = new ListOfFoodItem("My Fridge");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(lofi);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            lofi = reader.read();
            assertEquals("My Fridge", lofi.getName());
            assertEquals(0, lofi.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ListOfFoodItem lofi = new ListOfFoodItem("My Fridge");
            lofi.addFoodItem(new FoodItem("Chicken curry", "black box"));
            lofi.addFoodItem(new FoodItem("ribs", "red box"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(lofi);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            lofi = reader.read();
            assertEquals("My Fridge", lofi.getName());
            List<FoodItem> listOfFI = lofi.getListOfFoodItem();
            assertEquals(2, lofi.size());
            checkFoodItem("Chicken curry", "black box", listOfFI.get(0));
            checkFoodItem("ribs", "red box", listOfFI.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
