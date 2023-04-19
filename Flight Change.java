//Alex Kevorkian
import java.util.Scanner;

public class FlightChange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String currentFlightNumber, newFlightNumber, departureDate;

        // Get the current flight number from the user
        System.out.print("Enter your current flight number: ");
        currentFlightNumber = scanner.nextLine();

        // Get the new flight number from the user
        System.out.print("Enter your new flight number: ");
        newFlightNumber = scanner.nextLine();

        // Get the departure date of the new flight from the user
        System.out.print("Enter the departure date of your new flight (YYYY-MM-DD): ");
        departureDate = scanner.nextLine();
    
    customerFlight.setFlightNumber(newFlightNumber);
    customerFlight.setDepartureDate(departureDate);
}
        Flight customerFlight = getCustomerFlight(currentFlightNumber);
        customerFlight.setFlightNumber(newFlightNumber);
        customerFlight.setDepartureDate(departureDate);
        saveCustomerFlight(customerFlight);

        System.out.println("Your flight has been changed to " + newFlightNumber + " on " + departureDate);
    }

    // A method to get the Flight object for the customer's current flight from the database
    private static Flight getCustomerFlight(String currentFlightNumber) {
   FlightDAO flightDAO = new FlightDAO();
    return flightDAO.getFlightByNumber(currentFlightNumber);
}
        // For example, you might use a FlightDAO class with a getFlightByNumber() method
        FlightDAO flightDAO = new FlightDAO();
        return flightDAO.getFlightByNumber(currentFlightNumber);
    }

    // A method to save the updated Flight object to the database
    private static void saveCustomerFlight(Flight customerFlight) {
        
        FlightDAO flightDAO = new FlightDAO();
        flightDAO.saveFlight(customerFlight);
    }
}
