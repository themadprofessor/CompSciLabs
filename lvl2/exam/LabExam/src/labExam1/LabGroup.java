package labExam1;

/**
 * Represents a single lab group, consisting of a group label and a time slot.
 */
public class LabGroup {
	private char label;
	private TimeSlot slot;

	/**
	 * Creates a new LabGroup based on the given specification: a single-character label,
	 * a space, and then a time-slot specification.
	 * 
	 * @param line The lab group specification in the above format
	 */
	public LabGroup(String line) {
	    label = line.charAt(0);
	    slot = new TimeSlot(line.substring(2));
	}

    public char getLabel() {
        return label;
    }

    public TimeSlot getSlot() {
        return slot;
    }

    @Override
    public String toString() {
	    return "" + label + " " + slot;
    }
}