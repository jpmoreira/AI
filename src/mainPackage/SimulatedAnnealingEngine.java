package mainPackage;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;

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
	

	
	//FIXME document it and test it
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
		
		//Verify stop conditions
		if(this.stopConditionMet())return;
		
		State[] newStates=new State[this.population.states().length];
		boolean noChange=true;
		
		double bestFitnessBefore=this.population.mostFitState().fitness();
		
		for(int i=0;i<this.population.states().length;i++){
			SimulatedAnnealingState s=(SimulatedAnnealingState) population.states()[i];
			newStates[i]=(State) s.evolve(defaultGenerator.nextRandomNr());
			
			if(newStates[i]!=this.population.states()[i])noChange=false;
			
		}
		this.population.setStates(newStates);
		if(noChange)consecutiveNonEvolvingGenerations++;
		else consecutiveNonEvolvingGenerations=0;
		
		double bestFitnessAfter=this.population.mostFitState().fitness();
		
		if(bestFitnessAfter>bestFitnessBefore)consecutiveNonImprovingGenerations=0;
		else consecutiveNonImprovingGenerations=0;
		
		this.currentTemperature*=this.decrementingFactor;//update temperature

		
	}

	//FIXME document it
	public void setTemperature(double temperature, double factor){
		
		this.currentTemperature=temperature;
		this.decrementingFactor=factor;
	}



	public double getCurrentTemperature() {

		return currentTemperature;
	}



	public double getVariationFactor() {
		// TODO Auto-generated method stub
		return decrementingFactor;
	}
	
}
