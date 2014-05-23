package mainPackage;

public interface State {
	
	/**
	 * 
	 * A method that returns the fitness of a given state. The most fit the state the bigger then number returned.
	 * @return a number representing the fitness of the state. This number should be between 0 and 1.
	 */
	double fitness();
	
	/**
	 * 
	 * Returns a number between 0 and 1 representing the likeliness with passed states. A 0 means the states is as likely as possible with the remaining population and 1 means it's as different as possible.
	 * @param states the states to be tested against this state for similarities.
	 * @return a number between 0 and 1 representing the level of similarities between this state and the ones passed as described above.
	 */
	double diversity(State[] states);
	
	/**
	 * 
	 * 
	 * A method that returns a string with the visual representation of a state
	 * @return the visual representation of a state
	 */
	String visualRepresentation();
	
	/**
	 * 
	 * A method to mutate a given segment of the chromosome. This method does not prevent that the mutation leads to multiple states with the same chromosome representation.
	 * @param segmentNr the number of the segment of the chromosome
	 * @param bitToToggle the bit that is to be changed
	 */
	public void mutate(int segmentNr,int bitToToggle);
	
	/**
	 * 
	 * A method use to pair two states. It does not prevent that the Resulting States has more than one tile with the same construction
	 * @param other_state the state this object is to be paired with
	 * @param segmentsFromSelf the segments from this object that are to be passed to the the first of the childs
	 * @return the state resulting from the pairing
	 */
	public TileProblemState[] pairWith(State other_state,Integer[]segmentsFromSelf);
	

	
	public int nrSegments();
	
	public double repetitionFactor();
	public void setRepetitionFactor(double newFactor);
	
	
}
