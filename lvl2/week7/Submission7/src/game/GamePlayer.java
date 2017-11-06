package game;

import java.util.ArrayList;
import java.util.List;

/**
 * A player who can play Rock Paper Scissors Lizard Spock.
 *
 * @author Stuart Reilly 2258082
 */
public abstract class GamePlayer {
    /**
     * The name of the player.
     */
    String name;

    /**
     * A list of all symbols this player has used in order of usage.
     */
    List<Symbol> myHistory;

    /**
     * A list of all symbols used by all opponents of this player in order of usage.
     */
    List<Symbol> opponentHistory;

    /**
     * Creates a new GamePlayer with the given name.
     * @param name The name of the player
     */
    public GamePlayer(String name) {
        this.name = name;
        myHistory = new ArrayList<>();
        opponentHistory = new ArrayList<>();
    }

    /**
     * Adds the given Symbols to the play history of this player.
     * @param myChoice This player's Symbol
     * @param opponentSymbol The opponent's Symbol
     */
    public void addHistory(Symbol myChoice, Symbol opponentSymbol) {
        myHistory.add(myChoice);
        opponentHistory.add(opponentSymbol);
    }

    /**
     * Gets this player's name.
     * @return Returns this player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets this player's name.
     * @param name The new name for this player
     * @return This player
     */
    public GamePlayer setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Prompt this player to chose their Symbol.
     * @return Returns the player's chosen Symbol
     */
    public abstract Symbol chooseSymbol();
}
