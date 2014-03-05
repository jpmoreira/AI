package tests;

import static org.junit.Assert.*;
import mainPackage.InputException;
import mainPackage.Solver;

import org.junit.Test;

public class SolverInputTest {
	
	
	@Test
	public void testInputException1() {
	    try {
			Solver s=new Solver(-1, 0);
			fail("Did Not throw");
		} catch (InputException e) {
			assertEquals(e.code, InputException.InputExceptionCode.InvalidTiles_Code.ordinal());
		}
	}
	
	@Test
	public void testInputException2() {
	    try {
			Solver s=new Solver(2, -1);
			fail("Did Not throw");
		} catch (InputException e) {
			assertEquals(e.code, InputException.InputExceptionCode.InvalidConstruction_Code.ordinal());
		}
	}
	
	@Test
	public void noExeption(){
		
		try {
			Solver s=new Solver(3,1);
			assertEquals(1,1);
		} catch (InputException e) {
			fail("Thown but shouldn't have done it");
		}
		
	}
}
