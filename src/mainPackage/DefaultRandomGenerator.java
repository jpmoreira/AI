package mainPackage;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DefaultRandomGenerator implements RandomNrGenerator,Serializable{

	@Override
	public double nextRandomNr() {
		return Math.random();
	}
	
	
	

}
