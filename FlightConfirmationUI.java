//Josue Ambrosio
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FlightConfirmationUI extends JFrame implements ActionListener {

    private JTextField flightField;
    private JButton viewButton, cancelButton;

    public FlightConfirmationUI() {
        // Set up the UI components
        JLabel flightLabel = new JLabel("Flight number:");

        flightField = new JTextField(10);

        viewButton = new JButton("View Confirmation");
        viewButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(flightLabel);
        panel.add(flightField);
        panel.add(viewButton);
        panel.add(cancelButton);

        // Set up the frame
        setTitle("Flight Confirmation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewButton) {
            String flight = flightField.getText();

            // Code to view the confirmation number goes here

            JOptionPane.showMessageDialog(this, "Your confirmation number is: 12345");
        } else if (e.getSource() == cancelButton) {
            flightField.setText("");
        }
    }

    public static void main(String[] args) {
        new FlightConfirmationUI();
    }
}