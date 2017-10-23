package sudoku;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SudokuGrid {
    private int[][] grid = new int[Utils.SIZE][Utils.SIZE];

    /**
     * Reads the given file, parses its CSV data to create a new SudokuGrid.
     * @param filename The file to read
     * @throws IOException Thrown if an IO error occurs during reading
     */
    public SudokuGrid(String filename) throws IOException {
        String[] rawData = Utils.loadGrid(filename).split(",");

        //Expand rawData to Utils.SIZE*Utils.SIZE to avoid ArrayOutOfBounds
        String[] data = new String[Utils.SIZE * Utils.SIZE];
        System.arraycopy(rawData, 0, data, 0, rawData.length);

        //Iterate over each cell, converting it into a number and store it in a 1D array
        int[] flatGrid = Arrays.stream(data)
                .map(s -> {
                    if (s == null || s.isEmpty()) {
                        return "0";
                    } else {
                        return s;
                    }
                })
                .mapToInt(c -> Character.getNumericValue(c.charAt(0)))
                .toArray();
        //Copy Utils.SIZE blocks of Utils.SIZE elements into each row of grid
        for (int i = 0; i < Utils.SIZE; i++) {
            System.arraycopy(flatGrid, i * Utils.SIZE, grid[i], 0, Utils.SIZE);
        }
    }

    /**
     * Checks if this SudokuGrid is invalid, incomplete or valid, return Utils.INVALID, Utils.INCOMPLETE, or Utils.VALID
     * respectively. A SudokuGrid is invalid if a row, column or square contains any duplicate values. A square is
     * defined as a 3x3 collection of cells which doesn't overlap with any other square, meaning a standard sudoku grid
     * contains 9 squares. A SudokuGrid is incomplete if its valid and not all cells have been filled. A SudokuGrid is
     * valid if and only if its not incomplete.
     * @return Returns <ul>
     *     <li>Utils.INVALID - if a row, column or square contains duplicate values.</li>
     *     <li>Utils.INCOMPLETE - if its not invalid and not all cells have been filled.</li>
     *     <li>Utils.VALID - if and only if its not incomplete.</li>
     * </ul>
     */
    public String check() {
        //Check if each row is valid
        for (int[] line : grid) {
            if (!isValidLine(line)) {
                return Utils.INVALID;
            }
        }

        //Check if each column is valid
        for (int i = 0; i < grid[0].length; i++) {
            IntStream.Builder builder = IntStream.builder();
            for (int[] aGrid : grid) {
                builder.accept(aGrid[i]);
            }
            if (!isValidLine(builder.build().boxed())) {
                return Utils.INVALID;
            }
        }

        //Check if each 3x3 square is valid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!isSquareValid(i, j)) {
                    return Utils.INVALID;
                }
            }
        }

        //Check if every cell is filled
        for (int[] line : grid) {
            if (Arrays.stream(line).anyMatch(v -> v == 0)) {
                return Utils.INCOMPLETE;
            }
        }

        return Utils.VALID;
    }

    /**
     * Checks if the given 3x3 square is valid. A square is valid if and only if it contains no duplicate values. The
     * coordinates given are to reference which square, not a particular cell, i.e. the coordinates 1,1 refer to the
     * square [3,5]x[3,5].
     * @param x The x coordinate of the cell
     * @param y the y coordinate of the cell
     * @return Returns true if the given square contains no duplicate values
     */
    private boolean isSquareValid(int x, int y) {
        Map<Integer, Long> counts = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int cell = grid[x * 3 + i][y * 3 + j];
                if (cell != 0) {
                    counts.merge(cell, 1L, (old, newVal) -> old + newVal);
                }
            }
        }

        return counts.values()
                .stream()
                .noneMatch(entry -> entry > 1);
    }

    /**
     * Checks if the given line is valid. A line is valid if and only if it contains no duplicate values. This is
     * equivalent to isValidLine(Arrays.stream(line).boxed());
     * @param line The line to check
     * @return Returns true if the line contains no duplicate values, and false otherwise
     */
    private boolean isValidLine(int[] line) {
        return isValidLine(Arrays.stream(line).boxed());
    }

    /**
     * Checks if the given line is valid. A line is valid if and only if it contains no duplicate values.
     * @param line The line to check
     * @return Returns true if the line contains no duplicate values, and false otherwise
     */
    private boolean isValidLine(Stream<Integer> line) {
        Map<Integer, Long> counts = line.collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        return counts.entrySet()
                        .stream()
                        .filter(entry -> entry.getKey() != 0L)
                        .noneMatch(entry -> entry.getValue() > 1);
    }

    /**
     * {@inheritDoc}
     * Each row is a row of values separated by ',', empty cells are represented by the space character and end with a
     * new line.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int[] row : grid) {
            builder.append(Arrays.stream(row).mapToObj(String::valueOf).map(v -> {
                if (v.equals("0")) {
                    return " ";
                } else {
                    return v;
                }
            }).collect(Collectors.joining(",")));
            builder.append('\n');
        }
        return builder.toString();
    }
}
