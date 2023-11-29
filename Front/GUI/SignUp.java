package Front.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + //Matches valid username
                            "[a-zA-Z0-9_+&*-]+)*@" +  //Allows for dot seperated usenames (gmail, hotmail, etc.) and then matches @ after
                            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"; //Matches the domain and then top level domain (.ca, .org, etc.)
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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

                if(firstName.length()==0){
                    JOptionPane.showMessageDialog(SignUp.this, "First Name Cannot Be Empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(lastName.length()==0){
                    JOptionPane.showMessageDialog(SignUp.this, "Last Name Cannot Be Empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(username.length()==0){
                    JOptionPane.showMessageDialog(SignUp.this, "Username Cannot Be Empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(password.length()==0){
                    JOptionPane.showMessageDialog(SignUp.this, "Password Cannot Be Empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(email.length()==0){
                    JOptionPane.showMessageDialog(SignUp.this, "Email Cannot Be Empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!isEmailValid(email)) {
                    JOptionPane.showMessageDialog(SignUp.this, "Invalid email address.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                List<List<String>> queryResult = 
                    Database.dbExecute(String.format("SELECT * FROM User WHERE Username = \"%s\"", username));
                List<List<String>> queryResult2 = 
                    Database.dbExecute(String.format("SELECT * FROM User WHERE Email = \"%s\"", email));
                    

                System.out.print(queryResult.size());
                if(!(queryResult.size() == 0 )){
                    JOptionPane.showMessageDialog(SignUp.this, "Username already taken!","Error", JOptionPane.ERROR_MESSAGE);
                } else if (!(queryResult2.size() == 0)){
                    JOptionPane.showMessageDialog(SignUp.this, "Email already taken!","Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    // Update name table first
                    List<String> valsName = List.of(firstName, lastName, "");
                    String nameID = Database.dbInsert("Name (FirstName, LastName, MiddleName)", valsName);
                    // Update user table
                    List<String> valsUser = List.of(nameID, username, password, email);
                    Database.dbInsert("User (NameID, Username, Password, Email)", valsUser);
                    JOptionPane.showMessageDialog(SignUp.this, "Registration Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } 
                // Print the gathered information to the terminal
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
                System.out.println("Email: " + email);
            }
        });
        panel.add(signUpButton);

        getContentPane().add(panel);
    }

}
