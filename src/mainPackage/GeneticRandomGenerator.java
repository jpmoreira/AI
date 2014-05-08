package mainPackage;
import java.util.ArrayList;
import java.util.Arrays;

import com.sun.tools.javac.code.Attribute.Array;

import mainPackage.State;
import mainPackage.constructions.Construction;


public class GeneticRandomGenerator {


	/**
	 * the lower bound for all the probability to rank algorithm sub intervals. Public for testing purposes
	 * 
	 */
	public double[] lowerBound;
	/**
	 * the upper bound for all the probability to rank algorithm sub intervals. Public for testing purposes.
	 * 
	 */
	public double[] upperBound;
	/**
	 * 
	 * the default random number generator to be used in case no other was specified. Specification of random generator is made on a method basis, mainly to facilitate testing.
	 * 
	 */
	private RandomNrGenerator defaultGenerator;
	
	/**
	 * 
	 * The decreasing factor by which the {@link #mutationProbability} value is decreased in each iteration. The default value is 1.0 which implies that {@link #mutationProbability} value doesn't decrease.
	 * To set this value  {@link #setVariableMutationProbability(double)} should be used.
	 * 
	 * 
	 */
	private double mutationProbVarFactor;
	
	
	/**
	 * The current probability of a mutation occurring
	 * 
	 */
	private double mutationProbability;
	
	
	/**
	 * 
	 * The factor to be used to calculate the probability of a state being chosen based on it's ranking. This parameter is 1 minus the parameter passed in {@link #enableFitnessToRank(double rankProb)}.
	 * 
	 */
	private double probToRankFactor;
	/**
	 * 
	 * A number between 0 and 1 that represents the factor by which the diversity is to be multiplied to calculate the ultimate value of a state. A value of 1 means that fitness will be totally discarded and diversity used instead. A value of 0 means that only fitness will be used
	 * 
	 */
	private double diversityUsageFactor;
	
	
	
	/**
	 * 
	 * A boolean that states whether a direct translation of fitness to probability is to be used or on the other hand a ranking method.
	 * for more detailed information see documentation of {@link #enableFitnessToRank(double)} and {@link #enableDirectMethod()}.
	 * 
	 */
	private boolean directFitnessToProbability;
	/**
	 * 
	 * The number of states to be paired in each iteration
	 * 
	 */
	int statesToPair;
	/**
	 * 
	 * The Population We are dealing with
	 * 
	 */
	Population population;
	
	
	/**
	 * 
	 * A method that returns the states to be paired for the next generation. It selects the method to select the states based on the current setting
	 * @param gen the random generator to be used. If null is passed the {@link #defaultGenerator} will be used.
	 * @return an array withTheStatesForNextGeneration
	 */
	public State [] statesForReproduction(RandomNrGenerator gen){
		
		
		if(this.directFitnessToProbability)return this.statesForReproduction_Direct_Fitness_To_Probability(gen);
		else return this.statesForReproduction_Fitness_To_Rank(gen);
		
		
	}
	
	/**
	 * 
	 * A method that returns the states for reproduction using the method fitness to rank.
	 * @param gen the random number generator to be used. If null is passed the {@link #defaultGenerator} will be used instead.
	 * @return the states to be used in reproduction
	 */
	private State [] statesForReproduction_Fitness_To_Rank(RandomNrGenerator gen){
		
		if(gen==null)gen=defaultGenerator;
		
		GeneticRandomGenerator.BubbleSort(population.states(), population.states().length,this.diversityUsageFactor);//sort states
		
		ArrayList<State> statesForReproduction=new ArrayList<State>();
		
		double randomNumber;
		double lowerBound=0;
		double upperBound=(1.0-this.probToRankFactor);
		State[] states=this.population.states();
		
		while(statesForReproduction.size()<this.statesToPair){
			
			randomNumber=gen.nextRandomNr();
			for(int i=0;i<states.length;i++){
				if(randomNumber>=this.lowerBound[i] && randomNumber<=this.upperBound[i]){
					statesForReproduction.add(states[i]);
					break;
				}
			}
		}
		
		return statesForReproduction.toArray(new State[statesForReproduction.size()]);
		
	}

