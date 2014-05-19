package constructionsTests;

import static org.junit.Assert.*;
import mainPackage.TileProblemState;
import mainPackage.Tile;
import mainPackage.constructions.AirportConstruction;
import mainPackage.constructions.FactoryConstruction;
import mainPackage.constructions.ParkConstruction;
import mainPackage.constructions.Construction;

import org.junit.Test;

public class ParkConstructionTest {

	@Test
	public void testAdjacenciesConstraintPlusInclinationGivingZeroAffinity() {
		ParkConstruction park=new ParkConstruction();
		AirportConstruction airport=new AirportConstruction();
		FactoryConstruction factory=new FactoryConstruction();
		
		Tile park_tile=new Tile();
		Tile airport_tile=new Tile();
		Tile factory_tile=new Tile();
		park_tile.setMaxInclination(0.6);
		
		park_tile.addAdjacentTile(airport_tile);
		park_tile.addAdjacentTile(factory_tile);
		
		
		TileProblemState s=new TileProblemState(new Construction[]{park,airport,factory},new Tile[]{park_tile,airport_tile,factory_tile});
		
		double aff=park.affinityToTileInState(park_tile, s);
		
		assertEquals(0.0,aff,0.00001);
		
	}

}
