package mainPackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import com.sun.tools.javac.code.Attribute.Array;

import junit.extensions.RepeatedTest;
import mainPackage.State;


@SuppressWarnings("serial")
public class GeneticEngine extends AlgorithmEngine implements Serializable {

	/**
	 * the lower bound for all the probability to rank algorithm sub intervals.
	 * Public for testing purposes
	 * 
	 */
	public double[] lowerBound;
	/**
	 * the upper bound for all the probability to rank algorithm sub intervals.
	 * Public for testing purposes.
	 * 
	 */
//	public double[] upperBound;
	

	/**
	 * 
	 * The decreasing factor by which the {@link #mutationProbability} value is
	 * decreased in each iteration. The default value is 1.0 which implies that
	 * {@link #mutationProbability} value doesn't decrease. To set this value
	 * {@link #setVariableMutationProbability(double)} should be used.
	 * 
	 * 
	 */
	private double mutationProbVarFactor;
	
	/**
	 * 
	 * The number of states to pair
	 */
	private int statesToPair;
	
	//FIXME document it
	private int elitistStates=0;

	public int getElitistStates() {
		return elitistStates;
	}

	public void setElitistStates(int elististStates) {
		this.elitistStates = elististStates;
	}



	/**
	 * The current probability of a mutation occurring
	 * 
	 */
	private double mutationProbability;

	/**
	 * 
	 * The factor to be used to calculate the probability of a state being
	 * chosen based on it's ranking. This parameter is 1 minus the parameter
	 * passed in {@link #enableFitnessToRank(double rankProb)}.
	 * 
	 */
	private double probToRankFactor;
	/**
	 * 
	 * A number between 0 and 1 that represents the factor by which the
	 * diversity is to be multiplied to calculate the ultimate value of a state.
	 * A value of 1 means that fitness will be totally discarded and diversity
	 * used instead. A value of 0 means that only fitness will be used
	 * 
	 */
	private double diversityUsageFactor;

	/**
	 * 
	 * A boolean that states whether a direct translation of fitness to
	 * probability is to be used or on the other hand a ranking method. for more
	 * detailed information see documentation of
	 * {@link #enableFitnessToRank(double)} and {@link #enableDirectMethod()}.
	 * 
	 */
	private boolean directFitnessToProbability;


	/**
	 * 
	 * The number of crossover points to be used
	 * 
	 */
	private int crossOverPoints=1;

	/**
	 * 
	 * A method that returns the states to be paired for the next generation. It
	 * selects the method to select the states based on the current setting
	 * 
	 * @param gen
	 *            the random generator to be used. If null is passed the
	 *            {@link #defaultGenerator} will be used.
	 * @return an array withTheStatesForNextGeneration
	 */
	public GeneticState[] selectBestStates(int nrStates,RandomNrGenerator gen) {

		
		GeneticState [] states;
		if (this.directFitnessToProbability)
			states= this
					.statesForReproduction_Direct_Fitness_To_Probability(nrStates,gen);
		else
			states= this.statesForReproduction_Fitness_To_Rank(nrStates,gen);
		
		return states;

	}

	/**
	 * 
	 * A method that returns the states for reproduction using the method
	 * fitness to rank.
	 * 
	 * @param gen
	 *            the random number generator to be used. If null is passed the
	 *            {@link #defaultGenerator} will be used instead.
	 * @return the states to be used in reproduction
	 */
	private GeneticState[] statesForReproduction_Fitness_To_Rank(int nrStates,RandomNrGenerator gen) {

		if (gen == null)
			gen = defaultGenerator;

		GeneticEngine.BubbleSort(population.states(),
				nrStates, this.diversityUsageFactor);// sort
																		// states

		HashSet<GeneticState> statesForReproduction = new HashSet<GeneticState>();

		double randomNumber;
		State[] states = this.population.states();

		while (statesForReproduction.size() < nrStates) {

			randomNumber = gen.nextRandomNr();
			for (int i = 1; i < states.length; i++) {
				if (randomNumber >= this.lowerBound[i-1]
						&& randomNumber < this.lowerBound[i]) {
					statesForReproduction.add((GeneticState)states[i-1]);
					break;
				}
			}
		}

		return statesForReproduction.toArray(new GeneticState[statesForReproduction
				.size()]);

	}

