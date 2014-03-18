package mainPackage;
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
		FitnessAndDiversityToProbability
		
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
	 * the size of the population we are dealing with
	 * 
	 */
	int populationSize;
	
	/**
	 * 
	 * the number of bit of a chromossome
	 * 
	 */
	int bitsPerChromossome;
	
	/**
	 * 
	 * A method that returns the states to be paired for the next generation
	 * @para inputStates the states to be selected
	 * @return 
	 */
	public State [] statesForReproduction(State[] inputStates){
		
		
		  return inputStates;//TODO implement this
	}
	/**
	 * 
	 * A method that returns the states that are to be passed along to the next generation 
	 * @param inputStates the states that will possibly be selected
	 * @return
	 */
	public State [] statesForNextGeneration(State[] inputStates){
		
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
	public GeneticRandomGenerator(fitnessToProbabilityType toNextGen,int populationSize,int bitsPerChromossome) {
		
		toNextGenerationMethod=toNextGen;
		this.populationSize=populationSize;
		this.bitsPerChromossome=bitsPerChromossome;
		
		
	}
	
	
	
	public float probabilityForNextGenerationOfState(State state){
		
		
		//TODO implement this
		
		return 0;
	}
	
	
	 

}
