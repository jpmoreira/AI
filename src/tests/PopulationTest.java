package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import mainPackage.Population;
import mainPackage.State;
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
			public double affinityToTileInState(Tile tile, State s) {
				return 0;
			}
		};
		c2 = new Construction("Escola") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 0;
			}
		};

		Tile[] tiles = { tile1, tile2 };
		Construction[] constructions = { c1, c2 };

		Population pop = new Population(tiles, constructions, 2, 1.0, 0);

		State[] states = pop.states();

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
			public double affinityToTileInState(Tile tile, State s) {
				return 0;
			}
		};
		c2 = new Construction("Escola") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 0;
			}
		};
		c3 = new Construction("Aeroporto") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 0;
			}
		};

		c4 = new Construction("Autoestrada") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 0;
			}
		};

		Tile[] tiles = { tile1, tile2 };
		Construction[] constructions = { c1, c2, c3, c4 };
		Population pop = new Population(tiles, constructions, 2, 1.0, 0);

		State state1 = pop.states()[0];
		State state2 = pop.states()[1];

		Integer[] array = { 0 };
		State[] newStates = pop.pairStatesAtIndexes(0, 1, array);

		assertEquals(newStates[0].constructions[0], state1.constructions[0]);
		assertEquals(newStates[0].constructions[1], state2.constructions[1]);
		assertEquals(newStates[1].constructions[0], state2.constructions[0]);
		assertEquals(newStates[1].constructions[1], state1.constructions[1]);
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
			public double affinityToTileInState(Tile tile, State s) {
				return 1;
			}
		};
		c2 = new Construction("Apartamento") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 10;
			}
		};
		c3 = new Construction("Escola") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 100;
			}
		};

		c4 = new Construction("Aeroporto") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 27;
			}
		};

		Tile[] tiles = { tile1, tile2 };
		Construction[] constructions = { c1, c2, c3, c4 };
		Population pop = new Population(tiles, constructions, 4, 1.0, 2);

		State st1 = pop.states()[0];
		State st2 = pop.states()[1];
		State st3 = pop.states()[2];
		State st4 = pop.states()[3];

		State best, secondBest;

		if (st1.fitness() >= st2.fitness()) {
			best = st1;
			secondBest = st2;
		} else {
			best = st2;
			secondBest = st1;
		}
		for (State s : pop.states()) {
			if (s.fitness() > best.fitness())
				best = s;

		}
		for (State s : pop.states()) {
			if (s.fitness() > secondBest.fitness() && s != best)
				secondBest = s;

		}

		pop.pair();

		boolean foundBest = false;
		boolean foundSecondBest = false;

		for (State s : pop.states()) {
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

		State.resetStates();

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
			public double affinityToTileInState(Tile tile, State s) {
				return 1;
			}
		};
		c2 = new Construction("Casa") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 10;
			}
		};
		c3 = new Construction("Aeroporto") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 100;
			}
		};
		c4 = new Construction("Prisao") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 27;
			}
		};

		Tile[] tiles = { tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8 };
		Construction[] constructions = { c2, c3, c4, c3, c1 };
		Population pop = new Population(tiles, constructions, 5, 0.01, 2);

		State best = pop.mostFitState();

		System.out.println("BEFORE " + best.fitness() + " "
				+ best.visualRepresentation());

		for (int i = 0; i < 100000; i++) {

			pop.iterate();

		}

		State bestAfter = pop.mostFitState();
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
			public double affinityToTileInState(Tile tile, State s) {
				return 1;
			}
		};
		c2 = new Construction("Apartamento") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 10;
			}
		};
		c3 = new Construction("Parque de Estacionamento") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 100;
			}
		};

		c4 = new Construction("Escola") {

			@Override
			public double affinityToTileInState(Tile tile, State s) {
				return 27;
			}
		};

		Tile[] tiles = { tile1, tile2 };
		Construction[] constructions = { c1, c2, c3, c4 };
		Population pop = new Population(tiles, constructions, 4, 1.0, 2);

		int nrStatesBefore = pop.states().length;

		pop.pair();

		int nrStatesAfter = pop.states().length;

		assertEquals(nrStatesBefore, nrStatesAfter);

	}

}
