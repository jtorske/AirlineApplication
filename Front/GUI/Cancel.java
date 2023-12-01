package Front.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Database.Database.Database;
import java.util.List;

public class Cancel extends JFrame {
    private JTextField ticketIdField;
    private JButton cancelFlightButton;

    public Cancel() {
        createView();
        setTitle("Cancel Flight");
        setSize(300, 100);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void createView() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        panel.add(new JLabel("Ticket ID:"));
        ticketIdField = new JTextField(20);
        panel.add(ticketIdField);

        cancelFlightButton = new JButton("Cancel Flight");
        cancelFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ticketId = ticketIdField.getText();

                System.out.println("Ticket ID: " + ticketId);
                String query = "SELECT n.FirstName, n.LastName, p.Email, p.PassportID " +
                        "FROM Ticket t " +
                        "JOIN Passenger p ON t.PassengerID = p.PassengerID " +
                        "JOIN Name n ON p.NameID = n.NameID " +
                        "WHERE t.TicketID = '" + ticketId + "'";

                Database.dbExecute(query);
                List<List<String>> rows = Database.dbExecute(query);
                if (!rows.isEmpty()) {
                    List<String> firstRow = rows.get(0);
                    String firstName = firstRow.get(0);
                    String lastName = firstRow.get(1);
                    String email = firstRow.get(2);
                    String passportID = firstRow.get(3);

                    System.out.println("Name: " + firstName + " " + lastName);
                    System.out.println("Email: " + email);
                    System.out.println("PassportID: " + passportID);
                    confirmAndCancelTicket(ticketId, firstName, lastName, email, passportID);

                } else {
                    JOptionPane.showMessageDialog(Cancel.this, "No ticket found with ID: " + ticketId);
                }
            }
        });
        panel.add(cancelFlightButton);

        getContentPane().add(panel);
    }

    private void confirmAndCancelTicket(String ticketId, String firstName, String lastName, String email,
            String passportID) {
        JFrame confirmFrame = new JFrame("Confirm Cancellation");
        confirmFrame.setSize(400, 200);
        confirmFrame.setLocationRelativeTo(null);
        confirmFrame.setLayout(new GridLayout(5, 2, 5, 5));

        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField passportIDField = new JTextField();

        confirmFrame.add(new JLabel("First Name:"));
        confirmFrame.add(firstNameField);
        confirmFrame.add(new JLabel("Last Name:"));
        confirmFrame.add(lastNameField);
        confirmFrame.add(new JLabel("Email:"));
        confirmFrame.add(emailField);
        confirmFrame.add(new JLabel("Passport ID:"));
        confirmFrame.add(passportIDField);

        JButton confirmButton = new JButton("Confirm and Cancel Ticket");
        confirmButton.setPreferredSize(new Dimension(500, 40)); 
        confirmFrame.add(confirmButton);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputFirstName = firstNameField.getText();
                String inputLastName = lastNameField.getText();
                String inputEmail = emailField.getText();
                String inputPassportID = passportIDField.getText();

                if (inputFirstName.equalsIgnoreCase(firstName) && inputLastName.equalsIgnoreCase(lastName) &&
                        inputEmail.equalsIgnoreCase(email) && inputPassportID.equalsIgnoreCase(passportID)) {
                    Database.dbDelete("Ticket", "TicketID", ticketId);
                    JOptionPane.showMessageDialog(confirmFrame, "Ticket cancelled successfully.");
                    confirmFrame.dispose(); // Close the confirmation window
                    Cancel.this.dispose(); // Close the Cancel JFrame
                } else {
                    JOptionPane.showMessageDialog(confirmFrame, "The provided details do not match. Please try again.");
                }
            }
        });

        confirmFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Cancel().setVisible(true);
            }
        });
    }
}
