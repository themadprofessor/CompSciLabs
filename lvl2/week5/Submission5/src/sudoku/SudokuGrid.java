package sudoku;

import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SudokuGrid {
    private int[][] grid = new int[Utils.SIZE][Utils.SIZE];

    public SudokuGrid(String filename) throws IOException {
        String data = Utils.loadGrid(filename);
        //Iterate over each cell, converting it into a number and store it in a 1D array
        int[] flatGrid = Stream.of(data.split(","))
                .map(s -> {
                    if (s.isEmpty()) {
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

    public String check() {
        String result = null;

        for (int i = 0; i < grid.length; i++) {
            IntStream.of(grid[i])
                    .collect(HashMap::new, (map, val) -> map.merge(val, 1, (old, inc) -> Integer.sum(old, inc)));
        }

        return result;
    }
}
