/**
 * A BrickSet which is no longer for sale.
 * @author 2258082R
 */
public class RetiredSet extends BrickSet {
	/**
	 * The year this BrickSet retired from sale.
	 */
    private int retirementYear;

    /**
     * {@inheritDoc}
     * @param retirementYear Year of retirement from sale.
     */
    public RetiredSet(int setNumber, String name, String theme, int numPieces, int retirementYear) {
        super(setNumber, name, theme, numPieces);
        this.retirementYear = retirementYear;
    }

    @Override
    /**
     * {@inheritDoc}
     * Returns the retirement year of this BrickSet in the form: "Retirement Year: [getRetiredYear()"
     */
    protected String getDetails() {
        return "Retirement Year: [" + retirementYear + ']';
    }

    /**
     * Returns the year this BrickSet was retired from sale.
     * @return Year of retirement.
     */
    public int getRetiredYear() {
        return retirementYear;
    }
}
