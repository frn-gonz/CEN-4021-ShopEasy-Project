package UserInterface;

import javax.swing.*;
import java.awt.*;

public class SignUp {
    public static void main(String[] args){
        JFrame signup = new JFrame("Sign Up for ShopEasy");
        signup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signup.setSize(800,600);
        signup.setTitle("Sign Up for ShopEasy");
        signup.setLocationRelativeTo(null);
        signup.setLayout(null);

        JLabel register = new JLabel("Create your ShopEasy Account");
        register.setBounds(300, 50, 200, 40);

        JLabel firstname = new JLabel("First name:");
        firstname.setBounds(200, 100, 100, 40);
        JTextField fname = new JTextField(20);
        fname.setBounds(300, 100, 150, 40);

        JLabel lastname = new JLabel("Last name:");
        lastname.setBounds(200, 150, 100, 40);
        JTextField lname = new JTextField(20);
        lname.setBounds(300, 150, 150, 40);

        JLabel emailAdd = new JLabel("Email address:");
        emailAdd.setBounds(200, 200, 100, 40);
        JTextField email = new JTextField(30);
        email.setBounds(300, 200, 150, 40);

        JLabel userName = new JLabel("Username:");
        userName.setBounds(200, 250, 150, 40);
        JTextField unameBox = new JTextField(20);
        unameBox.setBounds(300, 250, 150, 40);

        JLabel passType = new JLabel("Password:");
        passType.setBounds(200, 300, 100, 40);
        JPasswordField passBox = new JPasswordField(20);
        passBox.setBounds(300, 300, 150, 40);

        JLabel passRetype = new JLabel("Re-type password:");
        passRetype.setBounds(150, 350, 150, 40);
        JPasswordField repassBox = new JPasswordField(20);
        repassBox.setBounds(300, 350, 150, 40);

        JButton rbutton = new JButton("Register");
        rbutton.setBounds(300, 400, 100, 30);

        signup.add(register);
        signup.add(firstname);
        signup.add(fname);
        signup.add(lastname);
        signup.add(lname);
        signup.add(emailAdd);
        signup.add(email);
        signup.add(userName);
        signup.add(unameBox);
        signup.add(passType);
        signup.add(passBox);
        signup.add(passRetype);
        signup.add(repassBox);
        signup.add(rbutton);
        signup.setVisible(true);
    }
}
