package Domains.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public Login() {
        createView();
        setTitle("Login");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
    }

     private void createView() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField(20);
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        panel.add(passwordField);

        panel.add(new JLabel());
        loginButton = new JButton("Login");

        //TODO: Actual Login Implementation
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                // Print username and password to the terminal
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);

                // Placeholder for actual login logic
                JOptionPane.showMessageDialog(Login.this, "Login attempt...");
            }
        });
        panel.add(loginButton);

        getContentPane().add(panel);
    }

}