package Domains.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cancel extends JFrame {
    private JTextField firstNameField, lastNameField, airTicketField, emailField, ReceiptField;
    private JButton CancelFlightButton;

    public Cancel() {
        createView();
        setTitle("Cancel Flight");
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

        panel.add(new JLabel("Air Ticket Number:"));
        airTicketField = new JTextField(20);
        panel.add(airTicketField);

        panel.add(new JLabel("Receipt Number:"));
        ReceiptField = new JPasswordField(20);
        panel.add(ReceiptField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField(20);
        panel.add(emailField);

        panel.add(new JLabel());
        CancelFlightButton = new JButton("Cancel Flight");

        //TODO: Actual Sign Up Implementation
        CancelFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String airTicketNumString = airTicketField.getText();
                String receiptString = ReceiptField.getText();
                String email = emailField.getText();

                // Print the gathered information to the terminal
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Air Ticket Number: " + airTicketNumString);
                System.out.println("Receipt Number: " + receiptString); 
                System.out.println("Email: " + email);

                // Placeholder for actual sign up logic
                JOptionPane.showMessageDialog(Cancel.this, "Cancel attempt...");
            }
        });
        panel.add(CancelFlightButton);

        getContentPane().add(panel);
    }

}