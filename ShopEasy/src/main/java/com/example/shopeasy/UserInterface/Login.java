package com.example.shopeasy.UserInterface;

import com.example.shopeasy.Accounts.AccountsRepository;
import com.example.shopeasy.Accounts.Session;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Optional;

public class Login {
    public Login(Navigator navigator, AccountsRepository accountsRepository, Session session) {
        JFrame frame = new JFrame("ShopEasy Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // main window: closing it quits the app
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

        // Runs when "Log in" is clicked or Enter is pressed in the password box
        ActionListener logIn = e -> {
            String enteredUsername = login.getText().trim(); // SignUp trims usernames too
            String enteredPassword = new String(password.getPassword());

            // Blank fields never match, so there is no need to ask the database
            Optional<com.example.shopeasy.Accounts.Account> account = Optional.empty();
            if (!enteredUsername.isEmpty() && !enteredPassword.isEmpty()) {
                account = accountsRepository.findByUsername(enteredUsername).stream()
                        .filter(match -> match.passwordMatches(enteredPassword))
                        .findFirst();
            }

            if (account.isEmpty()) {
                password.setText("");
                JOptionPane.showMessageDialog(frame, "Invalid username or password (Error 111)",
                        "Login Failed", JOptionPane.ERROR_MESSAGE);
                return;
            }

            session.setCurrentAccount(account.get());
            navigator.openHome(); // also closes this Login window
        };
        signButton.addActionListener(logIn);
        password.addActionListener(logIn);

        signupButton.addActionListener(e -> navigator.openSignUp());

        frame.add(welcome);
        frame.add(username);
        frame.add(login);
        frame.add(passwordlabel);
        frame.add(password);
        frame.add(signButton);
        frame.add(newUser);
        frame.add(signupButton);
        frame.setVisible(true);

    }
}

