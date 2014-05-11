package mainPackage.constructions;

import Exceptions.ConstructionException;
import mainPackage.State;
import mainPackage.Tile;
import mainPackage.Tile.SoilType;

/**
 * 
 * A class to represent a house
 * 
 * 
 */
public class HouseConstruction extends Construction {

	
	
	/**
	 * 
	 * A simple constructor.
	 * 
	 * @param minArea
	 *            the minimum area a tile must have to include this construction
	 * @param maxArea
	 *            the maximum area a tile must have to include this construction
	 */
	public HouseConstruction(double minArea, double maxArea) {

		super("House");
		this.setAreaConstraint(minArea, maxArea, 0.2);
		this.setForbiddenAdjacenciesConstraint(new Construction[0], new String[]{PrisonConstruction.class.getCanonicalName(),FactoryConstruction.class.getCanonicalName()}, 0.5);
		this.setInclinationConstrain(0.0, 0.2, 0.05);
		this.setSoilConstraint(new SoilType[]{SoilType.SoilType_Clay}, 0.05);
		this.setMustHaveAdjacenciesConstraint(new String[]{ParkConstruction.class.getCanonicalName()}, new Construction[0], 0.05);
	}
	
	//TODO test this and prision

	@Override
	public double affinityToTileInState(Tile tile, State state) {
		double currentAffinity=1.0;
		
		currentAffinity-=this.defaultPenaltyForAdjacentConstruction(tile.adjacencies(), state);
		
		currentAffinity-=defaultAreaPenalty(tile);
		currentAffinity-=defaultSoilTilePenalty(tile);
		currentAffinity-=defaultInclinationPenalty(tile);
		currentAffinity-=defaultMustHaveAdjacenciesPenalty(tile.adjacencies(), state);
		
		if(currentAffinity<0)currentAffinity=0;
		
		return currentAffinity;

	}

}
