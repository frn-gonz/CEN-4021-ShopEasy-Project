package com.example.shopeasy.UserInterface;
import javax.swing.*;
import java.awt.Image;

public class WishList {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Wish List");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        /*
        This section of code is commented as it relies on images being loaded. This can be uncommented once images are loaded from database
        Two lines of code in the panel.add() section of code are commented for the same reason, and can be uncommented.

        ImageIcon itemimage1 = new ImageIcon(Notifications.class.getResource("/pants.jpg")); //Placeholder: image should be taken from database
        Image imageresize1 = itemimage1.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        ImageIcon resizeditem1 = new ImageIcon(imageresize1);

        ImageIcon itemimage2 = new ImageIcon(Notifications.class.getResource("/shirt.jpg")); //Placeholder: image should be taken from database
        Image imageresize2 = itemimage2.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        ImageIcon resizeditem2 = new ImageIcon(imageresize2); 

        JLabel item2 = new JLabel(resizeditem2);
        item2.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel item1 = new JLabel(resizeditem1);
        item1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        */ 

        JLabel title = new JLabel("Wish List");
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel item1info = new JLabel("Item: Jeans | Qty: 1 | Price: $39.99"); //Placeholder: should get item info from database
        item1info.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel item2info = new JLabel("Item: Black Tee | Qty: 1 | Price: $19.99"); //Placeholder: should get item info from database
        item2info.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton cart1 = new JButton("Add to Cart");
        cart1.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton cart2 = new JButton("Add to Cart");
        cart2.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton remove1 = new JButton("Remove from Wish List");
        remove1.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton remove2 = new JButton("Remove from Wish List");
        remove2.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton home = new JButton("Return to Home");
        home.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(title);
        panel.add(Box.createVerticalStrut(30));
        //panel.add(item1);
        panel.add(item1info);
        panel.add(Box.createVerticalStrut(5));
        panel.add(cart1);
        panel.add(Box.createVerticalStrut(5));
        panel.add(remove1);
        panel.add(Box.createVerticalStrut(15));
        //panel.add(item2);
        panel.add(item2info);
        panel.add(Box.createVerticalStrut(5));
        panel.add(cart2);
        panel.add(Box.createVerticalStrut(5));
        panel.add(remove2);
        panel.add(Box.createVerticalStrut(25));
        panel.add(home);
        panel.add(Box.createVerticalGlue());

        frame.setSize(750, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