	/**
	 * 
	 * A method that returns the states for reproduction using the method
	 * {@link #directFitnessToProbability}.
	 * 
	 * @param gen
	 *            the random generator to be used. If null is passed the
	 *            {@link #defaultGenerator} will be used.
	 * @return the states to be used in reproduction
	 */
	public GeneticState[] statesForReproduction_Direct_Fitness_To_Probability(int nrStates,
			RandomNrGenerator gen) {

		if (gen == null)
			gen = this.defaultGenerator;
		
		HashSet<State> statesForRep=new HashSet<State>();

		double overallFitness = this.population.overallFitness()*(1-diversityUsageFactor)+this.population.overallDiversity()*diversityUsageFactor;
		double randomNumber;
		double probForReproduction;
		int stateIndex = 0;
		GeneticState currentState;

		while (statesForRep.size() < nrStates) {

			randomNumber = gen.nextRandomNr();
			currentState =(GeneticState) this.population.states()[stateIndex];
			probForReproduction = (((State)currentState).fitness()*(1-diversityUsageFactor)+diversityUsageFactor*((State)currentState).diversity(this.population.states())) / overallFitness;//may not be safe
			if (randomNumber <= probForReproduction)//FIXME here its not working for fitness of the state being 0
				statesForRep.add((State)currentState);

			stateIndex++;
			if (stateIndex == this.population.states().length)
				stateIndex = 0;

		}

		return statesForRep.toArray(new GeneticState[statesForRep
				.size()]);

	}

	public GeneticState[] elitistStatesForNextGen() {

		State[] orderedStates = this.population.states().clone();


		GeneticEngine.BubbleSort(orderedStates,
				elitistStates, diversityUsageFactor);

		GeneticState[] statesForNextGen = new GeneticState[elitistStates];

		for (int i = 0; i < elitistStates; i++) {
			statesForNextGen[i] = (GeneticState)orderedStates[i];
		}

		return statesForNextGen;

	}

	/**
	 * 
	 * A function return a value that is intended to make a state mutate or not.
	 * The best state is never mutated.
	 * 
	 * @param gen
	 *            the random generator to be used. If null is passed the
	 *            {@link #defaultGenerator} will be used.
	 * @return a boolean stating if a state should mutate
	 */
	boolean stateShouldMutate(GeneticState s, RandomNrGenerator gen) {

		if (gen == null)
			gen = this.defaultGenerator;
		if (s == population.mostFitState())
			return false;// never mutates most fit state
		if (gen.nextRandomNr() < mutationProbability)
			return true;

		return false;
	}

	/**
	 * 
	 * A method that indicates a chromosome segment to be mutated for a
	 * particular state
	 * 
	 * @param s
	 *            the state whose chromosome segment is to be chosen
	 * @param gen
	 *            the random generator to be used. If null is passed the
	 *            {@link #defaultGenerator} will be used.
	 * @return the zero based index of the chromossome segment to be mutated
	 */
	int mutatingSegmentForState(GeneticState s, RandomNrGenerator gen) {
		if (gen == null)
			gen = this.defaultGenerator;
		return (int) (gen.nextRandomNr() * s.nrSegments());

	}

	/**
	 * 
	 * A simple constructor.
	 * 
	 * @param pop
	 *            the Population to whom this {@link GeneticEngine} is
	 *            link
	 * @param statesToPair
	 *            the number of states to pair
	 * @param mutationProb
	 *            the initial mutation probability
	 */
	public GeneticEngine(Population pop,
			double mutationProb,int statesToPair) {
		super(pop);

		this.statesToPair=statesToPair;
		this.directFitnessToProbability = true;
		this.diversityUsageFactor = 0.0;
		this.mutationProbVarFactor = 1.0;
		this.population = pop;
		this.mutationProbability = mutationProb;
		
	}
	

