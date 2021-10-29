package persistence;

import model.FoodItem;
import model.ListOfFoodItem;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfFoodItem lofi = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            ListOfFoodItem lofi = reader.read();
            assertEquals(0, lofi.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            ListOfFoodItem lofi = reader.read();
            List<FoodItem> listOfFI = lofi.getListOfFoodItem();
            assertEquals(2, listOfFI.size());
            checkFoodItem("Chicken curry", "black box", listOfFI.get(0));
            checkFoodItem("ribs", "red box", listOfFI.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

