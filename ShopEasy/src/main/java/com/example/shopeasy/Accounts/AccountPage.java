package com.example.shopeasy.Accounts;


import javax.swing.*;

public class AccountPage {
    private String hideCardNumber(String cardNumber) {
        if(cardNumber == null || cardNumber.length() < 4) {
            return "Not Set";
        }

        return "************" + cardNumber.substring(cardNumber.length() - 4);
    }

    public AccountPage(AccountsRepository accountsRepository, Account currentAccount) {

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

        JLabel payinfo = new JLabel("Current Card Number: " + hideCardNumber(currentAccount.getPaymentInfo()));
        payinfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel shipinfo = new JLabel("Current Shipping Address: " + currentAccount.getShippingAddress());
        shipinfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel userInfo = new JLabel("Username: " + currentAccount.getUsername());
        userInfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JTextField paynew = new JTextField();
        paynew.setMaximumSize(new java.awt.Dimension(200, 60));
        paynew.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JTextField shipnew = new JTextField();
        shipnew.setMaximumSize(new java.awt.Dimension(200, 60));
        shipnew.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton back = new JButton("Return to Home");
        back.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        back.addActionListener(e -> frame.dispose()); // Home is still open behind this window

        updatepay.addActionListener(e -> {
            String newpay = paynew.getText().trim();

            if (newpay.equals(null) || newpay.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter your payment information.");
                return;
            }

            currentAccount.setPaymentInfo(newpay);
            accountsRepository.save(currentAccount);

            payinfo.setText("New card number: " + hideCardNumber(currentAccount.getPaymentInfo()));

            JOptionPane.showMessageDialog(frame, "Payment Info Updated");
        });

        updateaddress.addActionListener(e -> {
            String newaddress = shipnew.getText().trim();
            if (newaddress.equals(null) || newaddress.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter your shipping address.");
                return;
            }
            currentAccount.setShippingAddress(newaddress);
            accountsRepository.save(currentAccount);
            shipinfo.setText("Current Shipping Address: " + currentAccount.getShippingAddress());
            JOptionPane.showMessageDialog(frame, "Shipping Address Updated");
        });


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

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
