package mainPackage;


/**
 * 
 * A class to hold information about the location of a point in the globe
 * 
 * */
public class Coordinate {
	
	public float latitude,longitude;
	
	
	/**
	 * 
	 * A method to calculate the distance between two points in the globe
	 * 
	 * */
	public float distanceToPoint(Coordinate otherPoint){
		
		
		//TODO implement it check http://www.movable-type.co.uk/scripts/latlong.html for formula
		
		return 0;
	}
	
	
	/**
	 * 
	 * Simple Constructor
	 * 
	 * 
	 * */
	
	public Coordinate(float lat,float longit){
		this.latitude=lat;
		this.longitude=longit;
	}
	

}
