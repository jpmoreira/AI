package mainPackage;

import java.util.ArrayList;

import mainPackage.constructions.Construction;

public class Population {

	/**
	 * 
	 * The states belonging to this Population
	 * 
	 */
	public State[] states;
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
	 * @param populationSize the size of the initial population
	 */
	public Population(Tile[] tiles,Construction[] constructions,int populationSize){
		
		states=new State[populationSize];//initialize the initial size as the double of the initial capacity
		this.tiles=tiles;
		this.constructions=constructions;
		for(int i=0;i<populationSize;i++){
			State stateToAdd=new State(this.generateRandomConstructionArray(tiles.length));
			states[i]=stateToAdd;
		
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
	
	
	public float[] fitnessArray(){
		
		float[] fitnesses=new float[states.length];
		for(int i=0;i<states.length;i++){
			fitnesses[i]=states[i].fitnessForTiles(tiles);
		}
		
		return fitnesses;
	}

	//TODO: Make test to test this
	public void pairStatesAtIndexes(int index1,int index2,int[] segmentsOfFirstOne){
		
		State [] newOnes=states[index1].pairWith(states[index2], segmentsOfFirstOne);
		states[index1]=newOnes[0];
		states[index2]=newOnes[1];
	}
}
