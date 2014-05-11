package mainPackage.constructions;

import mainPackage.State;
import mainPackage.Tile;

public class PrisonConstruction extends Construction {

	
	public PrisonConstruction(double max_area, double min_area) {
		super("Prison");
		this.setAreaConstraint(min_area, max_area, 0.2);
		//TODO implement
	}

	//TODO implement this
	@Override
	public double affinityToTileInState(Tile tile, State state) {
		return 0;
	}

}
