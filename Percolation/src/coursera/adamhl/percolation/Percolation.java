
package coursera.adamhl.percolation;
public class Percolation {
	// This is the N x N grid
	public int[][] grid ;
	
	// There are two values that a grid element can have: "open" or "blocked"
	public static final int FULL = 0;
	public static final int OPEN = 1;
	
	public Percolation(int N)              // create N-by-N grid, with all sites blocked
	{
		// We want to initialize all entries to blocked.
		grid = new int[N][N];
		for (int row = 0; row < grid.length; row++)
		{
			for (int col = 0; col < grid.length; col++) 
			{
				grid[row][col] = FULL ;
			}
		}

	}
	
	public boolean isFull(int i, int j) {
		if (grid[i-1][j-1] == FULL) {
			return true ;
		} else {
			return false ;
		}
	}

	public boolean isOpen(int i, int j) {
		return (!this.isFull(i, j)) ;
	}
	
	// open site (row i, column j) if it is not already
	public void open(int i, int j) {
		if (this.isFull(i,j)) {
			this.grid[i-1][j-1] = OPEN ;		
		}
	}
	
	// does the system percolate?
	public boolean percolates()  {
		return true ;
	}
}
