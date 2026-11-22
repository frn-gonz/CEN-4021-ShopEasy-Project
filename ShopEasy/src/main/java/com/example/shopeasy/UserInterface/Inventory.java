package com.example.shopeasy.UserInterface;
import javax.swing.*;
import java.awt.Image;

public class Inventory {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Product Inventory");
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

        JLabel title = new JLabel("Product Inventory");
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel msg = new JLabel("Search:");
        msg.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JTextField search = new JTextField();
        search.setMaximumSize(new java.awt.Dimension(200, 60));
        search.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel item1info = new JLabel("Item: Jeans | Qty: 1 | Price: $39.99"); //Placeholder: should get item info from database
        item1info.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel item2info = new JLabel("Item: Black Tee | Qty: 1 | Price: $19.99"); //Placeholder: should get item info from database
        item2info.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton addtocart1 = new JButton("Add to Cart");
        addtocart1.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton addtocart2 = new JButton("Add to Cart");
        addtocart2.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton addtowishlist1 = new JButton("Add to Wish List");
        addtowishlist1.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton addtowishlist2 = new JButton("Add to Wish List");
        addtowishlist2.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton nextpage = new JButton("Next Page");
        nextpage.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton previouspage = new JButton("Previous Page");
        previouspage.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton home = new JButton("Return to Home");
        home.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(title);
        panel.add(Box.createVerticalStrut(30));
        panel.add(msg);
        panel.add(Box.createVerticalStrut(5));
        panel.add(search);
        panel.add(Box.createVerticalStrut(10));
        //panel.add(item2);
        panel.add(item2info);
        panel.add(Box.createVerticalStrut(5));
        panel.add(addtocart2);
        panel.add(Box.createVerticalStrut(5));
        panel.add(addtowishlist2);
        panel.add(Box.createVerticalStrut(15));
        //panel.add(item1);
        panel.add(item1info);
        panel.add(Box.createVerticalStrut(5));
        panel.add(addtocart1);
        panel.add(Box.createVerticalStrut(5));
        panel.add(addtowishlist1);
        panel.add(Box.createVerticalStrut(25));
        panel.add(nextpage);
        panel.add(Box.createVerticalStrut(5));
        panel.add(previouspage);
        panel.add(Box.createVerticalStrut(5));
        panel.add(home);
        panel.add(Box.createVerticalGlue());

        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
