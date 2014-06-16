
package coursera.adamhl.percolation;
public class Percolation {
	// This is the N x N grid
	public int[][] grid ;
	
	// There are two values that a grid element can have: "open" or "blocked"
	public static final int BLOCKED = 0;
	public static final int OPEN = 0;
	
	public Percolation(int N)              // create N-by-N grid, with all sites blocked
	{
		// We want to initialize all entries to blocked.
		grid = new int[N][N];
		for(int row = 0; row < grid.length; row++)
		{
			for (int col = 0; col < grid.length; col++) 
			{
				grid[row][col] = BLOCKED ;
			}
		}

	}
//    public void open(int i, int j)         // open site (row i, column j) if it is not already
//	public boolean isOpen(int i, int j)    // is site (row i, column j) open?
//    public boolean isFull(int i, int j)    // is site (row i, column j) full?
//	public boolean percolates()            // does the system percolate?
}
