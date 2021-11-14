package ui;

import model.FoodItem;
import model.ListOfFoodItem;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUI implements ActionListener {
    int count = 0;
    JLabel label;
    JFrame frame;
    JFrame frame2;
    JLabel itemNameLabel;
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
    JTextArea textArea;



    public GUI() {
        initialize();
        addToPanel();

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Your Fridge");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }

    public void addToPanel() {
        panel.add(itemNameLabel);
        panel.add(nameOfFoodItem);
        panel.add(descriptionOfContainer);
        panel.add(addButton);
        panel.add(checkButton);
        panel.add(removeButton);
        panel.add(checkDateButton);
        panel.add(displayButton);
        panel.add(saveButton);
        panel.add(loadButton);
        panel.add(label);
        panel.add(textArea);

    }

    public void initialize() {
        frame = new JFrame();
        addButton = new JButton("Add Item");
        checkButton = new JButton("Check Item");
        removeButton = new JButton("Remove Item");
        checkDateButton = new JButton("Check the date a food item was inserted");
        displayButton = new JButton("Display all of the food items in your fridge");
        saveButton = new JButton("Save your fridge");
        loadButton = new JButton("Load your previous fridge");
        addButton.addActionListener(this);
        checkButton.addActionListener(this);
        removeButton.addActionListener(this);
        displayButton.addActionListener(this);
        label = new JLabel("Number of items added: 0");
        itemNameLabel = new JLabel("Enter the name of the Food Item:");
        nameOfFoodItem = new JTextField();
        descriptionOfContainer = new JTextField();
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        textArea = new JTextArea("Hello World!");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameOfFoodItem.getText();
        String description = descriptionOfContainer.getText();
        if (e.getSource().equals(addButton)) {
            FoodItem newFoodItem = new FoodItem(name, description);
            listOfFI.addFoodItem(newFoodItem);
            JOptionPane.showMessageDialog(frame, "Item Successfully added!");
            count++;
            label.setText("Number of items added: " + count);
        } else if (e.getSource().equals(checkButton)) {
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
        } else if (e.getSource().equals(removeButton)) {
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
                    listOfFI.removeAll(toRemove);
                    count = count - 1;
                    label.setText("Number of items added: " + count);
                }
            }
        } else if (e.getSource().equals(displayButton)) {
            frame2 = new JFrame();
            JPanel panel2 = new JPanel();
            panel2.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
            panel2.setLayout(new GridLayout(0, 1));
            frame2.add(panel2, BorderLayout.CENTER);
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame2.setTitle("Your Fridge");
            frame2.pack();
            frame.setSize(500,500);
            frame2.setVisible(true);
            List<FoodItem> listOfFoodItems = listOfFI.getListOfFoodItem();
            for (FoodItem item: listOfFoodItems) {
                panel2.add(new JTextArea(item.getName()));
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
    }
}
