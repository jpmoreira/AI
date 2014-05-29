package mainPackage;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class AlgorithmEngine.
 */
public abstract class AlgorithmEngine implements Serializable{

	/** The population. */
	protected Population population;
	
	/**
	 * 
	 * the default random number generator to be used in case no other was
	 * specified. Specification of random generator is made on a method basis,
	 * mainly to facilitate testing.
	 * 
	 */
	protected RandomNrGenerator defaultGenerator;
	
	
	/**
	 * 
	 * The number of the current iteration.
	 */
	protected int currentIteration=0;
	
	/** The consecutive non improving generations. */
	protected int consecutiveNonImprovingGenerations=0;
	
	
	/** The max number allowed non improving generations. */
	protected int maxNrAllowedNonImprovingGenerations=Integer.MAX_VALUE;
	
	/** The max number iterations. */
	protected int maxNrIterations=Integer.MAX_VALUE;
	
	/** The minimum fitness. */
	protected double minimumFitness=1.1;
	
	/** The max difference to mean. */
	protected double maxDifferenceToMean=0;
	
	/**
	 * Gets the maximum number of allowed non improving generations for the algorithm to stop.
	 *
	 * @return the maximum number allowed non improving generations
	 */
	public int getMaxNrAllowedNonImprovingGenerations() {
		return maxNrAllowedNonImprovingGenerations;
	}

	/**
	 * Sets the maximum number of allowed non improving generations for the algorithm to stop.
	 *
	 * @param maxNrAllowedNonImprovingGenerations the new max nr allowed non improving generations
	 */
	public void setMaxNrAllowedNonImprovingGenerations(
			int maxNrAllowedNonImprovingGenerations) {
		this.maxNrAllowedNonImprovingGenerations = maxNrAllowedNonImprovingGenerations;
	}

	/**
	 * Gets the max number iterations for the algorithm to stop.
	 *
	 * @return the max number iterations
	 */
	public int getMaxNrIterations() {
		return maxNrIterations;
	}

	/**
	 * Sets the max number of iterations for the algorithm to stop.
	 *
	 * @param maxNrIterations the new max number iterations
	 */
	public void setMaxNrIterations(int maxNrIterations) {
		this.maxNrIterations = maxNrIterations;
	}

	/**
	 * Gets the minimum fitness required for the algorithm to stop.
	 *
	 * @return the minimum fitness
	 */
	public double getMinimumFitness() {
		return minimumFitness;
	}

	/**
	 * Sets the minimum fitness required for the algorithm to stop.
	 *
	 * @param minimumFitness the new minimum fitness
	 */
	public void setMinimumFitness(double minimumFitness) {
		this.minimumFitness = minimumFitness;
	}

	
	/**
	 * Gets the max difference to mean.
	 *
	 * @return the max difference to mean
	 */
	public double getMaxDifferenceToMean() {
		return maxDifferenceToMean;
	}

	/**
	 * A setter for the maximum allowed distance from the best state to the mean in order to stop.
	 *
	 * @param maxDifferenceToMean the new max difference to mean
	 */
	public void setMaxDifferenceToMean(double maxDifferenceToMean) {
		this.maxDifferenceToMean = maxDifferenceToMean;
	}

	/**
	 * Sets the stop condition.
	 *
	 * @param maxNonEvolvingIterations the max non evolving iterations
	 * @param maxNonImprovingIterations the max non improving iterations
	 */
	public void setStopCondition(int maxNonEvolvingIterations, int maxNonImprovingIterations){
		
		this.maxNrAllowedNonImprovingGenerations=maxNonImprovingIterations;
	}
	
	/**
	 * Stop condition met.
	 *
	 * @return true, if successful
	 */
	public boolean stopConditionMet(){
		
		if(consecutiveNonImprovingGenerations>maxNrAllowedNonImprovingGenerations)return true;
		if(currentIteration>=maxNrIterations)return true;
		if(population.mostFitState().fitness()>minimumFitness)return true;
		if(population.meanFitness()/population.mostFitState().fitness()<maxDifferenceToMean)return true;
		return false;
		
	}

	/**
	 * Iterate.
	 */
	public abstract void iterate();

	/**
	 * Gets the population.
	 *
	 * @return the population
	 */
	public Population getPopulation() {

		return population;
	}
	
	/**
	 * Instantiates a new algorithm engine.
	 *
	 * @param p the p
	 */
	AlgorithmEngine(Population p){
		
		this.population=p;
		this.defaultGenerator=new DefaultRandomGenerator();
	}
}
