package mainPackage;

import java.util.ArrayList;


import mainPackage.constructions.Construction;

public class Population {

	/**
	 * 
	 * The states belonging to this Population
	 * 
	 */
	private State[] states;
	/**
	 * 
	 * The Constructions in this problem
	 * 
	 */
	public Construction[] constructions;
	/**
	 * 
	 * The Tiles in this problem
	 * 
	 */
	public Tile[] tiles;
	
	public GeneticRandomGenerator coinTosser;
	
	
	public int populationSize(){
		
		return states.length;
	}
	
	public State[] states(){
		
		return this.states;
	}
	
	public Tile[] tiles(){
		
		return this.tiles;
	}
	
	
	
	
	
	
	/**
	 * 
	 * A method to add a state to the population
	 * @param stateToAdd
	 */
	void addState(State stateToAdd){
		
		
	}
	
	/**
	 * 
	 * A method that removes a state from the state list.
	 * This may happen for example because a state may "die"
	 * 
	 * @param stateToRemove the state to be removed from the population
	 */
	void removeState(State stateToRemove){
		
	}
	
	
	/**
	 * 
	 * 
	 * @param tiles the tiles that exist in the problem
	 * @param constructions the constructions that exist in the problem
	 * @param populationSize the size of the initial population
	 * @param mutationProb the probability of a mutation occurring
	 * @param statesToPair the probability of a pairing occurring
	 * 
	 */
	public Population(Tile[] tiles,Construction[] constructions,int populationSize,double mutationProb,int statesToPair){
		
		states=new State[populationSize];//initialize the initial size as the double of the initial capacity
		this.tiles=tiles;
		this.constructions=constructions;
		this.coinTosser=new GeneticRandomGenerator( this, statesToPair, mutationProb);
		for(int i=0;i<populationSize;i++){
			State stateToAdd=new State(this.generateRandomConstructionArray(tiles.length),tiles);
			states[i]=stateToAdd;
		
		}
		
		
		
		
	}
	
	/**
	 * 
	 * 
	 * A method that returns a given number of construction by a random order
	 * @param size
	 * @return
	 */
	Construction[] generateRandomConstructionArray(int size){
		
		Construction[] constructionsToReturn=new Construction[size];
		for(int i=0;i<size;i++){
			int constructionNr= (int)(Math.random()*(constructions.length));
			constructionsToReturn[i]=constructions[constructionNr];
		}
		return constructionsToReturn;
		
	}
	
	/**
	 * 
	 * A method that returns the sum of the fitnesses of all states
	 */
	public double overallFitness(){
		
		
		double overallFitness=0;
		for (State state : states) {
			overallFitness+=state.fitness();
		}
		
		return overallFitness;
		
		
	}
	
	
	public double[] fitnessArray(){
		
		double[] fitnesses=new double[states.length];
		for(int i=0;i<states.length;i++){
			fitnesses[i]=states[i].fitness();
		}
		
		return fitnesses;
	}

	
	/**
	 * A method that pairs all the states that are to be paired
	 */
	public void pair(){
		
		
		State[] statesToBePaired=coinTosser.statesForReproduction();
		State st1,st2;
		Integer[] segments;
		State[] childs;
		
		ArrayList<State> statesAfterPairing=new ArrayList<State>();
		
		
		for(int i=0;i<statesToBePaired.length-1;i+=2){
			st1=statesToBePaired[i];
			st2=statesToBePaired[i+1];
			segments=coinTosser.segmentsOfState(st1);
			childs=st1.pairWith(st2, segments);
			statesAfterPairing.add(childs[0]);
			statesAfterPairing.add(childs[1]);
			
		}
		
		
		State[] directToNextGenStates=coinTosser.statesForNextGen();
		
		for(int i=0;i<directToNextGenStates.length;i++){//add states passing intact
			states[i]=directToNextGenStates[i];
		}

		for(int i=directToNextGenStates.length;i<states.length;i++){//ass states paired
			states[i]=statesAfterPairing.get(i-directToNextGenStates.length);
		}
		
		
		
	}
	
	/**
	 * 
	 * A method that mutates all the states that are to be mutated
	 * 
	 */
	public void mutate(){
		
		
		boolean mutate;
		for(State s: states){
			mutate=coinTosser.stateShouldMutate(s);
			if(mutate){
				s.mutate(coinTosser.mutatingSegmentForState(s));
			}
			
		}
		
	}
	
	/**
	 * 
	 * A method that performs a pairing followed by a mutation step
	 */
	public void iterate(){
		this.pair();
		this.mutate();

	}
	/**
	 * 
	 * A method that returns the most fit state of this population
	 * @return the most fit state of this population
	 */
	public State mostFitState(){
		
		State best=states[0];
		
		for (State s : states) {
			if(best.fitness()<s.fitness())best=s;
		}
		return best;
	}
	
	/**
	 * 
	 * A method that pairs two states and replaces them  for the new ones.
	 * Public for testing purposes.
	 * @param index1
	 * @param index2
	 * @param segmentsOfFirstOne
	 * @return
	 */
	public State[] pairStatesAtIndexes(int index1,int index2,Integer[] segmentsOfFirstOne){
		
		State [] newOnes=states[index1].pairWith(states[index2], segmentsOfFirstOne);
		states[index1]=newOnes[0];
		states[index2]=newOnes[1];
		
		return newOnes;
	}
}
