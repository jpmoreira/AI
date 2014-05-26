package mainPackage;

public interface SimulatedAnnealingState {
	
	//FIXME test this
	SimulatedAnnealingState evolve(double randomNr);
	
	//FIXME test this
	int nrSuccessors();

}
