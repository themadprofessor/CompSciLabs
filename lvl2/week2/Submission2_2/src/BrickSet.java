/**
 * A toy set.
 *
 * @author 2258082R Stuart Reilly
 */
public class BrickSet {
    private int setNumber;
    private String Name;
    private String theme;
    private int pieceCount;
    private int price;

    /**
     * Initialise a new BrickSet with the given arguments
     * @param setNumber The unique number for this set
     * @param name The name of this set
     * @param theme The theme of this set
     * @param pieceCount The number of pieces in this set
     * @param price The retail price of this set
     */
    public BrickSet(int setNumber, String name, String theme, int pieceCount, int price) {
        this.setNumber = setNumber;
        Name = name;
        this.theme = theme;
        this.pieceCount = pieceCount;
        this.price = price;
    }

    /**
     * Returns the price per piece of this set.
     * @return Price per piece
     */
    public double getPricePerPiece() {
        return (double) price/ (double) pieceCount;
    }

    /**
     * Retuns the unique set number of this set.
     * @return Unique set number
     */
    public int getSetNumber() {
        return setNumber;
    }

    /**
     * Returns the name of this set.
     * @return Name of set
     */
    public String getName() {
        return Name;
    }

    /**
     * Returns the theme of this set.
     * @return
     */
    public String getTheme() {
        return theme;
    }

    /**
     * Returns the number of pieces in this set.
     * @return Number of pieces
     */
    public int getPieceCount() {
        return pieceCount;
    }

    /**
     * Returns the retail price of this set.
     * @return Retail price
     */
    public int getRetailPrice() {
        return price;
    }

    /**
     * Sets the retail price of this set to the given value.
     * @param price New retail price
     */
    public void setRetailPrice(int price) {
        this.price = price;
    }
}
