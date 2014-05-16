package mainPackage.constructions;

import mainPackage.Tile;

@SuppressWarnings("serial")
public class ParkConstruction extends Construction {


	
	/**
	 * 
	 * A simple constructor for a Park.
	 * Parks should not be placed near {@link FactoryConstruction factories}.
	 * Parks should not be placed near {@link Airports airports}.
	 * Parks should not be placed near high inclination {@link Tile tiles}.
	 * 
	 */
	public ParkConstruction() {
		super("Park");
		this.setForbiddenAdjacenciesConstraint(new Construction[0], new String[] {FactoryConstruction.class.getCanonicalName(),AirportConstruction.class.getCanonicalName()}, 0.5);
		this.setInclinationConstrain(0.0, 0.3, 0.1);
		
	}


}
