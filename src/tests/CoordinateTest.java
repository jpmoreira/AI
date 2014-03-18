package tests;

import static org.junit.Assert.*;
import mainPackage.Coordinate;

import org.junit.Test;

public class CoordinateTest {

	@Test
	public void testDistance1() {
		Coordinate TOKYO=new Coordinate(35.61472, 139.58111);//coordinates for Tokyo
		Coordinate BOSTON=new Coordinate(42.35,-71.06667);//coordinates for Boston
		
		assertEquals(8833, TOKYO.distanceToPoint(BOSTON), 100);
	}
	//TODO correct this

}
