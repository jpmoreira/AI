package tests;

import static org.junit.Assert.*;
import mainPackage.State;
import mainPackage.Tile;
import mainPackage.constructions.Construction;

import org.junit.Test;

public class ConstructionTests {

	@Test
	public void testIndex1() {
		
		Construction.resetConstructions();//reset constructions
		assertEquals(Construction.latestConstructionIndex(), -1);
		
	}
	
	@Test
	public void testIndex2() {
		
		Construction.resetConstructions();
		Construction c=new Construction("dummy") {
			
			@Override
			public double affinityToTileInState(Tile tile, State state) {
				
				return 0;
			}
		};
		Construction c2=new Construction("dummy2") {
			
			@Override
			public double affinityToTileInState(Tile tile, State state) {
				
				return 0;
			}
		};
		
		assertEquals(Construction.latestConstructionIndex(), 1);
		
		Construction.resetConstructions();
		
		assertEquals(Construction.latestConstructionIndex(), -1);
	}

}
