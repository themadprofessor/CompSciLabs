package binPacking;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class PackingStrategy {
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

    private static Bin addNewBin(int capacity, List<Bin> bins) {
        Bin bin = new Bin(capacity);
        bins.add(bin);
        return bin;
    }

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

    private static List<Bin> getResult(Future<List<Bin>> future) {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            return new ArrayList<>();
        }
    }
}
