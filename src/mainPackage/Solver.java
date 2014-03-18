package mainPackage;
import java.util.HashMap;

import Exceptions.InputException;
import Exceptions.InputException.InputExceptionCode;
import mainPackage.constructions.Construction;

/**
 * 
 * Class that solves a given problem
 * 
 * */
public class Solver {
	
	HashMap<String, State> tabuMap;
	Population pop;
	Construction [] constructions;
	
	
	GeneticRandomGenerator coinFlipper;
	
	public boolean useTabu;
	public boolean useSimulatedAnnealing;
	public float mutationProbability;
	public float pairingProbability;
	/**
	 * 
	 * Simple Class constructor
	 * 
	 * 
	 * */
	public Solver(int populationSize,Construction[] constructions,Tile[] tiles) throws InputException{
		
		
		pop=new Population(tiles, constructions, populationSize);
		
		
		
	}
	
	/**
	 * 
	 * A method to enable or disable Tabu search
	 * 
	 * */
	public void useTabu(boolean use){
		
		
		if(use){
			
			useTabu=use;
			tabuMap=new HashMap<String, State>();
			
		}		
	}

	
	/**
	 * 
	 * A method for enabling or disabling simutaled annealing
	 * 
	 * */
	public void useSimulatedAnnealing(boolean use){
		
		this.useSimulatedAnnealing=use;
	}
	
	/**
	 * 
	 * A method for setting a fixed mutation probability
	 * 
	 * */
	public void setMutationProbability(float prob){
		
		if(prob<0)mutationProbability=0;
		else if(prob>1)mutationProbability=1;
		else mutationProbability=prob;
		
	}
	
	public void setPairingProbability(float prob){
		
		if(prob<0)pairingProbability=0;
		else if(prob>1)pairingProbability=1;
		else mutationProbability=prob;
		
	}

	
	/**
	 * 
	 * Method called when 
	 * 
	 */
	public void solve(int nrGenerations){
		
		for(int i=0;i<nrGenerations;i++){
			
			
		}
	}
}
