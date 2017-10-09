/**
 * Starter code for JP2 lab 3.
 */
public class BrickSet {
	/** Fields */
	private int setNumber;
	private String name;
	private String theme;
	private int numPieces;
	private int retailPrice;

	/** Creates a new BrickSet object with the given parameters */
	public BrickSet(int setNumber, String name, String theme, int numPieces, int retailPrice) {
		this.setNumber = setNumber;
		this.name = name;
		this.theme = theme;
		this.numPieces = numPieces;
		this.retailPrice = retailPrice;
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

	/** Override toString() to return a nicer string representation of the BrickSet */
	public String toString() {
		return setNumber + ": " + name + " (" + theme + "), " + numPieces + " pieces: current price £" + retailPrice;
	}

}
