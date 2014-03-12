package mainPackage.constructions;

import Exceptions.ConstructionException;
import mainPackage.Tile;

public class HouseConstruction extends Construction {

	//TODO document this class
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
