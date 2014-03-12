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
	 * The mutations that are supposed to occur per 10000 states
	 * 
	 */
	int mutationsPer10Thousand;
	/**
	 * 
	 * The method to be applied to serialize states that are to be passed along to the next generation
	 */
	fitnessToProbabilityType toNextGenerationMethod;
	/**
	 * 
	 * The method to be applied to find states to be paired
	 * 
	 */
	fitnessToProbabilityType toPairMethod;
	
	
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
		
		int randomNum =(int)(Math.random()*(10000+1));
		
		if (randomNum<=mutationsPer10Thousand)return true;
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
	public GeneticRandomGenerator(fitnessToProbabilityType toNextGen,fitnessToProbabilityType toPair,int mutationsPer10Tousand) {
		
		toNextGenerationMethod=toNextGen;
		toPairMethod=toPair;
		this.mutationsPer10Thousand=mutationsPer10Tousand;
		
		
	}
	
	
	
	public float probabilityForNextGenerationOfState(State state){
		
		
		//TODO implement this
		
		return 0;
	}
	
	
	 

}
