package mainPackage;
import java.util.HashMap;

import mainPackage.InputException.InputExceptionCode;
import mainPackage.constructions.Construction;

/**
 * 
 * Class that solves a given problem
 * 
 * */
public class Solver {
	
	
	HashMap<String, State> tabuMap;
	State [] states;
	Construction [] constructions;
	
	public boolean useTabu;
	public boolean useSimulatedAnnealing;
	public float mutationProbability;
	public int initialNumberOfStates;
	/**
	 * 
	 * Simple Class constructor
	 * 
	 * 
	 * */
	public Solver(int nrTiles,int nrConstructions) throws InputException{
		
		if(nrTiles<1)throw new InputException(InputExceptionCode.InvalidTiles_Code.ordinal());
		else if(nrConstructions<1)throw new InputException(InputExceptionCode.InvalidConstruction_Code.ordinal());
		
		
		states=new State[nrTiles];
		constructions=new Construction[nrConstructions];
		
		
		
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
		
		//TODO implement it
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
	

}
