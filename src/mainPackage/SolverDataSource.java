package mainPackage;

import mainPackage.constructions.Construction;

public interface SolverDataSource {

	int nrOfTilesForSolver(Solver s);
	Tile tileForSolverAtIndex(Solver s,int index);
	Construction constructionForSolverAtIndex(Solver s,int index);
	
	
}