	/**
	 * 
	 * 
	 * 
	 * This method is used to put at the head of the array a given number of the
	 * most fit states. Useful since most times not the hole array needs to be
	 * sorted
	 * 
	 * @param states
	 *            all the states
	 * @param nrStatesToOrder
	 *            the number of states to be guaranteed to be at the head of the
	 *            array
	 */
	public static void BubbleSort(State[] states, int nrStatesToOrder,
			double diversityFactor) {
		for (int i = 0; i < states.length && i < nrStatesToOrder; i++) {
			for (int x = states.length - 1; x > i; x--) {

				double valueX = states[x].fitness() * (1 - diversityFactor)
						+ states[x].diversity(states) * diversityFactor;
				double valueBeforeX = states[x - 1].fitness()
						* (1 - diversityFactor)
						+ states[x - 1].diversity(states) * diversityFactor;

				if (valueX > valueBeforeX) {
					State temp = states[x];
					states[x] = states[x - 1];
					states[x - 1] = temp;

				}
			}
		}
	}


	/**
	 * 
	 * A method to be called at each iteration. It updates the parameters
	 * necessary for algorithm to update. This method should be explicitly
	 * called at the end of each iteration
	 * 
	 * 
	 */
	public void updateParameters() {
 
		this.mutationProbability = this.mutationProbability
				* this.mutationProbVarFactor;
		this.diversityUsageFactor *= 0.90;// multiply diversityUsageFactorBy 0.9

	}

	// SETTING FITNESS TO PROB METHOD

	/**
	 * 
	 * A method that defines the way to translate a state's fitness to
	 * probability of being chosen for reproduction and/or selection for the
	 * next generation as a direct method. Meaning the likeliness of a state
	 * being chosen is directly proportional to his fitness. This is the default
	 * behaviour.
	 * 
	 */
	public void enableDirectMethod() {
		this.directFitnessToProbability = true;
	}

	/**
	 * 
	 * A method that defines the way to translate a state's fitness to
	 * probability of being chosen for reproduction and/or selection for the
	 * next generation as a direct method. Meaning the likeliness of a state
	 * being chosen is (1-rankProb)^n for the nth state with the best fitness.
	 * 
	 * @param rankProb
	 *            the parameter to be used for calculating the probability of a
	 *            state being chosen according to the formula presented above.
	 */
	public void enableFitnessToRank(double rankProb) {

		this.directFitnessToProbability = false;
		this.probToRankFactor = rankProb;// directly calculate
		this.lowerBound = new double[this.population.states().length+1];

		this.lowerBound[0] = 0.0;
		for (int i = 1; i < this.lowerBound.length; i++) {

			this.lowerBound[i] = this.lowerBound[i-1] + Math.pow((1-probToRankFactor), i-1)*probToRankFactor;

		}
		this.lowerBound[this.lowerBound.length-1] = 1.0;


	}

	
	// GETTERS AND SETTERS

	/**
	 * 
	 * 
	 * A setter for the mutation.
	 * 
	 * @param mutationProb
	 *            the new value for the mutation probability. This value should
	 *            be between 0 and 1
	 * @param varFactor
	 *            the variation factor for the mutation probability. At each
	 *            iteration the value of the mutation probability is multiplied
	 *            by this value.
	 */
	public void setMutationProbability(double mutationProb, double varFactor) {

		this.mutationProbability = mutationProb;
		this.mutationProbVarFactor = varFactor;
	}

	/**
	 * 
	 * A method that randomly returns a bit to be toggled
	 * 
	 * @param gen
	 *            the random generator to be used. If null is passed the
	 *            {@link #defaultGenerator} will be used.
	 * @return
	 */
	public int bitToToggle(RandomNrGenerator gen) {

		if (gen == null)
			gen = defaultGenerator;

		int nrMaxBit = population.maxBitToToggle();// the
																		// number
																		// of
																		// the
																		// highest
																		// order
																		// bit
																		// used
		return (int) (gen.nextRandomNr() * nrMaxBit+0.5);

	}

	/**
	 * 
	 * 
	 * A method that enables or disables the usage of diversity as a method to
	 * calculate likelihood of a state to pass along to the next generation or
	 * to reproduce itself. By default diversity is not used
	 * 
	 * @param usageFactor a number between 0 and 1 that defines the importance given to diversity when calculating state value.
	 */
	public void setDiversityUsage(double usageFactor) {

		this.diversityUsageFactor = usageFactor;
	}
	
	
	
