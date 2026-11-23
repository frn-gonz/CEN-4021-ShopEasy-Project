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

        //Login page created with title and dimensions
        JFrame frame = new JFrame("ShopEasy Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setTitle("Login Page");
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel welcome = new JLabel("Welcome to ShopEasy!");
        welcome.setBounds(300, 100, 150, 40);
        //The welcome label created

        JLabel username = new JLabel("Username:");
        username.setBounds(200, 150, 100, 40);
        JTextField login= new JTextField(20);
        login.setBounds(300, 150, 200, 40);
        //Username label and textfield

        JLabel passwordlabel = new JLabel("Password:");
        passwordlabel.setBounds(200, 200, 100, 40);
        JPasswordField password= new JPasswordField(20);
        password.setBounds(300, 200, 200, 40);
        //Passwordlabel and textfield

        JButton signButton = new JButton("Log in");
        signButton.setBounds(300, 250, 100, 30);
        //Login button created

        JLabel newUser = new JLabel("New user? Sign up ");
        newUser.setBounds(300, 300, 120, 40);
        JButton signupButton = new JButton("here!");
        signupButton.setBounds(420, 300, 80, 40);
        //Signup button created

        JButton forgotPasswordButton = new JButton("Forgot Password");
        forgotPasswordButton.setBounds(300, 350, 200, 30);
        //Forgot password button created

        signButton.addActionListener(e -> {
            String usernameIn = login.getText().trim();
            String passwordIn = new String(password.getPassword());
            //Saves the username and password input

            if(usernameIn.isEmpty() || passwordIn.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter username and password");
                return;
                //If either are empty, notifies user to enter both
            }

            Optional<Account> optionalAccount = accountsRepository.findByUsername(usernameIn);

            if(optionalAccount.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Invalid username or password (Error 111)");
                return;
                //If an account is not found by the username. Notifies user
            }

            Account account = optionalAccount.get();
            if(!passwordIn.equals(account.getPassword())) {
                JOptionPane.showMessageDialog(frame, "Invalid username or password (Error 111)");
                return;
                //If the password doesn't match the account, notifies user it is invalid
            }

            JOptionPane.showMessageDialog(frame, "Login successful. Welcome!");
            //Informs the user the login was successful

            Navigator.get().logIn(account); // saves the account in the Session, closes Login, opens Home

        });

        signupButton.addActionListener(e -> {
            //When user clicks signup button, closes this frame and opens SignUp
            frame.dispose();
            new SignUp(accountsRepository);
        });

        forgotPasswordButton.addActionListener(e -> {
            //When user clicks Forgot Password button, closes this frame and opens SignUp
            frame.dispose();
            new ForgotPassword(accountsRepository);
        });

        //Adds titles, labels,and buttons to the frame and sets it to visible
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

