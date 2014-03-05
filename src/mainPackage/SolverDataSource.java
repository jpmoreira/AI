package mainPackage;

import mainPackage.constructions.Construction;


/**
 * 
 * 
 * An interface to be implemented by the classes that manage the user interaction.
 * An object of type solver should be created and then the methods in this interface will be called on the UI class object to get the  {@link mainPackage.constructions.Construction Constructions} and {@link mainPackage.Tile Tiles}.
 * This methods will be called after a call to  {@link mainPackage.Solver#initialize initialize} is made on the 
 * 
 * */
public interface SolverDataSource {

	/**
	 * 
	 * A method to return the number of Tiles the problem has
	 * 
	 * */
	int nrOfTilesForSolver(Solver s);
	
	/**
	 * 
	 * A method to return the number of Constructions the problem has
	 * 
	 * */
	int nrOfConstructionsForSolver(Solver s);
	
	
	/**
	 * 
	 * A method to return the nth Tile for a given problem.
	 * 
	 * */
	Tile tileForSolverAtIndex(Solver s,int index);
	
	/**
	 * 
	 * A method to return the nth Construction for a given problem.
	 * 
	 * */
	Construction constructionForSolverAtIndex(Solver s,int index);
	
	
}
