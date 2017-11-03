package game;

import java.util.*;

public enum Symbol {
    ROCK,
    PAPER,
    SCISSORS,
    LIZARD,
    SPOCK;

    private static Map<Symbol, EnumSet<Symbol>> BEAT_MAP = new EnumMap<>(Symbol.class);

    static {
        BEAT_MAP.put(ROCK, EnumSet.of(LIZARD, SCISSORS));
        BEAT_MAP.put(PAPER, EnumSet.of(ROCK, SPOCK));
        BEAT_MAP.put(SCISSORS, EnumSet.of(PAPER, LIZARD));
        BEAT_MAP.put(LIZARD, EnumSet.of(SPOCK, PAPER));
        BEAT_MAP.put(SPOCK, EnumSet.of(SCISSORS, ROCK));
        BEAT_MAP = Collections.unmodifiableMap(BEAT_MAP);
    }

    public GameResult getResult(Symbol other) {
        if (this == other) {
            return GameResult.DRAW;
        } else if (BEAT_MAP.get(this).contains(other)) {
            return GameResult.WIN;
        } else {
            return GameResult.LOSE;
        }
    }

    public static Map<Symbol, Set<Symbol>> getBeatMap() {
        return Collections.unmodifiableMap(BEAT_MAP);
    }
}
