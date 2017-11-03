package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class GamePlayer {
    protected String name;
    protected List<Symbol> myHistory;
    protected List<Symbol> opponentHistory;

    public GamePlayer(String name) {
        this.name = name;
        myHistory = new ArrayList<>();
        opponentHistory = new ArrayList<>();
    }

    public void addHistory(Symbol myChoice, Symbol opponentSymbol) {
        myHistory.add(myChoice);
        opponentHistory.add(opponentSymbol);
    }

    public String getName() {
        return name;
    }

    public GamePlayer setName(String name) {
        this.name = name;
        return this;
    }

    public abstract Symbol chooseSymbol();
}
