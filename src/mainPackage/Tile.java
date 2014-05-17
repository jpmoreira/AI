package mainPackage;

import java.io.Serializable;
import java.util.ArrayList;




/**
 * 
 * A class that represents a given Tile and all its properties
 * 
 * */
@SuppressWarnings("serial")
public class Tile implements Serializable{

	/**
	 * 
	 * An enum to define the types of soil.
	 * 
	 * */
	public enum SoilType {
		SoilType_Sandy,
		SoilType_Silty,
		SoilType_Clay,
		SoilType_Peaty,
		SoilType_Rocky
		
	}

	/**
	 * The maximum inclination of this tile. This value should represent the percentual inclination in a number >=0 and <= 1.
	 * 
	 */
	private double maxInclination;
	/**
	 * The Area of the tile
	 */
	private double area;
	/**
	 * The price per area unit of this tile
	 * 
	 */
	private float pricePerAreaUnit;
	private SoilType soilType;


	private static int idForNextTile = 0;

	private int id;

	public Tile(){
		this.setId(idForNextTile);
		idForNextTile++;
	};
	
	private int[] adjIDs;


	public Tile(SoilType soil, double d, double incl, float price){
		this.soilType = soil;
		this.area = d;
		this.maxInclination = incl;
		this.pricePerAreaUnit = price;
		this.setId(idForNextTile);
		idForNextTile++;
	}

	/**
	 * A property intended to keep track of the {@link mainPackage.Tile tiles} adjacent to this tile.
	 * 
	 * */
	private ArrayList<Tile> adjacencies=new ArrayList<Tile>();

	/**
	 * 
	 * A method that adds an adjacencie to this tile. If the adjacencie in the opposite direction is not already set, this method sets it too.
	 * 
	 * @param tile the tile to add to the adjacencies array
	 */
	public void addAdjacentTile(Tile tile){
		if(this==tile)return;
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

	public double getMaxInclination() {
		return maxInclination;
	}
	public void setMaxInclination(double maxInclination) {
		this.maxInclination = maxInclination;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public float getPricePerAreaUnit() {
		return pricePerAreaUnit;
	}
	public void setPricePerAreaUnit(float pricePerAreaUnit) {
		this.pricePerAreaUnit = pricePerAreaUnit;
	}
	public SoilType getSoilType() {
		return soilType;
	}
	public void setSoilType(SoilType soilType) {
		this.soilType = soilType;
	}



	/**
	 * 
	 * A method that returns the adjacencies of a given Tile
	 * @return
	 */
	public Tile[] adjacencies(){


		return adjacencies.toArray(new Tile[adjacencies.size()]);
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

}
