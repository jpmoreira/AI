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
	public State(Construction[] constr,Tile[] tiles){
		
		this.constructions=constr;
		this.tiles=tiles;
	
	}

	

	public float fitness(){
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
