package tests;

import static org.junit.Assert.*;
import mainPackage.GeneticEngine;
import mainPackage.State;
import mainPackage.TileProblemPopulation;
import mainPackage.RandomNrGenerator;
import mainPackage.TileProblemState;
import mainPackage.Tile;
import mainPackage.constructions.Construction;

import org.junit.Test;

import Exceptions.ConstructionException;

public class GeneticRandomGeneratorTests {

	@Test
	public void testBubbleSort1() {
		
		

		Construction c1 = new Construction("Moradia") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 1;
			}
		};

		Construction c2 = new Construction("Apartamento") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 2;
			}
		};

		Construction c3 = new Construction("Escola") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 3;
			}
		};

		Construction c4 = new Construction("Aeroporto") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 4;
			}
		};

		Construction[] constructions1 = { c1 };
		Construction[] constructions2 = { c2 };
		Construction[] constructions3 = { c3 };
		Construction[] constructions4 = { c4 };
		Tile[] tiles = { new Tile() };
		TileProblemState s1 = new TileProblemState(constructions1, tiles,1.0);

		TileProblemState s2 = new TileProblemState(constructions2, tiles,1.0);

		TileProblemState s3 = new TileProblemState(constructions3, tiles,1.0);

		TileProblemState s4 = new TileProblemState(constructions4, tiles,1.0);

		TileProblemState[] statesArray = { s1, s2, s3, s4 };

		GeneticEngine.BubbleSort(statesArray, 1, 0.0);

		assertEquals(statesArray[0], s4);


	}

	@Test
	public void testBubbleSort2() {

		Construction c1 = new Construction("Moradia") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 1;
			}
		};

		Construction c2 = new Construction("Apartamento") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 2;
			}
		};

		Construction c3 = new Construction("Escola") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 3;
			}
		};

		Construction c4 = new Construction("Aeroporto") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 4;
			}
		};

		Construction[] constructions1 = { c1 };
		Construction[] constructions2 = { c2 };
		Construction[] constructions3 = { c3 };
		Construction[] constructions4 = { c4 };
		Tile[] tiles = { new Tile() };

		TileProblemState s1 = new TileProblemState(constructions1, tiles,1.0);

		TileProblemState s2 = new TileProblemState(constructions2, tiles,1.0);

		TileProblemState s3 = new TileProblemState(constructions3, tiles,1.0);

		TileProblemState s4 = new TileProblemState(constructions4, tiles,1.0);

		TileProblemState[] statesArray = { s1, s2, s3, s4 };

		GeneticEngine.BubbleSort(statesArray, 2, 0.0);

		assertEquals(statesArray[0], s4);
		assertEquals(statesArray[1], s3);

	}

	@Test
	public void testBubbleSortMultipleConstruction() {

		Construction c1 = new Construction("Moradia") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 1;
			}
		};

		Construction c2 = new Construction("Apartamento") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 2;
			}
		};

		Construction c3 = new Construction("Aeroporto") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 3;
			}
		};

		Construction c4 = new Construction("Escola") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 4;
			}
		};

		Construction[] constructions1 = { c1, c2 };
		Construction[] constructions2 = { c2, c3 };
		Construction[] constructions3 = { c3, c4 };
		Construction[] constructions4 = { c4, c4 };
		Tile[] tiles = { new Tile(), new Tile() };
		TileProblemState s1 = new TileProblemState(constructions1, tiles,1.0);

		TileProblemState s2 = new TileProblemState(constructions2, tiles,1.0);

		TileProblemState s3 = new TileProblemState(constructions3, tiles,1.0);

		TileProblemState s4 = new TileProblemState(constructions4, tiles,1.0);

		TileProblemState[] statesArray = { s1, s4, s3, s2 };

		GeneticEngine.BubbleSort(statesArray, 1, 0.0);

		
		assertEquals(statesArray[0], s4);

	}

	@Test
	public void testNextGeneration() {


			Tile[] tiles = { new Tile() };

			Construction c1 = new Construction("Aeroporto") {

				@Override
				public double affinityToTileInState(Tile tile, TileProblemState s) {

					return 1;
				}
			};
			Construction c2 = new Construction("Moradia") {

				@Override
				public double affinityToTileInState(Tile tile, TileProblemState s) {
					return 2;
				}
			};
			Construction c3 = new Construction("Parque") {

				@Override
				public double affinityToTileInState(Tile tile, TileProblemState s) {
					return 3;
				}
			};

			
			
			


			Construction[] constructions = { c1, c2, c3 };
			Construction[] constructions1 = { c1 };
			Construction[] constructions2 = { c2 };
			Construction[] constructions3 = { c3 };

			TileProblemState s1 = new TileProblemState(constructions1, tiles,1.0);
			TileProblemState s2 = new TileProblemState(constructions2, tiles,1.0);
			TileProblemState s3 = new TileProblemState(constructions3, tiles,1.0);

			TileProblemPopulation pop = new TileProblemPopulation(tiles, constructions, 3,1.0);
			// alter states not to be random
			TileProblemState[] states = pop.states();
			states[0] = s1;
			states[1] = s2;
			states[2] = s3;

			GeneticEngine gen = new GeneticEngine(pop, 0.5, 1);

			State[] statesOfNextGen = gen.statesForNextGen();
			State[] supposedStatesForNextGen = { s3, s2 };

			assertArrayEquals(statesOfNextGen, supposedStatesForNextGen);


	}

	@Test
	public void testRankIntervals() {


			Tile[] tiles = { new Tile() };

			Construction c1 = new Construction("Aeroporto") {

				@Override
				public double affinityToTileInState(Tile tile, TileProblemState s) {

					return 1;
				}
			};
			Construction c2 = new Construction("Moradia") {

				@Override
				public double affinityToTileInState(Tile tile, TileProblemState s) {
					return 2;
				}
			};
			Construction c3 = new Construction("Parque") {

				@Override
				public double affinityToTileInState(Tile tile, TileProblemState s) {
					return 3;
				}
			};



			Construction[] constructions = { c1, c2, c3 };
			Construction[] constructions1 = { c1 };
			Construction[] constructions2 = { c2 };
			Construction[] constructions3 = { c3 };

			TileProblemState s1 = new TileProblemState(constructions1, tiles,0.0);
			TileProblemState s2 = new TileProblemState(constructions2, tiles,0.0);
			TileProblemState s3 = new TileProblemState(constructions3, tiles,0.0);

			TileProblemPopulation pop = new TileProblemPopulation(tiles, constructions, 4,1.0);

			GeneticEngine gen = new GeneticEngine(pop, 0.5,2);

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
				public double affinityToTileInState(Tile tile, TileProblemState s) {

					return 1;
				}
			};
			Construction c2 = new Construction("Moradia") {

				@Override
				public double affinityToTileInState(Tile tile, TileProblemState s) {
					return 2;
				}
			};
			Construction c3 = new Construction("Parque") {

				@Override
				public double affinityToTileInState(Tile tile, TileProblemState s) {
					return 3;
				}
			};

			Construction[] constructions = { c1, c2, c3 };

	

			TileProblemPopulation pop = new TileProblemPopulation(tiles, constructions, 3);

			GeneticEngine generator= new GeneticEngine(pop, 0.5, 2);
			generator.enableFitnessToRank(0.5);
			GeneticEngine.BubbleSort(pop.states(),
					pop.states().length, 0);
			TileProblemState bestState = pop.states()[0];
			TileProblemState secondBestState = pop.states()[1];

			State[] statesForRep =  generator
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
		
		TileProblemPopulation pop=new TileProblemPopulation(tiles, constructions, 2,1.0);
		
		GeneticEngine gen=new GeneticEngine(pop, 0.5, 1);
		
		
		int bit=gen.bitToToggle(new RandomNrGenerator() {
			
			@Override
			public double nextRandomNr() {
				return 1.0;
			}
		});
		
		assertEquals(bit, 2);
		
		bit=gen.bitToToggle(new RandomNrGenerator() {
			
			@Override
			public double nextRandomNr() {
				return 0.5;
			}
		});
		
		assertEquals(bit, 1);
		
		bit=gen.bitToToggle(new RandomNrGenerator() {
			
			@Override
			public double nextRandomNr() {
				return 0.4;
			}
		});
		
		assertEquals(bit, 0);
		
		
	}
}
