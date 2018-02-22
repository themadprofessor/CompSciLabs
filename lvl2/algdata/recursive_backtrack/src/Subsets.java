import java.util.ArrayList;

/*
 * This class enumerates all possible subsets that can be formed from a given set of consecutive integers 1...cap
 */


public class Subsets extends AbstractBacktrack {

	public Subsets(int cap ) {
		this.partialSolution = new ArrayList<Integer>();
		for (int i=0; i<cap; i++) {
			partialSolution.add(null);
		}
		this.capacity = cap;
		this.data = capacity;
		this.solutions = new ArrayList<>();
	}
	
	//Hint: have all items been assigned membership status? Have you reached the edge of the array?
	@Override
	protected boolean isValidSolution(ArrayList<Integer> partialSolution, int cursor, int data) {
		return cursor == capacity-1;
	}

	//Collect in solutions-array to be printed out later (or print immediately)
	@Override
	protected void handleSolution(ArrayList<Integer> partialSolution) {
	    solutions.add(new ArrayList<>(partialSolution));
	}
	
	//Hint: It's Boolean
	@Override
	protected ArrayList<Integer> generateCandidates(ArrayList<Integer> partialSolution, int cursor, int data) {
	    ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
	    list.add(1);
		return list;

	}
	
	@Override
	public void printSolution() {
        for (ArrayList<Integer> solution : solutions) {
            StringBuilder builder = new StringBuilder().append("[");
            for (int i = 0; i < solution.size(); i++) {
                builder.append(solution.get(i) == 1 ? i : "");
                builder.append(", ");
            }
            builder.replace(builder.lastIndexOf(","), builder.length(), "");
            builder.append("]");
            System.out.println(builder);
        }
	}
}
