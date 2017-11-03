package game;

import java.util.*;

/**
 * A Symbol in the game Rock Paper Scissors Lizard Spock as defined
 * <a href="http://bigbangtheory.wikia.com/wiki/Rock_Paper_Scissors_Lizard_Spock">here</a>.
 *
 * @author Stuart Reilly 2258082
 */
public enum Symbol {
    /**
     * The Rock Symbol, which beats:
     * <ul>
     *     <li>SCISSORS</li>
     *     <li>LIZARD</li>
     * </ul>
     */
    ROCK,

    /**
     * The Paper Symbol, which beats:
     * <ul>
     *     <li>ROCK</li>
     *     <li>SPOCK</li>
     * </ul>
     */
    PAPER,

    /**
     * The Scissors Symbol, which beats:
     * <ul>
     *     <li>PAPER</li>
     *     <li>LIZARD</li>
     * </ul>
     */
    SCISSORS,

    /**
     * The Lizard Symbol, which beats:
     * <ul>
     *     <li>SPOCK</li>
     *     <li>PAPER</li>
     * </ul>
     */
    LIZARD,

    /**
     * The Spock Symbol, which beats:
     * <ul>
     *     <li>SCISSORS</li>
     *     <li>ROCK</li>
     * </ul>
     */
    SPOCK;

    private static Map<Symbol, EnumSet<Symbol>> BEAT_MAP = new EnumMap<>(Symbol.class);

    //Initialise BEAT_MAP
    static {
        BEAT_MAP.put(ROCK, EnumSet.of(LIZARD, SCISSORS));
        BEAT_MAP.put(PAPER, EnumSet.of(ROCK, SPOCK));
        BEAT_MAP.put(SCISSORS, EnumSet.of(PAPER, LIZARD));
        BEAT_MAP.put(LIZARD, EnumSet.of(SPOCK, PAPER));
        BEAT_MAP.put(SPOCK, EnumSet.of(SCISSORS, ROCK));
        BEAT_MAP = Collections.unmodifiableMap(BEAT_MAP);
    }

    /**
     * Determine if this Symbol beats the given Symbol as defined
     * <a href="http://bigbangtheory.wikia.com/wiki/Rock_Paper_Scissors_Lizard_Spock">here</a>.
     * @param other The opponent's Symbol
     * @return Returns DRAW if both Symbols are the same, WIN if this Symbol beats the given Symbol, LOSE otherwise
     */
    public GameResult getResult(Symbol other) {
        if (this == other) {
            return GameResult.DRAW;
        } else if (BEAT_MAP.get(this).contains(other)) {
            return GameResult.WIN;
        } else {
            return GameResult.LOSE;
        }
    }

    /**
     * Get an unmodifiable map of how each Symbol beats the other Symbols. Its laid out &lt;key&gt; beats all in
     * &lt;value&gt;.
     * @return Returns an unmodifiable beat map
     */
    public static Map<Symbol, Set<Symbol>> getBeatMap() {
        return Collections.unmodifiableMap(BEAT_MAP);
    }
}
