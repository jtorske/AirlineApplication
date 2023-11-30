package Front.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import Domains.Flights.Aircraft;
import Domains.Flights.Flights;
import Domains.Flights.TimeDate;
import Domains.Passenger.Passenger;
import Domains.Seats.Seat;
import Domains.Tickets.Insurance;
import Domains.Tickets.Ticket;
import Domains.User.*;
import Front.GUI.LoginCallback;
import Domains.Flights.Location;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class GUI extends JFrame implements LoginCallback{
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

    //Admin specific components
    private JButton browseAircraftButton;
    private JButton browseCrewsButton;
    private JButton browseFlightButton;
    private JButton manageFlightButton;
    private JButton manageCrewsButton;
    private JButton manageAircraftButton;

    static private String username=null;
    static private String identity=null;
    static private User user=null;

    @Override
    public void onLoginSuccess() {
        this.dispose(); 
    }
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
            loginWindow.setLoginCallback(this); // Set the callback
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
    private void createLogoutButton(GUI gui) {
        loginButton = new JButton("Logout");
        loginButton.setPreferredSize(new Dimension(80, 30)); 
        loginButton.setFont(new Font("Arial", Font.PLAIN, 10)); 
        loginButton.addActionListener(e -> {
            setUsername(null);
            gui.dispose();
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
        BrowsePassengerPanel.add(FlightNumArea);

        panel.add(BrowsePassengerPanel);
        browsePassengerButton = new JButton("Browse Passenger");
        browsePassengerButton.setPreferredSize(new Dimension(80, 30));
        browsePassengerButton.setFont(new Font("Arial", Font.PLAIN, 10));
        browsePassengerButton.addActionListener(e -> {
           searchPassenger(); 
        });
    }

    private void createManageTicketsButton() {
        JButton manageTicketsButton = new JButton("Manage Tickets");
        manageTicketsButton.setPreferredSize(new Dimension(160, 30));
        manageTicketsButton.setFont(new Font("Arial", Font.PLAIN, 10));
        manageTicketsButton.addActionListener(e -> {
            ManageTickets manageTicketsWindow = new ManageTickets(username);
            manageTicketsWindow.setVisible(true);
        });
    
        JPanel bottomLeftPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomLeftPanel.add(manageTicketsButton);
    
        getContentPane().add(bottomLeftPanel, BorderLayout.SOUTH);
    }
    
    
    private void createAdminView(JPanel panel){
        //Set up panels
        JPanel crewPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        browseCrewsButton = new JButton("Browse Crews");
        manageCrewsButton = new JButton("Manage Crews");
        crewPanel.add(browseCrewsButton);
        crewPanel.add(manageCrewsButton);

        JPanel flightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        browseFlightButton = new JButton("Browse Flights");
        manageFlightButton = new JButton("Manage Flights");
        flightPanel.add(browseFlightButton);
        flightPanel.add(manageFlightButton);

        JPanel aircraftPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        browseAircraftButton = new JButton("Browse Aircrafts");
        manageAircraftButton = new JButton("Manage Aircrafts");
        aircraftPanel.add(browseAircraftButton);
        aircraftPanel.add(manageAircraftButton);

        //TODO:
        //Implement buttons
        browseCrewsButton.addActionListener(e -> {
            browseCrews();
        });
        manageCrewsButton.addActionListener(e -> {
            manageCrews();
        });
        browseAircraftButton.addActionListener(e -> {
            browseAircrafts();
        });
        manageAircraftButton.addActionListener(e -> {
            //manageAircrafts();
        });
        browseFlightButton.addActionListener(e -> {
            browseFlights();
        });
        manageFlightButton.addActionListener(e -> {
            //manageFlights();
        });

        panel.add(crewPanel);
        panel.add(flightPanel);
        panel.add(aircraftPanel);
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
        
            createLogoutButton(this);
            rightPanel.add(loginButton);  
        
            createCancelButton();
            rightPanel.add(cancelButton);
        
            rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            topPanel.add(rightPanel, BorderLayout.EAST);
        
            mainContainer.add(topPanel, BorderLayout.NORTH);

            createManageTicketsButton();

        }

        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        mainContainer.add(panel, BorderLayout.CENTER);

        if(identity == "Admin"){
            createAdminView(panel);
        }
        else{
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
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //add a input box on top of the table
        
        JTextField input = new JTextField(20);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Please Enter the Flight Number:"));
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
                System.out.println(flightNum);
                for (int i = 0; i < flights.size(); i++){
                    if (flights.get(i).getFlightNum().equals(flightNum)){
                        flight = flights.get(i);
                        break;
                    }
                }
                if (flight == null){
                    System.out.println("Flight Not Found");
                    //pop up a window to display error message
                    //create a new window to show error message
                    JFrame frame = new JFrame("Error");
                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                    frame.add(panel, BorderLayout.CENTER);
                    panel.add(new JLabel("Error: Flight Not Found"));
                    JButton button = new JButton("Go Back");
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            frame.dispose();
                        }
                    });
                    panel.add(button);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                    return;}//display error message
                //fix when go to database
                User.SelectFlight(flight);
                //get the seat map from the flight
                String seatMap = User.BrowseSeat(flight);
                System.out.println(seatMap);
                //clear the frame
                frame.getContentPane().removeAll();
                //add in input box for seat selection
                System.out.println(flights.size());

                JTextField inputSeat = new JTextField(20);
                JPanel panel = new JPanel();
                panel.add(new JLabel("Please Enter the Seat Number:"));
                panel.add(inputSeat);
                input.setBounds(0, 0, 100, 50);
                frame.add(panel, BorderLayout.NORTH);
                JButton button = new JButton("Select");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SelectSeat(inputSeat.getText());
                    }
                });
                panel.add(button);
                //add in the seat map
                JTextArea seatMapArea = new JTextArea(seatMap);
                seatMapArea.setEditable(false);
                frame.add(seatMapArea, BorderLayout.CENTER);
                frame.revalidate();

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

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
    }

    private void SelectSeat(String seatNum) {
        //get the seat object from the seat number
        if (User.SelectSeat(seatNum)!=0){
            System.out.println("Seat Not Available");
            //pop up a window to display error message
            //create a new window to show error message
            JFrame frame = new JFrame("Error");
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            frame.add(panel, BorderLayout.CENTER);
            panel.add(new JLabel("Error: Seat Not Available"));
            JButton button = new JButton("Go Back");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });
            panel.add(button);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            return;
        }//display error message};
        System.out.println(User.GetSeat().Display());

        //create a new window for asking passenger information
        JFrame frame = new JFrame("Passenger Information");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel, BorderLayout.CENTER);
        //create a text field for each information, need to add in the passenger class
        //Name panel in horizontal box layout
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        JTextField firstNameField = new JTextField(20);
        JTextField middleNameField = new JTextField(20);
        JTextField lastNameField = new JTextField(20);
        namePanel.add(new JLabel("First Name:"));
        namePanel.add(firstNameField);
        namePanel.add(new JLabel("Middle Name:"));
        namePanel.add(middleNameField);
        namePanel.add(new JLabel("Last Name:"));
        namePanel.add(lastNameField);
        panel.add(namePanel);
        //Passport panel in horizontal box layout
        JPanel passportPanel = new JPanel();
        passportPanel.setLayout(new BoxLayout(passportPanel, BoxLayout.X_AXIS));
        JTextField passportNumberField = new JTextField(20);
        JTextField countryField = new JTextField(20);
        //int year, int month, int day
        JTextField expiryYearField = new JTextField(4);
        expiryYearField.setText("yyyy");
        expiryYearField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (expiryYearField.getText().equals("yyyy")) {
                    expiryYearField.setText("");
                }
            }
        });
        JTextField expiryMonthField = new JTextField(2);
        expiryMonthField.setText("mm");
        expiryMonthField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (expiryMonthField.getText().equals("mm")) {
                    expiryMonthField.setText("");
                }
            }
        });
        JTextField expiryDayField = new JTextField(2);
        expiryDayField.setText("dd");
        expiryDayField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (expiryDayField.getText().equals("dd")) {
                    expiryDayField.setText("");
                }
            }
        });
        JTextField issueYearField = new JTextField(4);
        issueYearField.setText("yyyy");
        issueYearField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (issueYearField.getText().equals("yyyy")) {
                    issueYearField.setText("");
                }
            }
        });
        JTextField issueMonthField = new JTextField(2);
        issueMonthField.setText("mm");
        issueMonthField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (issueMonthField.getText().equals("mm")) {
                    issueMonthField.setText("");
                }
            }
        });
        JTextField issueDayField = new JTextField(2);
        issueDayField.setText("dd");
        issueDayField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (issueDayField.getText().equals("dd")) {
                    issueDayField.setText("");
                }
            }
        });
        passportPanel.add(new JLabel("Passport Number:"));
        passportPanel.add(passportNumberField);
        passportPanel.add(new JLabel("Country:"));
        passportPanel.add(countryField);
        passportPanel.add(new JLabel("Expiry Date:"));
        passportPanel.add(expiryYearField);
        passportPanel.add(expiryMonthField);
        passportPanel.add(expiryDayField);
        passportPanel.add(new JLabel("Issue Date:"));
        passportPanel.add(issueYearField);
        passportPanel.add(issueMonthField);
        passportPanel.add(issueDayField);
        panel.add(passportPanel);
        //Address panel in horizontal box layout
        JPanel addressPanel = new JPanel();
        addressPanel.setLayout(new BoxLayout(addressPanel, BoxLayout.X_AXIS));
        JTextField streetNumberField = new JTextField(20);
        JTextField streetNameField = new JTextField(20);
        JTextField cityField = new JTextField(20);
        JTextField provinceField = new JTextField(20);
        JTextField countryAddressField = new JTextField(20);
        JTextField postalCodeField = new JTextField(20);
        addressPanel.add(new JLabel("Street Number:"));
        addressPanel.add(streetNumberField);
        addressPanel.add(new JLabel("Street Name:"));
        addressPanel.add(streetNameField);
        addressPanel.add(new JLabel("City:"));
        addressPanel.add(cityField);
        addressPanel.add(new JLabel("Province:"));
        addressPanel.add(provinceField);
        addressPanel.add(new JLabel("Country:"));
        addressPanel.add(countryAddressField);
        addressPanel.add(new JLabel("Postal Code:"));
        addressPanel.add(postalCodeField);
        panel.add(addressPanel);
        //Contact panel in horizontal box layout
        JPanel contactPanel = new JPanel();
        contactPanel.setLayout(new BoxLayout(contactPanel, BoxLayout.X_AXIS));
        JTextField emailField = new JTextField(20);
        JTextField countryCodeField = new JTextField(20);
        JTextField areaCodeField = new JTextField(20);
        JTextField phoneNumberField = new JTextField(20);
        contactPanel.add(new JLabel("Email:"));
        contactPanel.add(emailField);
        contactPanel.add(new JLabel("Phone Country Code:"));
        contactPanel.add(countryCodeField);
        contactPanel.add(new JLabel("Phone Area Code:"));
        contactPanel.add(areaCodeField);
        contactPanel.add(new JLabel("Phone Number:"));
        contactPanel.add(phoneNumberField);
        panel.add(contactPanel);
        //Insurance panel in horizontal box layout
        //ony 3 types, 1. basic 2. premium 3. premium plus
        JPanel insurancePanel = new JPanel();
        insurancePanel.setLayout(new BoxLayout(insurancePanel, BoxLayout.Y_AXIS));
        JTextField insuranceTypeField = new JTextField(1);
        insurancePanel.add(new JLabel("Insurance Type:\n 2. Premium\n 3. Premium Plus"));
        insurancePanel.add(insuranceTypeField);
        panel.add(insurancePanel);

        //create a button for submit
        JButton button = new JButton("Submit");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get the value from the input box when user press enter
                String firstName = firstNameField.getText();
                String middleName = middleNameField.getText();
                String lastName = lastNameField.getText();
                String passportNumber = passportNumberField.getText();
                String country = countryField.getText();
                int expireYear = Integer.parseInt(expiryYearField.getText());
                int expiryMonth = Integer.parseInt(expiryMonthField.getText());
                int expiryDay = Integer.parseInt(expiryDayField.getText());
                int issueYear = Integer.parseInt(issueYearField.getText());
                int issueMonth = Integer.parseInt(issueMonthField.getText());
                int issueDay = Integer.parseInt(issueDayField.getText());

                Calendar expiryCalendar = Calendar.getInstance();
                expiryCalendar.set(expireYear, expiryMonth - 1, expiryDay);
                Date expiryDate = expiryCalendar.getTime();

                Calendar issueCalendar = Calendar.getInstance();
                issueCalendar.set(issueYear, issueMonth - 1, issueDay);
                Date issueDate = issueCalendar.getTime();

                Date today = new Date();
                if (expiryDate.before(today) || issueDate.after(today) || expiryDate.before(issueDate)) {
                    System.out.println("Passport Date Invalid");
                    return;
                }

                String streetNumber = streetNumberField.getText();
                String streetName = streetNameField.getText();
                String city = cityField.getText();
                String province = provinceField.getText();
                String countryAddress = countryAddressField.getText();
                String postalCode = postalCodeField.getText();
                String email = emailField.getText();
                //check if the email is valid
                if (!email.contains("@")){
                    System.out.println("Invalid Email");
                    return;
                }
                int countryCode = Integer.parseInt(countryCodeField.getText());
                int areaCode = Integer.parseInt(areaCodeField.getText());
                int phoneNumber = Integer.parseInt(phoneNumberField.getText());
                String insuranceType = insuranceTypeField.getText();
                //clean the window and display the ticket
                
                JFrame frame = new JFrame("Ticket Preview");
                //remover the submit button
                frame.getContentPane().removeAll();
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                frame.add(panel, BorderLayout.CENTER);
                //create a text field for each information, need to add in the passenger class
                //Name panel in horizontal box layout
                JPanel namePanel = new JPanel();
                namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
                namePanel.add(new JLabel("Name:"));
                namePanel.add(new JLabel(firstName + " " + middleName + " " + lastName));
                panel.add(namePanel);
                //Passport panel in horizontal box layout
                JPanel passportPanel = new JPanel();
                passportPanel.setLayout(new BoxLayout(passportPanel, BoxLayout.X_AXIS));
                passportPanel.add(new JLabel("Passport Number:"));
                passportPanel.add(new JLabel(passportNumber));
                panel.add(passportPanel);
                //flight info, seat panel in vertical box layout
                JPanel flightPanel = new JPanel();
                flightPanel.setLayout(new BoxLayout(flightPanel, BoxLayout.Y_AXIS));
                flightPanel.add(new JLabel("Flight Number:"));
                flightPanel.add(new JLabel(User.GetFlight().getFlightNum()));
                flightPanel.add(new JLabel("Departure Time:"));
                flightPanel.add(new JLabel(User.GetFlight().getDepartureDate().toString()));
                flightPanel.add(new JLabel("Seat Type:"));
                String seatType = "";
                if (User.GetSeat().GetPrice()==50) {seatType = "Ordinary";}        
                else if (User.GetSeat().GetPrice()==70) {seatType = "Comfort";}
                else {seatType = "Business";}
                flightPanel.add(new JLabel(seatType));
                flightPanel.add(new JLabel("Seat Number:"));
                flightPanel.add(new JLabel(User.GetSeat().Display()));
                flightPanel.add(new JLabel("Price:"));
                flightPanel.add(new JLabel(Double.toString(User.GetSeat().GetPrice())));
                flightPanel.add(new JLabel("Insurance Type:"));
                flightPanel.add(new JLabel(insuranceType));
                panel.add(flightPanel);
                //contect panel in horizontal box layout
                JPanel contactPanel = new JPanel();
                contactPanel.setLayout(new BoxLayout(contactPanel, BoxLayout.Y_AXIS));
                contactPanel.add(new JLabel("Email:"));
                contactPanel.add(new JLabel(email));
                contactPanel.add(new JLabel("Phone Number:"));
                contactPanel.add(new JLabel(Integer.toString(countryCode) + Integer.toString(areaCode) + Integer.toString(phoneNumber)));
                contactPanel.add(button);
                panel.add(contactPanel);

                //Ask for Credit card info
                JPanel creditCardPanel = new JPanel();
                creditCardPanel.setLayout(new BoxLayout(creditCardPanel, BoxLayout.X_AXIS));
                JTextField cardNumberField = new JTextField(20);
                creditCardPanel.add(new JLabel("Credit Card Number:"));
                creditCardPanel.add(cardNumberField);
                panel.add(creditCardPanel);
                JPanel expiryPanel = new JPanel();
                expiryPanel.setLayout(new BoxLayout(expiryPanel, BoxLayout.X_AXIS));
                JTextField expiryYearField = new JTextField(4);
                JTextField expiryMonthField = new JTextField(2);
                JTextField cvvField = new JTextField(3);
                expiryPanel.add(new JLabel("Expiry Date:"));
                expiryPanel.add(expiryYearField);
                expiryPanel.add(expiryMonthField);
                expiryPanel.add(new JLabel("CVV:"));
                expiryPanel.add(cvvField);
                panel.add(expiryPanel);

                //create a button for confirm
                JButton button = new JButton("Confirm");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Passenger p=new Passenger(firstName, middleName, lastName, 
                            passportNumber, country, expireYear, expiryMonth, expiryDay, issueYear, issueMonth, issueDay, 
                            streetNumber, streetName, city, province, countryAddress, postalCode, 
                            email, countryCode, areaCode, phoneNumber);
                        Insurance i=new Insurance(insuranceType);

                        BuyTicket(p,i,cardNumberField.getText());
                    }
                });
                panel.add(button);
                //display the ticket
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.repaint();
                frame.setVisible(true);
            }
        });
        panel.add(button);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    private void BuyTicket(Passenger p, Insurance i, String cardString){
        try{
            System.out.println(User.GetSeat().GetPrice());
            User.BuyTicket(p,cardString,i);
        }
        catch(Exception e){
            System.out.println(e);
            //display error message
            //create a new window to show error message
            JFrame frame = new JFrame("Error");
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            frame.add(panel, BorderLayout.CENTER);
            panel.add(new JLabel("Error: " + e));
            JButton button = new JButton("Go Back");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //close only the current window
                    frame.dispose();
                }
            });
            panel.add(button);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            return;
        }
        //display "email sent"
        //create a new window 
        JFrame frame = new JFrame("Email Sent");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel, BorderLayout.CENTER);
        panel.add(new JLabel("Email Sent"));
        JButton button = new JButton("Go Back");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //close only the current window
                frame.dispose();
            }
        });
        panel.add(button);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    };

    private void searchPassenger() {
        // Collecting values inputted by user
        String flightNum = FlightNumArea.getText().toString();

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
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void browseCrews(){
        SystemAdmin admin = new SystemAdmin(username);
        ArrayList<CrewMember> crewList = admin.getCrewList();
        JFrame frame = new JFrame("Current Crew Members");

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
        String[] columnNames = {"Name", "Role", "ID", "FlightsWorking"};
        // Create a table model
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        // Add a row for each passenger
        for (CrewMember c : crewList){
            Object[] row = new Object[4];
            row[0] = c.getName().toString();
            row[1] = c.getRole();
            row[2] = c.getId();
            row[3] = c.getFlightsWorking();
            model.addRow(row);
        }
        JTable table = new JTable(model);
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        // Add the scroll pane to the frame
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void browseFlights(){
        SystemAdmin admin = new SystemAdmin(username);
        ArrayList<Flights> flightList = admin.getFlightList();
        JFrame frame = new JFrame("Current List of Flights");

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
        String[] columnNames = {"Flight Number", "Aircraft ID", "Departure Location", "Arrival Location", "Departure Date", "Arrival Date"};
        // Create a table model
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        // Add a row for each passenger
        for (Flights f : flightList){
            Object[] row = new Object[6];
            row[0] = f.getFlightNum();
            row[1] = f.getAircraft().getId();
            row[2] = f.getDepartureLocation().toString();
            row[3] = f.getArrivalLocation().toString();
            row[4] = f.getDepartureDate().toString();
            row[5] = f.getArrivalDate().toString();
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

    private void browseAircrafts(){
        SystemAdmin admin = new SystemAdmin(username);
        ArrayList<Aircraft> acList = admin.getAircraftList();
        JFrame frame = new JFrame("Current List of Flights");

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
        String[] columnNames = {"Aircraft ID", "Manufacturer", "Model", "Seat Capacity", "Type"};
        // Create a table model
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        // Add a row for each passenger
        for (Aircraft a : acList){
            Object[] row = new Object[5];
            row[0] = a.getId();
            row[1] = a.getCompany();
            row[2] = a.getModel();
            row[3] = a.getCapacity();
            row[4] = a.getType() == 1 ? "Narrow Body" : "Wide Body";
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

    private void manageCrews(){
        SystemAdmin admin = new SystemAdmin(username);
        ArrayList<CrewMember> crewList = admin.getCrewList();
        JFrame frame = new JFrame("Current Crew Members");

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
        String[] columnNames = {"Name", "Role", "ID", "FlightsWorking"};
        // Create a table model
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        // Add a row for each passenger
        for (CrewMember c : crewList){
            Object[] row = new Object[4];
            row[0] = c.getName().toString();
            row[1] = c.getRole();
            row[2] = c.getId();
            row[3] = c.getFlightsWorking();
            model.addRow(row);
        }
        JTable table = new JTable(model);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        //Add a button to remove selected entries
        JButton rm = new JButton("Remove Selected Row");
        rm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // check for selected row first
                if (table.getSelectedRow() != -1) {
                    // remove selected row from the model
                    admin.removeCrewMember(table.getValueAt(table.getSelectedRow(), 2).toString());
                    model.removeRow(table.getSelectedRow());
                }
            }
        });

        JPanel modPanel = new JPanel(new BorderLayout());
        modPanel.add(rm, BorderLayout.NORTH);

        //Panel and inputs for adding a new crew member
        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextArea nameArea = new JTextArea(1, 20);
        JTextArea roleArea = new JTextArea(1, 20);
        JTextArea usernameArea = new JTextArea(1, 20);
        JTextArea passwordArea = new JTextArea(1, 20);
        JButton addCrewButton = new JButton("Add");

        addCrewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n = nameArea.getText().toString();
                String r = roleArea.getText().toString();
                String u = usernameArea.getText().toString();
                String p = passwordArea.getText().toString();
                admin.addCrewMember(n, r, u, p);
            }
        });

        addPanel.add(new JLabel("Name:"));
        addPanel.add(nameArea);
        addPanel.add(new JLabel("Role:"));
        addPanel.add(roleArea);
        addPanel.add(new JLabel("Username:"));
        addPanel.add(usernameArea);
        addPanel.add(new JLabel("Password:"));
        addPanel.add(passwordArea);
        addPanel.add(addCrewButton);

        modPanel.add(new JLabel("Add a new user:"), BorderLayout.CENTER);
        modPanel.add(addPanel, BorderLayout.SOUTH);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        // Add the scroll pane to the frame
        frame.add(scrollPane, BorderLayout.NORTH);
        frame.add(modPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
