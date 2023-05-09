import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;

public class ReviewFlight {
    int confirmationNumberToCheck;
    int flightNumberToCheck = 0;
    String firstNameConfirmation = "";
    String lastNameConfirmation = "";
    String nameToCheck = "";
    int indexToRetrieve;

    void reviewFlight() throws Exception {

        // Get user info
        getUserInfo();

        // Verify that the passenger is booked with the flight
        verifyReviewRequest(firstNameConfirmation, lastNameConfirmation, flightNumberToCheck, confirmationNumberToCheck);
    }
    public void verifyReviewRequest(String fn, String ln, int fln, int cn) {
        // First, check to see if the flight number is valid
        try {
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader("JSON File.json"));
            for (Object o : a) {
                // typecasting obj to JSONObject
                JSONObject jo = (JSONObject) o;

                // Get Flight Number
                long flightNumber = (long) jo.get("flightNumber");

                // First, check if flight number matches user's flight number
                if (flightNumber == fln) {
                    // Next, check if the confirmation number exists for that flight
                    JSONArray confirmationArray = (JSONArray) jo.get("confirmationNumbers");

                    for (int index = 0; index < confirmationArray.size(); index++) {

                        long currentConfirmationNumber = (long)confirmationArray.get(index);
                        if(currentConfirmationNumber == confirmationNumberToCheck){
                            // Finally, check the passenger name
                            nameToCheck = firstNameConfirmation + " " + lastNameConfirmation;
                            // Get passengers
                            JSONArray passengerNamesArray = (JSONArray) jo.get("passengerNames");
                            for (int i = 0; i < passengerNamesArray.size(); i++) {
                                String currentName = (String)passengerNamesArray.get(i);
                                if(currentName.equals(nameToCheck)){
                                    indexToRetrieve = i;
                                    // PRINT OUT THE FLIGHT INFORMATION
                                    System.out.println("Here is your flight information:\n");
                                    readJSON();
                                    return;
                                }
                            }
                            System.out.println("That name is not associated with this flight!\n");
                            return;
                        }
                    }
                    System.out.println("That confirmation number does not exist!\n");
                    return;
                }
            }
            System.out.println("That flight number does not exist!\n");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    public void getUserInfo(){
        Scanner scanner = new Scanner(System.in);

        // Ask the user for their first name
        System.out.println("Enter your first name: ");
        // Capture the user's input
        firstNameConfirmation =  scanner.next();

        // Ask the user for their last name
        System.out.println("Enter your last name: ");
        // Capture the user's input
        lastNameConfirmation =  scanner.next();

        // Ask the user for their flight number
        System.out.println("Enter your flight number: ");
        // Capture the user's input
        flightNumberToCheck =  scanner.nextInt();

        // Ask the user for their confirmation number
        System.out.println("Enter your confirmation number: ");
        // Capture the user's input
        confirmationNumberToCheck =  scanner.nextInt();
    }

    void readJSON() throws Exception {
        try {
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader("JSON File.json"));

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) a.get(flightNumberToCheck - 1);

            // Get Flight Number
            long flightNumber = (long) jo.get("flightNumber");
            System.out.println("Flight Number: " + flightNumber);

            // Get Airline
            String airline = (String) jo.get("airline");
            System.out.println("\tAirline: " + airline);

            // Get capacity
            long maxPassengers = (long) jo.get("maxPassengers");
            long currentPassengers = (long) jo.get("currentPassengers");
            System.out.println("\tCurrent flight Capacity: " + currentPassengers + " / " + maxPassengers);

            // Get departure date
            String departDate = (String) jo.get("departDate");
            System.out.println("\tDeparture Date: " + departDate);

            // Get departure time
            String departTime = (String) jo.get("departTime");
            System.out.println("\tDeparture Time: " + departTime);

            // Get departure location
            String departLocation = (String) jo.get("departLocation");
            System.out.println("\tDeparture Location: " + departLocation);

            // Get arrival time
            String arrivalTime = (String) jo.get("arrivalTime");
            System.out.println("\tArrival Time: " + arrivalTime);

            // Get arrival location
            String arrivalLocation = (String) jo.get("arrivalLocation");
            System.out.println("\tArrival Location: " + arrivalLocation);

            // Get cost of flight
            String flightCost = (String) jo.get("cost");
            System.out.println("\tCost of Flight: " + flightCost);

            System.out.println();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
