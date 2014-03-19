package mainPackage;
import java.util.ArrayList;

import mainPackage.State;


public class GeneticRandomGenerator {
	
	
	public enum fitnessToProbabilityType{
		/**
		 * Method to translate fitness into probability that divides the actual fitness of a State by the overall fitness and translates that directly into probability
		 * 
		 */
		DirectFitnessToProbability,
		
		/**
		 * Method that orders the States by their fitness making the nth most fit state to be the nth with the biggest probability of survival.
		 * The value of the fitness itself doesn't influence the probability, is only a mean to indicate their relative order.  
		 * 
		 */
		FitnessToRank,
		
		/**
		 * 
		 * Method that takes into account the diversity along with the fitness to measure probabilities
		 * 
		 */
		FitnessAndDiversityToProbability//TODO maybe support it later
		
	}
	/**
	 * 
	 * The probability a single bit will be toggled
	 * 
	 */
	float mutationProbability;
	/**
	 * 
	 * The method to be applied to serialize states that are to be passed along to the next generation
	 */
	fitnessToProbabilityType toNextGenerationMethod;
	/**
	 * 
	 * The probability a state will be paired
	 * 
	 */
	float pairingProbability;
	/**
	 * 
	 * The Population We are dealing with
	 * 
	 */
	Population population;
	
	/**
	 * 
	 * the number of bit of a chromossome
	 * 
	 */
	int bitsPerChromossome;
	/**
	 * A number to be passed to check how many states are to be passed to the next generation in case {@link #toNextGenerationMethod toNextGenerationMethod} is set to {@link fitnessToProbabilityType#FitnessToRank FitnessToRank}
	 * 
	 */
	int nrOfElitistStatesToSelect;
	
	/**
	 * 
	 * A method that returns the states to be paired for the next generation
	 * @param inputStates the states to be selected
	 * @return an array withTheStatesForNextGeneration
	 */
	public State [] statesForReproduction(State[] inputStates){
		
		ArrayList<State> statesForReproduction=new ArrayList<State>();
		for (State state : inputStates) {
			double randomNumber=Math.random();
			if(randomNumber<=pairingProbability)statesForReproduction.add(state);
		}
		
		if(statesForReproduction.size()%2!=0)statesForReproduction.remove(0);//if there is an odd number of states remove one
		
		return (State[]) statesForReproduction.toArray();
		
	}
	/**
	 * 
	 * A method that returns the states that are to be passed along to the next generation 
	 * @param inputStates the states that will possibly be selected
	 * @return
	 */
	public State [] statesForNextGeneration(State[] inputStates){
		
		int popSize=population.populationSize();
		double overallFitness=population.overallFitness();
		ArrayList<State> selectedStates=new ArrayList<State>();
		
		if(toNextGenerationMethod==fitnessToProbabilityType.DirectFitnessToProbability){
			
			while(selectedStates.size()<popSize){//while we havent selected the nr of states required
				for (State state : inputStates) {//for each input state
					double fitness=state.fitness();
					double randomNr=Math.random();
					if(randomNr<=fitness)selectedStates.add(state);//if the number fits then add
					if(selectedStates.size()==popSize)return (State[])selectedStates.toArray();
				}
			}
			
		}
		else if(toNextGenerationMethod==fitnessToProbabilityType.FitnessToRank){//case of elitist selection
			
			
			
		}
		
		return inputStates;//TODO implement this
		
	}
	
	

	
	/**
	 * 
	 * A function return a value that is intended to make a state mutate or not
	 * 
	 * @return
	 */
	boolean stateShouldMutate(){
		
		//TODO implement this. Maybe this is not the betters predicate! Maybe this class should be asked bits to toogle or states to pair etc instead of this way!!!
		
		return false;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param toNextGen
	 * @param toPair the method to translate into Pairing Probability
	 * @param mutationsPer10Tousand the number of mutations that should happen to an individual per 10 Thousand generations
	 */
	public GeneticRandomGenerator(fitnessToProbabilityType toNextGen,int bitsPerChromossome,int nrOfElitistStatesToSelect,Population pop) {
		
		toNextGenerationMethod=toNextGen;
		this.bitsPerChromossome=bitsPerChromossome;
		this.nrOfElitistStatesToSelect=nrOfElitistStatesToSelect;
		this.population=pop;
		
		
	}
	
	
	
	public float probabilityForNextGenerationOfState(State state){
		
		
		//TODO implement this
		
		return 0;
	}
	
	
	/**
	 * 
	 * 
	 * 
	 * This method is used to put at the head of the array a given number of the most fit states
	 * @param states all the states
	 * @param nrStatesToOrder the number of states to be garanteed to be at the head of the array
	 * @param tiles the tiles to give the states in order for them to evaluate their fitness
	 */
	public static void BubbleSort(State[] states,int nrStatesToOrder,Tile[] tiles) {
		 for (int i = 0; i < states.length && i<nrStatesToOrder; i++) {
		    for (int x = states.length-1; x >i; x--) {
		        if (states[x].fitness() > states[x-1].fitness()) {
		            State temp = states[x];
		            states[x] = states[x-1];
		            states[x-1] = temp;

		        }
		    }
		  }
		}
	
	 

}
