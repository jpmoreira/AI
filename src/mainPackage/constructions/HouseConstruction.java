package mainPackage.constructions;

import mainPackage.Tile.SoilType;

/**
 * 
 * A class to represent a house
 * 
 * 
 */
@SuppressWarnings("serial")
public class HouseConstruction extends Construction {

	
	/**
	 * 
	 * A simple construction constructor. Houses should not be placed in high inclination tiles.
	 * Houses should not be placed near {@link PrisonConstruction Prisons} or {@link FactoryConstruction Factories}.
	 * Houses should be in the vicinity of a {@link ParkConstruction park}.
	 * 
	 */
	
	public HouseConstruction(){
		
		
		super("House");
		this.setForbiddenAdjacenciesConstraint(new Construction[0], new String[]{PrisonConstruction.class.getCanonicalName(),FactoryConstruction.class.getCanonicalName()}, 0.5);
		this.setInclinationConstrain(0.0, 0.2, 0.05);
		this.setSoilConstraint(new SoilType[]{SoilType.SoilType_Clay}, 0.05);
		this.setMustHaveAdjacenciesConstraint(new String[]{ParkConstruction.class.getCanonicalName()}, new Construction[0], 0.05);
		
		
	}

}
