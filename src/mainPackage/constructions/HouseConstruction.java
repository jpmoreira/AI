package mainPackage.constructions;

import Exceptions.ConstructionException;
import mainPackage.Tile;
/**
 * 
 * A class to represent a house
 * 
 *
 */
public class HouseConstruction extends Construction {

	/**
	 * 
	 * 
	 * Simple Constructor
	 * @param latitude
	 * @param longitude
	 * @throws ConstructionException
	 */
	public HouseConstruction(double latitude, double longitude) throws ConstructionException {
		super("Moradia");
		// TODO implement House Construction stuff
	}

	@Override
	public double affinityToTile(Tile tile) {
		// TODO implement House Construction stuff
		return 0;
	}

}
