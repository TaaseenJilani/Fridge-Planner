package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI {

    public GUI() {
        JFrame frame = new JFrame();
        JButton addButton = new JButton("Add Item");

//        addButton.addActionListener(this);
        JLabel label = new JLabel("Number of items added: 0");

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(addButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Your Fridge");
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new GUI();
    }
}
