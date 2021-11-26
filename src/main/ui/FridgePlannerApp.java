package ui;

import model.FoodItem;
import model.ListOfFoodItem;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// Fridge Planner application
public class FridgePlannerApp extends ListOfFoodItem {
    private static final String JSON_STORE = "./data/ListOfFoodItem.json";
    private Scanner input;
    ListOfFoodItem loi;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;



    // EFFECTS: runs the FridgePlanner application
    public FridgePlannerApp() throws FileNotFoundException {
        super("My Fridge");
        input = new Scanner(System.in);
        loi = new ListOfFoodItem("My Fridge");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runFridgePlanner();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runFridgePlanner() {
        boolean running = true;
        String command = null;
        input = new Scanner(System.in);

        init();

        while (running) {
            displayPage();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                running = false;
                System.out.println("GOODBYE!");

            } else {
                processCommand(command);
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: processes the commands of the user

    private void processCommand(String command) {
        if (command.equals("a")) {
            addItem();
        } else if (command.equals("r")) {
            removeItem();
        } else if (command.equals("v")) {
            viewItems();
        } else if (command.equals("c")) {
            checkItem();
        } else if (command.equals("d")) {
            checkDateOfItem();
        } else if (command.equals("s")) {
            saveFridge();
        } else if (command.equals("l")) {
            loadFridge();
        } else {
            System.out.println("Invalid input!");
        }
    }

    // MODIFIES: this
    // EFFECTS: determines how the application will behave if the user's input is "a"
    //          lets the user add a FoodItem to the list of food items
    public void addItem() {
        System.out.println("Please add the name of the food item that you wish to add to the fridge:");
        String name = input.next();
        System.out.println("Please add a description of the container you are putting your food item in:");
        String description = input.next();
        FoodItem newFoodItem = new FoodItem(name, description);
        loi.addFoodItem(newFoodItem);
        System.out.println("Item added to your fridge!");
    }

    // MODIFIES: this
    // EFFECTS: determines how the application will behave if the user's input is "r"
    public void removeItem() {
        if (loi.size() == 0) {
            System.out.println("You cannot remove anything because your fridge is already empty!");
        } else {
            List<FoodItem> toRemove = new ArrayList<>();
            System.out.println("Please enter the name of the food item you want to remove:");
            String name = input.next();
            System.out.println("Please enter the description of the food item that you want to remove:");
            String description = input.next();
            List<FoodItem> listOfFoodItems = loi.getListOfFoodItem();
            for (FoodItem item : listOfFoodItems) {
                if (name.equals(item.getName()) && description.equals(item.getDescription())) {
                    toRemove.add(item);
                }
            }
            if (toRemove.size() == 0) {
                System.out.println("Unable to remove because this item is not in your fridge.");
            } else {
                for (FoodItem fooditem : toRemove) {
                    loi.removeFoodItem(fooditem);
                }
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: determines how the application will behave if the user's input is "c"
    //          checks to see if a certain food item is in the food item list
    public void checkItem() {
        List<Boolean> inFridge = new ArrayList<Boolean>();
        System.out.println("Please enter the name of the food item you want to check:");
        String name = input.next();
        System.out.println("Please enter the description of the box of that item:");
        String description = input.next();
        List<FoodItem> listOfFoodItems = loi.getListOfFoodItem();
        for (FoodItem item : listOfFoodItems) {
            if (name.equals(item.getName()) && description.equals(item.getDescription())) {
                System.out.println("Yes, " + name + " is in your fridge!");
                inFridge.add(true);
            }

        }
        if (inFridge.size() == 0) {
            System.out.println("No, " + name + " is not in your fridge!");
        }
    }

    // MODIFIES: this
    // EFFECTS: determines how the application will behave if the user's input is "d"
    //          lets the user check the date at which a certain food item was inserted
    public void checkDateOfItem() {
        List<Boolean> inFridgeForDate = new ArrayList<Boolean>();
        System.out.println("Please enter the name of the food item you want to check the date of:");
        String name = input.next();
        System.out.println("Please enter the description of the box which that food item is in:");
        String description = input.next();
        List<FoodItem> listOfFoodItems = loi.getListOfFoodItem();
        for (FoodItem item: listOfFoodItems) {
            if (name.equals(item.getName()) && description.equals(item.getDescription())) {
                System.out.println(name + " was inserted in the fridge on " + item.getDate());
                inFridgeForDate.add(true);
            }
        }
        if (inFridgeForDate.size() == 0) {
            System.out.println("This item is not in your fridge.");
        }
    }

    // MODIFIES: this
    // EFFECTS: determines how the application will behave if the user's input is "v"
    //          displays the list of items in the fridge to the user
    public void viewItems() {
        List<FoodItem> listOfFoodItems = loi.getListOfFoodItem();
        for (FoodItem item: listOfFoodItems) {
            System.out.println(item.getName());
        }

    }






    // MODIFIES: this
    // EFFECTS: initializes inputs
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays selectable options to user
    public void displayPage() {
        System.out.println("\n Select from: ");
        System.out.println("\t a -> Add a food item to the fridge");
        System.out.println("\t r -> Remove a food item to the fridge");
        System.out.println("\t v -> View all the items in your fridge");
        System.out.println("\t c -> Check whether a food item is in the fridge");
        System.out.println("\t d -> Check the date a food item was put into the fridge");
        System.out.println("\t s -> Save your Fridge");
        System.out.println("\t l -> Load your Fridge");
        System.out.println("\t q -> quit this fridge and start a new one");
    }

    // EFFECTS: saves the workroom to file
    private void saveFridge() {
        try {
            jsonWriter.open();
            jsonWriter.write(loi);
            jsonWriter.close();
            System.out.println("Saved " + loi.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadFridge() {
        try {
            this.loi = this.jsonReader.read();
            System.out.println("Loaded " + loi.getName() +  " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
