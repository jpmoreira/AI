package mainPackage;
import java.util.ArrayList;
import java.util.Arrays;

import com.sun.tools.javac.code.Attribute.Array;

import mainPackage.State;


public class GeneticRandomGenerator {
	
	
	private enum fitnessToProbabilityType{
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
	 * The decreasing factor by which the {@link mutationProbability} value is decreased in each iteration. The default value is 1.0 which implies that {@link mutationProbability} value doesn't decrease.
	 * To set this value  {@link setVariableMutationProbability()} should be used.
	 * 
	 * 
	 */
	private double mutationProbVarFactor;
	
	double mutationProbability;
	/**
	 * 
	 * The method to be applied to serialize states that are to be passed along to the next generation
	 */
	private fitnessToProbabilityType toNextGenerationMethod;
	
	
	/**
	 * 
	 * A boolean that states whether a direct translation of fitness to probability is to be used or on the other hand a ranking method.
	 * for more detailed information see documentation of {@link enableFitnessToRank()} and {@link enableDirectMethod()}.
	 * 
	 */
	private boolean directFitnessToProbability;
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
		
		
		return this.statesForReproduction_Direct_Fitness_To_Probability();//FIXME only supporting one mode!
		
		
	}
	
	/**
	 * 
	 * A method that returns the states for reproduction using the method fitness to rank.
	 * @return the states to be used in reproduction
	 */
	private State [] statesForReproduction_Fitness_To_Rank(){
		//TODO implement it
		
		GeneticRandomGenerator.BubbleSort(population.states(), population.states().length);//sort states
		
		
		
		return null;
	}

	/**
	 * 
	 * A method that returns the states for reproduction using the method {@link directFitnessToProbability}.
	 * @return the states to be used in reproduction
	 */
	public State [] statesForReproduction_Direct_Fitness_To_Probability(){
		
		ArrayList<State> statesForReproduction=new ArrayList<State>();
		
		
		double overallFitness=this.population.overallFitness();
		double randomNumber;
		double probForReproduction;
		int stateIndex=0;
		State currentState;
		
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
	
	
	/**
	 * 
	 * A method that returns the index of the segments of the choromosome of a given state to be passed along to his first descendant
	 * @param state the state whose chromosome is to be passed to his descendant
	 * @return the zero based index of the chromosome segments to be passed along to the passed state's first descendant
	 */
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
	boolean stateShouldMutate(State s){
		
		if(s==population.mostFitState())return false;//never mutates most fit state
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
	public GeneticRandomGenerator(Population pop,int statesToPair, double mutationProb) {
		
		toNextGenerationMethod=fitnessToProbabilityType.DirectFitnessToProbability;
		this.mutationProbVarFactor=1.0;
		this.population=pop;
		this.mutationProbability=mutationProb;
		this.statesToPair=statesToPair;
		
		
	}
	
	
	/**
	 * 
	 * 
	 * 
	 * This method is used to put at the head of the array a given number of the most fit states. Useful since most times not the hole array needs to be sorted
	 * @param states all the states
	 * @param nrStatesToOrder the number of states to be guaranteed to be at the head of the array
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
	
	/**
	 * 
	 * A method to be called at each iteration. It updates the parameters necessary for algorithm to update.
	 * This method should be explicitly called at the end of each iteration
	 * 
	 * 
	 */
	 public void updateParameters(){
		 
		 //FIXME probably more parameter should be updated;
		 //FIXME this method should be called after an iteration
		 this.mutationProbability=this.mutationProbability*this.mutationProbVarFactor;
		 
	 }
	
	//SETTING FITNESS TO PROB METHOD
	
	/**
	 * 
	 * A method that defines the way to translate a state's fitness to probability of being chosen for reproduction and/or selection for the next generation as a direct method. Meaning the likeliness of a state being chosen is directly proportional to his fitness. This is the default behaviour. 
	 * 
	 */
	public void enableDirectMethod(){
		//TODO implement it
	}
	
	/**
	 * 
	 * A method that defines the way to translate a state's fitness to probability of being chosen for reproduction and/or selection for the next generation as a direct method. Meaning the likeliness of a state being chosen is (1-rankProb)^n for the nth state with the best fitness.
	 * @param rankProb the parameter to be used for calculating the probability of a state being chosen according to the formula presented above.
	 */
	public void enableFitnessToRank(double rankProb){
		
		//TODO implement it
	}
	
	/**
	 * 
	 * 
	 * A method that enables or disables the usage of diversity as a method to calculate likelihood of a state to pass along to the next generation or to reproduce itself. By default diversity is not used
	 * @param enable a boolean that states whether diversity is to be used or not.
	 */
	public void enableFitnessAndDiversity(boolean enable){
	
		//TODO implement it
	}
	
	//SETTING DECREASING MUTATION PROB
	
	/**
	 * A method that sets the increment/decrement factor for {@link mutationProbability}.
	 * Change in {@link mutationProbability} as well as other variable parameters will only occur after a call to {@link updateParameters()}.
	 * 
	 * @param factor the factor by which {@link mutationProbability} should be multiplied at each cycle. This number shouldn't be negative.A value smaller than 1 means that {@link mutationProbability} will decrease over time.A value bigger than 1 means that {@link mutationProbability} will increase over time.
	 */
	public void setVariableMutationProbability(double factor){
		
		this.mutationProbVarFactor=factor;
		
	}


}
