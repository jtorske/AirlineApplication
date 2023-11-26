package Domains.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GUI extends JFrame {
    //Component declarations
    private JTextArea originCountryTextArea, destinationCountryTextArea, originProvinceTextArea, destinationProvinceTextArea, originCityTextArea, destinationCityTextArea;

    private JComboBox<String> tripTypeComboBox;

    private JSpinner guestNumberSpinner, departureDateField, returnDateField;

    private JButton buttonSearch;
    private JLabel returnDateLabel;
    
    private JButton loginButton;
    private JButton signUpButton;


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

    // Creates and adds components to the frame
    private void createView() {
        // Main container with BorderLayout
        JPanel mainContainer = new JPanel(new BorderLayout());
        getContentPane().add(mainContainer);

        // Login button
        createLoginButton();
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(loginButton);
        mainContainer.add(topPanel, BorderLayout.NORTH);

        // Sign Up button
        createSignUpButton();
        topPanel.add(signUpButton);
        topPanel.add(loginButton);
        mainContainer.add(topPanel, BorderLayout.NORTH);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
}
