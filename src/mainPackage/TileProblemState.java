package mainPackage;

import java.io.Serializable;

import mainPackage.Tile;
import mainPackage.constructions.Construction;

/**
 * 
 * A class that represents a given State in the resolution process
 * 
 * 
 * */
@SuppressWarnings("serial")
public class TileProblemState  extends State implements Serializable,GeneticState,SimulatedAnnealingState{

	/**
	 * 
	 * the Constructions this state has. Order matters
	 */
	public Construction[] constructions;

	private static int currentID = 0;

	private double repetedConstructionFactor=1.0;

	private int id;

	/**
	 * 
	 * A unique identifier for a state. This identifier attribution mechanism
	 * may be reset by using the method {@link #resetStates()}.
	 * 
	 * @return the unique identifier for the state
	 */
	public int id() {
		return this.id;
	}

	/**
	 * A method that resets the id generation process.
	 */
	public static void resetStates() {
		currentID = 0;

	}

	/**
	 * 
	 * 
	 * the tiles this state has.
	 * 
	 */
	public Tile[] tiles;

	/**
	 * 
	 * A method for getting the chromosome representation of the state
	 * 
	 * */
	public int[] chromossome() {

		int[] chromosome = new int[tiles.length];

		for (int i = 0; i < chromosome.length; i++) {
			chromosome[i] = constructions[i].toCromossome();
		}

		return chromosome;

	}

	/**
	 * 
	 * A method use to pair two states. It does not prevent that the Resulting
	 * States has more than one tile with the same construction
	 * 
	 * @param other_state
	 *            the state this object is to be paired with
	 * @param segmentsFromSelf
	 *            the segments from this object that are to be passed to the the
	 *            first of the childs
	 * @return the state resulting from the pairing
	 */
	public GeneticState[] pairWith(GeneticState other_state,
			Integer[] crossPoints) {

		TileProblemState otherState=(TileProblemState)other_state;
		boolean forThis=true;
		int start=0;//start point for this iteration (we start at the begining of the constructions)
		int end=crossPoints[0];
		Construction [] c1=new Construction[this.constructions.length];
		Construction [] c2=new Construction[this.constructions.length];
		
		for(int i=0;i<=crossPoints.length;i++){//run 1xcrossPoints + 1 time for the last part
			for(int f=start;f<end;f++){//this portion goes from start to end
				if(forThis){
					c1[f]=this.constructions[f];
					c2[f]=otherState.constructions[f];
				} 
				else{
					c2[f]=this.constructions[f];
					c1[f]=otherState.constructions[f];
					
				}
			}
			if(forThis)forThis=false;//if this segment was given from the "this" state next portion should not
			else forThis=true;
			if(i<crossPoints.length)start=crossPoints[i];
			if(i<crossPoints.length-1)end=crossPoints[i+1];//if not a last point
			else end=c1.length;//if at last crossoverPoint
		}
		
		TileProblemState s1=new TileProblemState(c1, this.tiles);
		TileProblemState s2=new TileProblemState(c2, this.tiles);
		
		GeneticState[] array={s1,s2};
		return array;
		
	}

	/**
	 * 
	 * A method to mutate a given segment of the chromosome. This method does
	 * not prevent that the mutation leads to multiple states with the same
	 * chromosome representation.
	 * 
	 * @param segmentNr
	 *            the number of the segment of the chromosome
	 * @param bitToToggle
	 *            the bit that is to be changed
	 */
	public void mutate(int segmentNr, int bitToToggle) {

		if (segmentNr >= constructions.length)
			return;

		int newSegment = (1 << bitToToggle);
		constructions[segmentNr] = Construction
				.constructionWithCromossome(newSegment);

	}

	/**
	 * 
	 * Simple constructor
	 * 
	 * @param constr
	 *            the array of constructions this state have
	 * @param tiles
	 *            the tiles to be associated with the constructions.
	 */
	public TileProblemState(Construction[] constr, Tile[] tiles,
			double repetedConstructionFactor) {

		this.constructions = constr;
		this.tiles = tiles;
		this.id = currentID++;
		this.repetedConstructionFactor = repetedConstructionFactor;

	}
	public TileProblemState(Construction[] constr, Tile[] tiles) {

		this(constr,tiles,1.0);
	}

