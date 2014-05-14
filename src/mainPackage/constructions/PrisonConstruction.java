package mainPackage.constructions;

import mainPackage.State;
import mainPackage.Tile;

public class PrisonConstruction extends Construction {

	/**
	 * 
	 * Simple constructor for a prison.
	 * For security reasons prisons should not be placed near {@link AirportConstruction airports}.
	 * Prisons should be placed in low value tiles.
	 * 
	 */
	public PrisonConstruction() {
		super("Prison");
		this.setForbiddenAdjacenciesConstraint(new Construction[0], new String[]{AirportConstruction.class.getCanonicalName()},0.3);
		this.setPriceConstraint(0.0, 5.0, 0.05);
	}



}
