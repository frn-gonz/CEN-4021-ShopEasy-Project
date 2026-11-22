package com.example.shopeasy.UserInterface;

import javax.swing.*;

public class Login {
    public static void main(String[] args){
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

