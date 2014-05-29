package mainPackage;
import java.io.Serializable;
import java.util.ArrayList;

import mainPackage.constructions.Construction;

@SuppressWarnings("serial")
public class TileProblemPopulation implements Serializable,Population{

	/**
	 * 
	 * The states belonging to this Population
	 * 
	 */
	private TileProblemState[] states;
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
	private TileProblemState bestStateEver=null;
	
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
	 * A factor by which a state fitness is to be decremented in case repeted states are found
	 * 
	 */
	private double repetedConstructionFactor=1.0;
	
	/**
	 * 
	 * 
	 * The object that controls all the random factors involved in the iteration process
	 */
	//public GeneticEngine coinTosser;
	

	
	
	//TODO verify if necessary
	public int populationSize(){
		
		return states.length;
	}
	
	public TileProblemState[] states(){
		
		return this.states;
	}
	

	/**
	 * 
	 * A method that changes the states of a given population. Used mainly for testing purposes.
	 * @param newStates the new group of states. This group of states should be of the same size as the one the population currently has.
	 */
	public void setStates(TileProblemState[] newStates){
		
		if(newStates.length==this.states.length)this.states=newStates;
		
		
	}
	/**
	 * 
	 * A method that sets the {@link #repetedConstructionFactor}.
	 * @param factor
	 */
	public void setRepetedConstructionsFactor(double factor){
		
		this.repetedConstructionFactor=factor;
		
		for (TileProblemState state : states) {
			state.setRepetitionFactor(factor);
		}
	}
	
	/**
	 * 
	 * A getter for {@link #repetedConstructionFactor}.
	 * @return a copy of {@link #repetedConstructionFactor}.
	 */
	public double getRepetedConstructionsFactor(){
			
		return this.repetedConstructionFactor;
	}
	
	public Tile[] tiles(){
		
		return this.tiles;
	}
	
	
	
	/**
	 * 
	 * A method to add a state to the population
	 * @param stateToAdd
	 */

	


	
	
	/**
	 * 
	 * 
	 * @param tiles the tiles that exist in the problem
	 * @param constructions the constructions that exist in the problem
	 * @param populationSize the size of the initial population
	 * @param statesToPair the probability of a pairing occurring
	 * 
	 */
	public TileProblemPopulation(Tile[] tiles,Construction[] constructions,int populationSize,double repetedFactor){
		
		states=new TileProblemState[populationSize];//initialize the initial size as the double of the initial capacity
		this.tiles=tiles;
		this.constructions=constructions;
		//this.coinTosser=new GeneticEngine( this, mutationProb);
		for(int i=0;i<populationSize;i++){
			TileProblemState stateToAdd=new TileProblemState(this.generateRandomConstructionArray(tiles.length),tiles,repetedConstructionFactor);
			stateToAdd.setRepetitionFactor(repetedFactor);
			states[i]=stateToAdd;
		
		}
		this.repetedConstructionFactor=repetedFactor;
		
		
		
		
	}
	
	public TileProblemPopulation(Tile[] tiles,Construction[] constructions,int populationSize){
		
		this(tiles,constructions,populationSize,1.0);
		
		
		
		
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
	
	
	//TODO verify if necessary
	/**
	 * 
	 * A method that returns the sum of the fitnesses of all states
	 */
	public double overallFitness(){
		
		
		double overallFitness=0;
		for (TileProblemState state : states) {
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
		
		return (State)this.bestStateEver;
	}
	
	public int iterationNrForBestStateEver(){
		
		return this.bestStateIterationNr;
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
		
		TileProblemState currentMostFit=this.mostFitState();
		
		if (this.bestStateEver == null){
			this.bestStateEver=currentMostFit;
		} else if(currentMostFit.fitness()>this.bestStateEver.fitness()){
		
			this.bestStateEver=currentMostFit;
			this.bestStateIterationNr=this.currentIteration;
			
		}
		
		currentIteration++;
		mutationsSoFar+=mutationsThisIteration;
		mutationsThisIteration=0;
	}
	

	/**
	 * 
	 * A method that returns the most fit state of this population
	 * @return the most fit state of this population
	 */
	public TileProblemState mostFitState(){
		
		TileProblemState best=states[0];
		
		for (TileProblemState s : states) {
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
	public GeneticState[] pairStatesAtIndexes(int index1,int index2,Integer[] segmentsOfFirstOne){
		
		GeneticState [] newOnes=states[index1].pairWith(states[index2], segmentsOfFirstOne);
		states[index1]=(TileProblemState)newOnes[0];
		states[index2]=(TileProblemState)newOnes[1];
		
		return newOnes;
	}


	public int getCurrentIterationNr() {
		return this.currentIteration;
	}





	@Override
	public void setStates(State[] newStates) {
		ArrayList<TileProblemState> theStates=new ArrayList<TileProblemState>();
		for (State state : newStates) {
			theStates.add((TileProblemState)state);
		}
		this.states=theStates.toArray(new TileProblemState[theStates.size()]);
	}

	@Override
	public void addMutationThisIteration() {
		mutationsThisIteration++;
	}

	/**
	 * 
	 * A method to add a state to the population
	 * @param stateToAdd
	 */
	@Override
	public void addState(State stateToAdd) {
		if(stateToAdd.getClass().isAssignableFrom(TileProblemState.class)){
			
			TileProblemState[] newStates=new TileProblemState[this.states.length+1];
			int i;
			for (i = 0; i < this.states.length; i++) {
				newStates[i]=this.states[i];
			}
			newStates[i]=(TileProblemState)stateToAdd;
			
			
			
			this.states=newStates;
		}
		
	}

	/**
	 * 
	 * A method that removes a state from the state list.
	 * This may happen for example because a state may "die"
	 * 
	 * @param stateToRemove the state to be removed from the population
	 */
	@Override
	public void removeState(State stateToRemove) {
		if(stateToRemove.getClass().isAssignableFrom(TileProblemState.class)){
			
			ArrayList<TileProblemState> newStates=new ArrayList<TileProblemState>();
			for (TileProblemState state : states) {
				if(state!=stateToRemove){
					newStates.add(state);
				}
			}
			
			this.states=newStates.toArray(new TileProblemState[newStates.size()]);
			
		}
		
	}

	@Override
	public int maxBitToToggle() {
		return Construction.nrConstructions()-1;
	}

	public Construction[] getConstructions() {
		return this.constructions;
	}
}
