/**
 * A BrickSet which is currently for sale.
 * @author 2258082R
 */
public class CurrentSet extends BrickSet {
	/**
	 * The retail price of this BrickSet
	 */
    private int retailPrice;

    /**
     * {@inheritDoc}
     * @param retailPrice Retail price.
     */
    public CurrentSet(int setNumber, String name, String theme, int numPieces, int retailPrice) {
        super(setNumber, name, theme, numPieces);
        this.retailPrice = retailPrice;
    }

    /**
     * Returns this BrickSet's retail price.
     * @return Retail price.
     */
    public int getRetailPrice() {
		return retailPrice;
	}

    /**
     * Sets this BrickSet's retail price to the given value.
     * @param retailPrice New retail price.
     */
	public void setRetailPrice(int retailPrice) {
		this.retailPrice = retailPrice;
	}

    /**
     * Returns the price per piece of this BrickSet.
     * @return Price per piece.
     */
    public double getPricePerPiece() {
        return (double)retailPrice/numPieces;
    }

    @Override
    /**
     * {@inheritDoc}
     * Returns the retail price of this BrickSet in the form: "Price: [getRetailPrice()]".
     */
    protected String getDetails() {
        return "Price: [" + retailPrice + ']';
    }
}
