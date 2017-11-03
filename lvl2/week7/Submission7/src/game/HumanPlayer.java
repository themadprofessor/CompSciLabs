package game;

import java.util.Iterator;

public class HumanPlayer extends GamePlayer {
    private Iterator<String> input;

    public HumanPlayer(String name, Iterator<String> input) {
        super(name);
        this.input = input;
    }

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
