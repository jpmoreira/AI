package constructionsTests;
import static org.junit.Assert.*;
import mainPackage.State;
import mainPackage.Tile;
import mainPackage.Tile.SoilType;
import mainPackage.constructions.Construction;
import mainPackage.constructions.HouseConstruction;
import mainPackage.constructions.ParkConstruction;
import mainPackage.constructions.PrisonConstruction;

import org.junit.Test;

public class HouseConstructionTests {

	@Test
	public void testAffinityFunction1() {
		HouseConstruction c=new HouseConstruction();
		PrisonConstruction p=new PrisonConstruction();
		
		Tile houseTile=new Tile(SoilType.SoilType_Clay, 250.0, 0.9, 10);
		Tile prisonTile=new Tile(SoilType.SoilType_Peaty,150,0.9,10);
		prisonTile.addAdjacentTile(houseTile);
		
		State s=new State(new Construction[] {c,p}, new Tile[] {houseTile,prisonTile});
		
		double aff=c.affinityToTileInState(houseTile, s);
		assertEquals(0.35,aff,0.0001);
	}
	
	
	@Test
	public void testAffinityFunction2() {
		HouseConstruction c=new HouseConstruction();
		PrisonConstruction p=new PrisonConstruction();
		ParkConstruction pa=new ParkConstruction();
		
		Tile houseTile=new Tile(SoilType.SoilType_Clay, 250.0, 0.9, 10);
		Tile prisonTile=new Tile(SoilType.SoilType_Peaty,150,0.9,10);
		Tile parkTile=new Tile(SoilType.SoilType_Peaty,150,0.9,10);
		
		prisonTile.addAdjacentTile(houseTile);
		parkTile.addAdjacentTile(houseTile);
		
		
		State s=new State(new Construction[] {c,p,pa}, new Tile[] {houseTile,prisonTile,parkTile});
		
		double aff=c.affinityToTileInState(houseTile, s);
		assertEquals(0.4,aff,0.0001);
	}

}
