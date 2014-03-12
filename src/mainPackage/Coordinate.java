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
	public double distanceToPoint(Coordinate otherPoint){
		
		
		 double R = 6371; // Radius of the earth in km
		 double dLat = deg2rad(otherPoint.latitude-latitude);  // deg2rad below
		 double dLon = deg2rad(otherPoint.longitude-longitude); 
		 double a = 
		    Math.sin(dLat/2) * Math.sin(dLat/2) +
		    Math.cos(deg2rad(latitude)) * Math.cos(deg2rad(otherPoint.latitude)) * 
		    Math.sin(dLon/2) * Math.sin(dLon/2)
		    ; 
		  double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		  double d = R * c; // Distance in km
		  return d;
	}


	private double deg2rad(double deg) {
		  return deg * (Math.PI/180);
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
