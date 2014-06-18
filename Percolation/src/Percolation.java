

public class Percolation {
    // This is the N x N grid
    public int[][] grid ;
    public int size ;
    public int numberOpenSites ;

    private WeightedQuickUnionUF uf ;
    private int topVirtualSite ;
    private int bottomVirtualSite ;
    
    // There are two values that a grid element can have: "open" or "blocked"
    public static final int FULL = 0;
    public static final int OPEN = 1;
    
    /**
     * The Percolation class.
     * @param N
     */
    public Percolation(int N)              // create N-by-N grid, with all sites blocked
    {
        this.size = N ;
       
        // We want to initialize all entries to be full.
        grid = new int[size][size];
        for (int row = 0; row < grid.length; row++)
        {
            for (int col = 0; col < grid.length; col++) 
            {
                grid[row][col] = FULL ;
            }
        }
        
        // No sites are open.
        this.numberOpenSites = 0 ;
        
        // Create a UF object for all the points in the N-by-N grid 
        // PLUS two "virtual" sites (the top and bottom).
        // The "top" virtual site will have index 0.
        // The "bottom" virtual site will have index 1.
        uf = new WeightedQuickUnionUF(N*N + 2);
        
        // Set the top and bottom virtual sites.
        this.topVirtualSite    = N * N ;
        this.bottomVirtualSite = N * N + 1 ;
                
    }
    
    /*
     * 
     * Convert a grid coordinate (i,j) to a a single integer
     */
    private int rowColToSite(int i, int j) {
        return (i - 1) + ((j - 1) * this.size) ;
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
    
    // open site (row i, column j) if it is not already open.
    public void open(int row, int col) {
        if (this.isFull(row, col)) {
            this.grid[row - 1][col - 1] = OPEN ;
            this.numberOpenSites = this.numberOpenSites + 1 ;
            
            // We need to connect this site to the open sites
            // to the North, South, East, and West.
            int mySite = this.rowColToSite(row, col) ;
            
            // NORTH
            if (row == 1) {
                // This is the top row, so connect to the top virtual site
                //System.out.println("unioning with top Virtual site");
                this.uf.union(this.topVirtualSite, mySite);
            } else {
                // This is NOT the top row, so connect to the above element IF 
                // the above element is open.
                if (isOpen(row - 1, col)) {
                    this.uf.union(rowColToSite(row - 1, col), mySite);
                }
            }
            
            // SOUTH
            if (row == size) {
                // This is the bottom row, so connect to the bottom virtual site
                //System.out.println("unioning with bottom Virtual site");
                this.uf.union(bottomVirtualSite, mySite);
            } else {
                // This is NOT the bottom row, so connect to the below element IF 
                // the above element is open.
                if (isOpen(row + 1, col)) {
                    this.uf.union(rowColToSite(row + 1, col), mySite);
                }
            }
            
            // WEST
            if (col != 1) {
                // This is not on the left edge, so connect to the 
                // western neightbor.
                if (isOpen(row, col - 1)) {
                    this.uf.union(rowColToSite(row, col - 1), mySite);
                }
            }
            
            // EAST
            if (col != size) {
                // This is not on the RIGHT edge, so connect to the 
                // eastern neighbor.
                if (isOpen(row, col + 1)) {
                    this.uf.union(rowColToSite(row, col + 1), mySite);
                }
            }
            
        }
    }
    
    // does the system percolate?
    public boolean percolates()  {
        //System.out.println(uf.count());
        return this.uf.connected(this.topVirtualSite, this.bottomVirtualSite) ;
    }
}
