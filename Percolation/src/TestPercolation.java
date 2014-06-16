import static org.junit.Assert.*;

import org.junit.Test;

import coursera.adamhl.percolation.Percolation;


public class TestPercolation {

	@Test
	public void PercolationTests() {
		// Create a Percolation object.
		Percolation percolation = new Percolation(10) ;
		assertTrue(percolation instanceof coursera.adamhl.percolation.Percolation) ;
		
		// Check that some of the elements in the initialized array are BLOCKED.
		assertEquals(percolation.grid[0][0], Percolation.BLOCKED) ;
		assertEquals(percolation.grid[2][3], Percolation.BLOCKED) ;
		
		assertTrue(true) ;
	}

}
