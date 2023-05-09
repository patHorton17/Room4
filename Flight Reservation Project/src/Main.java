import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {
        ReserveFlight reserveFlightClass = new ReserveFlight();
        CancelFlight cancelFlightClass = new CancelFlight();
        ReviewFlight reviewFlightClass = new ReviewFlight();

        String managerUsername = "FlightManager123";
        String managerPassword = "ManagerPassword123";
        String enteredUsername;
        String enteredPassword;

        int userSelection = 0;
        // Present the options to the user
        while (userSelection != 6) {
            // Print the menu of options
            System.out.println("FLIGHT RESERVATION TOOL");
            System.out.println("\t1) Reserve a Flight");
            System.out.println("\t2) Cancel a Flight");
            System.out.println("\t3) Change a Flight");
            System.out.println("\t4) Review a Booked Flight");
            System.out.println("\t5) View Management Information");
            System.out.println("\t6) Quit Program");

            // Instruct the user to select an option
            Scanner optionSelector = new Scanner(System.in);
            System.out.println("\nEnter Selection: ");

            // Capture the user's input
            userSelection =  optionSelector.nextInt();

            // Call the appropriate method based on the choice by the user
            if (userSelection == 1) {
                reserveFlightClass.reserveFlight();
            }

            else if (userSelection == 2) {
                cancelFlightClass.cancelFlight();
            }

            else if (userSelection == 3) {
                // Ask the user to provide their information
                System.out.println("Provide the details of the flight that you wish to change: ");
                if (cancelFlightClass.cancelFlight()) {
                    // Next, ask the user to pick a new flight
                    reserveFlightClass.reserveFlight();
                }
            }

            else if (userSelection == 4) {
                reviewFlightClass.reviewFlight();
            }

            else if (userSelection == 5) {
                Scanner managerInput = new Scanner(System.in);
                System.out.println("Enter Management Username: ");
                enteredUsername =  optionSelector.next();

                System.out.println("Enter Management Password: ");
                enteredPassword =  optionSelector.next();

                if (enteredUsername.equals(managerUsername) && enteredPassword.equals(managerPassword)) {
                    reserveFlightClass.readJSON(true);
                }
                else {
                    System.out.println("Those credentials are invalid!\n");
                }
            }

            else if (userSelection == 6) {
                System.out.println("Program Closed.");
            }
        }
    }
}