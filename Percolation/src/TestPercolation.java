import static org.junit.Assert.*;

import org.junit.Test;

import coursera.adamhl.percolation.Percolation;

public class TestPercolation {

	@Test
	public void PercolationTestsBasic() {
		// Create a Percolation object.
		Percolation percolation = new Percolation(10) ;
		assertTrue(percolation instanceof coursera.adamhl.percolation.Percolation) ;
		
		// Check that some of the elements in the initialized array are BLOCKED.
		assertEquals(percolation.grid[0][0], Percolation.FULL) ;
		assertEquals(percolation.grid[2][3], Percolation.FULL) ;
		
		// Check the is_blocked method
		assertTrue(percolation.isFull(1, 1)) ;
		assertTrue(percolation.isFull(2, 3)) ;
		assertTrue(percolation.isFull(10, 10)) ;
		
		assertFalse(percolation.isOpen(1, 1)) ;
		
	    percolation.open(1, 1);
	    assertTrue(percolation.isOpen(1, 1)) ;
	    
	    // Does this system percolate? (NO!)
	    //assertFalse(percolation.percolates()) ;
	}

	// Test for exceptions.
	@Test(expected = java.lang.IndexOutOfBoundsException.class)
	public void PercolationTestsException1() {
		Percolation percolation = new Percolation(10) ;
		assertTrue(percolation.isOpen(20, 1)) ;
	}
	
	@Test(expected = java.lang.IndexOutOfBoundsException.class)
	public void PercolationTestsException2() {
		Percolation percolation = new Percolation(10) ;
		assertTrue(percolation.isOpen(11, 1)) ;
	}
	
	@Test(expected = java.lang.IndexOutOfBoundsException.class)
	public void PercolationTestsException3() {
		Percolation percolation = new Percolation(10) ;
		assertTrue(percolation.isOpen(1, 20)) ;
	}
	
	@Test(expected = java.lang.IndexOutOfBoundsException.class)
	public void PercolationTestsException4() {
		Percolation percolation = new Percolation(10) ;
		assertTrue(percolation.isOpen(1, 11)) ;
	}
	
	@Test(expected = java.lang.IndexOutOfBoundsException.class)
	public void PercolationTestsException5() {
		Percolation percolation = new Percolation(10) ;
		assertTrue(percolation.isOpen(0, 6)) ;
	}
	
	@Test(expected = java.lang.IndexOutOfBoundsException.class)
	public void PercolationTestsException6() {
		Percolation percolation = new Percolation(10) ;
		assertTrue(percolation.isOpen(6, 0)) ;
	}
}
