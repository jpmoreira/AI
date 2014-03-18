package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import mainPackage.Population;
import mainPackage.State;
import mainPackage.Tile;
import mainPackage.constructions.Construction;

import org.junit.Test;

import Exceptions.ConstructionException;

public class PopulationTest {

	@Test
	public void testNrStates() {
		
		Tile tile1=new Tile();
		Tile tile2=new Tile();
		
		Construction c1;
		Construction c2;
		
		try {
			
			c1=new Construction(10,10) {
				
				@Override
				public float affinityToTile(Tile tile) {	
					return 0;
				}
			};
			c2 = new Construction(20,20) {
				
				@Override
				public float affinityToTile(Tile tile) {
					return 0;
				}
			};
			
			Tile[] tiles={tile1,tile2};
			Construction[] constructions={c1,c2};
			
			Population pop=new Population(tiles, constructions, 2);
			
			
			State[] states=pop.states;
			
			assertEquals(2,states.length);
			
			
			
			
			
			
		} catch (ConstructionException e) {
			fail(e.message());
		}
		
		
		
		
		
		
	}
	
	
	
	
	

}
