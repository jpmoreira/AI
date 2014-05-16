package mainPackage;
import java.io.Serializable;
import java.util.ArrayList;

import mainPackage.constructions.Construction;

public class Population implements Serializable{

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
	private Construction[] constructions;
	/**
	 * 
	 * The Tiles in this problem
	 * 
	 */
	private Tile[] tiles;
	
	
	/**
	 * 
	 * The number of the current iteration.
	 */
	private int currentIteration=0;
	
	/**
	 * 
	 * The best most fit state ever.
	 */
	private State bestStateEver=null;
	
	/**
	 * 
	 * The iteration where the best state ever first appeared.
	 * 
	 */
	private int bestStateIterationNr=0;
	
	
	private int mutationsSoFar=0;
	private int mutationsThisIteration=0;
	
	
	/**
	 * 
	 * 
	 * The object that controls all the random factors involved in the iteration process
	 */
	public GeneticRandomGenerator coinTosser;
	
	/**
	 * 
	 * The number of states to be paired in each iteration
	 * 
	 */
	private int statesToPair;
	
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
		this.coinTosser=new GeneticRandomGenerator( this, mutationProb);
		this.statesToPair=statesToPair;
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
	 * @return the array with the constructions by a random order
	 */
	Construction[] generateRandomConstructionArray(int size){
		
		
		int upperBound=size;
		if(constructions.length>size)upperBound=constructions.length;
		
		Construction[] constructionsToReturn=new Construction[size];
		for(int i=0;i<size;i++){
			int constructionNr= (int)(Math.random()*(upperBound));//generate a number that is between zero and the biggest number between the number of tiles and constructions
			
			if(constructions.length<=constructionNr) constructionsToReturn[i]=Construction.nullConstruction();//if was generated a construction that doesn't exist place a null construction then
			else constructionsToReturn[i]=constructions[constructionNr];//else use a real construction
			
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
	
	/**
	 * 
	 * A method that returns an array with the fitness for all the states in the population 
	 * @return an array with the fitness for each state.
	 */
	public double[] fitnessArray(){
		
		double[] fitnesses=new double[states.length];
		for(int i=0;i<states.length;i++){
			fitnesses[i]=states[i].fitness();
		}
		
		return fitnesses;
	}

	/**
	 * 
	 * Returns the best state ever for this population.
	 * @return
	 */
	public State bestStateEver(){
		
		return this.bestStateEver;
	}
	
	public int iterationNrForBestStateEver(){
		
		return this.bestStateIterationNr;
	}
	
	/**
	 * A method that pairs all the states that are to be paired
	 */
	public void pair(){
		
		
		State[] statesToBePaired=coinTosser.statesForReproduction(null);
		State st1,st2;
		Integer[] segments;
		State[] childs;
		
		ArrayList<State> statesAfterPairing=new ArrayList<State>();
		
		
		for(int i=0;i<statesToBePaired.length-1;i+=2){
			st1=statesToBePaired[i];
			st2=statesToBePaired[i+1];
			segments=coinTosser.segmentsOfState(st1,null);
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
		
		mutationsThisIteration=0;
		boolean mutate;
		for(State s: states){
			mutate=coinTosser.stateShouldMutate(s,null);
			if(mutate){
				mutationsThisIteration++;
				s.mutate(coinTosser.mutatingSegmentForState(s,null),coinTosser.bitToToggle(null));
			}
			
		}
		mutationsSoFar+=mutationsThisIteration;
		
	}

	/**
	 * 
	 * A method that returns the number of mutations this last iteration
	 * @return the number of mutations this last iteration
	 */
	public int mutationsThisIteration(){
		
		return mutationsThisIteration;
	}
	
	/**
	 * 
	 * A method that returns the mean number of mutations per iteration
	 * @return the number of mutations per iteration
	 */
	public int meanMutationsPerIteration(){
		
		
		if(currentIteration!=0)return mutationsSoFar/currentIteration;
		return 0;
		
	}
	
	/**
	 * 
	 * 
	 * A method that returns the mean number of mutations so far.
	 * @return the number of mutations so far
	 */
	public int mutationsSoFar(){
		return mutationsSoFar;
	}

	/**
	 * 
	 * A method that updates the statistics relative to this population.
	 */
	public void updateStatistics(){
		
		State currentMostFit=this.mostFitState();
		
		if (this.bestStateEver == null){
			this.bestStateEver=currentMostFit;
		} else if(currentMostFit.fitness()>this.bestStateEver.fitness()){
		
			this.bestStateEver=currentMostFit;
			this.bestStateIterationNr=this.currentIteration;
			
		}
	}
	
	/**
	 * 
	 * A method that performs a pairing followed by a mutation step
	 */
	public void iterate(){
		this.pair();
		this.mutate();
		this.coinTosser.updateParameters();
		this.updateStatistics();
		this.currentIteration++;
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
	 * 
	 * A method that returns the current mean fitness.
	 * @return the current mean fitness of the population
	 */
	public double meanFitness(){
		
		double total=0.0;
		for (double value : this.fitnessArray()) {
			total+=value;
		}
		return total/this.fitnessArray().length;
		
	}
	
	/**
	 * 
	 * A method that pairs two states and replaces them  for the new ones.
	 * Public for testing purposes.
	 * @param index1
	 * @param index2
	 * @param segmentsOfFirstOne
	 * @return the two states that are the result of the pairing
	 */
	public State[] pairStatesAtIndexes(int index1,int index2,Integer[] segmentsOfFirstOne){
		
		State [] newOnes=states[index1].pairWith(states[index2], segmentsOfFirstOne);
		states[index1]=newOnes[0];
		states[index2]=newOnes[1];
		
		return newOnes;
	}

	/**
	 * 
	 * A method that returns the number of states to be paired
	 * @return the number of states to be paired
	 */
	public int statesToPair(){
		
		return this.statesToPair;
	}
	/**
	 * 
	 * A simple setter for {@link #statesToPair}
	 * @param nr a number bigger than 0 and less or equal to the size of the population
	 */
	public void setStatesToPair(int nr){
		
		this.statesToPair=nr;
		
	}

	public int getIteration() {
		return this.currentIteration;
	}
}
