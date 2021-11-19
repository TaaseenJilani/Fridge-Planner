package ui;

import model.FoodItem;
import model.ListOfFoodItem;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUI implements ActionListener {
    JFrame frame;
    JFrame frame2;
    JLabel itemNameLabel;
    JLabel descriptionLabel;
    JLabel imageLabel;
    ListOfFoodItem listOfFI = new ListOfFoodItem("My Fridge");
    JButton addButton;
    JButton checkButton;
    JButton removeButton;
    JButton checkDateButton;
    JButton displayButton;
    JButton saveButton;
    JButton loadButton;
    JTextField nameOfFoodItem;
    JTextField descriptionOfContainer;
    JPanel panel;
    JPanel panel2;
    String name;
    String description;
    JsonWriter jsonWriter;
    JsonReader jsonReader;
    private static final String JSON_STORE = "./data/ListOfFoodItem.json";
    private ImageIcon image;



    public GUI() {
        initialize();
        addActionListenersToAllButtons();
        addToPanel();
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Your Fridge");
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(100, 100, 1000, 1000);
        frame.setBackground(Color.gray);
        frame.setIconImage(image.getImage());
    }


    // EFFECTS: Adds all components to the panel of the main frame
    public void addToPanel() {
        panel.add(itemNameLabel);
        panel.add(nameOfFoodItem);
        panel.add(descriptionLabel);
        panel.add(descriptionOfContainer);
        panel.add(addButton);
        panel.add(checkButton);
        panel.add(removeButton);
        panel.add(checkDateButton);
        panel.add(displayButton);
        panel.add(saveButton);
        panel.add(loadButton);

    }

    // EFFECTS: Initializes all components
    public void initialize() {
        frame = new JFrame();
        addButton = new JButton("Add Item");
        checkButton = new JButton("Check Item");
        removeButton = new JButton("Remove Item");
        checkDateButton = new JButton("Check the date a food item was inserted");
        displayButton = new JButton("Display all of the food items in your fridge");
        saveButton = new JButton("Save your fridge");
        loadButton = new JButton("Load your previous fridge");
        itemNameLabel = new JLabel("Enter the name of the Food Item:");
        descriptionLabel = new JLabel("Enter the description of the container your food item is in:");
        nameOfFoodItem = new JTextField();
        descriptionOfContainer = new JTextField();
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.setBackground(Color.gray);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        image = new ImageIcon(this.getClass().getResource("/fridge.jpg"));
        imageLabel = new JLabel(image);
        imageLabel.setSize(100, 200);
        imageLabel.setBounds(100, 100, 100, 100);
    }


    // EFFECTS: Adds action Listeners to all buttons
    public void addActionListenersToAllButtons() {
        addButton.addActionListener(this);
        checkButton.addActionListener(this);
        removeButton.addActionListener(this);
        displayButton.addActionListener(this);
        checkDateButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
    }

    @Override
    //EFFECTS: Determines what Happens after each of the different buttons are clicked
    public void actionPerformed(ActionEvent e) {
        name = nameOfFoodItem.getText();
        description = descriptionOfContainer.getText();
        if (e.getSource().equals(addButton)) {
            addButtonPressed();
        } else if (e.getSource().equals(checkButton)) {
            checkButtonPressed();
        } else if (e.getSource().equals(removeButton)) {
            removeButtonPressed();
        } else if (e.getSource().equals(displayButton)) {
            displayButtonPressed();
        } else if (e.getSource() == checkDateButton) {
            checkDateButtonPressed();
        } else if (e.getSource() == saveButton) {
            saveButtonPressed();
        } else if (e.getSource() == loadButton) {
            loadButtonPressed();
        }
    }

    // EFFECTS: Determines what happens when the add button is pressed
    public void addButtonPressed() {
        FoodItem newFoodItem = new FoodItem(name, description);
        listOfFI.addFoodItem(newFoodItem);
        JOptionPane.showMessageDialog(frame, "Item Successfully added!");
    }

    // EFFECTS: Determines what happens when the check button is pressed
    public void checkButtonPressed() {
        List<Boolean> inFridge = new ArrayList<Boolean>();
        List<FoodItem> listOfFoodItems = listOfFI.getListOfFoodItem();
        for (FoodItem item : listOfFoodItems) {
            if (name.equals(item.getName()) && description.equals(item.getDescription())) {
                JOptionPane.showMessageDialog(frame, "Item in list!");
                inFridge.add(true);
            }
        }
        if (inFridge.size() == 0) {
            JOptionPane.showMessageDialog(frame, "Item NOT in list!");
        }
    }

    // EFFECTS: Determines what happens when the remove button is pressed
    public void removeButtonPressed() {
        if (listOfFI.size() == 0) {
            JOptionPane.showMessageDialog(frame, "Cannot remove item because fridge empty!");
        } else {
            List<FoodItem> toRemove = new ArrayList<>();
            List<FoodItem> listOfFoodItems = listOfFI.getListOfFoodItem();
            for (FoodItem item : listOfFoodItems) {
                if (name.equals(item.getName()) && description.equals(item.getDescription())) {
                    toRemove.add(item);
                }
            }
            if (toRemove.size() == 0) {
                JOptionPane.showMessageDialog(frame, "Cannot remove item because item not in fridge");
            } else {
                listOfFI.removeAllElements(toRemove);
            }
        }
    }

    // Determines what happens when the display button is pressed
    public void displayButtonPressed() {
        makeDisplayFrame();
        List<FoodItem> listOfFoodItems = listOfFI.getListOfFoodItem();
        for (FoodItem item: listOfFoodItems) {
            JTextArea displayed = new JTextArea(item.getName());
            JScrollPane sp = new JScrollPane(displayed, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            panel2.add(sp);
        }
        JButton goBack = new JButton("go back");
        panel2.add(goBack);
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.dispose();
            }
        });
    }

    // EFFECTS: Creates a new display frame for displaying all the items
    public void makeDisplayFrame() {
        frame2 = new JFrame();
        panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel2.setLayout(new GridLayout(0, 1));
        frame2.add(panel2, BorderLayout.CENTER);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setTitle("Your Fridge");
        frame2.pack();
        frame.setSize(500,500);
        frame2.setVisible(true);
        frame2.setBounds(100, 100, 1000, 1000);
        frame2.setResizable(false);
    }


    // EFFECTS: Determines what happens when the checkDate button is pressed
    public void checkDateButtonPressed() {
        Boolean found = false;
        for (FoodItem fooditem: listOfFI.getListOfFoodItem()) {
            if (found == false) {
                if (name.equals(fooditem.getName()) && description.equals(fooditem.getDescription())) {
                    found = true;
                    JOptionPane.showMessageDialog(frame, "This item was inserted to the fridge on "
                            + fooditem.getDate());
                }
            }

        }
    }

    // EFFECTS: Determines what happens when the save button is pressed
    public void saveButtonPressed() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfFI);
            jsonWriter.close();
            JOptionPane.showMessageDialog(frame, "Saved " + listOfFI.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException exception) {
            JOptionPane.showMessageDialog(frame, "Unable to write to file: " + JSON_STORE);
        }
    }


    public void loadButtonPressed() {
        try {
            this.listOfFI = this.jsonReader.read();
            JOptionPane.showMessageDialog(frame, "Loaded " + listOfFI.getName() +  " from " + JSON_STORE);
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(frame, "Unable to read from file: " + JSON_STORE);
        }
    }

}
