package Front.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageTickets extends JFrame {
    private JTextField ticketIdField;
    private JTextArea resultTextArea;
    private JButton searchButton;

    public ManageTickets() {
        setTitle("Manage Tickets");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); 
        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Ticket ID:"));
        ticketIdField = new JTextField(20);
        inputPanel.add(ticketIdField);
        add(inputPanel, BorderLayout.NORTH);

        searchButton = new JButton("Select");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
            }
        });
        inputPanel.add(searchButton);

        // Result text area
        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        add(scrollPane, BorderLayout.CENTER);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ManageTickets().setVisible(true);
            }
        });
    }
}
