package mainPackage;

public  abstract class State {
	
	/**
	 * 
	 * A method that returns the fitness of a given state. The most fit the state the bigger then number returned.
	 * @return a number representing the fitness of the state. This number should be between 0 and 1.
	 */
	abstract double fitness();
	
	/**
	 * 
	 * Returns a number between 0 and 1 representing the likeliness with passed states. A 0 means the states is as likely as possible with the remaining population and 1 means it's as different as possible.
	 * @param states the states to be tested against this state for similarities.
	 * @return a number between 0 and 1 representing the level of similarities between this state and the ones passed as described above.
	 */
	abstract double diversity(State[] states);
	
	/**
	 * 
	 * 
	 * A method that returns a string with the visual representation of a state
	 * @return the visual representation of a state
	 */
	abstract String visualRepresentation();

	
	abstract public int nrSegments();
	
	abstract public double repetitionFactor();
	abstract public void setRepetitionFactor(double newFactor);
	
}
