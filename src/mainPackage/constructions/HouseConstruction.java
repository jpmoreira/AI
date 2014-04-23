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
	public HouseConstruction(float latitude, float longitude) throws ConstructionException {
		super(latitude, longitude);
		// TODO implement House Construction stuff
	}

	@Override
	public float affinityToTile(Tile tile) {
		// TODO implement House Construction stuff
		return 0;
	}

}
