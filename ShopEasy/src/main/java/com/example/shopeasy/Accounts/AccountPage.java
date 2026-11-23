package com.example.shopeasy.Accounts;


import javax.swing.*;

public class AccountPage {
    private String hideCardNumber(String cardNumber) {
        //Private method to hide card number from account page
        if(cardNumber == null || cardNumber.length() < 4) {
            return "Not Set";
            //If a cardNumber is null or too short, it returns Not Set
        }
        //Otherwise, hides them with * and shows the last four digits.
        return "************" + cardNumber.substring(cardNumber.length() - 4);
    }

    public AccountPage(AccountsRepository accountsRepository, Account currentAccount) {
        //AccountPage constructor

        //Creates the Jframe and Panel
        JFrame frame = new JFrame("Account");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        //Creates the JLabels for the page and aligns them
        JLabel title = new JLabel("Account Information");
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel paybelow = new JLabel("Enter new number below:");
        paybelow.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel shipbelow = new JLabel("Enter new address below:");
        shipbelow.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton updatepay = new JButton("Update");
        updatepay.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        //JButton for updating payment info

        JButton updateaddress = new JButton("Update");
        updateaddress.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        //JButton for updating address

        JLabel payinfo = new JLabel("Current Card Number: " + hideCardNumber(currentAccount.getPaymentInfo()));
        payinfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        //Displays the hidden card number

        JLabel shipinfo = new JLabel("Current Shipping Address: " + currentAccount.getShippingAddress());
        shipinfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        //Shows shipping address

        JLabel userInfo = new JLabel("Username: " + currentAccount.getUsername());
        userInfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        //Shows username

        JTextField paynew = new JTextField();
        paynew.setMaximumSize(new java.awt.Dimension(200, 60));
        paynew.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        //Textfield for when user is entering a new card for payment
        JTextField shipnew = new JTextField();
        shipnew.setMaximumSize(new java.awt.Dimension(200, 60));
        shipnew.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        //Textfield for when user is updating their Shipping Address

        JButton back = new JButton("Return to Home");//JButton to return to the home page
        back.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        back.addActionListener(e -> frame.dispose()); // Home is still open behind this window

        updatepay.addActionListener(e -> {
            //Button functionality to update the database with the updated info
            String newpay = paynew.getText().trim();

            if (newpay.equals(null) || newpay.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter your payment information.");
                return;//If the user entered nothing, they will be prompted again to enter payment info.
            }

            currentAccount.setPaymentInfo(newpay);
            accountsRepository.save(currentAccount);
            //Updates the account and updates the repository

            payinfo.setText("New card number: " + hideCardNumber(currentAccount.getPaymentInfo()));
            //Returns new card number.

            JOptionPane.showMessageDialog(frame, "Payment Info Updated");
            //Notifies that payment info was successfully updated
        });

        updateaddress.addActionListener(e -> {
            //Button functionality for updating the shipping address
            String newaddress = shipnew.getText().trim();
            if (newaddress.equals(null) || newaddress.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter your shipping address.");
                return;
                //If empty, the user is prompted again
            }
            currentAccount.setShippingAddress(newaddress);
            accountsRepository.save(currentAccount);
            //Updated account and repository
            shipinfo.setText("Current Shipping Address: " + currentAccount.getShippingAddress());
            JOptionPane.showMessageDialog(frame, "Shipping Address Updated");
            //Shows updated address and notifies user that the update was successful
        });

        //Adds all buttons and labels to the panel, as well as structural adjustments
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

        //Sets the frame size, location, close, and visibility
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
