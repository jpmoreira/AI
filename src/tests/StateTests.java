package tests;

import static org.junit.Assert.*;
import mainPackage.Population;
import mainPackage.State;
import mainPackage.Tile;
import mainPackage.constructions.Construction;

import org.junit.Test;

import Exceptions.ConstructionException;

public class StateTests {

	@Test
	public void pairingTest1() {

		Construction[] constructions = new Construction[3];
		Tile[] tiles = new Tile[3];

		constructions[0] = new Construction("Parque") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {

				return 0;
			}
		};
		constructions[1] = new Construction("Moradia") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {

				return 0;
			}
		};
		constructions[2] = new Construction("Aeroporto") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {

				return 0;
			}
		};

		Population p = new Population(tiles, constructions, 2, 1.0, 0);

		State a = p.states()[0];
		State b = p.states()[1];
		Integer[] segments = { 0, 1, 2 };

		State[] childs = a.pairWith(b, segments);

		assertArrayEquals(childs[0].constructions, a.constructions);
	}

	@Test
	public void pairingTest2() {

		Construction[] constructions = new Construction[3];
		Tile[] tiles = new Tile[3];

		constructions[0] = new Construction("Parque") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 0;
			}
		};
		constructions[1] = new Construction("Moradia") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {

				return 0;
			}
		};
		constructions[2] = new Construction("Apartamentos") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {

				return 0;
			}
		};

		Population p = new Population(tiles, constructions, 2, 1.0, 0);

		State a = p.states()[0];
		State b = p.states()[1];
		Integer[] segments = { 0, 2 };

		State[] childs = a.pairWith(b, segments);
		State c = childs[0];
		State d = childs[1];

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
			public double affinityToTileInState(Tile tile, State s) {
				return 0;
			}
		};

		Construction c2 = new Construction("Apartamento") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 0;
			}
		};

		Construction[] array = { c, c2 };
		Tile[] tiles = { new Tile(), new Tile() };
		State s = new State(array, tiles);

		int[] expectedChromo = { 1, 2 };

		assertArrayEquals(expectedChromo, s.chromosome());

	}

	@Test
	public void testDiversity() {

		Tile tile1 = new Tile();
		Tile tile2 = new Tile();

		Construction c1 = new Construction("Moradia") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {

				return 0;
			}
		};
		Construction c2 = new Construction("Parque") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {

				return 0;
			}
		};
		Construction c3 = new Construction("Escola") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {

				return 0;
			}
		};

		Tile[] tiles = { tile1, tile2 };
		Construction[] constructions1 = { c1, c2, c3 };
		Construction[] constructions2 = { c2, c2, c1 };

		State state1 = new State(constructions1, tiles);
		State state2 = new State(constructions2, tiles);

		State[] states = { state1, state2 };

		assertEquals(2, state1.diversity(states),0.0001);

	}

	@Test
	public void testDiversity2() {

		
		Tile tile1=new Tile();
		Tile tile2=new Tile();
		
		
		try {
			
			Construction c1=new Construction("Moradia") {
				
				@Override
				public double affinityToTile(Tile tile) {
					
					return 0;
				}
			};
			Construction c2 = new Construction("Apartamento") {
				
				@Override
				public double affinityToTile(Tile tile) {
					
					return 0;
				}
			};
			Construction c3 = new Construction("Parque") {
				
				@Override
				public double affinityToTile(Tile tile) {
					
					return 0;
				}
			};
			
			
			Tile[] tiles={tile1,tile2};
			Construction[] constructions1={c1,c2,c3};
			Construction[] constructions2={c1,c2,c1};
			
			State state1=new State(constructions1,tiles);
			State state2=new State(constructions2,tiles);
			
			State[] states={state1,state2};
			
			assertEquals(1, state1.diversity(states));
			
			
			
			
			
			
		} catch (ConstructionException e) {
			fail(e.message());
		}
		
		
		
		
		
		
	} 

		Tile tile1 = new Tile();
		Tile tile2 = new Tile();

		Construction c1 = new Construction("Moradia") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {

				return 0;
			}
		};
		Construction c2 = new Construction("Apartamento") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {

				return 0;
			}
		};
		Construction c3 = new Construction("Parque") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {

				return 0;
			}
		};

		Tile[] tiles = { tile1, tile2 };
		Construction[] constructions1 = { c1, c2, c3 };
		Construction[] constructions2 = { c1, c2, c1 };

		State state1 = new State(constructions1, tiles);
		State state2 = new State(constructions2, tiles);

		State[] states = { state1, state2 };

		assertEquals(1, state1.diversity(states),0.0001);

	}

	@Test
	public void mutationToAcceptableConstruction() {

		// TODO implement this test to assert if mutations are being made to
		// only acceptable constructions
	}

	@Test
	public void fitnessWithingAcceptableBounds() {

		// TODO implement this to test if the fitness of a state is <=1 and >=0

	}

}
