package tests;
//TODO set price constraints
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
		HouseConstruction c=new HouseConstruction(100, 200);
		PrisonConstruction p=new PrisonConstruction(200, 500);
		
		Tile houseTile=new Tile(SoilType.SoilType_Clay, 250.0, 0.9, 10);
		Tile prisonTile=new Tile(SoilType.SoilType_Peaty,150,0.9,10);
		prisonTile.addAdjacentTile(houseTile);
		
		State s=new State(new Construction[] {c,p}, new Tile[] {houseTile,prisonTile});
		
		double aff=c.affinityToTileInState(houseTile, s);
		assertEquals(0.15,aff,0.0001);
	}
	
	
	@Test
	public void testAffinityFunction2() {
		HouseConstruction c=new HouseConstruction(100, 200);
		PrisonConstruction p=new PrisonConstruction(200, 500);
		ParkConstruction pa=new ParkConstruction();
		
		Tile houseTile=new Tile(SoilType.SoilType_Clay, 250.0, 0.9, 10);
		Tile prisonTile=new Tile(SoilType.SoilType_Peaty,150,0.9,10);
		Tile parkTile=new Tile(SoilType.SoilType_Peaty,150,0.9,10);
		
		prisonTile.addAdjacentTile(houseTile);
		parkTile.addAdjacentTile(houseTile);
		
		
		State s=new State(new Construction[] {c,p,pa}, new Tile[] {houseTile,prisonTile,parkTile});
		
		double aff=c.affinityToTileInState(houseTile, s);
		assertEquals(0.2,aff,0.0001);
	}

}
