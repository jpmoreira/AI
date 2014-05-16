package mainPackage.constructions;

@SuppressWarnings("serial")
public class FactoryConstruction extends Construction {

	public FactoryConstruction() {
		super("Factory");
		this.setMustHaveAdjacenciesConstraint(new String[]{AirportConstruction.class.getCanonicalName()}, new Construction[0], 0.2);
		this.setInclinationConstrain(0.0, 0.5, 0.1);
	}

	
}
