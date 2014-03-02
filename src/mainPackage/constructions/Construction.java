package mainPackage.constructions;
import java.lang.reflect.Array;

import mainPackage.Tile;
import mainPackage.Coordinate;
/**
 * 
 * A class that represents a construction and all its properties. Intended to be subclassed
 * 
 * */
abstract public class Construction {
	
	/**
	 * 
	 * Class property to hold the representations that have already been used
	 * 
	 * */
	private static char[] usedRepresentations;
	/**
	 * 
	 * A property that holds the value of the prefered location for this particular construction
	 * 
	 * */
	private Coordinate preferedLocation;
	
	/**
	 * 
	 * A property to hold the chromossome representation for the object
	 * 
	 * */
	private char chromoRepresentation;
	/**
	 * 
	 * A method that should calculate how good a tile is for a given type of construction.
	 * @return A float ranging from 0 to 1. A zero represents that the tile doesn't suit this construction.
	 * 
	 * */
	abstract public float affinityToTile(Tile tile);
	
	/**
	 * A method to attribute a chromossome representation to a given construction
	 * 
	 * */
	static private void attributeChromoRepresentation(Construction construction){
		
		if(Construction.usedRepresentations==null){
			
			construction.chromoRepresentation='A';
			Construction.usedRepresentations=new char[1];//create array
			Construction.usedRepresentations[0]='A';
			
		} 
		
		else{
			
			char[] newArray=new char [Construction.usedRepresentations.length+1];//create a bigger array
			for(int i=0;i<newArray.length-1;i++){//copy old one's contents
				newArray[i]=Construction.usedRepresentations[i];
			}
			
			char newChar=(char) (newArray[newArray.length-2]+1);//the new one is the char next to the last one used
			newArray[newArray.length-1]=newChar;
			Construction.usedRepresentations=newArray;
			construction.chromoRepresentation=newChar;
			
		}
		
		
	}
	
	/**
	 * 
	 * Simple constructor for the construction
	 *
	 */
	
	public Construction(float latitude,float longitude){
		
		preferedLocation=new Coordinate(latitude, longitude);
		//TODO continue implementation
		
	}

	/**
	 * 
	 *A method to pass a Construction object to a Cromossome representation
	 * 
	 * */
	public char toCromossome() {
		return this.chromoRepresentation;
	}
}
