package mainPackage.constructions;

import mainPackage.State;
import mainPackage.Tile;

public class PrisonConstruction extends Construction {

	
	public PrisonConstruction(double max_area, double min_area) {
		super("Prison", max_area, min_area, 0.01);
		// TODO Auto-generated constructor stub
	}

	//TODO implement this
	@Override
	public double affinityToTileInState(Tile tile, State state) {
		// TODO Auto-generated method stub
		return 0;
	}

}
