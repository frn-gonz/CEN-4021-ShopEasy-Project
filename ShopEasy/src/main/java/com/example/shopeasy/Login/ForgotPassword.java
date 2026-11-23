package com.example.shopeasy.Login;

import com.example.shopeasy.Accounts.Account;
import com.example.shopeasy.Accounts.AccountsRepository;
import javax.swing.*;
import java.util.Optional;

public class ForgotPassword {
    private final AccountsRepository accountsRepository;

    public ForgotPassword(AccountsRepository accountsRepository){
        this.accountsRepository = accountsRepository;

        //Creates fPassword frame and sets dimensions and location
        JFrame fPassword = new JFrame("Forgot Password?");
        fPassword.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fPassword.setSize(800,600);
        fPassword.setTitle("Forgot Password?");
        fPassword.setLocationRelativeTo(null);
        fPassword.setLayout(null);

        //Creates forgot password and reset labels
        JLabel forgot = new JLabel("Forgot your password?");
        forgot.setBounds(200, 150, 200, 40);
        JLabel resetP = new JLabel("Enter your email address, and if an existing account matches, you can reset your password.");
        resetP.setBounds(200, 200, 500, 40);

        //Label and textfield to enter email address
        JLabel emailLine = new JLabel("Email Address:");
        emailLine.setBounds(200, 250, 150, 40);
        JTextField emailBox = new JTextField(30);
        emailBox.setBounds(300, 250, 150, 40);

        //Creates the reset button
        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(200, 300, 100, 40);

        //Home button created
        JButton home = new JButton("Exit");
        home.setBounds(350, 300, 100, 40);
        home.addActionListener(e -> fPassword.dispose());
        //Closes window when pressed

        resetButton.addActionListener(e -> {
            //Button functionality to reset password
            String email = emailBox.getText().trim();
            if(email.isEmpty()){
                JOptionPane.showMessageDialog(fPassword, "Please enter your email address.");
                return;
                //If password is empty, notifies user and reminds them they need to enter an email address
            }
            Optional<Account> optionalAccount = accountsRepository.findByEmail(email);

            if(optionalAccount.isEmpty()){
                //If no account exists with the email address, notifies user
                JOptionPane.showMessageDialog(fPassword, "No account exists with that email address.");
                return;
            }

            String newPassword = JOptionPane.showInputDialog(fPassword, "Enter your new password");
            //Asks user to enter a new password
            if(newPassword == null || newPassword.isEmpty()){
                JOptionPane.showMessageDialog(fPassword, "Please enter your new password.");
                return;
                //If empty, asks them again
            }

            String confirmPassword = JOptionPane.showInputDialog(fPassword, "Confirm your new password");
            //Asks the user to enter the new password again
            if(confirmPassword == null || confirmPassword.isEmpty()){
                JOptionPane.showMessageDialog(fPassword, "Please confirm your new password.");
                return; //If the confirmPassword is empty, notifies the user
            }
            if(!confirmPassword.equals(newPassword)){
                //If the passwords don't match, the user is notified
               JOptionPane.showMessageDialog(fPassword, "The passwords do not match.");
               return;
            }

            //Saves the newPassword to the account and saves it to the repository
            Account account = optionalAccount.get();
            account.setPassword(newPassword);
            accountsRepository.save(account);

            JOptionPane.showMessageDialog(fPassword, "Your password has been reset.");
            //Notifies the user
            fPassword.dispose();
            //Closess the window and creates a new Login
            new Login(accountsRepository);
        });

        //Adds all labels, buttons, and textfields to the frame
        fPassword.add(forgot);
        fPassword.add(resetP);
        fPassword.add(emailLine);
        fPassword.add(emailBox);
        fPassword.add(resetButton);
        fPassword.add(home);
        fPassword.setVisible(true);


    }
}
