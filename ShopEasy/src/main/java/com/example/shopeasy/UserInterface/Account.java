package com.example.shopeasy.UserInterface;
import javax.swing.*;

public class Account {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Account");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        JLabel title = new JLabel("Account Information");
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel paybelow = new JLabel("Enter new number below:");
        paybelow.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel shipbelow = new JLabel("Enter new address below:");
        shipbelow.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton updatepay = new JButton("Update");
        updatepay.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton updateaddress = new JButton("Update");
        updateaddress.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel payinfo = new JLabel("Current Card Number: XXXX-XXXX-XXXX-XX01"); //Placeholder: Should read card number from account
        payinfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel shipinfo = new JLabel("Current Shipping Address: 1234 Sample Dr., Miami, FL"); //Placeholder: Should read address from account
        shipinfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JTextField paynew = new JTextField();
        paynew.setMaximumSize(new java.awt.Dimension(200, 60));
        paynew.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JTextField shipnew = new JTextField();
        shipnew.setMaximumSize(new java.awt.Dimension(200, 60));
        shipnew.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton back = new JButton("Return to Home");
        back.setAlignmentX(JComponent.CENTER_ALIGNMENT);


        panel.add(Box.createVerticalGlue());
        panel.add(title);
        panel.add(Box.createVerticalStrut(30));
        panel.add(payinfo);
        panel.add(Box.createVerticalStrut(5));
        panel.add(paybelow);
        panel.add(paynew);
        panel.add(Box.createVerticalStrut(10));
        panel.add(updatepay);
        panel.add(Box.createVerticalStrut(15));
        panel.add(shipinfo);
        panel.add(Box.createVerticalStrut(5));
        panel.add(shipbelow);
        panel.add(shipnew);
        panel.add(Box.createVerticalStrut(10));
        panel.add(updateaddress);
        panel.add(Box.createVerticalStrut(15));
        panel.add(back);
        panel.add(Box.createVerticalGlue());

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
