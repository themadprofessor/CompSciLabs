/**
 * Starter code for JP2 Laboratory 1, Submission 2.
 * 
 * @author Mary Ellen Foster <MaryEllen.Foster@glasgow.ac.uk>
 * @author 2258082R Stuart Reilly
 *
 */
@SuppressWarnings("Duplicates")
public class GuessingGame2 {

	public static void main(String[] args) {
		// Initialise the scanner
		java.util.Scanner stdin = new java.util.Scanner(System.in);

		// Put your solution to Submission 2 here
        System.out.println("Enter maximum number:");
        int max = stdin.nextInt();
        System.out.println("Enter maximum number of guesses:");
        int maxGuess = stdin.nextInt();

        int target = 1 + (int) (Math.random() * max);

		int guessCount = 0;
		boolean complete = false;
		while (!complete) {
		    if (guessCount >= maxGuess) {
		        System.out.println("No more guesses. The correct answer is " + target);
		        complete = true;
		        continue;
            }

			System.out.println("Enter your guess:");
			int guess = stdin.nextInt();
			if (guess <= 0 || guess > max) {
			    System.out.println("Out of range!");
            } else if (guess > target) {
				System.out.println("Too high!");
			} else if (guess < target) {
				System.out.println("Too low!");
			} else {
				System.out.println("Just right!");
				complete = true;
			}
            guessCount++;
		}

        System.out.println("You took " + guessCount + " guesses.");

		// Close the scanner
		stdin.close();
	}

}
