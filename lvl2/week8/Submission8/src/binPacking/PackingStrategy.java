package binPacking;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * A class containing static methods which will store the given weights into a few bins as possible.
 */
public class PackingStrategy {
    /**
     * Stores the given weights in as few Bins of given capacity as possible using the Best Fit Decreasing algorithm.
     * @param weights weights to be stored
     * @param capacity capacity of bins
     * @return list of bins storing the given weights
     */
    public static List<Bin> packBestFit(List<Integer> weights, int capacity) {
        List<Bin> bins = new ArrayList<>();
        weights.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(weight -> bins.stream()
                        .filter(bin -> bin.getSpace() >= weight)
                        .findFirst()
                        .orElseGet(()-> addNewBin(capacity, bins))
                        .store(weight));
        return bins;
    }

    /**
     * Creates a new Bin with given capacity, store the new Bin in the given list and return the new Bin.
     * @param capacity capacity of the bin
     * @param bins list of bins to add the new list to
     * @return new bin
     */
    private static Bin addNewBin(int capacity, List<Bin> bins) {
        Bin bin = new Bin(capacity);
        bins.add(bin);
        return bin;
    }

    /**
     * Stores the given weights in as few Bins of given capacity as possible using the Best Fit Decreasing algorithm by
     * splitting the task among the given number of threads.
     * @param allInts weights to be stored
     * @param capacity capacity of bins
     * @param numThreads number of threads
     * @return list of bins storing the weights
     */
    public static List<Bin> packBestFitParallel(List<Integer> allInts, int capacity, int numThreads) {
        ExecutorService pool = Executors.newFixedThreadPool(numThreads);
        Collection<Callable<List<Bin>>> tasks = new ArrayList<>();

        int offset = allInts.size()/numThreads;
        for (int i = 0; i < numThreads; i++) {
            List<Integer> sublist = allInts.subList(i*offset, (i+1)*offset);
            tasks.add(() -> packBestFit(sublist, capacity));
        }
        try {
            List<Bin> bins = pool.invokeAll(tasks)
                    .stream()
                    .map(PackingStrategy::getResult)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
            pool.shutdown();
            return bins;
        } catch (InterruptedException e) {
            System.err.println("Tasks were interrupted! " + e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * Waits for the given Future to finish, and return its value. If the Future throws an exception, return an empty
     * list.
     * @param future future to wait for
     * @return list of bin produced by given future or an empty list if the future fails
     */
    private static List<Bin> getResult(Future<List<Bin>> future) {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            return Collections.emptyList();
        }
    }
}
