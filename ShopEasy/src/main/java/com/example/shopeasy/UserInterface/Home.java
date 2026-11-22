package com.example.shopeasy.UserInterface;

import com.example.shopeasy.Accounts.Session;

import javax.swing.*;

public class Home {
    public Home(Navigator navigator, Session session) {
        JFrame frame = new JFrame("Home Page");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        JLabel title = new JLabel("Welcome to ShopEasy!");
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton acc = new JButton("Go to Account Information");
        acc.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton shop = new JButton("Go to Shopping Cart");
        shop.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton wish = new JButton("Go to Wish List");
        wish.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton inv = new JButton("Go to Inventory");
        inv.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton noti = new JButton("Go to Notifications");
        noti.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton emp = new JButton("Go to Employee View");
        emp.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel emplabel = new JLabel("Enter Employee ID:");
        emplabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JTextField empfield = new JTextField();
        empfield.setMaximumSize(new java.awt.Dimension(200, 60));
        empfield.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton logout = new JButton("Logout");
        logout.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        // Each screen opens in its own window on top of Home; Home stays open behind it
        acc.addActionListener(e -> navigator.openAccount());
        inv.addActionListener(e -> navigator.openInventory());
        shop.addActionListener(e -> navigator.openShoppingCart());
        wish.addActionListener(e -> navigator.openWishList());
        noti.addActionListener(e -> showComingSoon(frame, "Notifications"));
        emp.addActionListener(e -> showComingSoon(frame, "Employee View"));

        logout.addActionListener(e -> {
            session.logout();
            navigator.openLogin(); // also closes Home and any other open screens
        });

        panel.add(Box.createVerticalGlue());
        panel.add(title);
        panel.add(Box.createVerticalStrut(30));
        panel.add(acc);
        panel.add(Box.createVerticalStrut(15));
        panel.add(inv);
        panel.add(Box.createVerticalStrut(15));
        panel.add(shop);
        panel.add(Box.createVerticalStrut(15));
        panel.add(wish);
        panel.add(Box.createVerticalStrut(15));
        panel.add(noti);
        panel.add(Box.createVerticalStrut(15));
        panel.add(emplabel);
        panel.add(empfield);
        panel.add(Box.createVerticalStrut(5));
        panel.add(emp);
        panel.add(Box.createVerticalStrut(25));
        panel.add(logout);
        panel.add(Box.createVerticalGlue());

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // main window: closing it quits the app
        frame.setVisible(true);
    }

    // For buttons whose screens haven't been built yet
    private static void showComingSoon(JFrame frame, String feature) {
        JOptionPane.showMessageDialog(frame, "Coming soon!", feature, JOptionPane.INFORMATION_MESSAGE);
    }
}