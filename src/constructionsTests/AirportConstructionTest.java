package constructionsTests;

import static org.junit.Assert.*;
import mainPackage.TileProblemState;
import mainPackage.Tile;
import mainPackage.constructions.AirportConstruction;
import mainPackage.constructions.Construction;

import org.junit.Test;

public class AirportConstructionTest {

	@Test
	public void testInclination() {
		AirportConstruction airport=new AirportConstruction();
		
		Tile t=new Tile();
		t.setMaxInclination(0.9);
		
		TileProblemState s=new TileProblemState(new Construction[]{airport},new Tile[]{t});
		
		assertEquals(0.6, airport.affinityToTileInState(t, s),0.00001);
	}

}
