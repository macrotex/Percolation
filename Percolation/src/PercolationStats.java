
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

    public static void main(String[] args) {
        
    }

}


