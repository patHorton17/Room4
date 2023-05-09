import com.sun.org.apache.xpath.internal.operations.Bool;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class CancelFlight {
    String firstNameConfirmation = "";
    String lastNameConfirmation = "";
    String nameToCheck = "";
    int confirmationNumberToCheck = 0;
    int flightNumberToCheck = 0;
    JSONObject jsonObjectToUpdate;
    int indexToRemove;
    boolean cancelFlight() throws Exception {

        // Get user info
        getUserInfo();

        // Verify that the passenger is booked with the flight
        return verifyCancellationRequest(firstNameConfirmation, lastNameConfirmation, flightNumberToCheck, confirmationNumberToCheck);
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
    public boolean verifyCancellationRequest(String fn, String ln, int fln, int cn) {
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
                                    jsonObjectToUpdate = jo;
                                    indexToRemove = i;
                                    deleteFromFlight();
                                    return true;
                                }
                            }
                            System.out.println("That name is not associated with this flight!\n");
                            return false;
                        }
                    }
                    System.out.println("That confirmation number does not exist!\n");
                    return false;
                }
            }
            System.out.println("That flight number does not exist!\n");
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    public void deleteFromFlight() throws IOException, ParseException {
        JSONArray newConfirmationArray = (JSONArray) jsonObjectToUpdate.get("confirmationNumbers");
        JSONArray newPassengerNamesArray = (JSONArray) jsonObjectToUpdate.get("passengerNames");

        // Update the confirmation numbers
        newConfirmationArray.remove(indexToRemove);
        jsonObjectToUpdate.put("confirmationNumbers", newConfirmationArray);

        // Update the passenger names
        newPassengerNamesArray.remove(indexToRemove);
        jsonObjectToUpdate.put("passengerNames", newPassengerNamesArray);

        // Update the capacity
        long currentPassengers = (long) jsonObjectToUpdate.get("currentPassengers");
        currentPassengers = currentPassengers - 1;
        jsonObjectToUpdate.put("currentPassengers", currentPassengers);

        // Update the number of cancellations
        long numberCancellations = (long) jsonObjectToUpdate.get("numberOfCancellations");
        numberCancellations = numberCancellations + 1;
        jsonObjectToUpdate.put("numberOfCancellations", numberCancellations);

        // Now, write to the file
        writeToJSONFile();

        // Confirm to the user the flight has been cancelled
        System.out.println("You have cancelled your flight!");
    }
    public void writeToJSONFile() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray a = (JSONArray) parser.parse(new FileReader("JSON File.json"));

        //Write into the file
        try (FileWriter file = new FileWriter("JSON File.json"))
        {
            a.set(flightNumberToCheck - 1, jsonObjectToUpdate);
            file.write(String.valueOf(a));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
