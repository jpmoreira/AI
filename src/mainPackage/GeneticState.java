package mainPackage;

public interface GeneticState {
	
	
	int nrSegments();
	
	/**
	 * 
	 * A method for getting the chromosome representation of the state
	 * 
	 * */
	int[] chromossome();
	
	/**
	 * 
	 * A method use to pair two states. It does not prevent that the Resulting States has more than one tile with the same construction
	 * @param otherState the state this object is to be paired with
	 * @param segmentsFromSelf the segments from this object that are to be passed to the the first of the childs
	 * @return the state resulting from the pairing
	 */
	public GeneticState[] pairWith(GeneticState otherState, Integer[] segmentsOfSelf);
	
	
	/**
	 * 
	 * A method to mutate a given segment of the chromosome. This method does not prevent that the mutation leads to multiple states with the same chromosome representation.
	 * @param segmentNr the number of the segment of the chromosome
	 * @param bitToToggle the bit that is to be changed
	 */
	public void mutate(int segmentNr,int bitToToggle);

}
