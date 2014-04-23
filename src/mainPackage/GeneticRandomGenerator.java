package mainPackage;
import java.util.ArrayList;
import java.util.Arrays;

import com.sun.tools.javac.code.Attribute.Array;

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
	double mutationProbability;
	/**
	 * 
	 * The method to be applied to serialize states that are to be passed along to the next generation
	 */
	fitnessToProbabilityType toNextGenerationMethod;
	/**
	 * 
	 * The number of states to be paired in each iteration
	 * 
	 */
	int statesToPair;
	/**
	 * 
	 * The Population We are dealing with
	 * 
	 */
	Population population;
	
	
	/**
	 * 
	 * A method that returns the states to be paired for the next generation
	 * @param inputStates the states to be selected
	 * @return an array withTheStatesForNextGeneration
	 */
	public State [] statesForReproduction(){
		
		
		ArrayList<State> statesForReproduction=new ArrayList<State>();
		
		
		double overallFitness=this.population.overallFitness();
		double randomNumber;
		double probForReproduction;
		int stateIndex=0;
		State currentState;
		
		//TODO for now only supporting direct fitness to probability method
		while(statesForReproduction.size()<this.statesToPair){
			
			randomNumber=Math.random();
			currentState=this.population.states()[stateIndex];
			probForReproduction=currentState.fitness()/overallFitness;
			if(randomNumber<=probForReproduction)statesForReproduction.add(currentState);
			
			stateIndex++;
			if(stateIndex==this.population.states().length)stateIndex=0;
			
			
			
		}
		
		
		return statesForReproduction.toArray(new State[statesForReproduction.size()]);
		
		
		
		
		
	}

	//TODO document it
	public Integer[] segmentsOfState(State state){
		
		ArrayList<Integer> segments=new ArrayList<Integer>();
		double randomNr=Math.random();
		
		for(int i=0;i<state.tiles.length;i++){
			
			if(randomNr<0.5)segments.add(i);
			
		}
		
		return segments.toArray(new Integer[segments.size()]);
		
	}
	
	

	//TODO implement it
	public State[] statesForNextGen(){
		
		State[] orderedStates=this.population.states().clone();
		
		int nrOfElitistStatesToSelect=this.population.states().length-statesToPair;
		
		GeneticRandomGenerator.BubbleSort(orderedStates, nrOfElitistStatesToSelect);
		
		State[] statesForNextGen=new State[nrOfElitistStatesToSelect];
		
		for(int i=0;i<nrOfElitistStatesToSelect;i++){
			statesForNextGen[i]=orderedStates[i];
		}
		
		return statesForNextGen;
		
	}
	
	/**
	 * 
	 * A function return a value that is intended to make a state mutate or not
	 * 
	 * @return
	 */
	boolean stateShouldMutate(){
		
		if(Math.random()<mutationProbability)return true;
		
		
		return false;
	}
	
	int mutatingSegmentForState(State s){
		
		return (int) Math.random()*s.tiles.length;
		
		
	}
	
	/**
	 * 
	 * @param toNextGen
	 * @param toPair the method to translate into Pairing Probability
	 * @param mutationsPer10Tousand the number of mutations that should happen to an individual per 10 Thousand generations
	 */
	public GeneticRandomGenerator(fitnessToProbabilityType toNextGen,Population pop,int statesToPair, double mutationProb) {
		
		toNextGenerationMethod=toNextGen;
		this.population=pop;
		this.mutationProbability=mutationProb;
		this.statesToPair=statesToPair;
		
		
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
	public static void BubbleSort(State[] states,int nrStatesToOrder) {
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
