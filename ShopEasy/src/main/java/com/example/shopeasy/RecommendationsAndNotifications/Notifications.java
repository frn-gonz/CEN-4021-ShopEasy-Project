package com.example.shopeasy.RecommendationsAndNotifications;
import com.example.shopeasy.Accounts.Session;
import com.example.shopeasy.ProductAndInventory.Product;
import com.example.shopeasy.Services.CartService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Notifications {
    private JPanel productRecommendation(Product product) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMaximumSize(new Dimension(250, 250));

        JLabel image = new JLabel();

        if(product.getProductImage() != null) {
            ImageIcon imageIcon = new ImageIcon(product.getProductImage());
            Image resizedImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

            image.setIcon(new ImageIcon(resizedImage));
        }

        JLabel infoLabel = new JLabel();
        if(product.getProductName() != null) {
            infoLabel.setText(product.getProductName() +"| $" + product.getPrice());
        }

        JButton cartButton = new JButton("Add to Cart");
        cartButton.addActionListener(e -> {

            try {
                CartService cartService = null;
                Session session = null;
                cartService.addItem(
                        session.getCurrentAccount().getAccountId(),
                        product.getProductId(),
                        1);

                JOptionPane.showMessageDialog(
                        null,
                        product.getProductName()
                                + " added to cart.");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage());
            }
        });

        JButton wishlistButton = new JButton("Add to Wishlist");

        JButton dismissButton = new JButton("Dismiss");
        dismissButton.addActionListener(e -> {
            panel.setVisible(false);
        });
        image.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        wishlistButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        dismissButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(image);
        panel.add(infoLabel);
        panel.add(cartButton);
        panel.add(wishlistButton);
        panel.add(dismissButton);
        return panel;

    }
    public Notifications(List<Product> recommendedProducts, CartService cartService, Session session) {
        JFrame frame = new JFrame("Notifications");


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane);



        JLabel title = new JLabel("Notifications");
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel msg = new JLabel("These items have been recommended based on your recent activity:");
        msg.setAlignmentX(JComponent.CENTER_ALIGNMENT);


        JButton home = new JButton("Return to Home");
        home.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        home.addActionListener(e -> frame.dispose());

        panel.add(Box.createVerticalGlue());
        panel.add(title);
        panel.add(Box.createVerticalStrut(10));
        panel.add(msg);
        panel.add(Box.createVerticalStrut(10));
        panel.add(home);
        panel.add(Box.createVerticalStrut(30));

        for(Product product : recommendedProducts) {
            panel.add(productRecommendation(product));
            panel.add(Box.createVerticalStrut(15));
        }

        panel.add(home);
        panel.add(Box.createVerticalGlue());

        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
