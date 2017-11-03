package game;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ComputerPlayer extends GamePlayer {
    public ComputerPlayer(String name) {
        super(name);
    }

    @Override
    public Symbol chooseSymbol() {
        if (opponentHistory.size() == 0) {
            return Symbol.values()[(int)(Math.random() * Symbol.values().length)];
        }

        Map<Symbol, Long> counts = opponentHistory.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return counts.entrySet()
                .stream()
                .sorted(Comparator.comparingLong(Map.Entry::getValue))
                .findFirst()
                .get()
                .getKey();
    }
}
