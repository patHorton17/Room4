// Patrick Horton

import java.util.Scanner;

public class CancelFlight
{
	
	int flightSelectionValue = 0;
    
	void cancelFlight() {

	// prompt for the user to select which flight they would like to cancel
	System.out.printf("Which flight would you like to cancel?");
	
	// Fetch and display the flight that the customer has reserved
	fetchReservedFlight();
	
	// Allow the user to confirm the flight they want to cancel
	Scanner reservationSelected = new Scanner(System.in);

	flightSelectionValue =  reservationSelected.nextLine();

	if (flightSelectionValue = "YES") {
		// Update the database to include this customer as an attendant
		updateDatabase(flightSelectionValue, "testUser");
	}
	else {
		// Take user back to main menu
		directToMainMenu()
	}

	return;
	}

    void fetchReservedFlight() {
        // TODO: Fetch the reserved flights from the directory and display them to the customer
        
        // This method makes a call to the database (text file) and prints out the reserved flights to the user.
        
    }
    
     void updateDatabase(int flightSelection, String customerName) {
        // TODO: Increment the number of available seats by 1 to account for the customer that cancelled the flight
        
        // This method will accept the flight selection, and name of customer
        
        // The output of this method will be the updated database that accounts for the removed attendant.
        
    }

    void (directToMainMenu) {
    	//TODO: Display the main UI to the user
    }
	
}
