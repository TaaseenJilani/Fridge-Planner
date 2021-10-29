package persistence;

import model.FoodItem;
import model.ListOfFoodItem;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// CITATION: All the code below is based upon the sample application that was provided as an example file
// Represents a reader that reads listOfFoodItem
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads listOfFoodItem from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfFoodItem read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfFoodItem(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private ListOfFoodItem parseListOfFoodItem(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ListOfFoodItem lofi = new ListOfFoodItem(name);
        addFoodItems(lofi, jsonObject);
        return lofi;
    }

    // MODIFIES: lofi
    // EFFECTS: parses thingies from JSON object and adds them to listOfFoodItem
    private void addFoodItems(ListOfFoodItem lofi, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("ListOfFoodItem");
        for (Object json : jsonArray) {
            JSONObject nextFoodItem = (JSONObject) json;
            addFoodItem(lofi, nextFoodItem);
        }
    }

    // MODIFIES: fi
    // EFFECTS: parses thingy from JSON object and adds it to listOfFoodItem
    private void addFoodItem(ListOfFoodItem fi, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String container = jsonObject.getString("container");
        FoodItem foodItem = new FoodItem(name, container);
        fi.addFoodItem(foodItem);
    }




}
