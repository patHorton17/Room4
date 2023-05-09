import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.io.FileReader;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class ReserveFlight
{
    int flightSelectionValue = 0;
    String passengerFirstName = "";
    String passengerLastName = "";
    String passengerName = "";
    long passengerConfirmationNumber = 0;

    void reserveFlight() throws Exception {
        // prompt for the sequence size
        System.out.printf("Which flight would you like to reserve?\n\n");

        // Fetch and display the available flights to pick from
        fetchAvailableFlights();

        // Allow the user to select which flight they want to reserve
        userReservationSelection();

        // Update the database to include this customer as an attendant
        updateJSON();
    }

    void fetchAvailableFlights() throws Exception {
        // This method makes a call to the database (text file) and prints out the flight details to the user.
        // They will be shown the departure location, destination, departure time, arrival time, cost, and available seats
        readJSON(false);
    }

    void readJSON(boolean bool) throws Exception {
        try {
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader("JSON File.json"));
            for (Object o : a)
            {
                // typecasting obj to JSONObject
                JSONObject jo = (JSONObject) o;

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

                if (bool == true) {
                    // Get passengers
                    JSONArray passengerArray = (JSONArray) jo.get("passengerNames");
                    Iterator<String> iterator = passengerArray.iterator();
                    System.out.print("\tPassenger Names: ");
                    while(iterator.hasNext()) {
                        System.out.print(iterator.next());
                        if (iterator.hasNext()) {
                            System.out.print(", ");
                        }
                    }
                    System.out.println();

                    // Get confirmation numbers
                    JSONArray confirmationArray = (JSONArray) jo.get("confirmationNumbers");
                    Iterator<Integer> confirmationIterator = confirmationArray.iterator();
                    System.out.print("\tConfirmation Numbers: ");
                    while(confirmationIterator.hasNext()) {
                        System.out.print(confirmationIterator.next());
                        if (confirmationIterator.hasNext()) {
                            System.out.print(", ");
                        }
                    }
                    System.out.println();

                    // Get number of cancellations
                    long numberOfCancellations = (long) jo.get("numberOfCancellations");
                    System.out.println("\tNumber of Cancellations: " + numberOfCancellations);
                }
                System.out.println();
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    void userReservationSelection() {
        // Allow the user to select which flight they want to reserve
        Scanner reservationSelected = new Scanner(System.in);
        System.out.println("Enter the flight number you wish to reserve: ");
        flightSelectionValue =  reservationSelected.nextInt();

        // Allow the user to enter their name for the flight
        System.out.println("Enter your first name: ");
        passengerFirstName = reservationSelected.next();
        System.out.println("Enter your last name: ");
        passengerLastName = reservationSelected.next();

        // Combine for full name
        passengerName = passengerFirstName + " " + passengerLastName;

        // Generate a confirmation Number
        generateConfirmationNumber();
    }

    void generateConfirmationNumber() {
        // Generate a random 5 digit confirmation number
        int randomNum = ThreadLocalRandom.current().nextInt(10000, 99999 + 1);
        passengerConfirmationNumber = randomNum;
    }

    void updateJSON() {
        try {
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader("JSON File.json"));
            for (Object o : a)
            {
                // typecasting obj to JSONObject
                JSONObject jo = (JSONObject) o;

                // Get Flight Number
                long flightNumber = (long) jo.get("flightNumber");

                // Check if flight number matches user's flight number
                if (flightNumber == flightSelectionValue) {

                    // Get passengers
                    JSONArray passengerArray = (JSONArray) jo.get("passengerNames");
                    String stringArray = passengerArray.toJSONString();
                    StringBuffer sb = new StringBuffer(stringArray);
                    sb.deleteCharAt(sb.length()-1);

                    String newString = sb.toString();
                    newString = newString + ",\"" + passengerName + "\"]";

                    JSONParser newParse = new JSONParser();
                    JSONArray newNamesArray = (JSONArray)parser.parse(newString);

                    // Add the passenger to the JSON file
                    jo.put("passengerNames", newNamesArray);

                    // Add the confirmation number to the JSON file
                    // Get confirmation number
                    JSONArray confirmationArray = (JSONArray) jo.get("confirmationNumbers");
                    String stringConfirmationArray = confirmationArray.toJSONString();
                    StringBuffer sbca = new StringBuffer(stringConfirmationArray);
                    sbca.deleteCharAt(sbca.length()-1);

                    String newConfirmationString = sbca.toString();
                    newConfirmationString = newConfirmationString + "," + passengerConfirmationNumber + "]";

                    JSONParser confirmationParse = new JSONParser();
                    JSONArray newConfirmationArray = (JSONArray)parser.parse(newConfirmationString);

                    // Add the passenger to the JSON file
                    jo.put("confirmationNumbers", newConfirmationArray);

                    // Increment the capacity
                    long currentPassengers = (long) jo.get("currentPassengers");
                    currentPassengers = currentPassengers + 1;

                    // Update the JSON File
                    jo.put("currentPassengers", currentPassengers);

                    //Write into the file
                    try (FileWriter file = new FileWriter("JSON File.json"))
                    {
                        String newJSON = "[";
                        for (Object ob : a)
                        {
                            newJSON = newJSON + (ob.toString()) + ",";
                        }
                        StringBuffer stringB = new StringBuffer(newJSON);
                        stringB.deleteCharAt(stringB.length()-1);

                        String updatedJSON = stringB.toString();
                        updatedJSON = updatedJSON + "]";
                        file.write(updatedJSON);
                        System.out.println("You have successfully booked your flight!");
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}