package mainPackage;

import java.io.Serializable;

public abstract class AlgorithmEngine implements Serializable{

	protected Population population;
	
	/**
	 * 
	 * the default random number generator to be used in case no other was
	 * specified. Specification of random generator is made on a method basis,
	 * mainly to facilitate testing.
	 * 
	 */
	protected RandomNrGenerator defaultGenerator;
	
	
	protected int consecutiveNonEvolvingGenerations=0;
	
	protected int consecutiveNonImprovingGenerations=0;
	
	protected int maxNrAllowedNonEvolvingGenerations=Integer.MAX_VALUE;
	
	protected int maxNrAllowedNonImprovingGenerations=Integer.MAX_VALUE;
	
	//FIXME document it implement this for Genetic engine
	public void setStopCondition(int maxNonEvolvingIterations, int maxNonImprovingIterations){
		
		this.maxNrAllowedNonEvolvingGenerations=maxNonEvolvingIterations;
		this.maxNrAllowedNonImprovingGenerations=maxNonImprovingIterations;
	}
	
	//FIXME test this and document
	public boolean stopConditionMet(){
		
		if(consecutiveNonEvolvingGenerations>maxNrAllowedNonEvolvingGenerations)return true;
		if(consecutiveNonImprovingGenerations>maxNrAllowedNonImprovingGenerations)return true;
		return false;
		
	}

	public abstract void iterate();

	public Population getPopulation() {

		return population;
	}
	
	AlgorithmEngine(Population p){
		
		this.population=p;
		this.defaultGenerator=new DefaultRandomGenerator();
	}
}
