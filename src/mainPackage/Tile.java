package mainPackage;




/**
 * 
 * A class that represents a given Tile and all its properties
 * 
 * */
public class Tile {
	
	/**
	 * 
	 * An Enum to define the types of soil.
	 * 
	 * */
	public enum SoilType {
	    SoilType_Sandy,
	    SoilType_Silty,
	    SoilType_Clay,
	    SoilType_Peaty
	    //TODO don't know if this type of values make sense... review this
	}
	
	
	public int maxInclination;
	public float area;
	public float pricePerAreaUnit;
	public SoilType soilType;
	

}
