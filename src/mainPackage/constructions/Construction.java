package mainPackage.constructions;

import java.util.HashMap;

import mainPackage.ConstantManager;
import mainPackage.State;
import mainPackage.Tile;
import Exceptions.ConstructionException;
import Exceptions.ConstructionException.ConstructionExeptionCode;

/**
 * 
 * A class that represents a construction and all its properties. Intended to be
 * subclassed
 * 
 * */
abstract public class Construction {

	/**
	 * the name of the construction
	 */
	private String name;
	/**
	 * 
	 * A private data member to hold the construction representing an invalid
	 * construction. This property should never be used explicitly as it may be
	 * null. A call to {@link #nullConstruction()} should be used instead where
	 * the lazy instantiation is done.
	 * 
	 */
	private static Construction nullConstruction;

	/**
	 * A method that returns a construction that represents an invalid
	 * construction. The construction returned has a fitness of 0 whatever the
	 * tile it is attached to
	 * 
	 * @return a construction representing an empty construction
	 */
	private static Construction nullConstruction() {

		if (nullConstruction == null) {

			nullConstruction = new Construction("NULL") {

				@Override
				public double affinityToTileInState(Tile tile,State s) {

					return 0;
				}
			};

		}
		return nullConstruction;
	};

	/**
	 * 
	 * A variable to hold the value of the index for the next construction to be created.
	 */
	private static int indexForNextConstruction = 0;

	/**
	 * 
	 * The id of the construction
	 */
	private int id;
	
	private static HashMap<Integer, Construction> constructions = new HashMap<Integer, Construction>();

	/**
	 * 
	 * A method that returns the id of the construction
	 * @return the id of the construction
	 */
	public int getID(){
		
		return id;
	}

	/**
	 * 
	 * A method that returns the largest index a construction has
	 * @return the largest index a construction has
	 */
	public static int latestConstructionIndex(){
		
		return indexForNextConstruction-1;
	}

	

	
	/**
	 * 
	 * A property to hold the chromossome representation for the object
	 * 
	 * */
	private int chromoRepresentation;

	/**
	 * 
	 * A method that should calculate how good a tile is for a given type of
	 * construction.
	 * 
	 * @return A float ranging from 0 to 1. A zero represents that the tile
	 *         doesn't suit this construction.
	 * 
	 * */
	abstract public double affinityToTileInState(Tile tile, State state);

	/**
	 * 
	 * A simple constructor.
	 * 
	 * @param theName
	 *            an alias for the construction. This will be used in the state
	 *            visual representation.
	 * @throws ConstructionException
	 */

	public Construction(String theName) {

		this.name = theName;
		chromoRepresentation = (1 << indexForNextConstruction++);
		constructions.put(this.chromoRepresentation, this);

	}

	/**
	 * 
	 * A method to pass a Construction object to a Cromossome representation
	 * 
	 * */
	public int toCromossome() {
		return this.chromoRepresentation;
	}

	/**
	 * 
	 * A method to get the construction that has a given chormossome sequence
	 * 
	 * @param chromossome
	 *            the chromossome sequence whose construction is to be retrieved
	 * @return the construction with the passed chromossome
	 */
	static public Construction constructionWithCromossome(int chromossome) {

		Construction c = constructions.get(chromossome);
		if (c == null) {

			c = nullConstruction();
		}
		return c;

	}

	// TODO document it
	// TODO test it
	static public Construction constructionWithConstraints(String name,
			final double tileAdjPenalty, final Construction[] forbidenAdjacencies,
			final double wrongTilePenalty, final Tile[] disallowedTiles,
			final double areaPenalty, final double minArea, final double maxArea,
			final double soilPenalty, final Tile.SoilType[] forbiddenTypes) {

		return new Construction(name) {

			@Override
			public double affinityToTileInState(Tile tile,State s) {

				double currentAffinity = 1.0;

				// Apply wrong tile penalty
				for (int i = 0; i < disallowedTiles.length; i++) {
					if (tile == disallowedTiles[i]) {
						currentAffinity -= wrongTilePenalty;
						break;
					}

				}
				
				Construction c=s.constructionForTile(tile);
				for (int i =0 ; i < forbidenAdjacencies.length; i++){
					if(forbidenAdjacencies[i]==c){
						currentAffinity-=tileAdjPenalty;
					}
				}
				
				if(tile.area>maxArea || tile.area<minArea)currentAffinity-=areaPenalty;
				
				for(int i=0;i<forbiddenTypes.length;i++){
					
					if(tile.soilType==forbiddenTypes[i]){
						currentAffinity-=soilPenalty;
						break;
					}
				}
				
				if(currentAffinity<0.0)currentAffinity=0.0;
				
				

				return currentAffinity;
			}
		};

	}

	/**
	 * 
	 * A method that removes all previously used constructions and resets the
	 * chromossome representations
	 * 
	 */
	static public void resetConstructions() {

		constructions.clear();
		indexForNextConstruction = 0;
	}

	public String name() {

		return this.name;
	}

}
