import java.util.Scanner;

public class ReserveFlight
{
	
	int flightSelectionValue = 0;
    
	void reserveFlight() {

	// prompt for the sequence size
	System.out.printf("Which flight would you like to reserve?");
	
	// Fetch and display the available flights to pick from
	fetchAvailableFlights();
	
	// Allow the user to select which flight they want to reserve
	Scanner reservationSelected = new Scanner(System.in);

	flightSelectionValue =  reservationSelected.nextInt();

	// Update the database to include this customer as an attendant
	updateDatabase(flightSelectionValue, "testUser");

	return;
	}

    void fetchAvailableFlights() {
        // TODO: Fetch available flights from the directory and display them to the customer
        
        // This method makes a call to the database (text file) and prints out the flight details to the user.
        // They will be shown the departure location, destination, departure time, arrival time, cost, and available seats
        
    }
    
     void updateDatabase(int flightSelection, String customerName) {
        // TODO: Decrement the number of available seats by 1 to account for the customer that reserved the flight
        
        // This method will accept the flight selection, and name of customer
        
        // The output of this method will be the updated database that accounts for the new attendant.
        
    }
	
}
