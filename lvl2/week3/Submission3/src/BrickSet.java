/**
 * Starter code for JP2 lab 3.
 */
public abstract class BrickSet {
	/** Fields */
    int setNumber;
	String name;
	String theme;
	int numPieces;

	/** Creates a new BrickSet object with the given parameters */
	public BrickSet(int setNumber, String name, String theme, int numPieces) {
		this.setNumber = setNumber;
		this.name = name;
		this.theme = theme;
		this.numPieces = numPieces;
	}

	/** Getters and setters */
	public int getSetNumber() {
		return setNumber;
	}

	public String getName() {
		return name;
	}

	public String getTheme() {
		return theme;
	}

	public int getNumPieces() {
		return numPieces;
	}

	/** Override toString() to return a nicer string representation of the BrickSet */
	public String toString() {
        return "Num: [" + setNumber +
                "] Name: [" + name +
                "] Type: [" + theme +
                "] Piece Count: [" + numPieces +
                "] Details: [" + getDetails() + ']';
	}

	protected abstract String getDetails();

}
