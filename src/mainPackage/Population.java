package mainPackage;

public interface Population {
	
	//TODO document it
	public State[] states();
	
	
	
	/**
	 * 
	 * A method to add a state to the population
	 * @param stateToAdd
	 */
	void addState(State stateToAdd);
	
	/**
	 * 
	 * A method that removes a state from the state list.
	 * This may happen for example because a state may "die"
	 * 
	 * @param stateToRemove the state to be removed from the population
	 */
	void removeState(State stateToRemove);
	
	/**
	 * 
	 * A method that changes the states of a given population. Used mainly for testing purposes.
	 * @param newStates the new group of states. This group of states should be of the same size as the one the population currently has.
	 */
	public void setStates(State[] newStates);
	
	///STATISTICS STUFF
	
	
	//TODO document it
		public int getCurrentIterationNr();
	
	/**
	 * 
	 * 
	 * A method that returns the current mean fitness.
	 * @return the current mean fitness of the population
	 */
	public double meanFitness();
	
	//TODO document it
	public int iterationNrForBestStateEver();
	
	/**
	 * 
	 * A method that returns the number of mutations this last iteration
	 * @return the number of mutations this last iteration
	 */
	public int mutationsThisIteration();
	
	/**
	 * 
	 * A method that returns the mean number of mutations per iteration
	 * @return the number of mutations per iteration
	 */
	public int meanMutationsPerIteration();
	
	/**
	 * 
	 * 
	 * A method that returns the mean number of mutations so far.
	 * @return the number of mutations so far
	 */
	public int mutationsSoFar();
	
	/**
	 * 
	 * A method that updates the statistics relative to this population.
	 */
	public void updateBestStateEver();
	
	/**
	 * 
	 * A method that returns the most fit state of this population
	 * @return the most fit state of this population
	 */
	public State mostFitState();
	
	/**
	 * 
	 * Returns the best state ever for this population.
	 * @return
	 */
	public State bestStateEver();
	
	
	/**
	 * 
	 * A method that returns an array with the fitness for all the states in the population 
	 * @return an array with the fitness for each state.
	 */
	public double[] fitnessArray();
	
	//TODO verify if necessary
		/**
		 * 
		 * A method that returns the sum of the fitnesses of all states
		 */
	public double overallFitness();
	
	//FIXME document
	public double overallDiversity();
	
	
	//TODO document it
	public void addMutationThisIteration();
	
	public int maxBitToToggle();
	
	public void updateIteration();

}
