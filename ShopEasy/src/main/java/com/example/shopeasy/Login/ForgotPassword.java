package com.example.shopeasy.Login;
import javax.swing.*;

public class ForgotPassword {
    public static void main(String[] args){
        JFrame fPassword = new JFrame("Forgot Password?");
        fPassword.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fPassword.setSize(800,600);
        fPassword.setTitle("Forgot Password?");
        fPassword.setLocationRelativeTo(null);
        fPassword.setLayout(null);

        JLabel forgor = new JLabel("Forgot your password?");
        forgor.setBounds(200, 150, 200, 40);
        JLabel resetP = new JLabel("Enter your email address, and we will send you a link to reset your password.");
        resetP.setBounds(200, 200, 500, 40);

        JLabel emailLine = new JLabel("Email Address:");
        emailLine.setBounds(200, 250, 150, 40);
        JTextField emailBox = new JTextField(30);
        emailBox.setBounds(300, 250, 150, 40);

        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(200, 300, 100, 40);

        fPassword.add(forgor);
        fPassword.add(resetP);
        fPassword.add(emailLine);
        fPassword.add(emailBox);
        fPassword.add(resetButton);
        fPassword.setVisible(true);


    }
}
