package Domains;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Domains.Flights.*;
import Domains.User.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GUI extends JFrame {
    //Component declarations
    private JTextArea originCountryTextArea, destinationCountryTextArea, originProvinceTextArea, destinationProvinceTextArea, originCityTextArea, destinationCityTextArea;

    private JComboBox<String> tripTypeComboBox;

    private JSpinner guestNumberSpinner, departureDateField, returnDateField;

    private JButton buttonSearch;
    private JLabel returnDateLabel;

    public GUI() {
        createView();
        setupFrame();
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
    
    // Creates and adds components to the frame
    private void createView() {
        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        getContentPane().add(panel);

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

        Location origin = new Location(originCountry, originProvince, originCity);
        Location destination = new Location(destinationCountry, destinationProvince, destinationCity);
        TimeDate departure = new TimeDate(departureDate);

        ArrayList<Flights> flights = User.BrowseFlights(departure, origin, destination);

        //display the flights in same window as table
        // Create column names
        String[] columnNames = {"Flight Number", "Departure Time", "Arrival Time"};

        // Create a table model
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Add a row for each flight
        for (Flights flight : flights) {
            Object[] row = new Object[3];
            row[0] = flight.getFlightNum();
            row[1] = flight.getDepartureDate().toString();
            row[2] = flight.getArrivalDate().toString();
            model.addRow(row);
        }
        JFrame frame = new JFrame("Flight Search Results");
        //add a input box on top of the table
        JTextField input = new JTextField(20);
        JPanel panel = new JPanel();
        panel.add(input);
        input.setBounds(0, 0, 100, 50);
        frame.add(panel, BorderLayout.NORTH);

        JTable table = new JTable(model);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the frame
        frame.add(scrollPane, BorderLayout.CENTER);

        // Set frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //get the value from the input box when user press enter
        String flightNum = input.getText();
        //get the flight object from the flight number
        Flights flight;
        for (int i = 0; i < flights.size(); i++){
            if (flights.get(i).getFlightNum().equals(flightNum)){
                flight = flights.get(i);
                break;
            }
        }
        //fix when go to database
        User.SelectFlight(flight);
        //get the seat map from the flight
        String seatMap = User.BrowseSeat(flight);

        //clear the frame
        frame.getContentPane().removeAll();
        //add in input box for seat selection
        
        JTextField inputSeat = new JTextField(20);
        panel = new JPanel();
        panel.add(input);
        input.setBounds(0, 0, 100, 50);
        frame.add(panel, BorderLayout.NORTH);

        //add in the seat map
        JTextArea seatMapArea = new JTextArea(seatMap);




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
