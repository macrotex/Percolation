/**
 * 
 * @author Yer Blues
 *
 */
public class PercolationStats {

    private Percolation perc;           // used to decide when grid percolates
    private int numberTestsPerformed;   // the number of runs performed
    private double[] fractionOpenSites; // stores the fraction of sites needed for percolation
                                        // for each run.
    
    /**
     * Runs the percolation experiment T times on a grid of square 
     * size N by N. For each run stores the fraction of sites in the 
     * grid that had to be opened before the grid percolates. 
     *  
     * @param gridSize     the size of the grid (gridSize-by-gridSize)
     * @param numberOfRuns the number of times to run the experiment
     */
    public PercolationStats(int gridSize, int numberOfRuns) {
        this.fractionOpenSites = new double[numberOfRuns];

        // Run numberOfRuns experiments
        for (int i = 0; i < numberOfRuns; ++i) {
            this.perc = new Percolation(gridSize);
            this.experiment();

            this.numberTestsPerformed = this.numberTestsPerformed + 1;
            this.fractionOpenSites[i] =
                    (this.perc.numberOpenSites / (double) (this.perc.size * this.perc.size));
        }

        System.out.println(this.mean());
        System.out.println(this.stddev());

    }

    // Perform a single percolation experiment. Returns nothing.
    private void experiment() {

        // Keep running until percolation happens.
        while (!this.perc.percolates()) {
            // Pick a random site.
            int[] randomSite;
            randomSite = this.chooseRandomSite();

            // Open that site.
            this.perc.open(randomSite[0],  randomSite[1]);
        }

        return;
    }

    // Pick a random site between (row, column) in {(a,b): 1<=a,b<=N}
    private int[] chooseRandomSite() {

        // Pick a random row and column.
        int[] randomSite;
        randomSite = new int[2];

        int size = this.perc.size;

        randomSite[0] = 1 + StdRandom.uniform(size);
        randomSite[1] = 1 + StdRandom.uniform(size);

        return randomSite;
    }

    public double mean() {
        return StdStats.mean(this.fractionOpenSites);
    }


    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.fractionOpenSites);
    }
    
    /**
     * Given the mean and standard deviation, return the lower 
     * part of the 95% confidence interval.
     * @param mean   the mean for all the runs
     * @param stdDev the standard deviation for all runs.
     * @return the high end of the 95% confidence interval
     */
    public double confidenceHi(double mean, double stdDev) {    
        return mean + ((1.96 * stdDev) / Math.sqrt(this.numberTestsPerformed));
    }

    /**
     * Given the mean and standard deviation, return the lower 
     * part of the 95% confidence interval.
     * @param mean   the mean for all the runs
     * @param stdDev the standard deviation for all runs.
     * @return the low end of the 95% confidence interval
     */
    public double confidenceLo(double mean, double stdDev) {    
        return mean - ((1.96 * stdDev) / Math.sqrt(this.numberTestsPerformed));
    }

    /**
     * With QuickFind,         the elapsed time is ~ T*(N^2.4)
     * With WeightedQuickFind, the elapsed time is ~ T*(N^1)
     * @param args
     */
    public static void main(String[] args) {
        int gridSize      = Integer.parseInt(args[0]);
        int numberOfTests = Integer.parseInt(args[1]);
        
        Stopwatch stopwatch = new Stopwatch();
        
        PercolationStats perStat = new PercolationStats(gridSize, numberOfTests);

        double mean = perStat.mean();
        double stdDev = perStat.stddev();
        double confHi = perStat.confidenceHi(mean, stdDev);
        double confLo = perStat.confidenceLo(mean, stdDev);
        
        System.out.println(String.format("%-24s= %f", "mean", mean));
        System.out.println(String.format("%-24s= %f", "stddev", stdDev));
        System.out.println(String.format("%-24s= %f, %f", "95% confidence interval", confLo, confHi));
        
        System.out.println(String.format("%s: %f", "elapsed time", stopwatch.elapsedTime()));

    }

}


