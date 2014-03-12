package mainPackage;

import java.util.ArrayList;




/**
 * 
 * A class that represents a given Tile and all its properties
 * 
 * */
public class Tile {
	
	/**
	 * 
	 * An enum to define the types of soil.
	 * 
	 * */
	public enum SoilType {
	    SoilType_Sandy,
	    SoilType_Silty,
	    SoilType_Clay,
	    SoilType_Peaty
	    //TODO don't know if this type of values make sense... review this
	}
	
	/**
	 * The maximum inclination of this tile. This value should represent the percentual inclination in a number >=0 and <= 100.
	 * 
	 */
	public int maxInclination;
	/**
	 * The Area of the tile
	 */
	public float area;
	/**
	 * The price per area unit of this tile
	 * 
	 */
	public float pricePerAreaUnit;
	public SoilType soilType;
	/**
	 * A property intended to keep track of the {@link mainPackage.Tile tiles} adjacent to this tile.
	 * 
	 * */
	private ArrayList<Tile> adjacencies;
	
	/**
	 * 
	 * A method that adds an adjacencie to this tile. If the adjacencie in the opposite direction is not already set, this method sets it too.
	 * 
	 * @param tile the tile to add to the adjacencies array
	 */
	public void addAdjacentTile(Tile tile){
		
		if(this.isAdjacent(tile))return;
		this.adjacencies.add(tile);
		tile.addAdjacentTile(this);//add this tile as adjacent to the other one (adjacency is a bidirectional property)
		
	}
	/**
	 * 
	 * A method that verifies if two tiles are adjacent
	 * 
	 * @param tile the tile whose adjacencie is to be tested against this tile
	 */
	public boolean isAdjacent(Tile tile){
		
		for (Tile adjacentTile : this.adjacencies) {
			
			if(adjacentTile==tile)return true;
			
		}
		return false;
		
		
	}
	

}
