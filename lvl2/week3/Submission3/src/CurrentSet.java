public class CurrentSet extends BrickSet {
    private int retailPrice;

    /**
     * Creates a new BrickSet object with the given parameters
     *
     * @param setNumber
     * @param name
     * @param theme
     * @param numPieces
     * @param retailPrice
     */
    public CurrentSet(int setNumber, String name, String theme, int numPieces, int retailPrice) {
        super(setNumber, name, theme, numPieces);
        this.retailPrice = retailPrice;
    }

    public int getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(int retailPrice) {
		this.retailPrice = retailPrice;
	}

    /** Computes price per piece */
    public double getPricePerPiece() {
        return (double)retailPrice/numPieces;
    }

    @Override
    protected String getDetails() {
        return "Price: [" + retailPrice + ']';
    }
}
