package binPacking;

import java.util.Arrays;
import java.util.Objects;

public class Bin {
    private final int capacity;
    private int[] contents;
    private int currentCapacity;
    private int index;

    public Bin(final int capacity) {
        this.capacity = capacity;
        contents = new int[capacity];
    }

    public void store(int weight) throws IllegalArgumentException {
        if (currentCapacity + weight > capacity) {
            throw new IllegalArgumentException("weight is too large for this bin");
        }
        currentCapacity += weight;
        contents[index] = weight;
        index++;
    }

    public int getSpace() {
        return capacity - currentCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bin)) return false;
        Bin bin = (Bin) o;
        return capacity == bin.capacity &&
                Arrays.equals(contents, bin.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, Arrays.hashCode(contents));
    }
}
