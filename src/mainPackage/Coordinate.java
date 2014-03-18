package mainPackage;

/**
 * 
 * A class to hold information about the location of a point in the globe
 * 
 * */
public class Coordinate {

	public double latitude, longitude;

	/**
	 * 
	 * A method to calculate the distance between two points in the globe
	 * 
	 * */
	public double distanceToPoint(Coordinate otherPoint) {

		double R = 6371; // Radius of the earth in km
		double dLat = deg2rad(otherPoint.latitude - latitude); // deg2rad below
		double dLon = deg2rad(otherPoint.longitude - longitude);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(deg2rad(latitude))
				* Math.cos(deg2rad(otherPoint.latitude)) * Math.sin(dLon / 2)
				* Math.sin(dLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = R * c; // Distance in km
		return d;
	}

	/**
	 * 
	 * Simple Constructor
	 * 
	 * 
	 * */

	public Coordinate(double d, double e) {
		this.latitude = d;
		this.longitude = e;
	}

	private double distance(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
				* Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;
		return (dist);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts decimal degrees to radians : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts radians to decimal degrees : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

}
