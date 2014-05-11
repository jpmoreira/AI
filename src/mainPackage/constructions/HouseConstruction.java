package mainPackage.constructions;

import Exceptions.ConstructionException;
import mainPackage.State;
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
	 * A simple constructor.
	 * 
	 * @param minArea
	 *            the minimum area a tile must have to include this construction
	 * @param maxArea
	 *            the maximum area a tile must have to include this construction
	 */
	public HouseConstruction(double minArea, double maxArea) {

		super("House",minArea,maxArea,0.2);
		this.setForbiddenAdjacentClassesConstraint(new String[]{PrisonConstruction.class.getCanonicalName()}, 0.15);

	}
	
	//TODO test this and prision

	@Override
	public double affinityToTileInState(Tile tile, State state) {
		double currentAffinity=1.0;
		for (Tile adjTile : tile.adjacencies()) {
			Construction c=state.constructionForTile(adjTile);
			currentAffinity-=this.defaultPenaltyForAdjacentConstruction(c);
		}
		
		currentAffinity-=defaultAreaPenalty(tile);
		
		if(currentAffinity<0)currentAffinity=0;
		
		return currentAffinity;

	}

}
