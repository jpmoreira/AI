package Exceptions;
/**
 * 
 * 
 * A class to encapsulate all thing related to errors occurred about constructions
 *
 */
public class ConstructionException extends Exception {
	
	/**
	 * 
	 * An enum to be used to increase the readability of code
	 * 
	 *
	 */
	public enum ConstructionExeptionCode{
		MaximumNumOfConstructionsReached_Code
		
	}
	
	
	public int code;
	/**
	 * 
	 * Simple Constructor
	 * @param theCode the code of the error that occured
	 */
	public ConstructionException (int theCode){
		
		this.code=theCode;
	}
	
	/**
	 * 
	 * A method that returns the message corresponding to a given exception
	 * @return the message
	 */
	public String message(){
		
		switch (code) {
		case 0:
			return "Attempt to create a construction but no more constructions can be created please reset constructions to keep adding them";

		default:
			return "Unknown Error";
		}
		
		
	}

}
