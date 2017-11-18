package labExam1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a student, based on their student number and a list of time slots
 * where they have a commitment.
 */
public class Student {
	private String number;
	private List<TimeSlot> commitments;

	/**
	 * Creates a new Student object based on the input parameters.
	 * 
	 * @param number
	 *            The student number
	 * @param slotSpecs
	 *            A list of time-slot specifications
	 */
	public Student(String number, List<String> slotSpecs) {
		this.number = number;
		commitments = slotSpecs.parallelStream()
                .map(TimeSlot::new)
                .collect(Collectors.toList());
	}

    public List<String> getLabGroups(Course course) {
        return course.getLabs()
                .stream()
                .filter(lab -> commitments.stream()
                        .noneMatch(slot -> slot.overlap(lab.getSlot())))
                .map(lab -> String.valueOf(lab.getLabel()))
                .collect(Collectors.toList());
    }

    public String getStudentNumber() {
        return number;
    }
}