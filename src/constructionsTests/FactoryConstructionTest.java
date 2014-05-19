package constructionsTests;

import static org.junit.Assert.*;
import mainPackage.TileProblemState;
import mainPackage.Tile;
import mainPackage.constructions.AirportConstruction;
import mainPackage.constructions.Construction;
import mainPackage.constructions.FactoryConstruction;
import mainPackage.constructions.PrisonConstruction;

import org.junit.Test;

public class FactoryConstructionTest {

	@Test
	public void testNoAirportNear() {
		
		FactoryConstruction factory=new FactoryConstruction();
		Construction c=new Construction("Dummy");
		
		Tile factory_tile=new Tile();
		Tile dummy_tile=new Tile();
		dummy_tile.addAdjacentTile(factory_tile);
		
		

		
		TileProblemState s=new TileProblemState(new Construction[]{factory,c},new Tile[]{factory_tile,dummy_tile});
		
		assertEquals(0.8, factory.affinityToTileInState(factory_tile, s),0.00001);
	}
	@Test
	public void testEverythingOK(){
		
		
		FactoryConstruction factory=new FactoryConstruction();
		AirportConstruction airport=new AirportConstruction();
		
		Tile factory_tile=new Tile();
		Tile airport_tile=new Tile();
		airport_tile.addAdjacentTile(factory_tile);
		
		

		
		TileProblemState s=new TileProblemState(new Construction[]{factory,airport},new Tile[]{factory_tile,airport_tile});
		
		assertEquals(1.0, factory.affinityToTileInState(factory_tile, s),0.00001);
		
		
	}

}
