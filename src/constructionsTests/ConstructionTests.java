package constructionsTests;

import static org.junit.Assert.*;
import mainPackage.TileProblemState;
import mainPackage.Tile;
import mainPackage.constructions.Construction;
import mainPackage.constructions.PrisonConstruction;

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
			public double affinityToTileInState(Tile tile, TileProblemState state) {
				
				return 0;
			}
		};
		Construction c2=new Construction("dummy2") {
			
			@Override
			public double affinityToTileInState(Tile tile, TileProblemState state) {
				
				return 0;
			}
		};
		
		assertEquals(Construction.latestConstructionIndex(), 1);
		
		Construction.resetConstructions();
		
		assertEquals(Construction.latestConstructionIndex(), -1);
	}

	@Test
	public void constructionFromConstraintTest(){
		
		Tile t1=new Tile();
		Tile t2=new Tile();
		Tile t3=new Tile();
		Tile t4=new Tile();
		t3.setArea(500);
		t1.addAdjacentTile(t2);
		t3.addAdjacentTile(t2);
		t3.addAdjacentTile(t4);
		t3.setSoilType(Tile.SoilType.SoilType_Peaty);
		Tile[] badTiles={t2};
		t1.setPricePerAreaUnit(200);
		
		String[] forbClasses={PrisonConstruction.class.getCanonicalName()};
		
		
		Construction c1=Construction.anonymousConstruction("A");
		Construction c2=Construction.anonymousConstruction("B");
		Construction c3=Construction.anonymousConstruction("C");
		

	
		c1.setForbiddenAdjacenciesConstraint(new Construction[]{c2},new String[0], 0.05);
		c1.setPriceConstraint(0, 100, 0.05);
		c2.setTilesConstraint(badTiles, 0.1);
		c3.setAreaConstraint(10, 100, 0.025);
		c3.setSoilConstraint(new Tile.SoilType[]{Tile.SoilType.SoilType_Peaty}, 0.05);
		c3.setForbiddenAdjacenciesConstraint(new Construction[0], forbClasses, 0.025);
		
		PrisonConstruction c4=new PrisonConstruction();
		

		
		Construction [] allConstructions={c1,c2,c3,c4};
		Tile [] allTiles={t1,t2,t3,t4};
		
		TileProblemState s=new TileProblemState(allConstructions, allTiles);
		
		double c1Aff=c1.affinityToTileInState(t1, s);
		double c2Aff=c2.affinityToTileInState(t2, s);
		double c3Aff=c3.affinityToTileInState(t3, s);
		double c4Aff=c4.affinityToTileInState(t4, s);
		System.out.println(c1Aff);
		System.out.println(c2Aff);
		System.out.println(c3Aff);
		
		double fitness=s.fitness();
		
		assertEquals(fitness, (0.9*3+c4Aff)/4,0.0001);
		
	}
}
