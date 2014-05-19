package mainPackage;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SimulatedAnnealingEngine extends AlgorithmEngine implements Serializable{
	
	
	double currentTemperature=0;
	
	double decrementingFactor=0.9;
	
	/**
	 * 
	 * 
	 * A method that updates the algorithm temperature.
	 * 
	 */
	private void updateTemperature(){
		
		 currentTemperature*=decrementingFactor;
	}
	
	public boolean stopConditionMet(){
		
		
	}
	
	public boolean nextState(TileProblemState s,RandomNrGenerator gen){
		
		
		
		//FIXME implement and document
	}
	
	
	public SimulatedAnnealingEngine(double initialTemperature, double decrementingFactor) {
		currentTemperature=initialTemperature;
		this.decrementingFactor=decrementingFactor;
	}

	@Override
	void iterate() {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
