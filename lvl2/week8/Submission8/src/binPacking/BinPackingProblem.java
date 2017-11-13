package binPacking;

import java.util.List;

/**
 * A Runnable wrapper to {@link PackingStrategy#packBestFit(List, int)}.
 */
public class BinPackingProblem implements Runnable {
    private List<Integer> weights;
    private List<Bin> bins;
    private int capcity;

    /**
     * Creates a new BinPackingProblem for the given weights and capacity.
     * @param weights weights to be used
     * @param capacity max capacity of bins
     */
    public BinPackingProblem(List<Integer> weights, int capacity) {
        this.weights = weights;
        this.capcity = capacity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        bins = PackingStrategy.packBestFit(weights, capcity);
    }

    /**
     * Returns the result of Runnable's packing.
     * @return resulting bins
     */
    public List<Bin> getBins() {
        return bins;
    }
}