	public void pair(){
		
		if(crossOverPoints==0)return;//if no crossoverpoints no pairing
		GeneticState[] statesToBePaired=selectBestStates(statesToPair,null);
		GeneticState st1,st2;
		Integer[] segments;
		GeneticState[] childs;
		
		ArrayList<GeneticState> statesAfterPairing=new ArrayList<GeneticState>();
		
		
		for(int i=0;i<statesToBePaired.length-1;i+=2){
			st1=(GeneticState)statesToBePaired[i];
			st2=(GeneticState)statesToBePaired[i+1];
			segments=crossoverPoints((State)st1,null);//may not be a safe cast
			childs=st1.pairWith(st2, segments);
			statesAfterPairing.add(childs[0]);
			statesAfterPairing.add(childs[1]);
			
		}
		
		
		GeneticState[] elitistStates=elitistStatesForNextGen();
		
		GeneticState[] remaining=selectBestStates(this.population.states().length-statesToPair-this.elitistStates, null);
		
		State[] states=population.states();
		
		for(int i=0;i<this.elitistStates;i++){//add states passing intact
			states[i]=(State)elitistStates[i];
		}

		for(int i=this.elitistStates;i<this.elitistStates+statesToPair;i++){//ass states paired
			states[i]=(State)statesAfterPairing.get(i-elitistStates.length);
		}
		
		for(int i=this.elitistStates+statesToPair;i<this.elitistStates+statesToPair+remaining.length;i++){
			
			states[i]=(State)remaining[i-(this.elitistStates+statesToPair)];
		}
		
		
		
	}

	//FIXME test stop conditions met
	public void iterate() {
		
		this.population.updateBestStateEver();
		
		double bestFitnessBefore=population.bestStateEver().fitness();
		this.population.updateIteration();
		
		this.pair();
		this.mutate();
		this.updateParameters();
		this.population.updateBestStateEver();
		
		
		double bestFitnessAfter=population.bestStateEver().fitness();
		
		if(bestFitnessAfter>bestFitnessBefore)consecutiveNonImprovingGenerations=0;
		else consecutiveNonImprovingGenerations++;
		
		currentIteration++;
		
	}

	private void mutate() {
		boolean mutate;
		for(State state: population.states()){
			
			GeneticState s=(GeneticState) state;
			mutate=stateShouldMutate(s,null);
			if(mutate){
				this.population.addMutationThisIteration();
				s.mutate(mutatingSegmentForState(s,null),bitToToggle(null));
			}
			
		}
		
		
	}

	public int statesToPair() {
		return this.statesToPair;
	}

	public double getDiversityUsageFac() {
		return this.diversityUsageFactor;
	}

	public boolean getDirectFitnessToProb() {
		return this.directFitnessToProbability;
	}

	public double getProbToRank() {
		return this.probToRankFactor;
	}

	public double getMutationProb() {
		return this.mutationProbability;
	}

	public double getMutationProbVarFac() {
		return this.mutationProbVarFactor;
	}

	public void setPairingStates(int pairingStates) {
		this.statesToPair = pairingStates;
	}

	
	//FIXME document this
	public Integer[] crossoverPoints(State s, RandomNrGenerator gen){
		
		if(gen==null)gen=defaultGenerator;
		
		HashSet<Integer> pointsToReturn=new HashSet<Integer>();
		int crossOverPoint;
		
		while(pointsToReturn.size()<this.crossOverPoints){
			crossOverPoint=(int)(gen.nextRandomNr()*(s.nrSegments()-2)+1.5);
			pointsToReturn.add(new Integer(crossOverPoint));
		}
		
		Integer[] points=pointsToReturn.toArray(new Integer[pointsToReturn.size()]);
		Arrays.sort(points);
		
		return points;
		
		
	}
	

	
	/**
	 * 
	 * A setter for the number of crossover points
	 * @param crossoverPoints the desired number of crossover points
	 */
	public void setCrossoverPoints(int crossoverPoints){
		
		this.crossOverPoints=crossoverPoints;
		
	}

}
