package tests;

import static org.junit.Assert.*;
import mainPackage.Population;
import mainPackage.RandomNrGenerator;
import mainPackage.SimulatedAnnealingEngine;
import mainPackage.SimulatedAnnealingState;
import mainPackage.State;
import mainPackage.Tile;
import mainPackage.TileProblemPopulation;
import mainPackage.TileProblemState;
import mainPackage.constructions.Construction;

import org.junit.Test;

public class SimulatedAnnealingTests {

	@Test
	public void testNextStateFunction() {
		
		Construction.resetConstructions();
		Construction c1=new Construction("A");
		Construction c2=new Construction("B");
		
		Tile t1=new Tile();
		Tile t2=new Tile();
		
		TileProblemState s=new TileProblemState(new Construction[]{c1,c2}, new Tile[]{t1,t2});
		TileProblemPopulation p=new TileProblemPopulation(new Tile[]{t1,t2}, new Construction[]{c1,c2}, 1);
		p.setStates(new TileProblemState[]{s});
		SimulatedAnnealingEngine eng=new SimulatedAnnealingEngine(p);
		
		TileProblemState s2=(TileProblemState)eng.nextState(s, new RandomNrGenerator() {
			
			@Override
			public double nextRandomNr() {
				return 0;
			}
		});
		
		
		assertEquals(s2.constructions[1],s.constructions[1]);
		
		assertEquals(s2.constructions[0],c2);
		
		
		
		
		
		
	}
	
	
	@Test
	public void testNrSuccessors(){
		
		Construction.resetConstructions();
		Construction c1=new Construction("A");
		Construction c2=new Construction("B");
		Construction c3=new Construction("C");
		
		Tile t1=new Tile();
		Tile t2=new Tile();
		
		TileProblemState s=new TileProblemState(new Construction[]{c1,c2}, new Tile[]{t1,t2});
		
		
		assertEquals(s.nrSuccessors(),6);
		
	}

	@Test
	public void testNextStateProb(){
		
		Construction.resetConstructions();
		Construction c1=new Construction("A"){
			
			public double affinityToTileInState(Tile tile, TileProblemState state) {
				
				return 10;
				
			};
			
		};
		
	
		
		
		Construction c2=new Construction("B"){
			
			
			public double affinityToTileInState(Tile tile, TileProblemState state) {
				
				return 0;
				
			};
			
		};
		
		Tile t1=new Tile();
		Tile t2=new Tile();
		
		TileProblemState s=new TileProblemState(new Construction[]{c1,c2}, new Tile[]{t1,t2});
		TileProblemPopulation p=new TileProblemPopulation(new Tile[]{t1,t2}, new Construction[]{c1,c2}, 1);
		p.setStates(new TileProblemState[]{s});
		SimulatedAnnealingEngine eng=new SimulatedAnnealingEngine(p);
		
		eng.setTemperature(2000, 0.9);
		
		
	
		TileProblemState s2=(TileProblemState)eng.nextState(s, new RandomNrGenerator() {
			
			public int i=0;
			
			public double[] nrs={0,0.0000001};
			
			@Override
			public double nextRandomNr() {
				return nrs[i++];
			}
		});
		
		System.out.println(s.nrSuccessors());
		TileProblemState s3=(TileProblemState)eng.nextState(s, new RandomNrGenerator() {
			
			public int i=0;
			
			public double[] nrs={0.0,1.0,0.0,1.0,0.0,1.0,0.0,1.0,0.0};
			
			@Override
			public double nextRandomNr() {
				return nrs[i++];
			}
		});

		
		assertEquals(s3.constructions[0],c1);
		
		assertEquals(s2.constructions[0],c2);
		
		
		
		
		
		
		
		
	}
}
