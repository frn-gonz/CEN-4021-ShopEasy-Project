package com.example.shopeasy.Accounts;
import com.example.shopeasy.DiscountsAndPromo.DiscountsRepository;
import com.example.shopeasy.ProductAndInventory.ProductsRepository;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;


public class EmployeePage {
    public EmployeePage(AccountsRepository accountsRepository, ProductsRepository productsRepository, DiscountsRepository discountsRepository) {
        JFrame frame = new JFrame("Employee View");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        frame.add(panel);

        JLabel title = new JLabel("Employee / Administrator");

        JLabel emailLabel = new JLabel("Account Email:");

        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField emailText = new JTextField();
        emailText.setMaximumSize(new Dimension(300, 30));

        JButton designateEmployee = new JButton("Designate Employee");
        designateEmployee.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel promoCode = new JLabel("Promo Code:");
        promoCode.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField promoCodeText = new JTextField();
        promoCodeText.setMaximumSize(new Dimension(300, 30));

        JLabel discountNum = new JLabel("Discount Rate:");
        discountNum.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField discountNumText = new JTextField();
        discountNumText.setMaximumSize(new Dimension(300, 30));

        JLabel expiryDate = new JLabel("Expiration Date:");
        expiryDate.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField expiryDateText = new JTextField();
        expiryDateText.setMaximumSize(new Dimension(300, 30));


        JButton createPromoCode = new JButton("Create Promo Code");
        createPromoCode.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton manageInventory = new JButton("Manage Inventory");
        JButton returnHome = new JButton("Return Home");
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(manageInventory);
        buttonsPanel.add(returnHome);

        panel.add(Box.createVerticalGlue());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        panel.add(Box.createVerticalStrut(30));
        panel.add(emailLabel);
        panel.add(emailText);

        panel.add(Box.createVerticalStrut(30));
        panel.add(designateEmployee);

        panel.add(Box.createVerticalStrut(40));
        panel.add(promoCode);
        panel.add(promoCodeText);

        panel.add(Box.createVerticalStrut(10));
        panel.add(discountNum);
        panel.add(discountNumText);

        panel.add(Box.createVerticalStrut(10));
        panel.add(expiryDate);
        panel.add(expiryDateText);

        panel.add(Box.createVerticalStrut(15));
        panel.add(createPromoCode);

        panel.add(Box.createVerticalStrut(25));
        panel.add(buttonsPanel);

        panel.add(Box.createVerticalGlue());

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }
}
