package mainPackage;

public class DefaultRandomGenerator implements RandomNrGenerator{

	@Override
	public double nextRandomNr() {
		return Math.random();
	}
	
	
	

}
