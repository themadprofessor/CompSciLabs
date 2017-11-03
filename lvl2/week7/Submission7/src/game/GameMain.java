package game;

import java.util.Scanner;

/**
 * Main method for Rock-Paper-Scissors-Lizard-Spock game.
 * 
 * JP2 Laboratory 7 2017.
 * 
 * @author mefoster
 *
 * @author Stuart Reilly 2258082
 */
public class GameMain {

	/**
	 * Prompts the user for the tournament parameters and then runs
	 * a tournament.
	 */
	public static void main(String[] args) {
		// Read everything from standard input
		Scanner stdin = new Scanner(System.in);

		// First player is always a computer
		GamePlayer player1 = new ComputerPlayer("Computer");
		
		// Second player may be a computer or a human
		GamePlayer player2;
		System.out.println("Enter name of human player, or empty string for two computer players");
		String name = stdin.nextLine();
		if (name.length() == 0) {
			System.out.println("Using two computer players");
			player2 = new ComputerPlayer("Computer2");
		} else {
			player2 = new HumanPlayer(name, stdin);
		}

		// Get the number of games required to win the tournament -- and be sure
		// it is a positive integer
		int numGames = -1;
		while (numGames <= 0) {
			System.out.println("Enter number of games to win: ");
			try {
				numGames = stdin.nextInt();
			} catch (NumberFormatException ex) {
				System.out.println("Invalid input!");
			}
			if (numGames <= 0) {
				System.out.println("Invalid input!");
			}
		}
		
		// Run the tournament with the given parameters
		GamePlayer winner = playTournament(player1, player2, numGames);

		System.out.println("------------------");
		System.out.println("Overall winner is: " + winner.getName());

		stdin.close();
	}

	private static GamePlayer playTournament(GamePlayer player1, GamePlayer player2, int numGames) {
	    int player1Wins = 0, player2Wins = 0;
	    //Play until either player reaches numGames
	    while (player1Wins != numGames && player2Wins != numGames) {
	        //Get both player's symbols and print them
			Symbol symbol1 = player1.chooseSymbol();
			Symbol symbol2 = player2.chooseSymbol();
			System.out.println(player1.name + ": " + symbol1);
			System.out.println(player2.name + ": " + symbol2);

			//Determine the winner, updating win counts accordingly
			switch (symbol1.getResult(symbol2)) {
                case WIN:
                    System.out.println(player1.getName() + " wins!");
                    player1Wins++;
                    break;
                case DRAW:
                    System.out.println("Draw");
                    break;
                case LOSE:
                    System.out.println(player2.getName() + " wins!");
                    player2Wins++;
                    break;
            }

            //Update player histories
            player1.addHistory(symbol1, symbol2);
			player2.addHistory(symbol2, symbol1);
		}

		//Return player1 if player1 won the most games, player2 otherwise
		return player1Wins > player2Wins ? player1 : player2;
	}
}
