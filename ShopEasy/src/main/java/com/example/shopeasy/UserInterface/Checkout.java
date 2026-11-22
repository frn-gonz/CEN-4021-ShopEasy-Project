package com.example.shopeasy.UserInterface;
import javax.swing.*;

public class Checkout {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Checkout");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        JLabel title = new JLabel("Checkout");
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel total = new JLabel("Current Total: $99.99"); //Placeholder: should calculate total from items being purchased
        total.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel paymentinfo = new JLabel("Card Number:");
        paymentinfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        JTextField paymentField = new JTextField();
        paymentField.setMaximumSize(new java.awt.Dimension(200, 60));
        paymentField.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel shippinginfo = new JLabel("Shipping Address:");
        shippinginfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        JTextField shippingField = new JTextField();
        shippingField.setMaximumSize(new java.awt.Dimension(200, 60));
        shippingField.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel code = new JLabel("Discount Code:");
        code.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        JTextField codeField = new JTextField();
        codeField.setMaximumSize(new java.awt.Dimension(200, 60));
        codeField.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton confirmorder = new JButton("Confirm Order");
        confirmorder.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton back = new JButton("Return to Shopping Cart");
        back.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(title);
        panel.add(Box.createVerticalStrut(15));
        panel.add(total);
        panel.add(Box.createVerticalStrut(30));
        panel.add(paymentinfo);
        panel.add(paymentField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(shippinginfo);
        panel.add(shippingField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(code);
        panel.add(codeField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(confirmorder);
        panel.add(Box.createVerticalStrut(15));
        panel.add(back);
        panel.add(Box.createVerticalGlue());

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
