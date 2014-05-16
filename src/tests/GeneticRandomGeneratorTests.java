package tests;

import static org.junit.Assert.*;
import mainPackage.GeneticRandomGenerator;
import mainPackage.Population;
import mainPackage.RandomNrGenerator;
import mainPackage.State;
import mainPackage.Tile;
import mainPackage.constructions.Construction;

import org.junit.Test;

import Exceptions.ConstructionException;

public class GeneticRandomGeneratorTests {

	@Test
	public void testBubbleSort1() {
		
		

		Construction c1 = new Construction("Moradia") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 1;
			}
		};

		Construction c2 = new Construction("Apartamento") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 2;
			}
		};

		Construction c3 = new Construction("Escola") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 3;
			}
		};

		Construction c4 = new Construction("Aeroporto") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 4;
			}
		};

		Construction[] constructions1 = { c1 };
		Construction[] constructions2 = { c2 };
		Construction[] constructions3 = { c3 };
		Construction[] constructions4 = { c4 };
		Tile[] tiles = { new Tile() };
		State s1 = new State(constructions1, tiles);

		State s2 = new State(constructions2, tiles);

		State s3 = new State(constructions3, tiles);

		State s4 = new State(constructions4, tiles);

		State[] statesArray = { s1, s2, s3, s4 };

		GeneticRandomGenerator.BubbleSort(statesArray, 1, 0.0,1.0);

		assertEquals(statesArray[0], s4);


	}

	@Test
	public void testBubbleSort2() {

		Construction c1 = new Construction("Moradia") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 1;
			}
		};

		Construction c2 = new Construction("Apartamento") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 2;
			}
		};

		Construction c3 = new Construction("Escola") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 3;
			}
		};

		Construction c4 = new Construction("Aeroporto") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 4;
			}
		};

		Construction[] constructions1 = { c1 };
		Construction[] constructions2 = { c2 };
		Construction[] constructions3 = { c3 };
		Construction[] constructions4 = { c4 };
		Tile[] tiles = { new Tile() };

		State s1 = new State(constructions1, tiles);

		State s2 = new State(constructions2, tiles);

		State s3 = new State(constructions3, tiles);

		State s4 = new State(constructions4, tiles);

		State[] statesArray = { s1, s2, s3, s4 };

		GeneticRandomGenerator.BubbleSort(statesArray, 2, 0.0,1.0);

		assertEquals(statesArray[0], s4);
		assertEquals(statesArray[1], s3);

	}

	@Test
	public void testBubbleSortMultipleConstruction() {

		Construction c1 = new Construction("Moradia") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 1;
			}
		};

		Construction c2 = new Construction("Apartamento") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 2;
			}
		};

		Construction c3 = new Construction("Aeroporto") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 3;
			}
		};

		Construction c4 = new Construction("Escola") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 4;
			}
		};

		Construction[] constructions1 = { c1, c2 };
		Construction[] constructions2 = { c2, c3 };
		Construction[] constructions3 = { c3, c4 };
		Construction[] constructions4 = { c4, c4 };
		Tile[] tiles = { new Tile(), new Tile() };
		State s1 = new State(constructions1, tiles);

		State s2 = new State(constructions2, tiles);

		State s3 = new State(constructions3, tiles);

		State s4 = new State(constructions4, tiles);

		State[] statesArray = { s1, s4, s3, s2 };

		GeneticRandomGenerator.BubbleSort(statesArray, 1, 0.0,1.0);

		
		assertEquals(statesArray[0], s4);

	}

	@Test
	public void testNextGeneration() {


			Tile[] tiles = { new Tile() };

			Construction c1 = new Construction("Aeroporto") {

				@Override
				public double affinityToTileInState(Tile tile, State s) {

					return 1;
				}
			};
			Construction c2 = new Construction("Moradia") {

				@Override
				public double affinityToTileInState(Tile tile, State s) {
					return 2;
				}
			};
			Construction c3 = new Construction("Parque") {

				@Override
				public double affinityToTileInState(Tile tile, State s) {
					return 3;
				}
			};

			
			
			


			Construction[] constructions = { c1, c2, c3 };
			Construction[] constructions1 = { c1 };
			Construction[] constructions2 = { c2 };
			Construction[] constructions3 = { c3 };

			State s1 = new State(constructions1, tiles);
			State s2 = new State(constructions2, tiles);
			State s3 = new State(constructions3, tiles);

			Population pop = new Population(tiles, constructions, 3, 0.5, 1);
			// alter states not to be random
			State[] states = pop.states();
			states[0] = s1;
			states[1] = s2;
			states[2] = s3;

			GeneticRandomGenerator gen = new GeneticRandomGenerator(pop, 0.5);

			State[] statesOfNextGen = gen.statesForNextGen();
			State[] supposedStatesForNextGen = { s3, s2 };

			assertArrayEquals(statesOfNextGen, supposedStatesForNextGen);


	}

	@Test
	public void testRankIntervals() {


			Tile[] tiles = { new Tile() };

			Construction c1 = new Construction("Aeroporto") {

				@Override
				public double affinityToTileInState(Tile tile, State s) {

					return 1;
				}
			};
			Construction c2 = new Construction("Moradia") {

				@Override
				public double affinityToTileInState(Tile tile, State s) {
					return 2;
				}
			};
			Construction c3 = new Construction("Parque") {

				@Override
				public double affinityToTileInState(Tile tile, State s) {
					return 3;
				}
			};



			Construction[] constructions = { c1, c2, c3 };
			Construction[] constructions1 = { c1 };
			Construction[] constructions2 = { c2 };
			Construction[] constructions3 = { c3 };

			State s1 = new State(constructions1, tiles);
			State s2 = new State(constructions2, tiles);
			State s3 = new State(constructions3, tiles);

			Population pop = new Population(tiles, constructions, 4, 0.5, 2);

			GeneticRandomGenerator gen = pop.coinTosser;

			gen.enableFitnessToRank(0.5);

			assertEquals(gen.lowerBound[0], 0.0, 0.001);
			assertEquals(gen.lowerBound[1], 0.5, 0.001);
			assertEquals(gen.lowerBound[2], 0.75, 0.001);
			assertEquals(gen.lowerBound[3], 0.875, 0.001);

			assertEquals(gen.upperBound[0], 0.5, 0.001);
			assertEquals(gen.upperBound[1], 0.75, 0.001);
			assertEquals(gen.upperBound[2], 0.875, 0.001);
			assertEquals(gen.upperBound[3], 1.0, 0.001);

	}

	@Test
	public void testNextGenerationRank() {

			Tile[] tiles = { new Tile() };

			Construction c1 = new Construction("Aeroporto") {

				@Override
				public double affinityToTileInState(Tile tile, State s) {

					return 1;
				}
			};
			Construction c2 = new Construction("Moradia") {

				@Override
				public double affinityToTileInState(Tile tile, State s) {
					return 2;
				}
			};
			Construction c3 = new Construction("Parque") {

				@Override
				public double affinityToTileInState(Tile tile, State s) {
					return 3;
				}
			};

			Construction[] constructions = { c1, c2, c3 };
			Construction[] constructions1 = { c1 };
			Construction[] constructions2 = { c2 };
			Construction[] constructions3 = { c3 };

			State s1 = new State(constructions1, tiles);
			State s2 = new State(constructions2, tiles);
			State s3 = new State(constructions3, tiles);

			Population pop = new Population(tiles, constructions, 3, 0.5, 2);
			pop.coinTosser.enableFitnessToRank(0.5);

			GeneticRandomGenerator generator = pop.coinTosser;
			GeneticRandomGenerator.BubbleSort(pop.states(),
					pop.states().length, 0,1.0);
			State bestState = pop.states()[0];
			State secondBestState = pop.states()[1];

			State[] statesForRep = generator
					.statesForReproduction(new RandomNrGenerator() {

						int i = 0;
						double[] seq = { 0.4, 0.7 };

						@Override
						public double nextRandomNr() {

							return seq[i++];
						}
					});

			assertEquals(statesForRep[0], bestState);
			assertEquals(statesForRep[1], secondBestState);

	}

	
	@Test
	public void testBitToToggleFunction1(){
		
		Construction.resetConstructions();
		Construction c1=new Construction("A") {
			
			@Override
			public double affinityToTileInState(Tile tile, State state) {
				return 0;
			}
		};
		Construction c2=new Construction("B") {
			
			@Override
			public double affinityToTileInState(Tile tile, State state) {
				return 0;
			}
		};
		Construction c3=new Construction("C") {
			
			@Override
			public double affinityToTileInState(Tile tile, State state) {
				return 0;
			}
		};
		
		Tile t1=new Tile();
		Tile t2=new Tile();
		Tile t3=new Tile();
		
		Construction[] constructions={c1,c2,c3};
		Tile[] tiles={t1,t2,t3};
		
		Population pop=new Population(tiles, constructions, 2, 0.5, 1);
		
		int bit=pop.coinTosser.bitToToggle(new RandomNrGenerator() {
			
			@Override
			public double nextRandomNr() {
				return 1.0;
			}
		});
		
		assertEquals(bit, 2);
		
		bit=pop.coinTosser.bitToToggle(new RandomNrGenerator() {
			
			@Override
			public double nextRandomNr() {
				return 0.5;
			}
		});
		
		assertEquals(bit, 1);
		
		bit=pop.coinTosser.bitToToggle(new RandomNrGenerator() {
			
			@Override
			public double nextRandomNr() {
				return 0.4;
			}
		});
		
		assertEquals(bit, 0);
		
		
	}
}
