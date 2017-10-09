public class RetiredSet extends BrickSet {
    private int retirementYear;

    /**
     * Creates a new BrickSet object with the given parameters
     *
     * @param setNumber
     * @param name
     * @param theme
     * @param numPieces
     */
    public RetiredSet(int setNumber, String name, String theme, int numPieces, int retirementYear) {
        super(setNumber, name, theme, numPieces);
        this.retirementYear = retirementYear;
    }

    @Override
    protected String getDetails() {
        return "Retirement Year: [" + retirementYear + ']';
    }

    public int getRetiredYear() {
        return retirementYear;
    }
}
