package ui;

import model.FoodItem;
import model.ListOfFoodItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// Fridge Planner application
public class FridgePlannerApp extends ListOfFoodItem {
    String name;
    String description;
    private Scanner input;
    Scanner in = new Scanner(System.in);

    // EFFECTS: runs the FridgePlanner application
    public FridgePlannerApp() {
        runFridgePlanner();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runFridgePlanner() {
        boolean running = true;
        String command = null;

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
            commandIsA();
        } else if (command.equals("r")) {
            commandIsR();
        } else if (command.equals("v")) {
            commandIsV();
        } else if (command.equals("c")) {
            commandIsC();
        } else if (command.equals("d")) {
            commandIsD();
        } else {
            System.out.println("Invalid input!");
        }
    }

    // MODIFIES: this
    // EFFECTS: determines how the application will behave if the user's input is "a"
    //          lets the user add a FoodItem to the list of food items
    public void commandIsA() {
        System.out.println("Please add the name of the food item that you wish to add to the fridge:");
        name = in.nextLine();
        System.out.println("Please add a description of the container you are putting your food item in:");
        description = in.nextLine();
        FoodItem newFoodItem = new FoodItem(name, description);
        addFoodItem(newFoodItem);
        System.out.println("Item added to your fridge!");
    }

    // MODIFIES: this
    // EFFECTS: determines how the application will behave if the user's input is "r"
    public void commandIsR() {
        if (listOfFoodItem.size() == 0) {
            System.out.println("You cannot remove anything because your fridge is already empty!");
        } else {
            List<FoodItem> toRemove = new ArrayList<FoodItem>();
            System.out.println("Please enter the name of the food item you want to remove:");
            name = in.nextLine();
            System.out.println("Please enter the description of the food item that you want to remove:");
            description = in.nextLine();
            for (FoodItem item : listOfFoodItem) {
                if (name.equals(item.getName()) && description.equals(item.getDescription())) {
                    toRemove.add(item);
                }
            }
            listOfFoodItem.removeAll(toRemove);
            if (toRemove.size() == 0) {
                System.out.println("Unable to remove because this item is not in your fridge.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: determines how the application will behave if the user's input is "c"
    //          checks to see if a certain food item is in the food item list
    public void commandIsC() {
        List<Boolean> inFridge = new ArrayList<Boolean>();
        System.out.println("Please enter the name of the food item you want to check:");
        name = in.nextLine();
        System.out.println("Please enter the description of the box of that item:");
        description = in.nextLine();
        for (FoodItem item : listOfFoodItem) {
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
    public void commandIsD() {
        List<Boolean> inFridgeForDate = new ArrayList<Boolean>();
        System.out.println("Please enter the name of the food item you want to check the date of:");
        name = in.nextLine();
        System.out.println("Please enter the description of the box which that food item is in:");
        description = in.nextLine();
        for (FoodItem item: listOfFoodItem) {
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
    public void commandIsV() {
        if (listOfFoodItem.size() == 0) {
            System.out.println("Your fridge is empty!");
        } else {
            for (FoodItem item: listOfFoodItem) {
                System.out.println(item.getName());
            }
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
        System.out.println("\t q -> quit this fridge and start a new one");
    }

}
