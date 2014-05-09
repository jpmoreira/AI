package tests;

import static org.junit.Assert.*;
import mainPackage.State;
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

	@Test
	public void constructionFromConstraintTest(){
		
		Tile t1=new Tile();
		Tile t2=new Tile();
		Tile t3=new Tile();
		Tile t4=new Tile();
		t3.area=500;
		t1.addAdjacentTile(t2);
		t3.addAdjacentTile(t2);
		t3.addAdjacentTile(t4);
		Tile[] badTiles={t2};
		
		String[] forbClasses={PrisonConstruction.class.getCanonicalName()};
		
		Construction c1=Construction.constructionWithConstraints("A", 0.1, 0.0, new Tile[0], 0.0, 10, 20, 0.0, new Tile.SoilType[0],0.0,forbClasses);//1-0.1
		Construction c2=Construction.constructionWithConstraints("B", 0.1, 0.1, badTiles, 0.0, 10, 20, 0.0, new Tile.SoilType[0],0.0,forbClasses);//1-0.1
		Construction c3=Construction.constructionWithConstraints("C", 0.05, 0.1, new Tile[0], 0.025, 10, 100, 0.0, new Tile.SoilType[0],0.025,forbClasses);//1-0.05-0.025
		PrisonConstruction c4=new PrisonConstruction(0,10000);
		

		
		Construction[] forbiddenForC1={c2};
		c1.forbiddenAdjacencies=forbiddenForC1;
		c3.forbiddenAdjacencies=forbiddenForC1;
		Construction [] allConstructions={c1,c2,c3,c4};
		Tile [] allTiles={t1,t2,t3,t4};
		
		State s=new State(allConstructions, allTiles);
		
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
