/*
@author 
Chun Lok Chan
Jordan Torske
Mohamad Hussein
Logan Nightingale
@version: 1.0
@since: 2023-11-23
 */
package Front.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Database.Login.Verify;

public class FlightAttendantLogin extends Login{
    public FlightAttendantLogin(){
        createView();
        setTitle("Login As A Flight Attendant");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    @Override
    protected void createView() {
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
        System.out.println("Login button created");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                if (!Verify.verifyCrewMember(username, password)){
                    JOptionPane.showMessageDialog(FlightAttendantLogin.this, "Incorrect flight attendant username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    return;}
                //change the username of main page
                GUI.setUsername(username);
                GUI.setIdentity("FA");

                //close the login window
                dispose();                    
                

                // Print username and password to the terminal
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);

                // Placeholder for actual login logic
                JOptionPane.showMessageDialog(FlightAttendantLogin.this, "Login attempt...");
            }
        });
        panel.add(loginButton);

        getContentPane().add(panel);
    }
}
