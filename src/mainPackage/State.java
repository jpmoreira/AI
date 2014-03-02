package mainPackage;
import mainPackage.Tile;
import mainPackage.constructions.Construction;
/**
 * 
 * A class that represents a given State in the resolution process
 * 
 * 
 * */
public class State {
	
	/**
	 * 
	 * An array representing the tiles. Order matters
	 * 
	 * */
	public Tile[] tiles;
	
	/**
	 * 
	 * An array representing the constructions assigned to the tiles. Order matters as construction at position i is assigned to tile at position i
	 * 
	 * */
	public Construction[] constructions;
	
	
	/**
	 * 
	 * A method for getting the chromossome representation of the state
	 * 
	 * */
	public String chromossome(){
		
		
		//TODO implement it
		return null;
		
	}
	
	/**
	 * 
	 * A method to pair two states
	 * 
	 * */
	public State pairWith(State otherState){
		
		
		//TODO implement it
		return null;
	}

	/**
	 * 
	 * A method used to mutate this object
	 * 
	 * */
	public void mutate(){
		
		
	}

	

}
