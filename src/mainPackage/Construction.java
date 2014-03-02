package mainPackage;
import mainPackage.Tile;
import mainPackage.Coordinate;
/**
 * 
 * A class that represents a construction and all its properties. Intended to be subclassed
 * 
 * */
abstract public class Construction {
	
	
	/**
	 * 
	 * A property that holds the value of the prefered location for this particular construction
	 * 
	 * */
	private Coordinate preferedLocation;
	
	/**
	 * 
	 * A method that should calculate how good a tile is for a given type of construction.
	 * @return A float ranging from 0 to 1. A zero represents that the tile doesn't suit this construction.
	 * 
	 * */
	abstract public float affinityToTile(Tile tile);

}
