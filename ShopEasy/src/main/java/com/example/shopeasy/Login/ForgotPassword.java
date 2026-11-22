package com.example.shopeasy.Login;

import com.example.shopeasy.Accounts.Account;
import com.example.shopeasy.Accounts.AccountsRepository;
import javax.swing.*;
import java.util.Optional;

public class ForgotPassword {
    private final AccountsRepository accountsRepository;

    public ForgotPassword(AccountsRepository accountsRepository){
        this.accountsRepository = accountsRepository;
        JFrame fPassword = new JFrame("Forgot Password?");
        fPassword.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fPassword.setSize(800,600);
        fPassword.setTitle("Forgot Password?");
        fPassword.setLocationRelativeTo(null);
        fPassword.setLayout(null);

        JLabel forgot = new JLabel("Forgot your password?");
        forgot.setBounds(200, 150, 200, 40);
        JLabel resetP = new JLabel("Enter your email address, and if an existing account matches, you can reset your password.");
        resetP.setBounds(200, 200, 500, 40);

        JLabel emailLine = new JLabel("Email Address:");
        emailLine.setBounds(200, 250, 150, 40);
        JTextField emailBox = new JTextField(30);
        emailBox.setBounds(300, 250, 150, 40);

        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(200, 300, 100, 40);

        resetButton.addActionListener(e -> {
            String email = emailBox.getText().trim();
            if(email.isEmpty()){
                JOptionPane.showMessageDialog(fPassword, "Please enter your email address.");
                return;
            }
            Optional<Account> optionalAccount = accountsRepository.findByEmail(email);

            if(optionalAccount.isEmpty()){
                JOptionPane.showMessageDialog(fPassword, "No account exists with that email address.");
                return;
            }

            String newPassword = JOptionPane.showInputDialog(fPassword, "Enter your new password");
            if(newPassword == null || newPassword.isEmpty()){
                JOptionPane.showMessageDialog(fPassword, "Please enter your new password.");
                return;
            }

            String confirmPassword = JOptionPane.showInputDialog(fPassword, "Confirm your new password");
            if(confirmPassword == null || confirmPassword.isEmpty()){
                JOptionPane.showMessageDialog(fPassword, "Please confirm your new password.");
                return;
            }
            if(!confirmPassword.equals(newPassword)){
               JOptionPane.showMessageDialog(fPassword, "The passwords do not match.");
               return;
            }

            Account account = optionalAccount.get();
            account.setPassword(newPassword);

            accountsRepository.save(account);

            JOptionPane.showMessageDialog(fPassword, "Your password has been reset.");

            fPassword.dispose();
            new Login(accountsRepository);
        });

        fPassword.add(forgot);
        fPassword.add(resetP);
        fPassword.add(emailLine);
        fPassword.add(emailBox);
        fPassword.add(resetButton);
        fPassword.setVisible(true);


    }
}
