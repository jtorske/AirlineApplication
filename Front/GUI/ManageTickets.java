package Front.GUI;

import javax.swing.*;
import Database.Database.Database;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageTickets extends JFrame {
    private JTextArea resultTextArea;
    private JTextField flightIdInput;
    private JButton selectButton;
    private String currentUser;

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
    
        flightIdInput = new JTextField(20); // Use the class field instead of creating a new local variable
        JPanel panel = new JPanel();
        panel.add(flightIdInput);
    
        selectButton = new JButton("Select");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String flightId = flightIdInput.getText().trim();
                getTicketInfo(flightId);
            }
        });
        panel.add(selectButton);
    
        add(panel, BorderLayout.NORTH); // Add panel to the ManageTickets frame
    }
    
    private void getTickets() {

        String query = "SELECT UserID FROM User WHERE Username = \"" + currentUser + "\"";
        

        List<List<String>> dbResult = Database.dbExecute(query);
         String userID = dbResult.get(0).get(0); 

        String ticketQuery = "SELECT "
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
        displayResults(ticketResult);
    }
    private void getTicketInfo(String flightId){
        //wip
    }
    private void displayResults(List<List<String>> dbResult) {
        // Define the column names for the table
        String[] columnNames = {
            "ID", "Seat Row", "Seat Column", "Policy", "Seat Type", 
            "Seat Price", "Departure Date", "Arrival Date", "Origin", "Destination"
        };
        
        // Create a 2D array to hold the data (with an additional column for ID)
        String[][] data = new String[dbResult.size()][columnNames.length];
        
        // Populate the data array with results, adding an ID to each row
        int id = 0;
        for (int i = 0; i < dbResult.size(); i++) {
            List<String> row = dbResult.get(i);
            data[i][0] = String.valueOf(id++); // Add the ID in the first column
            for (int j = 0; j < row.size(); j++) {
                data[i][j + 1] = row.get(j); // Offset by one because of the ID
            }
        }
        
        // Create a new JTable with the data and column names
        JTable table = new JTable(data, columnNames);
        
        // Replace the current JScrollPane's viewport with the new JTable
        JScrollPane scrollPane = new JScrollPane(table);
        this.getContentPane().add(scrollPane); // Add the new JScrollPane
    }
    

}
