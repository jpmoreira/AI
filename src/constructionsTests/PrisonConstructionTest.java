package constructionsTests;

import static org.junit.Assert.*;
import mainPackage.TileProblemState;
import mainPackage.Tile;
import mainPackage.constructions.AirportConstruction;
import mainPackage.constructions.Construction;
import mainPackage.constructions.PrisonConstruction;

import org.junit.Test;

public class PrisonConstructionTest {

	@Test
	public void testPenaltyAirportNear() {
		PrisonConstruction pri=new PrisonConstruction();
		AirportConstruction air=new AirportConstruction();
		
		Tile prisonTile=new Tile();
		Tile airportTile=new Tile();
		prisonTile.addAdjacentTile(airportTile);
		
		TileProblemState s=new TileProblemState(new Construction[]{pri,air},new Tile[]{prisonTile,airportTile} );
		
		double aff=pri.affinityToTileInState(prisonTile, s);
		
		assertEquals(0.7, aff,0.00001);
	}
	
	
	@Test
	public void testPenaltyAirportNearAndHighPrice() {
		PrisonConstruction pri=new PrisonConstruction();
		AirportConstruction air=new AirportConstruction();
		
		Tile prisonTile=new Tile();
		Tile airportTile=new Tile();
		prisonTile.addAdjacentTile(airportTile);
		prisonTile.setPricePerAreaUnit(100);
		
		TileProblemState s=new TileProblemState(new Construction[]{pri,air},new Tile[]{prisonTile,airportTile} );
		
		double aff=pri.affinityToTileInState(prisonTile, s);
		
		assertEquals(0.65, aff,0.00001);
	}
	
	@Test
	public void noPenalty() {
		PrisonConstruction pri=new PrisonConstruction();
		AirportConstruction air=new AirportConstruction();
		
		Tile prisonTile=new Tile();
		Tile airportTile=new Tile();
		
		TileProblemState s=new TileProblemState(new Construction[]{pri,air},new Tile[]{prisonTile,airportTile} );
		
		double aff=pri.affinityToTileInState(prisonTile, s);
		
		assertEquals(1.0, aff,0.00001);
	}

}
