package labExam1;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Class to represent a course, including information on lab groups of the
 * course
 */
public class Course {
	private String name;
    private List<LabGroup> labs;

	/**
	 * Creates a new Course based on the specification. The first element of the
	 * input list will represent the course name, and the rest will represent the
	 * lab groups.
	 * 
	 * @param lines
	 *            The lines read from the courses file.
	 */
	public Course(List<String> lines) {
        name = lines.remove(0);
        labs = lines.parallelStream()
                .map(LabGroup::new)
                .collect(Collectors.toList());
	}

    public String getName() {
        return name;
    }

    public List<LabGroup> getLabs() {
        return labs;
    }

    @Override
    public String toString() {
	    return name + labs.parallelStream()
                .map(LabGroup::toString)
                .collect(Collectors.joining("\n    ", "\n    ", "\n"));
    }
}