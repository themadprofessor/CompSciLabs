package binPacking;

import java.util.Arrays;
import java.util.Objects;

/**
 * A bin for storing up to finite amount of content.
 */
public class Bin {
    private final int capacity;
    private int[] contents;
    private int currentCapacity;
    private int index;

    /**
     * Creates a new bin with the given capacity.
     * @param capacity capacity of bin
     */
    public Bin(final int capacity) {
        this.capacity = capacity;
        contents = new int[capacity];
    }

    /**
     * Stores the given value in the bin.
     * @param weight value to be stored
     * @throws IllegalArgumentException Thrown if the given weight would exceed this Bin's capacity if added
     */
    public void store(int weight) throws IllegalArgumentException {
        if (currentCapacity + weight > capacity) {
            throw new IllegalArgumentException("weight is too large for this bin");
        }
        currentCapacity += weight;
        contents[index] = weight;
        index++;
    }

    /**
     * Returns the available space in this bin.
     * @return space in this bin
     */
    public int getSpace() {
        return capacity - currentCapacity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bin)) return false;
        Bin bin = (Bin) o;
        return capacity == bin.capacity &&
                Arrays.equals(contents, bin.contents);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(capacity, Arrays.hashCode(contents));
    }
}
