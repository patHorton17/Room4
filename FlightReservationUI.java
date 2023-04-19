//Josue Ambrosio
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FlightReservationUI extends JFrame implements ActionListener {

    private JTextField nameField, flightField, dateField;
    private JButton reserveButton, cancelButton;

    public FlightReservationUI() {
        // Set up the UI components
        JLabel nameLabel = new JLabel("Name:");
        JLabel flightLabel = new JLabel("Flight number:");
        JLabel dateLabel = new JLabel("Date (MM/DD/YYYY):");

        nameField = new JTextField(20);
        flightField = new JTextField(10);
        dateField = new JTextField(10);

        reserveButton = new JButton("Reserve");
        reserveButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(flightLabel);
        panel.add(flightField);
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(reserveButton);
        panel.add(cancelButton);

        // Set up the frame
        setTitle("Flight Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reserveButton) {
            String name = nameField.getText();
            String flight = flightField.getText();
            String date = dateField.getText();

            // Code to reserve the flight goes here

            JOptionPane.showMessageDialog(this, "Reservation successful!");
        } else if (e.getSource() == cancelButton) {
            nameField.setText("");
            flightField.setText("");
            dateField.setText("");
        }
    }

    public static void main(String[] args) {
        new FlightReservationUI();
    }
}