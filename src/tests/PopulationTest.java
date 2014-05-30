package tests;

//TEST more tiles that constructions and more constructions than tiles
import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import mainPackage.GeneticEngine;
import mainPackage.GeneticState;
import mainPackage.TileProblemPopulation;
import mainPackage.TileProblemState;
import mainPackage.Tile;
import mainPackage.constructions.Construction;

import org.junit.Test;

import Exceptions.ConstructionException;

public class PopulationTest {

	@Test
	public void testNrStates() {

		Tile tile1 = new Tile();
		Tile tile2 = new Tile();

		Construction c1;
		Construction c2;

		c1 = new Construction("Casa") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 0;
			}
		};
		c2 = new Construction("Escola") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 0;
			}
		};

		Tile[] tiles = { tile1, tile2 };
		Construction[] constructions = { c1, c2 };

		TileProblemPopulation pop = new TileProblemPopulation(tiles, constructions, 2,1.0);

		TileProblemState[] states = pop.states();

		assertEquals(2, states.length);

	}

	@Test
	public void testPairStates() {

		Tile tile1 = new Tile();
		Tile tile2 = new Tile();

		Construction c1;
		Construction c2;
		Construction c3;
		Construction c4;

		c1 = new Construction("Casa") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 0;
			}
		};
		c2 = new Construction("Escola") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 0;
			}
		};
		c3 = new Construction("Aeroporto") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 0;
			}
		};

		c4 = new Construction("Autoestrada") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 0;
			}
		};

		Tile[] tiles = { tile1, tile2 };
		Construction[] constructions = { c1, c2, c3, c4 };
		TileProblemPopulation pop = new TileProblemPopulation(tiles, constructions, 2,1.0);

		TileProblemState state1 = pop.states()[0];
		TileProblemState state2 = pop.states()[1];

		Integer[] array = { 1 };
		GeneticState[] newStates = pop.pairStatesAtIndexes(0, 1, array);

		assertEquals(((TileProblemState)newStates[0]).constructions[0], state1.constructions[0]);
		assertEquals(((TileProblemState)newStates[0]).constructions[1], state2.constructions[1]);
		assertEquals(((TileProblemState)newStates[1]).constructions[0], state2.constructions[0]);
		assertEquals(((TileProblemState)newStates[1]).constructions[1], state1.constructions[1]);
	}

	@Test
	public void testImediateProceadingStatesTest() {

		Tile tile1 = new Tile();
		Tile tile2 = new Tile();

		Construction c1;
		Construction c2;
		Construction c3;
		Construction c4;

		c1 = new Construction("Moradia") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 1;
			}
		};
		c2 = new Construction("Apartamento") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 10;
			}
		};
		c3 = new Construction("Escola") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 100;
			}
		};

		c4 = new Construction("Aeroporto") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 27;
			}
		};

		Tile[] tiles = { tile1, tile2 };
		Construction[] constructions = { c1, c2, c3, c4 };
		TileProblemPopulation pop = new TileProblemPopulation(tiles, constructions, 4);
		GeneticEngine gen=new GeneticEngine(pop, 1.0, 2);

		TileProblemState st1 = pop.states()[0];
		TileProblemState st2 = pop.states()[1];
		TileProblemState st3 = pop.states()[2];
		TileProblemState st4 = pop.states()[3];

		TileProblemState best, secondBest;

		if (st1.fitness() >= st2.fitness()) {
			best = st1;
			secondBest = st2;
		} else {
			best = st2;
			secondBest = st1;
		}
		for (TileProblemState s : pop.states()) {
			if (s.fitness() > best.fitness())
				best = s;

		}
		for (TileProblemState s : pop.states()) {
			if (s.fitness() > secondBest.fitness() && s != best)
				secondBest = s;

		}

		gen.pair();

		boolean foundBest = false;
		boolean foundSecondBest = false;

		for (TileProblemState s : pop.states()) {
			if (s.id() == best.id())
				foundBest = true;
			else if (s.id() == secondBest.id())
				foundSecondBest = true;

		}

		assertTrue(foundBest);
		assertTrue(foundSecondBest);

	}

	@Test
	public void testBestFitnessEvolution() {

		Construction.resetConstructions();

		TileProblemState.resetStates();

		Tile tile1 = new Tile();
		Tile tile2 = new Tile();
		Tile tile3 = new Tile();
		Tile tile4 = new Tile();
		Tile tile5 = new Tile();
		Tile tile6 = new Tile();
		Tile tile7 = new Tile();
		Tile tile8 = new Tile();

		Construction c1;
		Construction c2;
		Construction c3;
		Construction c4;

		c1 = new Construction("Escola") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 1;
			}
		};
		c2 = new Construction("Casa") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 10;
			}
		};
		c3 = new Construction("Aeroporto") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 100;
			}
		};
		c4 = new Construction("Prisao") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 27;
			}
		};

		Tile[] tiles = { tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8 };
		Construction[] constructions = { c2, c3, c4, c3, c1 };
		TileProblemPopulation pop = new TileProblemPopulation(tiles, constructions, 5,1.0);
		TileProblemState best = pop.mostFitState();
		
		GeneticEngine gen=new GeneticEngine(pop, 0.01, 2);

		System.out.println("BEFORE " + best.fitness() + " "
				+ best.visualRepresentation());

		for (int i = 0; i < 100000; i++) {

			gen.iterate();

		}

		TileProblemState bestAfter = pop.mostFitState();
		System.out.println("AFTER " + bestAfter.fitness() + " "
				+ bestAfter.visualRepresentation());

		assertTrue(bestAfter.fitness() >= best.fitness());

	}

	@Test
	public void testMantainNumberOfStates() {

		Tile tile1 = new Tile();
		Tile tile2 = new Tile();

		Construction c1;
		Construction c2;
		Construction c3;
		Construction c4;

		c1 = new Construction("Moradia") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 1;
			}
		};
		c2 = new Construction("Apartamento") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 10;
			}
		};
		c3 = new Construction("Parque de Estacionamento") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 100;
			}
		};

		c4 = new Construction("Escola") {

			@Override
			public double affinityToTileInState(Tile tile, TileProblemState s) {
				return 27;
			}
		};

		Tile[] tiles = { tile1, tile2 };
		Construction[] constructions = { c1, c2, c3, c4 };
		TileProblemPopulation pop = new TileProblemPopulation(tiles, constructions, 4,1.0);

		
		GeneticEngine gen=new GeneticEngine(pop, 1.0, 2);
		int nrStatesBefore = pop.states().length;

		gen.pair();

		int nrStatesAfter = pop.states().length;

		assertEquals(nrStatesBefore, nrStatesAfter);

	}

	@Test
	public void serializePopulation(){
		
		Tile t1=new Tile();
		Tile t2=new Tile();
		Tile t3=new Tile();
		
		Construction c1=new Construction("C1");
		Construction c2=new Construction("C2");
		Construction c3=new Construction("C3");
		
		TileProblemPopulation p=new TileProblemPopulation(new Tile[]{t1,t2,t3}, new Construction[]{c1,c2,c3}, 2,1.0);
		GeneticEngine gen=new GeneticEngine(p, 0.01, 2);
		p.updateBestStateEver();
		gen.iterate();
		
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("popTest.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(p);
	         out.close();
	         fileOut.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	          fail("Exception Thrown");
	      }
		
		TileProblemPopulation p2=null;
		try
	      {
	         FileInputStream fileIn = new FileInputStream("popTest.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         p2 = (TileProblemPopulation) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         fail("Exception Thrown");
	      }catch(ClassNotFoundException c)
	      {
	         c.printStackTrace();
	         fail("Exception Thrown");
	      }
		
		
		assertTrue(p2.mostFitState().fitness()==p.mostFitState().fitness());
		assertTrue(p2.states()[0].constructions[0].toCromossome()==p.states()[0].constructions[0].toCromossome());
		
	}

	
	@Test
	public void moreConstructionsThanTiles(){
		Construction c1=new Construction("c1");
		Construction c2=new Construction("c2");
		Construction c3=new Construction("c3");
		Construction c4=new Construction("c4");
		
		Tile t1=new Tile();
		Tile t2=new Tile();
		
		TileProblemPopulation p=new TileProblemPopulation(new Tile[]{t1,t2}, new Construction[]{c1,c2,c3,c4},3,1.0);
		GeneticEngine gen=new GeneticEngine(p, 0.01, 2);
		p.updateBestStateEver();
		gen.iterate();
		
		assertTrue(1==1);//possible to iterate so everything ok
		
	}
	@Test
	public void moreTilesThanConstructions(){
		
		Construction c1=new Construction("c1");
		Construction c2=new Construction("c2");
		
		Tile t1=new Tile();
		Tile t2=new Tile();
		Tile t3=new Tile();
		Tile t4=new Tile();
		
		TileProblemPopulation p=new TileProblemPopulation(new Tile[]{t1,t2,t3,t4}, new Construction[]{c1,c2,},3,1.0);
		GeneticEngine gen=new GeneticEngine(p, 0.01, 2);
		p.updateBestStateEver();
		gen.iterate();
		
		assertTrue(1==1);//possible to iterate so everything ok
		
	}
	
	@Test
	public void testRepetedStatesPenalty(){
		
		Construction.resetConstructions();
		Construction c1=new Construction("C1");
		Construction c2=new Construction("C2");
		Construction c3=new Construction("C3");
		Construction c4=new Construction("C4");
		
		Tile t1=new Tile();
		Tile t4=new Tile();
		Tile t3=new Tile();
		Tile t2=new Tile();
		
		TileProblemState s1=new TileProblemState(new Construction[]{c1,c2,c3,c4}, new Tile[]{t1,t2,t3,t4},1.0);
		TileProblemState s2=new TileProblemState(new Construction[]{c1,c2,c4,c4}, new Tile[]{t1,t2,t3,t4},1.0);
		TileProblemState s3=new TileProblemState(new Construction[]{c1,c4,c4,c4}, new Tile[]{t1,t2,t3,t4},1.0);
		TileProblemState s4=new TileProblemState(new Construction[]{c1,c1,c4,c4}, new Tile[]{t1,t2,t3,t4},1.0);
		TileProblemPopulation p=new TileProblemPopulation(new Tile[]{t1,t2,t3,t4}, new Construction[]{c1,c2,c3,c4}, 4,1.0);
		//GeneticEngine gen=new GeneticEngine(p, 0.01, 2);
		p.setStates(new TileProblemState[]{s1,s2,s3,s4});
		p.setRepetedConstructionsFactor(0.5);
		
		double[] fitnesses=p.fitnessArray();
		
		assertEquals(1.0, fitnesses[0],0.00001);
		assertEquals(0.25, fitnesses[1],0.00001);
		assertEquals(0.0625, fitnesses[2],0.00001);
		assertEquals(0.0625, fitnesses[3],0.00001);
		
		
	}
}
