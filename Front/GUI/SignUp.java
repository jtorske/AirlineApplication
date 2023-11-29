package Front.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import Database.Database.Database;

public class SignUp extends JFrame {
    private JTextField firstNameField, lastNameField, usernameField, emailField;
    private JPasswordField passwordField;
    private JButton signUpButton;

    public SignUp() {
        createView();
        setTitle("Sign Up");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void createView() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 5, 5));

        panel.add(new JLabel("First Name:"));
        firstNameField = new JTextField(20);
        panel.add(firstNameField);

        panel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField(20);
        panel.add(lastNameField);

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField(20);
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        panel.add(passwordField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField(20);
        panel.add(emailField);

        panel.add(new JLabel());
        signUpButton = new JButton("Sign Up");

        // TODO: Actual Sign Up Implementation
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String email = emailField.getText();

                List<List<String>> queryResult = 
                    Database.dbExecute(String.format("SELECT * FROM User WHERE Username = \"%s\"", username));

                System.out.print(queryResult.size());

                if (queryResult.size() == 0) {
                    // Update name table first
                    List<String> valsName = List.of(firstName, lastName, "");
                    String nameID = Database.dbInsert("Name (FirstName, LastName, MiddleName)", valsName);
                    // Update user table
                    List<String> valsUser = List.of(nameID, username, password, email);
                    Database.dbInsert("User (NameID, Username, Password, Email)", valsUser);
                } else {
                    JOptionPane.showMessageDialog(SignUp.this, "Account already taken!");

                }
                // Print the gathered information to the terminal
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
                System.out.println("Email: " + email);

                // Placeholder for actual sign up logic
                JOptionPane.showMessageDialog(SignUp.this, "Sign Up attempt...");
            }
        });
        panel.add(signUpButton);

        getContentPane().add(panel);
    }

}
