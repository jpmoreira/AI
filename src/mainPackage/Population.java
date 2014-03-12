package mainPackage;

import java.util.ArrayList;

import mainPackage.constructions.Construction;

public class Population {

	/**
	 * 
	 * The states belonging to this Population
	 * 
	 */
	public ArrayList<State> states;
	/**
	 * 
	 * The Constructions in this problem
	 * 
	 */
	public Construction[] constructions;
	/**
	 * 
	 * The Tiles in this problem
	 * 
	 */
	public Tile[] tiles;
	
	
	GeneticRandomGenerator coinFlipper;
	
	
	
	
	
	
	/**
	 * 
	 * A method to add a state to the population
	 * @param stateToAdd
	 */
	void addState(State stateToAdd){
		
		
	}
	
	/**
	 * 
	 * A method that removes a state from the state list.
	 * This may happen for example because a state may "die"
	 * 
	 * @param stateToRemove the state to be removed from the population
	 */
	void removeState(State stateToRemove){
		
	}
	
	
	/**
	 * 
	 * 
	 * @param tiles the tiles that exist in the problem
	 * @param constructions the constructions that exist in the problem
	 * @param initialPopulation the size of the initial population
	 */
	public Population(Tile[] tiles,Construction[] constructions,int initialPopulation){
		
		states=new ArrayList<State>(2*initialPopulation);//initialize the initial size as the double of the initial capacity
		this.tiles=tiles;
		this.constructions=constructions;
		for(int i=0;i<initialPopulation;i++){
			State stateToAdd=new State(this.generateRandomConstructionArray(tiles.length));
			states.add(stateToAdd);
		}
		
		
		
		
	}
	
	/**
	 * 
	 * 
	 * A method that returns a given number of construction by a random order
	 * @param size
	 * @return
	 */
	Construction[] generateRandomConstructionArray(int size){
		
		Construction[] constructionsToReturn=new Construction[size];
		for(int i=0;i<size;i++){
			int constructionNr= (int)(Math.random()*(constructions.length));
			constructionsToReturn[i]=constructions[constructionNr];
		}
		return constructionsToReturn;
		
	}
	
	/**
	 * 
	 * A method that returns the sum of the fitnesses of all states
	 */
	public float overallFitness(){
		
		float overallFitness=0;
		for (State state : states) {
			overallFitness+=state.fitnessForTiles(tiles);
		}
		
		return overallFitness;
		
		
	}
	
	
}
