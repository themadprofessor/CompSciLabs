/**
 * Starter code for JP2 Laboratory 1, Submission 1.
 * 
 * @author Mary Ellen Foster <MaryEllen.Foster@glasgow.ac.uk>
 * @author 2258082R Stuart Reilly
 *
 */

@SuppressWarnings("Duplicates")
public class GuessingGame {
	public static void main(String[] args) {
		// Initialise the scanner and choose the target value (a random number between 1 and 20)
		java.util.Scanner stdin = new java.util.Scanner(System.in);
		int target = 1 + (int) (Math.random() * 20);

		// Put your solution to Submission 1 here
        int guessCount = 0;
        boolean success = false;
        while (!success) {
        	System.out.println("Enter your guess:");
        	byte guess = stdin.nextByte(); // Use byte as all possible values for game fit
        	guessCount++;
        	if (guess > target) {
        	    System.out.println("Too high!");
            } else if (guess < target) {
        	    System.out.println("Too low!");
            } else {
        	    System.out.println("Just right!");
        	    success = true;
            }
		}

		System.out.println("You took " + guessCount + " guesses.");

		stdin.close();
	}
}
