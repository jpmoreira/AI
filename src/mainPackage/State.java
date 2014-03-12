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
	
	/**
	 * 
	 * A method for getting the chromosome representation of the state
	 * 
	 * */
	public int[] chromosome(int nrTiles){

		
		
		int []chromosome=new int[nrTiles];
		
		for(int i=0;i<chromosome.length;i++){
			chromosome[i]=constructions[i].toCromossome();
		}
		
		return chromosome;
		
		
		
		
	}
	
	/**
	 * 
	 * A method use to pair two states. It does not prevent that the Resulting States has more than one tile with the same construction
	 * @param otherState the state this object is to be paired with
	 * @param segmentsFromSelf the segments from this object that are to be passed to the next generation
	 * @return the state resulting from the pairing
	 */
	public State pairWith(State otherState,int[]segmentsFromSelf){
		
		Construction[] constructionsOfChild=new Construction[this.constructions.length];
		for(int i=0;i<segmentsFromSelf.length;i++)constructionsOfChild[segmentsFromSelf[i]]=this.constructions[segmentsFromSelf[i]];//if it's ment to be one of this then assign it
		
		for(int i=0;i<constructionsOfChild.length;i++){//search for the ones that have not been assigned yet.
			if(constructionsOfChild[i]==null){//if was not previously set
				constructionsOfChild[i]=otherState.constructions[i];//set it
			}
		}
		
		
		return new State(constructionsOfChild);
		
	}

	/**
	 * 
	 * A method to mutate a given segment of the chromosome. This method does not prevent that the mutation leads to multiple states with the same chromosome representation.
	 * @param segmentNr the number of the segment of the chromosome
	 */
	public void mutate(int segmentNr){
		
		if(segmentNr>=constructions.length)return;
		
		int bitToBeOne= (int)(Math.random()*(ConstantManager.INT_NR_BITS-1));
		
		int newSegment=(1<<bitToBeOne);
		constructions[segmentNr]=Construction.constructionWithCromossome(newSegment);
		
		
		
		
	}
	
	/**
	 * 
	 * Simple constructor
	 * 
	 * @param pop the population this State belongs to
	 * @param constr the array of constructions this state have
	 */
	public State(Construction[] constr){
		
		this.constructions=constr;
	
	}

	

	public float fitnessForTiles(Tile[] tiles){
		float fitness = 0;
		if(tiles.length<constructions.length)return (float) -1.0;
		for(int i=0;i<tiles.length;i++){
			fitness+=constructions[i].affinityToTile(tiles[i]);
		}
		return fitness;
	}

	public int diversity(State[] states){
		
		int diversity=0;
		
		for (State state : states) {
			for(int i=0;i<state.constructions.length;i++){
				if(state.constructions[i]!=this.constructions[i])diversity++;
			}
		}
		
		return diversity;
	}
	
}
