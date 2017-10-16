package creditcard;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Asks the user for a credit card number and checks if it is a valid credit card number
 * @author 2258082R Stuart Reilly
 */
public class CreditCardMain {
	/**
	 * Runs the program
	 * @param args command line arguments - ignored
	 */
    public static void main(String[] args) {
        //Initialise stdin and checker
        Scanner input = new Scanner(System.in);
        CreditCardChecker checker = new CreditCardChecker();

        //Prompt the user for a card number, informing the user if something went wrong during reading
        String creditCard;
        while(true) {
            System.out.println("Enter the credit card number:");
            try {
                creditCard = input.nextLine();
                IOException exception;
                if ((exception = input.ioException()) != null) {
                    System.err.println("Failed to read credit card number: " + exception.getLocalizedMessage());
                } else {
                    break;
                }
            } catch (NoSuchElementException e) {
                System.err.println("No credit card number given!");
            }
        }

        //Validate the card number, catching all possible exceptions and informing the user of their presence as needed
        try {
            if (checker.validate(creditCard)) {
                System.out.println("Card number is valid");
            } else {
                System.out.println("Card number is invalid");
            }
        } catch (IllegalCardFormatException e) {
            System.err.println("Invalid credit card number format: " + e.getLocalizedMessage());
        } catch (IllegalCardLengthException e) {
            System.err.println("Invalid credit card number length: " + e.getLocalizedMessage());
        }
    }
}