	/**
	 * 
	 * A method that returns the states for reproduction using the method {@link #directFitnessToProbability}.
	 * @param gen the random generator to be used. If null is passed the {@link #defaultGenerator} will be used.
	 * @return the states to be used in reproduction
	 */
	public State [] statesForReproduction_Direct_Fitness_To_Probability(RandomNrGenerator gen){
		
		if(gen==null)gen=this.defaultGenerator;
		ArrayList<State> statesForReproduction=new ArrayList<State>();
		
		
		double overallFitness=this.population.overallFitness();
		double randomNumber;
		double probForReproduction;
		int stateIndex=0;
		State currentState;
		
		while(statesForReproduction.size()<this.statesToPair){
			
			randomNumber=gen.nextRandomNr();
			currentState=this.population.states()[stateIndex];
			probForReproduction=currentState.fitness()/overallFitness;
			if(randomNumber<=probForReproduction)statesForReproduction.add(currentState);
			
			stateIndex++;
			if(stateIndex==this.population.states().length)stateIndex=0;
			
			
			
		}
		
		
		return statesForReproduction.toArray(new State[statesForReproduction.size()]);
		
		
		
	}
	
	
	/**
	 * 
	 * A method that returns the index of the segments of the choromosome of a given state to be passed along to his first descendant
	 * @param state the state whose chromosome is to be passed to his descendant
	 * @param gen the random generator to be used. If null is passed the {@link #defaultGenerator} will be used.
	 * @return the zero based index of the chromosome segments to be passed along to the passed state's first descendant
	 */
	public Integer[] segmentsOfState(State state,RandomNrGenerator gen){
		
		if(gen==null)gen=this.defaultGenerator;
		
		ArrayList<Integer> segments=new ArrayList<Integer>();
		double randomNr=gen.nextRandomNr();
		
		for(int i=0;i<state.tiles.length;i++){
			
			if(randomNr<0.5)segments.add(i);
			
		}
		
		return segments.toArray(new Integer[segments.size()]);
		
	}
	
	

	public State[] statesForNextGen(){
		
		State[] orderedStates=this.population.states().clone();
		
		int nrOfElitistStatesToSelect=this.population.states().length-statesToPair;
		
		GeneticRandomGenerator.BubbleSort(orderedStates, nrOfElitistStatesToSelect,diversityUsageFactor);
		
		State[] statesForNextGen=new State[nrOfElitistStatesToSelect];
		
		for(int i=0;i<nrOfElitistStatesToSelect;i++){
			statesForNextGen[i]=orderedStates[i];
		}
		
		return statesForNextGen;
		
	}
	
	/**
	 * 
	 * A function return a value that is intended to make a state mutate or not. The best state is never mutated.
	 * @param gen the random generator to be used. If null is passed the {@link #defaultGenerator} will be used.
	 * @return a boolean stating if a state should mutate
	 */
	boolean stateShouldMutate(State s,RandomNrGenerator gen){
		
		if(gen==null)gen=this.defaultGenerator;
		if(s==population.mostFitState())return false;//never mutates most fit state
		if(gen.nextRandomNr()<mutationProbability)return true;
		

		
		return false;
	}
	/**
	 * 
	 * A method that indicates a chromosome segment to be mutated for a particular state
	 * @param s the state whose chromosome segment is to be chosen
	 * @param gen the random generator to be used. If null is passed the {@link #defaultGenerator} will be used.
	 * @return the zero based index of the chromossome segment to be mutated
	 */
	int mutatingSegmentForState(State s,RandomNrGenerator gen){
		if(gen==null)gen=this.defaultGenerator;
		return (int) gen.nextRandomNr()*s.tiles.length;
		
		
	}
	
	/**
	 * 
	 * A simple constructor.
	 * 
	 * @param pop the Population to whom this {@link GeneticRandomGenerator} is link
	 * @param statesToPair the number of states to pair
	 * @param mutationProb the initial mutation probability
	 */
	public GeneticRandomGenerator(Population pop,int statesToPair, double mutationProb) {
		
		this.directFitnessToProbability=true;
		this.diversityUsageFactor=0.0;
		this.mutationProbVarFactor=1.0;
		this.population=pop;
		this.mutationProbability=mutationProb;
		this.statesToPair=statesToPair;
		this.defaultGenerator=new DefaultRandomGenerator();
		
		
	}
	
	
	/**
	 * 
	 * 
	 * 
	 * This method is used to put at the head of the array a given number of the most fit states. Useful since most times not the hole array needs to be sorted
	 * @param states all the states
	 * @param nrStatesToOrder the number of states to be guaranteed to be at the head of the array
	 */
	public static void BubbleSort(State[] states,int nrStatesToOrder,double diversityFactor) {
		 for (int i = 0; i < states.length && i<nrStatesToOrder; i++) {
		    for (int x = states.length-1; x >i; x--) {
		    	
		    	double valueX=states[x].fitness()*(1-diversityFactor)+states[x].diversity(states)*diversityFactor;
		    	double valueBeforeX=states[x-1].fitness()*(1-diversityFactor)+states[x-1].diversity(states)*diversityFactor;
		    	
		        if (valueX > valueBeforeX) {
		            State temp = states[x];
		            states[x] = states[x-1];
		            states[x-1] = temp;

		        }
		    }
		  }
		}
	
