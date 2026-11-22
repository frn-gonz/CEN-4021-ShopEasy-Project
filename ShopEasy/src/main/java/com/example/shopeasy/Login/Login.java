package com.example.shopeasy.Login;

import com.example.shopeasy.Accounts.Account;
import com.example.shopeasy.Accounts.AccountsRepository;
import com.example.shopeasy.UserInterface.Navigator;
import javax.swing.*;
import java.util.Optional;

public class Login {
    private final AccountsRepository accountsRepository;

    public Login(AccountsRepository accountsRepository){
        this.accountsRepository = accountsRepository;
        JFrame frame = new JFrame("ShopEasy Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setTitle("Login Page");
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel welcome = new JLabel("Welcome to ShopEasy!");
        welcome.setBounds(300, 100, 150, 40);

        JLabel username = new JLabel("Username:");
        username.setBounds(200, 150, 100, 40);
        JTextField login= new JTextField(20);
        login.setBounds(300, 150, 200, 40);

        JLabel passwordlabel = new JLabel("Password:");
        passwordlabel.setBounds(200, 200, 100, 40);
        JPasswordField password= new JPasswordField(20);
        password.setBounds(300, 200, 200, 40);

        JButton signButton = new JButton("Log in");
        signButton.setBounds(300, 250, 100, 30);

        JLabel newUser = new JLabel("New user? Sign up ");
        newUser.setBounds(300, 300, 120, 40);
        JButton signupButton = new JButton("here!");
        signupButton.setBounds(420, 300, 80, 40);

        JButton forgotPasswordButton = new JButton("Forgot Password");
        forgotPasswordButton.setBounds(300, 350, 200, 30);

        signButton.addActionListener(e -> {
            String usernameIn = login.getText().trim();
            String passwordIn = new String(password.getPassword());

            if(usernameIn.isEmpty() || passwordIn.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter username and password");
                return;
            }

            Optional<Account> optionalAccount = accountsRepository.findByUsername(usernameIn);

            if(optionalAccount.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Invalid username or password (Error 111)");
                return;
            }

            Account account = optionalAccount.get();
            if(!passwordIn.equals(account.getPassword())) {
                JOptionPane.showMessageDialog(frame, "Invalid username or password (Error 111)");
                return;
            }

            JOptionPane.showMessageDialog(frame, "Login successful. Welcome!");

            Navigator.get().logIn(account); // saves the account in the Session, closes Login, opens Home

        });

        signupButton.addActionListener(e -> {

            frame.dispose();
            new SignUp(accountsRepository);
        });

        forgotPasswordButton.addActionListener(e -> {
            frame.dispose();
            new ForgotPassword(accountsRepository);
        });

        frame.add(welcome);
        frame.add(username);
        frame.add(login);
        frame.add(passwordlabel);
        frame.add(password);
        frame.add(signButton);
        frame.add(newUser);
        frame.add(signupButton);
        frame.add(forgotPasswordButton);
        frame.setVisible(true);

    }
}

