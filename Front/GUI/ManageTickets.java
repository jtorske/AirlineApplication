package Front.GUI;

import javax.swing.*;
import Database.Database.Database;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageTickets extends JFrame {
    private JTextField ticketIdInput;
    private JButton selectButton;
    private String currentUser;
    private JTable table; 
    private JScrollPane scrollPane; 
    
    public ManageTickets(String username) {
        this.currentUser = username;
        System.out.println("\nINFO:" + currentUser);
        setTitle("Manage Tickets");
        setSize(15000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeComponents();
        getTickets();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        ticketIdInput = new JTextField(20);
        JPanel panel = new JPanel();
        panel.add(ticketIdInput);

        selectButton = new JButton("Cancel Flight");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ticketId = ticketIdInput.getText().trim(); // Get the input text
                deleteTicket(ticketId);
            }
        });
        panel.add(selectButton);

        add(panel, BorderLayout.NORTH);
    }

    private void getTickets() {
        

        String query = "SELECT UserID FROM User WHERE Username = \"" + currentUser + "\"";

        List<List<String>> dbResult = Database.dbExecute(query);
        String userID = dbResult.get(0).get(0);

        String ticketQuery = "SELECT "
                + "t.TicketID, "
                + "t.SeatRow, "
                + "t.SeatColumn, "
                + "i.Policy, "
                + "s.Type AS SeatType, "
                + "s.Price AS SeatPrice, "
                + "f.DepartureDate, "
                + "f.ArrivalDate, "
                + "l.City AS Origin, "
                + "l2.City AS Destination "
                + "FROM passenger AS p "
                + "JOIN user AS u ON u.UserID = " + userID + " "
                + "JOIN ticket AS t ON t.PassengerID = p.PassengerID "
                + "JOIN Insurance AS i ON t.InsuranceID = i.InsuranceID "
                + "JOIN SeatType AS s ON t.SeatTypeID = s.SeatTypeID "
                + "JOIN Flight AS f ON t.FlightID = f.FlightID "
                + "JOIN Location AS l ON f.DepartureLocationID = l.LocationID "
                + "JOIN Location AS l2 ON f.ArrivalLocationID = l2.LocationID";

        List<List<String>> ticketResult = Database.dbExecute(ticketQuery);
                if (scrollPane != null) {
            getContentPane().remove(scrollPane); 
        }
        displayResults(ticketResult);
    }

    private void deleteTicket(String inputTicketId) {
        if (inputTicketId != null && inputTicketId.matches("\\d+")) {
            String checkQuery = "SELECT COUNT(*) FROM Ticket WHERE TicketID = " + inputTicketId;
            List<List<String>> checkResult = Database.dbExecute(checkQuery);

            if (!checkResult.isEmpty() && Integer.parseInt(checkResult.get(0).get(0)) > 0) {
                Database.dbDelete("Ticket", "TicketID", inputTicketId);
                JOptionPane.showMessageDialog(this, "Deletion Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                getTickets();
            } else {
                JOptionPane.showMessageDialog(this, "No Ticket with ID " + inputTicketId + " found.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Ticket ID. Please enter a valid numeric ID.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayResults(List<List<String>> dbResult) {
        String[] columnNames = {
                "TicketID", "Seat Row", "Seat Column", "Policy", "Seat Type",
                "Seat Price", "Departure Date", "Arrival Date", "Origin", "Destination"
        };

        String[][] data = new String[dbResult.size()][columnNames.length];

        for (int i = 0; i < dbResult.size(); i++) {
            List<String> row = dbResult.get(i);
            for (int j = 0; j < row.size(); j++) {
                data[i][j] = row.get(j);
            }
        }
        table = new JTable(data, columnNames); 
        scrollPane = new JScrollPane(table); 
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        getContentPane().validate();
        getContentPane().repaint();
    }

}
