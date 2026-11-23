package com.example.shopeasy.Accounts;
import com.example.shopeasy.DiscountsAndPromo.DiscountCode;
import com.example.shopeasy.DiscountsAndPromo.DiscountsRepository;
import com.example.shopeasy.ProductAndInventory.ProductsRepository;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;


public class EmployeePage {
    //Employee Page constructor
    public EmployeePage(AccountsRepository accountsRepository, ProductsRepository productsRepository, DiscountsRepository discountsRepository) {
        //Frame and panel created
        JFrame frame = new JFrame("Employee View");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        JLabel title = new JLabel("Employee / Administrator");//Title label

        //Label, textfield, and alignments for Account Email
        JLabel emailLabel = new JLabel("Account Email:");
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField emailText = new JTextField();
        emailText.setMaximumSize(new Dimension(300, 30));
        emailText.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Creates the designate employee button and aligns it
        JButton designateEmployee = new JButton("Designate Employee");
        designateEmployee.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Label, textfield, and alignments for Promo Code
        JLabel promoCode = new JLabel("Promo Code:");
        promoCode.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField promoCodeText = new JTextField();
        promoCodeText.setMaximumSize(new Dimension(300, 30));
        promoCodeText.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Label, textfield, and alignments for the discount rate
        JLabel discountNum = new JLabel("Discount Rate:");
        discountNum.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField discountNumText = new JTextField();
        discountNumText.setMaximumSize(new Dimension(300, 30));
        discountNumText.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Label, textfield, and alignments for the code's expiration date
        JLabel expiryDate = new JLabel("Expiration Date:");
        expiryDate.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField expiryDateText = new JTextField();
        expiryDateText.setMaximumSize(new Dimension(300, 30));
        expiryDateText.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Button for discount code creation functionality
        JButton createPromoCode = new JButton("Create Promo Code");
        createPromoCode.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Manage inventory button. Not included in prototype functionality, present as a mock-up
        JButton manageInventory = new JButton("Manage Inventory");
        //Return Home button
        JButton returnHome = new JButton("Return Home");
        JPanel buttonsPanel = new JPanel();
        //Panel created to hold both buttons
        buttonsPanel.add(manageInventory);
        buttonsPanel.add(returnHome);

        createPromoCode.addActionListener(e -> {
            //Promo code creation functionality
            try {
                String code = promoCodeText.getText().trim();
                //The promoCode text is saved to code
                BigDecimal discountRate = new BigDecimal(discountNumText.getText().trim());
                //The discount rate is saved as discountRate
                LocalDate expirationDate = LocalDate.parse(expiryDateText.getText().trim());
                //Expiration date is saved
                if(expirationDate.isBefore(LocalDate.now())) {
                    JOptionPane.showMessageDialog(frame, "Expiration Date must be a future date.");
                    return;
                    //If the expiration date is in the past, user is notified it is invalid
                }

                if(code.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please Enter Promo Code");
                    return;
                    //Notifies the user that they cannot leave the promoCode empty for creation
                }

                if(discountRate.compareTo(BigDecimal.ZERO) <= 0 || discountRate.compareTo(BigDecimal.ONE) >= 0) {
                    JOptionPane.showMessageDialog(null, "Discount Rate must be greater than 0 and less than 1");
                    return;
                    //Notifies the user that the discount rate must be between 0 and 1
                }

                //If there are no issues, the information is saved to a discountCode object and saved to the repository
                DiscountCode discountCode = new DiscountCode();
                discountCode.setCodeName(code);
                discountCode.setDiscount(discountRate);
                discountCode.setExpirationDate(expirationDate);
                discountsRepository.save(discountCode);
                //Notifies the user the promo code has been successfully created
                JOptionPane.showMessageDialog(null, "Promo Code created");

                //Strings that hold text values for promocodes are emptied for future use
                promoCodeText.setText("");
                discountNumText.setText("");
                expiryDateText.setText("");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(frame, "Invalid Promo Code. Please try again with the correct format.\n Date format YYYY-MM-DD\n" +
                        "Discount rate format: 0.00");
                //If an exception occurs and the promo code cannot be made, the user is notified and reminded of proper structure.
            }
        });

        designateEmployee.addActionListener(e -> {
            //Designate employee functionality
            String email = emailText.getText().trim();
            //User email address is saved as email
            if(email.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please Enter Email Address");
                return;
                //If empty, the user is notified
            }

            Optional<Account> account = accountsRepository.findByEmail(email);
            if(account.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Account Not Found");
                return;
                //If an account does not exist, the user is notified
            }

            Account foundAccount = account.get();
            //Creates an account to save the found account

            foundAccount.setEmployeeStatus(true);
            accountsRepository.save(foundAccount);
            //Sets employee status to true and updates repository

            JOptionPane.showMessageDialog(frame, "Employee status designated");
            //Notifies user that the requested person has been designated as an employee
            emailText.setText("");
        });

        returnHome.addActionListener(e -> frame.dispose());
        //Return home button

        //Adjusts panel and adds title to it
        panel.add(Box.createVerticalGlue());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        //Adds email label and textfield to the panel
        panel.add(Box.createVerticalStrut(30));
        panel.add(emailLabel);
        panel.add(emailText);
        //Adds designate employee button to the panel
        panel.add(Box.createVerticalStrut(30));
        panel.add(designateEmployee);
        //adds promocode label and textfield
        panel.add(Box.createVerticalStrut(40));
        panel.add(promoCode);
        panel.add(promoCodeText);
        //Adds discountNum label and textfield
        panel.add(Box.createVerticalStrut(10));
        panel.add(discountNum);
        panel.add(discountNumText);
        //Adds expiryDate label and textfield
        panel.add(Box.createVerticalStrut(10));
        panel.add(expiryDate);
        panel.add(expiryDateText);
        //Adds create promo code button
        panel.add(Box.createVerticalStrut(15));
        panel.add(createPromoCode);

        //Adds the buttons saved to buttonsPanel
        panel.add(Box.createVerticalStrut(25));
        panel.add(buttonsPanel);
        panel.add(Box.createVerticalGlue());

        //Sets frame dimensions and placement before making it visible
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }
}
