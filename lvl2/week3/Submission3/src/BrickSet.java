/**
 * @author 2258082R Stuart Reilly
 * 
 * A super type of for all brick sets. 
 */
public abstract class BrickSet {
	/**
	 * The unique ID of this BrickSet.
	 */
    int setNumber;
    
    /**
     * The name of this BrickSet.
     */
	String name;
	
	/**
	 * The theme of this BrickSet.
	 */
	String theme;
	
	/**
	 * The number of pieces in this BrickSet.
	 */
	int numPieces;

	/**
	 * Creates a new BrickSet with the given paramters.
	 * @param setNumber Unique ID.
	 * @param name Name of BrickSet.
	 * @param theme Theme of BrickSet.
	 * @param numPieces Number of pieces in BrickSet.
	 */
	public BrickSet(int setNumber, String name, String theme, int numPieces) {
		this.setNumber = setNumber;
		this.name = name;
		this.theme = theme;
		this.numPieces = numPieces;
	}

	/**
	 * Returns this BrickSet's unique ID.
	 * @return Unique ID.
	 */
	public int getSetNumber() {
		return setNumber;
	}

	/**
	 * Returns this BrickSet's name.
	 * @return BrickSet's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns this BrickSet's theme.
	 * @return BrickSet's theme.
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * Returns the number of pieces in this BrickSet.
	 * @return Number of pieces
	 */
	public int getNumPieces() {
		return numPieces;
	}

	/**
	 * Returns a String representation of this BrickSet in the form:
	 * "Num: [getSetNumber()] Name: [getName()] Theme: [getTheme()] Piece Count: [getNumPieces()] Details: [getDetails()]"
	 */
	public String toString() {
        return "Num: [" + setNumber +
                "] Name: [" + name +
                "] Theme: [" + theme +
                "] Piece Count: [" + numPieces +
                "] Details: [" + getDetails() + ']';
	}

	/**
	 * Returns extra details about a specific type of BrickSet. The result of this method is appended to the toString() impl.
	 * @return Extra details.
	 */
	protected abstract String getDetails();

}