	/**
	 * 
	 * 
	 * A function that computes the sum over k from k=first till k=last of r to the kth. 
	 * @param first the start of the sum
	 * @param last the end of the sum
	 * @param r the ratio of the sum
	 * @return the sum
	 */
	public static double GeometricSum(int first,int last,double r){
		
		
		return (Math.pow(r,first)-Math.pow(r,last+1))/(1-r);//geometric progression sum
		
	}
	/**
	 * 
	 * A method to be called at each iteration. It updates the parameters necessary for algorithm to update.
	 * This method should be explicitly called at the end of each iteration
	 * 
	 * 
	 */
	 public void updateParameters(){
		 
		 //FIXME probably more parameter should be updated;
		 this.mutationProbability=this.mutationProbability*this.mutationProbVarFactor;
		 this.diversityUsageFactor*=0.90;//multiply diversityUsageFactorBy 0.9
		 
	 }
	
	//SETTING FITNESS TO PROB METHOD
	
	/**
	 * 
	 * A method that defines the way to translate a state's fitness to probability of being chosen for reproduction and/or selection for the next generation as a direct method. Meaning the likeliness of a state being chosen is directly proportional to his fitness. This is the default behaviour. 
	 * 
	 */
	public void enableDirectMethod(){
		this.directFitnessToProbability=true;
	}
	
	/**
	 * 
	 * A method that defines the way to translate a state's fitness to probability of being chosen for reproduction and/or selection for the next generation as a direct method. Meaning the likeliness of a state being chosen is (1-rankProb)^n for the nth state with the best fitness.
	 * @param rankProb the parameter to be used for calculating the probability of a state being chosen according to the formula presented above.
	 */
	public void enableFitnessToRank(double rankProb){
		
		this.directFitnessToProbability=false;
		this.probToRankFactor=1.0-rankProb;//directly calculate
		this.lowerBound=new double[this.population.states().length];
		this.upperBound=new double[this.population.states().length];

		
		for(int i=0;i<this.lowerBound.length;i++){
			
			this.lowerBound[i]=GeometricSum(1, i, this.probToRankFactor);
			this.upperBound[i]=GeometricSum(1, i+1, this.probToRankFactor);
		}
		this.lowerBound[0]=0.0;
		this.upperBound[upperBound.length-1]=1.0;
	
	}
	
	/**
	 * 
	 * 
	 * A method that enables or disables the usage of diversity as a method to calculate likelihood of a state to pass along to the next generation or to reproduce itself. By default diversity is not used
	 * @param enable a boolean that states whether diversity is to be used or not.
	 */
	public void enableFitnessAndDiversity(boolean enable){
	
			
		if(enable){
			this.diversityUsageFactor=0.5;		
		}
		else{
			this.diversityUsageFactor=0.0;
		}
	}
	
	//SETTING DECREASING MUTATION PROB
	
	/**
	 * A method that sets the increment/decrement factor for {@link #mutationProbability}.
	 * Change in {@link #mutationProbability} as well as other variable parameters will only occur after a call to {@link #updateParameters()}.
	 * 
	 * @param factor the factor by which {@link #mutationProbability} should be multiplied at each cycle. This number shouldn't be negative.A value smaller than 1 means that {@link #mutationProbability} will decrease over time.A value bigger than 1 means that {@link #mutationProbability} will increase over time.
	 */
	public void setVariableMutationProbability(double factor){
		
		this.mutationProbVarFactor=factor;
		
	}


	/**
	 * 
	 * A method that randomly returns a bit to be toggled
	 * @param gen the random generator to be used. If null is passed the {@link #defaultGenerator} will be used.
	 * @return
	 */
	public int bitToToggle(RandomNrGenerator gen){
		
		if(gen==null)gen=defaultGenerator;
		
		int nrMaxBit=Construction.latestConstructionIndex()/2+1;//the number of the highest order bit used
		return (int)(gen.nextRandomNr()*(nrMaxBit));
		
	}

}
