package game;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 * A computer player which implements both frequency analysis and random guessing to implement
 * {@link #chooseSymbol() chooseSymbol()}.
 *
 * @author Stuart Reilly 2258082
 */
public class ComputerPlayer extends GamePlayer {
    /**
     * {@inheritDoc}
     */
    public ComputerPlayer(String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     * If this is the first round this ComputerPlayer has played, this will return a random Symbol. Otherwise, the most
     * frequent Symbol used by this ComputerPlayer's opponents will be used to find a Symbol which will beat the most
     * frequent Symbol, returning the winning Symbol.
     * @return Returns a random symbol if this is this ComputerPlayer's first move, otherwise uses frequency analysis to
     * to find most likely winning symbol
     */
    @Override
    public Symbol chooseSymbol() {
        //Return random symbol if first go
        //This is the only time there will not be a most frequent choice
        if (opponentHistory.size() == 0) {
            //The index is a value between 0 (inclusive) and the number of Symbols (exclusive)
            return Symbol.values()[(int)(Math.random() * Symbol.values().length)];
        }

        //Create a map of each symbol and the number of times it appears in the opponent's history
        Map<Symbol, Long> counts = opponentHistory.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        //The following Options do not need to be checked for presence as the only time they are empty is if this is the
        //first go, which returns fast above

        //Get the most frequent Symbol
        Symbol mostFreq = counts.entrySet()
                                .stream()
                                .sorted(Comparator.comparingLong(Map.Entry::getValue))
                                .findFirst()
                                .get()
                                .getKey();

        //Get the first Symbol which beats the most frequent symbol
        return Symbol.getBeatMap()
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().contains(mostFreq))
                .findFirst()
                .get()
                .getKey();
    }
}