	/**
	 * 
	 * A method that returns the number of repeated constructions in this state
	 * 
	 * @return the number of repeated constructions in this state
	 */
	private int repetedConstructions() {

		int repetitions = 0;
		for (int i = 0; i < this.constructions.length; i++) {
			for (int f = i + 1; f < this.constructions.length; f++) {
				if (constructions[f] == constructions[i])
					repetitions++;
			}
		}

		return repetitions;
	}

	/**
	 * 
	 * A method that returns the fitness of a given state. The most fit the
	 * state the bigger then number returned.
	 * 
	 * @param repetedConstructionsFactor
	 *            the factor by which the fitness is to be multiplied by each
	 *            construction repetition
	 * @return a number representing the fitness of the state. This number
	 *         should be between 0 and 1.
	 */
	public double fitness() {
		double fitness = 0;
		if (tiles.length < constructions.length)
			return (double) -1.0;
		for (int i = 0; i < tiles.length; i++) {
			fitness += constructions[i].affinityToTileInState(tiles[i], this);
		}
		fitness /= tiles.length;

		fitness *= Math.pow(repetedConstructionFactor,
				this.repetedConstructions());
		return fitness;
	}

	/**
	 * 
	 * Returns a number between 0 and 1 representing the likeliness with passed
	 * states. A 0 means the states is as likely as possible with the remaining
	 * population and 1 means it's as different as possible.
	 * 
	 * @param states
	 *            the states to be tested against this state for similarities.
	 * @return a number between 0 and 1 representing the level of similarities
	 *         between this state and the ones passed as described above.
	 */
	public double diversity(State[] states) {

		TileProblemState[] s=(TileProblemState[])states;
		int diversity = 0;
		int iterations = 0;

		for (TileProblemState state : s) {
			if (state == this)
				continue;
			for (int i = 0; i < state.constructions.length; i++) {
				iterations++;
				if (state.constructions[i] != this.constructions[i])
					diversity++;
			}
		}

		return diversity / (double) iterations;
	}

	/**
	 * 
	 * 
	 * A method that returns a string with the visual representation of a state
	 * 
	 * @return the visual representation of a state
	 */
	public String visualRepresentation() {

		String rep = id + "-[";

		for (int i = 0; i < constructions.length; i++) {

			rep += constructions[i].name();
			if (constructions.length - 1 != i)
				rep += "-";
			else
				rep += "]";
		}

		return rep;
	}

	/**
	 * 
	 * A method that returns the construction in a given tile
	 * 
	 * @param t
	 *            the tile whose constructions is to be returned
	 * @return the construction that is to be placed on the passed tile
	 */

	public Construction constructionForTile(Tile t) {

		for (int i = 0; i < tiles.length; i++) {
			if (this.tiles[i] == t) {
				return this.constructions[i];
			}
		}
		return null;

	}


	public int nrSegments() {
		return this.tiles.length;
	}

	@Override
	public double repetitionFactor() {
		return this.repetedConstructionFactor;

	}

	@Override
	public void setRepetitionFactor(double newFactor) {
		this.repetedConstructionFactor=newFactor;
		
	}



	@Override
	public SimulatedAnnealingState evolve(double randomNr) {
		
		int constructionToChange=(int)((this.tiles.length-1)*randomNr);
		
		Construction cToChange=constructions[constructionToChange];
		
		
		Construction newC;
		if(cToChange==Construction.nullConstruction()){
			newC=Construction.constructionWithCromossome(1);
		}
		else{
			newC=Construction.constructionWithCromossome(cToChange.toCromossome()<<1);
		}
		
		
		if(newC==null){
			
			newC=Construction.constructionWithCromossome(1);
			
		}
		
		Construction[] newConstructionArray=this.constructions.clone();
		
		newConstructionArray[constructionToChange]=newC;
		
		
		return new TileProblemState(newConstructionArray, this.tiles);
	}

	@Override
	public int nrSuccessors() {
		return (Construction.latestConstructionIndex()+1)*this.tiles.length;
	}
	
	



	
}
