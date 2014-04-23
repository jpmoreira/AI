package mainPackage.constructions;
import java.util.HashMap;

import mainPackage.ConstantManager;
import mainPackage.Coordinate;
import mainPackage.Tile;
import Exceptions.ConstructionException;
import Exceptions.ConstructionException.ConstructionExeptionCode;
/**
 * 
 * A class that represents a construction and all its properties. Intended to be subclassed
 * 
 * */
abstract public class Construction {
	
	
	/**
	 * 
	 * A private data member to hold the construction representing an invalid construction. This property should never be used explicitly as it may be null.
	 * A call to {@link nullConstruction()} should be used instead where the lazy instantiation is done.
	 * 
	 */
	private static Construction nullConstruction;
	/**
	 * A method that returns a construction that represents an invalid construction. The construction returned has a fitness of 0 whatever the tile it is attached to
	 * @return
	 */
	private static Construction nullConstruction(){
		
		if(nullConstruction==null){
			
			
			try {
				nullConstruction=new Construction(0,0) {
					
					@Override
					public float affinityToTile(Tile tile) {
						
						return 0;
					}
				};
			} catch (ConstructionException e) {}
			
		}
		return nullConstruction;
	};
	
	
	private static int indexForNextConstruction=0;

	private static HashMap<Integer, Construction> constructions=new HashMap<Integer,Construction>();
	
	/**
	 * 
	 * A property that holds the value of the prefered location for this particular construction
	 * 
	 * */
	private Coordinate preferedLocation;
	
	/**
	 * 
	 * A property to hold the chromosome representation for the object
	 * 
	 * */
	private int chromoRepresentation;
	/**
	 * 
	 * A method that should calculate how good a tile is for a given type of construction.
	 * @return A float ranging from 0 to 1. A zero represents that the tile doesn't suit this construction.
	 * 
	 * */
	abstract public float affinityToTile(Tile tile);
	
	
	/**
	 * 
	 * A simple constructor.
	 * 
	 * @param latitude the prefered latitude for the construction
	 * @param longitude the prefered longitude for the construction
	 * @param index the index of the construction (necessary to have a chomosome representation)
	 * @throws ConstructionException 
	 */
	
	public Construction(float latitude,float longitude) throws ConstructionException {
		
		
		if(indexForNextConstruction>=ConstantManager.INT_NR_BITS-1)throw new ConstructionException(ConstructionExeptionCode.MaximumNumOfConstructionsReached_Code.ordinal());
		
		preferedLocation=new Coordinate(latitude, longitude);
		chromoRepresentation=(1<<indexForNextConstruction++);
		constructions.put(this.chromoRepresentation,this);
		
	}

	/**
	 * 
	 *A method to pass a Construction object to a Cromossome representation
	 * 
	 * */
	public int toCromossome() {
		return this.chromoRepresentation;
	}

	/**
	 * 
	 * A method to get the construction that has a given chormossome sequence
	 * 
	 * @param chromossome the chromossome sequence whose construction is to be retrieved
	 * @return the construction with the passed chromossome
	 */
	static public Construction constructionWithCromossome(int chromossome){
		
		
		Construction c=constructionWithCromossome(chromossome);
		if(c==null){
			
			
		}
		return constructions.get(chromossome);
		
		
	}
	
	/**
	 * 
	 * A method that removes all previously used constructions and resets the chromossome representations
	 * 
	 */
	static public void resetConstructions(){
		
		constructions.clear();
		indexForNextConstruction=0;
	}
	
	
	
}
