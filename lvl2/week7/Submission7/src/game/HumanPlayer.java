package game;

import java.util.Iterator;

/**
 * {@inheritDoc}
 * A human player who's Symbols are read from the given Iterator, prompting the user via stdout if the Symbol is not
 * defined in {@link Symbol Symbol}.
 *
 * @author Stuart Reilly 2258082
 */
public class HumanPlayer extends GamePlayer {
    private Iterator<String> input;

    /**
     *{@inheritDoc}
     * Creates a new human player from the given iterator.
     * @param name The name of the player
     * @param input The iterator to be used as input
     */
    public HumanPlayer(String name, Iterator<String> input) {
        super(name);
        this.input = input;
    }

    /**
     * {@inheritDoc}
     * Prompts the user indefinitely until the user inputs a valid Symbol as defined in {@link Symbol Symbol}.
     * @return The Symbol the user inputs
     */
    @Override
    public Symbol chooseSymbol() {
        Symbol symbol = null;
        while (symbol == null) {
            try {
                System.out.println("Please enter a symbol: ");
                symbol = Symbol.valueOf(input.next());
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid option!");
            }
        }

        return symbol;
    }
}
