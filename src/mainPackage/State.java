package mainPackage;
import mainPackage.Tile;
import mainPackage.constructions.Construction;
import mainPackage.ConstantManager;
/**
 * 
 * A class that represents a given State in the resolution process
 * 
 * 
 * */
public class State {
	
	
	/**
	 * 
	 * the Constructions this state has. Order matters
	 */
	public Construction[] constructions;
	private static int currentID=0;
	
	private int id;
	
	/**
	 * 
	 * A unique identifier for a state. This identifier attribution mechanism may be reset by using the method {@link #resetStates()}.
	 * @return the unique identifier for the state
	 */
	public int id(){
		return this.id;
	}
	
	/**
	 * A method that resets the id generation process.
	 */
	public static void resetStates(){
		currentID=0;
		
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
	public int[] chromosome(){

		
		
		int []chromosome=new int[tiles.length];
		
		for(int i=0;i<chromosome.length;i++){
			chromosome[i]=constructions[i].toCromossome();
		}
		
		return chromosome;
		
		
		
		
	}
	
	/**
	 * 
	 * A method use to pair two states. It does not prevent that the Resulting States has more than one tile with the same construction
	 * @param otherState the state this object is to be paired with
	 * @param segmentsFromSelf the segments from this object that are to be passed to the the first of the childs
	 * @return the state resulting from the pairing
	 */
	public State[] pairWith(State otherState,Integer[]segmentsFromSelf){
		
		Construction[] constructionsOfChild1=new Construction[this.constructions.length];
		Construction[] constructionsOfChild2=new Construction[this.constructions.length];
		for(int i=0;i<segmentsFromSelf.length;i++){
			constructionsOfChild1[segmentsFromSelf[i]]=this.constructions[segmentsFromSelf[i]];//if it's ment to be one of this then assign it
			constructionsOfChild2[segmentsFromSelf[i]]=otherState.constructions[segmentsFromSelf[i]];
			
		}
		
		for(int i=0;i<constructionsOfChild1.length;i++){//search for the ones that have not been assigned yet.
			if(constructionsOfChild1[i]==null){//if was not previously set
				constructionsOfChild1[i]=otherState.constructions[i];//set it
				constructionsOfChild2[i]=this.constructions[i];
			}
		}
		
		
		State[] childs=new State[2];
		childs[0]=new State(constructionsOfChild1,tiles);
		childs[1]=new State(constructionsOfChild2,tiles);
		return childs;
		
	}

	/**
	 * 
	 * A method to mutate a given segment of the chromosome. This method does not prevent that the mutation leads to multiple states with the same chromosome representation.
	 * @param segmentNr the number of the segment of the chromosome
	 */
	public void mutate(int segmentNr){
		
		if(segmentNr>=constructions.length)return;
		
		//FIXME not sure this is the correct formula
		
		int nrMaxBit=currentID/2+1;//the number of the highest order bit used
		
		int bitToBeOne= (int)(Math.random()*(nrMaxBit));
		
		int newSegment=(1<<bitToBeOne);
		constructions[segmentNr]=Construction.constructionWithCromossome(newSegment);
		
		
		
		
	}
	
	/**
	 * 
	 * Simple constructor
	 * 
	 * @param constr the array of constructions this state have
	 * @param tiles the tiles to be associated with the constructions.
	 */
	public State(Construction[] constr,Tile[] tiles){
		
		this.constructions=constr;
		this.tiles=tiles;
		this.id=currentID++;
	
	}

	
	/**
	 * 
	 * A method that returns the fitness of a given state. The most fit the state the bigger then number returned.
	 * @return a number representing the fitness of the state. This number should be between 0 and 1.
	 */
	public double fitness(){
		double fitness = 0;
		if(tiles.length<constructions.length)return (double) -1.0;
		for(int i=0;i<tiles.length;i++){
			fitness+=constructions[i].affinityToTileInState(tiles[i],this);
		}
		return fitness/tiles.length;
	}
	/**
	 * 
	 * Returns a number between 0 and 1 representing the likeliness with passed states. A 0 means the states is as likely as possible with the remaining population and 1 means it's as different as possible.
	 * @param states the states to be tested against this state for similarities.
	 * @return a number between 0 and 1 representing the level of similarities between this state and the ones passed as described above.
	 */
	public double diversity(State[] states){
		
		int diversity=0;
		int iterations=0;
		
		
		for (State state : states) {
			for(int i=0;i<state.constructions.length;i++){
				iterations++;
				if(state.constructions[i]!=this.constructions[i])diversity++;
			}
		}
		
		return diversity/(double)iterations;
	}
	
	/**
	 * 
	 * 
	 * A method that returns a string with the visual representation of a state
	 * @return the visual representation of a state
	 */
	public String visualRepresentation(){
		
		String rep=id+"-[";
		
		for(int i=0;i<constructions.length;i++){
			
			rep+=constructions[i].name();
			if(constructions.length-1!=i)rep+="-";
			else rep+="]";
		}
		
		return rep;
	}

	
	//TODO document it
	
	public Construction constructionForTile(Tile t){
		
		for(int i=0;i<tiles.length;i++){
			if(this.tiles[i]==t){
				return this.constructions[i];
			}
		}
		return null;
		
	}

}
