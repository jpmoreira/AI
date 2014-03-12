package Exceptions;


public class InputException extends Exception {

	
	/**
	 * 
	 * An enum to improve readability of InputException constructors when used
	 * 
	 * */
	public enum InputExceptionCode {
		TooMuchConstructions_Code,
		InvalidTiles_Code,
		InvalidConstruction_Code
	     
	}

	
	public int code;


	public InputException(int theCode){
		this.code=theCode;
	}
	
	public String message(){
		
		
		switch (this.code) {
		case 0:
			return "Not enough tiles for the constructions set";
		case 1:
			return "Invalid number of Tiles";
		case 2:
			return "Invalid number of Constructions";
		default:
			return "Unknown Error";
		}
		
	}
	
}
