package tests;

import static org.junit.Assert.*;
import mainPackage.TileProblemPopulation;
import mainPackage.TileProblemState;
import mainPackage.Tile;
import mainPackage.constructions.AirportConstruction;
import mainPackage.constructions.Construction;
import mainPackage.constructions.FactoryConstruction;
import mainPackage.constructions.HouseConstruction;

import org.junit.Test;

import Exceptions.ConstructionException;

public class StateTests {

	@Test
	public void pairingTest1() {

		Construction[] constructions = new Construction[3];
		Tile[] tiles = new Tile[3];

		constructions[0] = new Construction("Parque") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {

				return 0;
			}
		};
		constructions[1] = new Construction("Moradia") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {

				return 0;
			}
		};
		constructions[2] = new Construction("Aeroporto") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {

				return 0;
			}
		};

		TileProblemPopulation p = new TileProblemPopulation(tiles, constructions, 2,1.0);

		TileProblemState a = p.states()[0];
		TileProblemState b = p.states()[1];
		Integer[] segments = { 0, 1, 2 };

		TileProblemState[] childs = a.pairWith(b, segments);

		assertArrayEquals(childs[0].constructions, a.constructions);
	}

	@Test
	public void pairingTest2() {

		Construction[] constructions = new Construction[3];
		Tile[] tiles = new Tile[3];

		constructions[0] = new Construction("Parque") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 0;
			}
		};
		constructions[1] = new Construction("Moradia") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {

				return 0;
			}
		};
		constructions[2] = new Construction("Apartamentos") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {

				return 0;
			}
		};

		TileProblemPopulation p = new TileProblemPopulation(tiles, constructions, 2);

		TileProblemState a = p.states()[0];
		TileProblemState b = p.states()[1];
		Integer[] segments = { 0, 2 };

		TileProblemState[] childs = a.pairWith(b, segments);
		TileProblemState c = childs[0];
		TileProblemState d = childs[1];

		assertEquals(c.constructions[0], a.constructions[0]);
		assertEquals(c.constructions[1], b.constructions[1]);
		assertEquals(c.constructions[2], a.constructions[2]);

		assertEquals(d.constructions[0], b.constructions[0]);
		assertEquals(d.constructions[1], a.constructions[1]);
		assertEquals(d.constructions[2], b.constructions[2]);

	}

	@Test
	public void chromossomeTests() {

		Construction.resetConstructions();

		Construction c = new Construction("Moradia") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 0;
			}
		};

		Construction c2 = new Construction("Apartamento") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 0;
			}
		};

		Construction[] array = { c, c2 };
		Tile[] tiles = { new Tile(), new Tile() };
		TileProblemState s = new TileProblemState(array, tiles);

		int[] expectedChromo = { 1, 2 };

		assertArrayEquals(expectedChromo, s.chromossome());

	}

	@Test
	public void testDiversity() {

		Tile tile1 = new Tile();
		Tile tile2 = new Tile();

		Construction c1 = new Construction("Moradia") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {

				return 0;
			}
		};
		Construction c2 = new Construction("Parque") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {

				return 0;
			}
		};
		Construction c3 = new Construction("Escola") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {

				return 0;
			}
		};

		Tile[] tiles = { tile1, tile2 };
		Construction[] constructions1 = { c1, c2, c3 };
		Construction[] constructions2 = { c2, c2, c1 };

		TileProblemState state1 = new TileProblemState(constructions1, tiles);
		TileProblemState state2 = new TileProblemState(constructions2, tiles);

		TileProblemState[] states = { state1, state2 };

		assertEquals(2/3.0, state1.diversity(states),0.0001);

	}

	@Test
	public void testDiversity2() { 

		Tile tile1 = new Tile();
		Tile tile2 = new Tile();

		Construction c1 = new Construction("Moradia") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {

				return 0;
			}
		};
		Construction c2 = new Construction("Apartamento") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {

				return 0;
			}
		};
		Construction c3 = new Construction("Parque") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {

				return 0;
			}
		};

		Tile[] tiles = { tile1, tile2 };
		Construction[] constructions1 = { c1, c2, c3 };
		Construction[] constructions2 = { c1, c2, c1 };

		TileProblemState state1 = new TileProblemState(constructions1, tiles);
		TileProblemState state2 = new TileProblemState(constructions2, tiles);

		TileProblemState[] states = { state1,state2 };

		assertEquals(1/3.0, state1.diversity(states),0.0001);

	}

	@Test
	public void mutationToAcceptableConstruction() {

		
		Construction.resetConstructions();
		Construction c1=new Construction("A") {
			
			@Override
			public double affinityToTileInState(Tile tile, TileProblemState state) {
				return 0;
			}
		};
		Construction c2=new Construction("B") {
			
			@Override
			public double affinityToTileInState(Tile tile, TileProblemState state) {
				return 0;
			}
		};
		Construction c3=new Construction("C") {
			
			@Override
			public double affinityToTileInState(Tile tile, TileProblemState state) {
				return 0;
			}
		};
		
		Tile t1=new Tile();
		Tile t2=new Tile();
		Tile t3=new Tile();
		
		Construction[] constructions={c1,c2,c3};
		Tile[] tiles={t1,t2,t3};
		
		TileProblemState s=new TileProblemState(constructions, tiles);
		
		
		assertEquals(s.constructions[0], c1);
		s.mutate(0, 1);
		
		assertEquals(s.constructions[0], c2);

	}

	@Test
	public void fitnessWithingAcceptableBounds() {
		
		Construction c=new Construction("A");
		c.setMustHaveAdjacenciesConstraint(new String[]{AirportConstruction.class.getCanonicalName()}, new Construction[0], 0.1);

		AirportConstruction airport=new AirportConstruction();
		
		FactoryConstruction factory=new FactoryConstruction();
		
		HouseConstruction house=new HouseConstruction();
		
		Tile t1=new Tile();
		Tile t2=new Tile();
		Tile t3=new Tile();
		Tile t4=new Tile();
		t2.addAdjacentTile(t3);
		t2.addAdjacentTile(t1);
		t3.addAdjacentTile(t4);
		
		TileProblemState s=new TileProblemState(new Construction[]{c,airport,factory,house},new Tile[]{t1,t2,t3,t4});
		
		assertTrue(s.fitness()<=1.0);
		double mean=(c.affinityToTileInState(t1, s)+airport.affinityToTileInState(t2, s)+factory.affinityToTileInState(t3, s)+house.affinityToTileInState(t4, s))/4;
		assertEquals(s.fitness(),mean ,0.0001);
		
		
		
		

	}

	
	@Test
	public void evolveTest(){
		
		
		Construction.resetConstructions();
		
		Construction c1=new Construction("A");
		Construction c2=new Construction("B");
		Tile t1=new Tile();
		Tile t2=new Tile();
		
		TileProblemState s=new TileProblemState(new Construction[]{c2,c1}, new Tile[]{t1,t2});
		
		TileProblemState s2=(TileProblemState) s.evolve(0);
		
		
		
		assertEquals(s2.constructions[0],Construction.nullConstruction());
		
		s2=(TileProblemState)s2.evolve(0);
		
		assertEquals(s2.constructions[0],c1);
		
		
	}
}
