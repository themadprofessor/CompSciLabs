package wishlist;

import java.io.*;
import java.util.*;

public class WishList implements Serializable {
    private static final long serialVersionUID = 123L;

    private SortedSet<BrickSet> sets;

    /**
     * Creates a new WishList.
     */
    public WishList() {
        sets = new TreeSet<>();
    }

    /**
     * Returns the collection of BrickSets in this WishList, in an unmodifiable collection, in increasing order of
     * BrickSet's setNumber.
     * @return An ordered, unmodifiableCollection of BrickSets
     */
    public Collection<BrickSet> getSets() {
        return Collections.unmodifiableSortedSet(sets);
    }

    /**
     * Adds the given BrickSet to this WishList.
     * @param set The BrickSet to be added
     * @return Returns true if the set was successfully added, false otherwise
     */
    public boolean addSet(BrickSet set) {
        return sets.add(set);
    }

    /**
     * Removes the given BrickSet from this WishList.
     * @param set The BrickSet to be removed
     * @return Returns true if the set was successfully removed, false otherwise
     */
    public boolean removeSet(BrickSet set) {
        return sets.remove(set);
    }

    /**
     * Writes this WishList to the given file according to
     * <a href="https://docs.oracle.com/javase/8/docs/platform/serialization/spec/output.html>Java's Object Serialization Specification</a>.
     * @param filename The file to write this WishList to
     * @throws IOException Thrown if an IO error occurs during writing
     */
    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) {
            out.writeObject(this);
        }
    }

    /**
     * Reads a new WishList from the given file according to
     * <a href="https://docs.oracle.com/javase/8/docs/platform/serialization/spec/input.html">Java's Object Serialization Specification</a>.
     * @param filename The file to read from
     * @return The WishList in the given file
     * @throws IOException Thrown if an IO error occurs during reading or the file contains an object which is not defined
     */
    public static WishList loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename))))  {
            return (WishList) in.readObject();
        }
    }


}
