package Front.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import Domains.Flights.Flights;
import Domains.Flights.TimeDate;
import Domains.Passenger.Passenger;
import Domains.User.*;
import Domains.Flights.Location;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GUI extends JFrame {
    //Component declarations
    private JTextArea originCountryTextArea, destinationCountryTextArea, originProvinceTextArea, destinationProvinceTextArea, originCityTextArea, destinationCityTextArea, FlightNumArea;

    private JComboBox<String> tripTypeComboBox;

    private JSpinner guestNumberSpinner, departureDateField, returnDateField;

    private JButton buttonSearch;
    private JLabel returnDateLabel;
    
    private JButton loginButton;
    private JButton signUpButton;
    private JButton cancelButton;
    private JButton crewMemberLoginButton;
    private JButton adminLoginButton;
    private JButton browsePassengerButton;

    static private String username=null;
    static private String identity=null;
    static private User user=null;
    static public void setIdentity(String i){
        identity = i;
        GUI gui = new GUI();
        gui.setVisible(true);
    }
    static public void setUsername(String u){
        username = u;
        //refresh the page
        GUI gui = new GUI();
        gui.setVisible(true);
    }
    static public String getUsername(){
        return username;
    }  
    static public void setUser(User u){
        user = u;
    }
    public GUI() {
        createView();
        setupFrame();
        createLoginButton();
        createSignUpButton();
    }

    // Sets up the main frame
    private void setupFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setTitle("Airline Application");
        setSize(1200, 500);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    // Sets up the login button
    private void createLoginButton() {
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(80, 30)); 
        loginButton.setFont(new Font("Arial", Font.PLAIN, 10)); 
        loginButton.addActionListener(e -> {
            Login loginWindow = new Login();
            loginWindow.setVisible(true);
        });
    }

    // Sets up the flight attendent login button
    private void createFALoginButton() {
        crewMemberLoginButton = new JButton("Flight Attendant Login");
        crewMemberLoginButton.setPreferredSize(new Dimension(160, 30)); 
        crewMemberLoginButton.setFont(new Font("Arial", Font.PLAIN, 10)); 
        crewMemberLoginButton.addActionListener(e -> {
            Login loginWindow = new FlightAttendantLogin();
            loginWindow.setVisible(true);
        });
    }

    // Sets up the admin login button
    private void createAdminLoginButton() {
        adminLoginButton = new JButton("Admin Login");
        adminLoginButton.setPreferredSize(new Dimension(100, 30)); 
        adminLoginButton.setFont(new Font("Arial", Font.PLAIN, 10)); 
        adminLoginButton.addActionListener(e -> {
            Login loginWindow = new AdminLogin();
            loginWindow.setVisible(true);
        });
    }

    // Sets up the logout button
    private void createLogoutButton() {
        loginButton = new JButton("Logout");
        loginButton.setPreferredSize(new Dimension(80, 30)); 
        loginButton.setFont(new Font("Arial", Font.PLAIN, 10)); 
        loginButton.addActionListener(e -> {
            setUsername(null);
        });
    }
    // Sets up the cancel button
    private void createCancelButton() {
        cancelButton = new JButton("Cancel Flight");
        cancelButton.setPreferredSize(new Dimension(160, 30));
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 10));
        cancelButton.addActionListener(e -> {
            Cancel CancelWindow = new Cancel();
            CancelWindow.setVisible(true);
        });
    }

    // Sets up the Sign Up button
    private void createSignUpButton() {
        signUpButton = new JButton("Sign Up");
        signUpButton.setPreferredSize(new Dimension(80, 30));
        signUpButton.setFont(new Font("Arial", Font.PLAIN, 10));
        signUpButton.addActionListener(e -> {
            SignUp signUpWindow = new SignUp();
            signUpWindow.setVisible(true);
        });
    }
        // Sets up the Sign Up button
    private void createBrowsePassengerPanel(JPanel panel) {
        JPanel BrowsePassengerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        FlightNumArea = new JTextArea(1, 20);
        BrowsePassengerPanel.add(new JLabel("Flight Number:"));
        BrowsePassengerPanel.add(originCountryTextArea);

        panel.add(BrowsePassengerPanel);
        browsePassengerButton = new JButton("Browse Passenger");
        browsePassengerButton.setPreferredSize(new Dimension(80, 30));
        browsePassengerButton.setFont(new Font("Arial", Font.PLAIN, 10));
        browsePassengerButton.addActionListener(e -> {
           searchPassenger(); 
        });
    }
    // Creates and adds components to the frame
    private void createView() {
        System.out.println("Username: " + username);
        System.out.println("Identity: " + identity);
        // Main container with BorderLayout
        JPanel mainContainer = new JPanel(new BorderLayout());

        getContentPane().add(mainContainer);
        
        JPanel loginPanel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        if (username == null){
            // Login button
            createLoginButton();
            rightPanel.add(loginButton);
            loginPanel.add(leftPanel, BorderLayout.NORTH);
            mainContainer.add(loginPanel, BorderLayout.NORTH);
            // Sign Up button
            createSignUpButton();
            rightPanel.add(signUpButton);
            loginPanel.add(leftPanel, BorderLayout.NORTH);
            mainContainer.add(loginPanel, BorderLayout.NORTH);
            // Flight Attendent Button
            createFALoginButton();
            rightPanel.add(crewMemberLoginButton);
            loginPanel.add(leftPanel, BorderLayout.SOUTH);
            mainContainer.add(loginPanel, BorderLayout.NORTH);
            // Admin Button
            createAdminLoginButton();
            rightPanel.add(adminLoginButton);
            loginPanel.add(leftPanel, BorderLayout.SOUTH);
            mainContainer.add(loginPanel, BorderLayout.NORTH);

                    
            // Cancel button
            createCancelButton();
            rightPanel.add(cancelButton);
            mainContainer.add(rightPanel, BorderLayout.NORTH);
        }
        else {
            JPanel topPanel = new JPanel(new BorderLayout());
        
            JLabel userLabel = new JLabel("Welcome: " + username);

            userLabel.setFont(new Font("Arial", Font.BOLD, 20)); 

            leftPanel.add(userLabel);
            
        
            topPanel.add(leftPanel, BorderLayout.WEST);
        
            createLogoutButton();
            rightPanel.add(loginButton);  
        
            createCancelButton();
            rightPanel.add(cancelButton);
        
            rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            topPanel.add(rightPanel, BorderLayout.EAST);
        
            mainContainer.add(topPanel, BorderLayout.NORTH);
        }
        



                
        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        mainContainer.add(panel, BorderLayout.CENTER);

        // Panel for trip type and number of guests
        JPanel tripAndGuestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tripTypeComboBox = new JComboBox<>(new String[] { "One Way", "Return" });
        tripAndGuestPanel.add(new JLabel("Trip Type:"));
        tripAndGuestPanel.add(tripTypeComboBox);
        guestNumberSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        tripAndGuestPanel.add(new JLabel("Number of Guests:"));
        tripAndGuestPanel.add(guestNumberSpinner);
        panel.add(tripAndGuestPanel);

        // Panel for origin information
        JPanel originPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        originCountryTextArea = new JTextArea(1, 20);
        originProvinceTextArea = new JTextArea(1, 20);
        originCityTextArea = new JTextArea(1, 20);
        originPanel.add(new JLabel("Origin Country:"));
        originPanel.add(originCountryTextArea);
        originPanel.add(new JLabel("Origin Province:"));
        originPanel.add(originProvinceTextArea);
        originPanel.add(new JLabel("Origin City:"));
        originPanel.add(originCityTextArea);
        panel.add(originPanel);

        // Panel for destination information
        JPanel destinationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        destinationCountryTextArea = new JTextArea(1, 20);
        destinationProvinceTextArea = new JTextArea(1, 20);
        destinationCityTextArea = new JTextArea(1, 20);
        destinationPanel.add(new JLabel("Destination Country:"));
        destinationPanel.add(destinationCountryTextArea);
        destinationPanel.add(new JLabel("Destination Province:"));
        destinationPanel.add(destinationProvinceTextArea);
        destinationPanel.add(new JLabel("Destination City:"));
        destinationPanel.add(destinationCityTextArea);
        panel.add(destinationPanel);

        // Panel for date selection
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date initDate = calendar.getTime();
        Date earliestDate = new Date(Long.MIN_VALUE);
        Date latestDate = new Date(Long.MAX_VALUE);

        // Date selectors
        SpinnerDateModel departureDateModel = new SpinnerDateModel(initDate, earliestDate, latestDate,
                Calendar.DAY_OF_MONTH);
        departureDateField = new JSpinner(departureDateModel);
        JSpinner.DateEditor departureDateEditor = new JSpinner.DateEditor(departureDateField, "dd/MM/yyyy");
        departureDateField.setEditor(departureDateEditor);
        SpinnerDateModel returnDateModel = new SpinnerDateModel(initDate, earliestDate, latestDate,
                Calendar.DAY_OF_MONTH);
        returnDateField = new JSpinner(returnDateModel);
        JSpinner.DateEditor returnDateEditor = new JSpinner.DateEditor(returnDateField, "dd/MM/yyyy");
        returnDateField.setEditor(returnDateEditor);
        returnDateField.setVisible(false);
        returnDateLabel = new JLabel("Return Date:");
        returnDateLabel.setVisible(false);
        datePanel.add(new JLabel("Departure Date:"));
        datePanel.add(departureDateField);
        datePanel.add(returnDateLabel);
        datePanel.add(returnDateField);
        panel.add(datePanel);

        // Dynamically display return date if return trip selected
        tripTypeComboBox.addActionListener(e -> {
            boolean isReturn = "Return".equals(tripTypeComboBox.getSelectedItem());
            returnDateField.setVisible(isReturn);
            returnDateLabel.setVisible(isReturn);
        });

        // Search button setup
        buttonSearch = new JButton("Search Flights");
        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFlights();
            }
        });
        panel.add(buttonSearch);
        if (identity == "FA"){
            createBrowsePassengerPanel(panel);
            panel.add(browsePassengerButton);
        }
    }

    // TODO: Implement actual flight search
    private void searchFlights() {
        // Collecting values inputted by user
        String originCountry = originCountryTextArea.getText();
        String originProvince = originProvinceTextArea.getText();
        String originCity = originCityTextArea.getText();
        String destinationCountry = destinationCountryTextArea.getText();
        String destinationProvince = destinationProvinceTextArea.getText();
        String destinationCity = destinationCityTextArea.getText();
        String tripType = (String) tripTypeComboBox.getSelectedItem();
        int numberOfGuests = (Integer) guestNumberSpinner.getValue();
        Date departureDate = (Date) departureDateField.getValue();
        Date returnDate = (Date) returnDateField.getValue();
        Location origin = new Location(originCountry, originProvince, originCity);
        Location destination = new Location(destinationCountry, destinationProvince, destinationCity);
        TimeDate departure = new TimeDate(departureDate);

        ArrayList<Flights> flights = User.BrowseFlights(departure, origin, destination);

        //display the flights in same window as table
        // Create column names
        String[] columnNames = {"Flight Number", "Origin", "Destination", "Departure Time", "Arrival Time"};

        // Create a table model
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Add a row for each flight
        for (Flights flight : flights) {
            Object[] row = new Object[columnNames.length];
            row[0] = flight.getFlightNum();
            row[1] = flight.getDepartureLocation().toString();
            row[2] = flight.getArrivalLocation().toString();
            row[3] = flight.getDepartureDate().toString();
            row[4] = flight.getArrivalDate().toString();
            model.addRow(row);
        }
        JFrame frame = new JFrame("Flight Search Results");
        //add a input box on top of the table
        JTextField input = new JTextField(20);
        JPanel panel = new JPanel();
        panel.add(input);
        input.setBounds(0, 0, 100, 50);
        JButton button = new JButton("Select");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get the value from the input box when user press enter
                String flightNum = input.getText();
                //get the flight object from the flight number
                Flights flight=null;
                for (int i = 0; i < flights.size(); i++){
                    if (flights.get(i).getFlightNum().equals(flightNum)){
                        flight = flights.get(i);
                        break;
                    }
                }
                if (flight == null){
                    //display error message
                    return;
                }
                //fix when go to database
                User.SelectFlight(flight);
                //get the seat map from the flight
                String seatMap = User.BrowseSeat(flight);

                //clear the frame
                frame.getContentPane().removeAll();
                //add in input box for seat selection
                
                JTextField inputSeat = new JTextField(20);
                JPanel panel = new JPanel();
                panel.add(inputSeat);
                input.setBounds(0, 0, 100, 50);
                frame.add(panel, BorderLayout.NORTH);

                //add in the seat map
                JTextArea seatMapArea = new JTextArea(seatMap);
                seatMapArea.setEditable(false);
                frame.add(seatMapArea, BorderLayout.CENTER);

            }
        });
        panel.add(button);
        frame.add(panel, BorderLayout.NORTH);

        JTable table = new JTable(model);

        // Center first column
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the frame
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // Temporarily displaying gathered info in terminal for testing
        System.out.println("Origin: " + originCity + ", " + originProvince + ", " + originCountry);
        System.out.println("Destination: " + destinationCity + ", " + destinationProvince + ", " + destinationCountry);
        System.out.println("Trip Type: " + tripType);
        System.out.println("Number of Guests: " + numberOfGuests);
        System.out.println("Departure Date: " + new SimpleDateFormat("dd/MM/yyyy").format(departureDate));
        if ("Return".equals(tripType) && returnDate != null) {
            System.out.println("Return Date: " + new SimpleDateFormat("dd/MM/yyyy").format(returnDate));
        }

        // Placeholder for actual flight search functionality
        JOptionPane.showMessageDialog(this, "Searching flights...");
    }

    private void searchPassenger() {
        // Collecting values inputted by user
        String flightNum = FlightNumArea.getText();
        //fix when go to database
        ArrayList<Passenger> passengers = CrewMember.BrowsePassengers(flightNum);
        JFrame frame = new JFrame("Passenger Search Results");

        JButton button = new JButton("Go Back");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //close only the current window
                frame.dispose();
            }
        });
        frame.add(button, BorderLayout.SOUTH);
        //display the passengers in same window as table
        // Create column names
        String[] columnNames = {"Name", "Passport Number", "Country", "Expiry Date", "address", "Email", "Phone Number"};
        // Create a table model
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        // Add a row for each passenger
        for (Passenger p:passengers){
            Object[] row = new Object[7];
            row[0] = p.getName().toString();
            row[1] = p.getPassport().getPassportNumber();
            row[2] = p.getPassport().getCountry();
            row[3] = p.getPassport().getExpiryDate().toString();
            row[4] = p.getAddress().toString();
            row[5] = p.getEmail();
            row[6] = p.getPhoneNumber().toString();
            model.addRow(row);
        }
        JTable table = new JTable(model);
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        // Add the scroll pane to the frame
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
}
