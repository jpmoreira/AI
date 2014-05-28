package mainPackage;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class SimulatedAnnealingEngine extends AlgorithmEngine implements Serializable{
	
	
	public SimulatedAnnealingEngine(Population p) {
		super(p);
		
	}


	double currentTemperature=Float.MAX_VALUE;
	
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
	

	
	/**
	 * 
	 * A method that returns the nextState on the evolution process of a state.
	 * @param s the state to evolve
	 * @param gen the random number generator. Null should be passed if the default randomGenerator is to be used
	 * @return the new state. May be the same state in case no valid evolution could be found.
	 */
	public SimulatedAnnealingState nextState(SimulatedAnnealingState s,RandomNrGenerator gen){
		
		
		if(gen==null)gen=defaultGenerator;
	
		
		int i=0;
		while(i++<s.nrSuccessors()){
			
			SimulatedAnnealingState newState=s.evolve(gen.nextRandomNr());
			double delta_fitness=((State)newState).fitness()-((State)s).fitness();//may not be a safe cast

			if(delta_fitness>=0)return newState;//if it's an improvement then return it
			
			
			double randomNr=gen.nextRandomNr();
			
			double prob=Math.exp(delta_fitness/this.currentTemperature);
			
			if(randomNr<prob)return newState;
			
			
		}
		
		return s;//could not find a fitting successor!

		
		
		
	}
	
	
	public void SimulatedAnnealingEngine(double initialTemperature, double decrementingFactor) {
		currentTemperature=initialTemperature;
		this.decrementingFactor=decrementingFactor;
	}

	
	//FIXME test this
	@Override
	public void iterate() {
		
		State[] newStates=new State[this.population.states().length];
		
		double bestFitnessBefore=this.population.mostFitState().fitness();
		
		for(int i=0;i<this.population.states().length;i++){
			
			SimulatedAnnealingState s=this.nextState((SimulatedAnnealingState) (population.states()[i]), null);
			newStates[i]=(State) s;
			
		}
		this.population.setStates(newStates);
		
		
		double bestFitnessAfter=this.population.mostFitState().fitness();
		
		if(bestFitnessAfter>bestFitnessBefore)consecutiveNonImprovingGenerations=0;
		else consecutiveNonImprovingGenerations=0;
		
		this.currentTemperature*=this.decrementingFactor;//update temperature

		
		currentIteration++;
		
	}

	/**
	 * 
	 * 
	 * A method to set the temperature of the algorithm
	 * @param temperature the desired temperature
	 * @param factor the factor by witch it is decremented
	 */
	public void setTemperature(double temperature, double factor){
		
		this.currentTemperature=temperature;
		this.decrementingFactor=factor;
	}
	
}
