

public class PercolationStats {

	private Percolation perc ;
	private int numberTestsPerformed ;
	private double[] fractionOpenSites ;
	
	// perform T independent computational experiments on an N-by-N grid
	public PercolationStats(int N, int T)
	{
		fractionOpenSites = new double[T] ;
		
		
		// Run T experiments
		for (int i = 0; i < T; ++i) {
			perc = new Percolation(N) ;	
			this.experiment() ;
			
			this.numberTestsPerformed = this.numberTestsPerformed + 1 ;
			this.fractionOpenSites[i] = 
					(perc.numberOpenSites / (double) (perc.size * perc.size)) ;
		}
		
		System.out.println(this.mean());
		System.out.println(this.stddev());
		
	}

	// Perform a single percolation experiment. Returns nothing.
	public void experiment() {

		while (!perc.percolates()) {			
			// Pick a random site.
			int[] random_site ;
			random_site = chooseRandomSite() ;
			
			// Open that site.
			this.perc.open(random_site[0],  random_site[1]);	
		}
		
		System.out.println("number of open sites: " + this.perc.numberOpenSites) ;
		
		return ;
	}
	
	// Pick a random site between (row, column) in {(a,b): 1<=a,b<=N}
	private int[] chooseRandomSite() {
				
		// Pick a random row and column.
		int[] random_site ;
		random_site = new int[2] ;

		int size = this.perc.size ;

		random_site[0] = 1 + (int) (Math.random() * size) ;
		random_site[1] = 1 + (int) (Math.random() * size) ;
		
		return random_site ;
	}
	
	
	public double mean() {
		double sum = 0.0 ;
		for (int i=0; i < this.numberTestsPerformed; ++i )
		{
			sum = sum + this.fractionOpenSites[i] ;
		}
		return (sum / this.numberTestsPerformed) ;
	}
	
	
	// sample standard deviation of percolation threshold
	public double stddev() {
		double numerator = 0.0 ;
		double mean = this.mean() ;

		for (int i=0; i < this.numberTestsPerformed; ++i )
		{
			double diff = mean - this.fractionOpenSites[i] ;
			numerator = numerator + (diff * diff) ;
		}
		
		return Math.sqrt(numerator / (double)(this.numberTestsPerformed - 1)) ;	
	}
	
	
	public static void main()
	{
		System.out.println("Hello World!");
		
	}
}


