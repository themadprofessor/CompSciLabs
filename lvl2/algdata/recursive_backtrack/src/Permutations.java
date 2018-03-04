import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.stream.Collectors;

/*
 * This class enumerates all possible arrangements that can be formed from a given set of consecutive integers 1...cap
 */

public class Permutations extends AbstractBacktrack {
	
	public Permutations(int cap) {
		this.capacity = cap;
		this.data = cap;
		this.partialSolution = new ArrayList<Integer>();
		for (int i=0; i<cap; i++) {
			partialSolution.add(null);
		}
		this.solutions = new ArrayList<>();
	}

	//Hint: Have all slots been assigned an object? Have you reached the edge of the array?
	@Override
	protected boolean isValidSolution(ArrayList<Integer> partialSolution, int cursor, int data) {
	    System.out.println("" + partialSolution + " : " + (cursor == capacity-1));
		return cursor == capacity-1;
	}

	@Override
	protected void handleSolution(ArrayList<Integer> partialSolution) {
	    solutions.add(new ArrayList<>(partialSolution));
	}

	//Hint: Which objects have not yet been assigned?
	@Override
	protected ArrayList<Integer> generateCandidates(ArrayList<Integer> partialSolution, int cursor, int data) {
	    ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            if (!partialSolution.contains(i)) {
                list.add(i);
            }
        }

        return list;
	}
	

	@SuppressWarnings("Duplicates")
	@Override
	public void printSolution() {
	    System.out.println(solutions.stream()
                .map(AbstractCollection::toString)
                .collect(Collectors.joining("\n")));
	}
}
