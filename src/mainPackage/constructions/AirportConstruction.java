package mainPackage.constructions;

@SuppressWarnings("serial")
public class AirportConstruction extends Construction {

	/**
	 * 
	 * Simple construction constructor.
	 * Airport should be placed in a tile with low inclination.
	 * 
	 */
	
	public AirportConstruction() {
		super("Airport");
		this.setInclinationConstrain(0.0, 0.2, 0.4);
	}
}
