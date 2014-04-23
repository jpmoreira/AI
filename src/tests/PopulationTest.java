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
		
		Tile tile1=new Tile();
		Tile tile2=new Tile();
		
		Construction c1;
		Construction c2;
		
		try {
			
			c1=new Construction(10,10) {
				
				@Override
				public float affinityToTile(Tile tile) {	
					return 0;
				}
			};
			c2 = new Construction(20,20) {
				
				@Override
				public float affinityToTile(Tile tile) {
					return 0;
				}
			};
			
			Tile[] tiles={tile1,tile2};
			Construction[] constructions={c1,c2};
			
			Population pop=new Population(tiles, constructions, 2, 1.0, 0);
			
			
			State[] states=pop.states();
			
			assertEquals(2,states.length);
			
			
			
			
			
			
		} catch (ConstructionException e) {
			fail(e.message());
		}
		
		
		
		
		
		
	}
	
	
	@Test
	public void testPairStates(){
		
		Tile tile1=new Tile();
		Tile tile2=new Tile();
		
		Construction c1;
		Construction c2;
		Construction c3;
		Construction c4;
		
		try {
			
			c1=new Construction(10,10) {
				
				@Override
				public float affinityToTile(Tile tile) {	
					return 0;
				}
			};
			c2 = new Construction(20,20) {
				
				@Override
				public float affinityToTile(Tile tile) {
					return 0;
				}
			};
			c3 = new Construction(30,30) {
				
				@Override
				public float affinityToTile(Tile tile) {
					return 0;
				}
			};
			
			c4 = new Construction(40,40) {
				
				@Override
				public float affinityToTile(Tile tile) {
					return 0;
				}
			};
			
			Tile[] tiles={tile1,tile2};
			Construction[] constructions={c1,c2,c3,c4};
			Population pop=new Population(tiles, constructions, 2,1.0,0);
			
			
			State state1=pop.states()[0];
			State state2=pop.states()[1];
			
			Integer[] array={0};
			State[] newStates=pop.pairStatesAtIndexes(0, 1, array);
			
			assertEquals(newStates[0].constructions[0], state1.constructions[0]);
			assertEquals(newStates[0].constructions[1], state2.constructions[1]);
			assertEquals(newStates[1].constructions[0],state2.constructions[0]);
			assertEquals(newStates[1].constructions[1],state1.constructions[1]);
			
			
			
			
			
			
			
			
			
		} catch (ConstructionException e) {
			fail(e.message());
		}
		
	}

	
	@Test
	public void testImediateProceadingStatesTest(){
		
		Tile tile1=new Tile();
		Tile tile2=new Tile();
		
		Construction c1;
		Construction c2;
		Construction c3;
		Construction c4;
		
		try {
			
			c1=new Construction(10,10) {
				
				@Override
				public float affinityToTile(Tile tile) {	
					return 1;
				}
			};
			c2 = new Construction(20,20) {
				
				@Override
				public float affinityToTile(Tile tile) {
					return 10;
				}
			};
			c3 = new Construction(30,30) {
				
				@Override
				public float affinityToTile(Tile tile) {
					return 100;
				}
			};
			
			c4 = new Construction(40,40) {
				
				@Override
				public float affinityToTile(Tile tile) {
					return 27;
				}
			};
			
			Tile[] tiles={tile1,tile2};
			Construction[] constructions={c1,c2,c3,c4};
			Population pop=new Population(tiles, constructions, 4,1.0,2);
			
			
			State st1=pop.states()[0];
			State st2=pop.states()[1];
			State st3=pop.states()[2];
			State st4=pop.states()[3];
			
			State best,secondBest;
			
			if(st1.fitness()>=st2.fitness()){
				best=st1;
				secondBest=st2;
			}
			else{
				best=st2;
				secondBest=st1;
			}
			for(State s:pop.states()){
				if(s.fitness()>best.fitness())best=s;
				
			}
			for(State s:pop.states()){
				if(s.fitness()>secondBest.fitness() && s!=best)secondBest=s;
				
			}
	
			
			pop.pair();
			
			
			boolean foundBest=false;
			boolean foundSecondBest=false;
			
			for(State s : pop.states()){
				if(s==best)foundBest=true;
				else if(s==secondBest)foundSecondBest=true;
				
			}
			
			assertTrue(foundBest);
			assertTrue(foundSecondBest);
			
			
			
			
			
			
			
			
			
			
		} catch (ConstructionException e) {
			fail(e.message());
		}
		
	}
	
	

	@Test
	public void testBestFitnessEvolution(){
		
		

		Tile tile1=new Tile();
		Tile tile2=new Tile();
		
		Construction c1;
		Construction c2;
		Construction c3;
		Construction c4;
		
		try {
			
			c1=new Construction(10,10) {
				
				@Override
				public float affinityToTile(Tile tile) {	
					return 1;
				}
			};
			c2 = new Construction(20,20) {
				
				@Override
				public float affinityToTile(Tile tile) {
					return 10;
				}
			};
			c3 = new Construction(30,30) {
				
				@Override
				public float affinityToTile(Tile tile) {
					return 100;
				}
			};
			
			c4 = new Construction(40,40) {
				
				@Override
				public float affinityToTile(Tile tile) {
					return 27;
				}
			};
			
			Tile[] tiles={tile1,tile2};
			Construction[] constructions={c1,c2,c3,c4};
			Population pop=new Population(tiles, constructions, 4,0.1,2);
			
			
			
			State best=pop.mostFitState();
	
			System.out.println("BEFORE "+best.fitness());
		
			for(int i=0;i<200;i++){
				
				pop.iterate();
				System.out.println("i="+i);
				
			}
			
			State bestAfter=pop.mostFitState();
			System.out.println("AFTER "+bestAfter.fitness());
			
			assertTrue(bestAfter.fitness()>=best.fitness());
			
			
			
			
			
			
			
			
			
		} catch (ConstructionException e) {
			fail(e.message());
		}
		
	}


@Test
public void testMantainNumberOfStates(){
	
	Tile tile1=new Tile();
	Tile tile2=new Tile();
	
	Construction c1;
	Construction c2;
	Construction c3;
	Construction c4;
	
	try {
		
		c1=new Construction(10,10) {
			
			@Override
			public float affinityToTile(Tile tile) {	
				return 1;
			}
		};
		c2 = new Construction(20,20) {
			
			@Override
			public float affinityToTile(Tile tile) {
				return 10;
			}
		};
		c3 = new Construction(30,30) {
			
			@Override
			public float affinityToTile(Tile tile) {
				return 100;
			}
		};
		
		c4 = new Construction(40,40) {
			
			@Override
			public float affinityToTile(Tile tile) {
				return 27;
			}
		};
		
		Tile[] tiles={tile1,tile2};
		Construction[] constructions={c1,c2,c3,c4};
		Population pop=new Population(tiles, constructions, 4,1.0,2);
		
		
		int nrStatesBefore=pop.states().length;

		
		pop.pair();
		
		int nrStatesAfter=pop.states().length;
		
		assertEquals(nrStatesBefore, nrStatesAfter);
		
		
		
		
		
		
		
		
		
	} catch (ConstructionException e) {
		fail(e.message());
	}
	
}



}
