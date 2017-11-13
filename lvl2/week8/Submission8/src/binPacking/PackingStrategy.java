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
                        //Find the first bin with enough space, creating a new one if none are found, then add the weight
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
        Thread[] threads = new Thread[numThreads];
        BinPackingProblem[] bins = new BinPackingProblem[numThreads];
        int offset = allInts.size()/numThreads;

        for (int i = 0; i < threads.length; i++) {
            BinPackingProblem bin = new BinPackingProblem(allInts.subList(i*offset, (i+1)*offset), capacity);
            Thread thread = new Thread(bin);
            thread.start();
            threads[i] = thread;
            bins[i] = bin;
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Iterate over all the resulting List<Bin>, and collect all the contents into a single 1D list
        return Arrays.stream(bins)
                .map(BinPackingProblem::getBins)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
