package mainPackage;

public abstract class AlgorithmEngine {

	protected Population population;

	abstract void iterate();

	Population getPopulation() {

		return population;
	}
	
	AlgorithmEngine(Population p){
		
		this.population=p;
	}
}
