package mainPackage.constructions;

import java.util.HashMap;

import mainPackage.ConstantManager;
import mainPackage.State;
import mainPackage.Tile;
import mainPackage.Tile.SoilType;
import Exceptions.ConstructionException;
import Exceptions.ConstructionException.ConstructionExeptionCode;

/**
 * 
 * A class that represents a construction and all its properties. Intended to be
 * subclassed
 * 
 * */
abstract public class Construction {

	/**
	 * the name of the construction
	 */
	private String name;
	/**
	 * 
	 * A private data member to hold the construction representing an invalid
	 * construction. This property should never be used explicitly as it may be
	 * null. A call to {@link #nullConstruction()} should be used instead where
	 * the lazy instantiation is done.
	 * 
	 */
	private static Construction nullConstruction;

	/**
	 * A method that returns a construction that represents an invalid
	 * construction. The construction returned has a fitness of 0 whatever the
	 * tile it is attached to
	 * 
	 * @return a construction representing an empty construction
	 */
	private static Construction nullConstruction() {

		if (nullConstruction == null) {

			nullConstruction = new Construction("NULL") {

				@Override
				public double affinityToTileInState(Tile tile,State s) {

					return 0;
				}
			};

		}
		return nullConstruction;
	};

	/**
	 * 
	 * A variable to hold the value of the index for the next construction to be created.
	 */
	private static int indexForNextConstruction = 0;

	/**
	 * 
	 * An array of the construction subclasses whose instances should not be near this class
	 * 
	 */
	private String[] forbiddenAdjacentClasses=new String[0];

	/**
	 * 
	 * A variable to hold the minimum required area for this construction.
	 * 
	 */
	private double minArea=0;
	
	/**
	 * 
	 * A variable to hold the maximum area required for this construction.
	 */
	private double maxArea=Integer.MAX_VALUE;
	
	/**
	 * 
	 * The penalty to be given to this constraint
	 * 
	 */
	private double areaPenalty=0.0;
	
	/**
	 * 
	 * The minimum inclination allowed for this construction
	 * 
	 */
	private double minInclination=0.0;
	
	/**
	 * 
	 * The maximum inclination allowed for this construction
	 * 
	 */
	private double maxInclination=1.0;
	
	/**
	 * 
	 * The penalty to be given to a violation of the inclination values
	 * 
	 */
	
	private double inclinationPenalty=0.0;
	
	/**
	 * The tiles where this construction should not be placed
	 * 
	 */
	private Tile[] forbiddenTiles=new Tile[0];
	
	/**
	 * 
	 * The penalty to be given to the to this construction to 
	 * 
	 */
	private double forbiddenTilesPenalty=0.0;
	
	/**
	 * 
	 * The array of the constructions that should not be near this one. Only used in case a custom construction is created
	 * 
	 */
	private Construction[] forbiddenAdjacencies=new Construction[0];

	
	/**
	 * 
	 * The penalty to be given by one of {@link #forbiddenAdjacencies} being adjacent to this construction
	 * 
	 */
	private double forbiddenConstructionPenalty=0.0;
	
	/**
	 * An array with the constructions that should be nearby.
	 * 
	 */
	private Construction[] mustHaveAdjacenciesInstances=new Construction[0];
	

	
	/**
	 * An array with the name of the classes whose instances should be nearby.
	 * 
	 */
	private String[] mustHaveAdjacentClasses=new String[0];
	
	/**
	 * 
	 * The penalty given to the must have adjacencies not being present
	 * 
	 */
	private double missingMustHaveAdjacenciePenalty=0.0;
	
	/**
	 * The penalty to be given to a state being in a tile with a forbidden soil.
	 */
	private double forbiddenSoilPenalty=0.0;
	/**
	 * 
	 * An array with all the soilTypes that are forbidden for this construction.
	 * 
	 */
	private SoilType[] forbiddenSoils=new SoilType[0];
	
	
	/**
	 * 
	 * The maximum price a tile should have to fit this construction
	 * 
	 */
	private double maxPrice=Integer.MAX_VALUE;
	
	/**
	 * 
	 * The minimum price a tile must have in order to fit this construction
	 * 
	 */
	private double minPrice=0.0;
	
	/**
	 * 
	 * The penalty to be given to a construction in a tile that violates the price constraint
	 * 
	 */
	private double pricePenalty=0.0;
	
	
	private static HashMap<Integer, Construction> constructions = new HashMap<Integer, Construction>();



	/**
	 * 
	 * A method that sets the area penalty to be a applied to a given construction
	 * @param min_Area the minimum area allowed for this construction
	 * @param max_Area the maximum area allowed for this construction
	 * @param areaPen the penalty to be given to a tile containing this construction, that has an inadequate area
	 */
	public void setAreaConstraint(double min_Area,double max_Area,double areaPen){
		this.minArea=min_Area;
		this.maxArea=max_Area;
		this.areaPenalty=areaPen;
		
	}
	
	/**
	 * 
	 * 
	 * A method that sets the inclination constraint parameters
	 * @param minIncl the minimum inclination allowed. Should be bigger than 0 and less than 1. Is a percentual value.
	 * @param maxIncl the maximum inclination allowe
	 * @param penalty
	 */
	public void setInclinationConstrain(double minIncl,double maxIncl,double penalty){
		
		this.minInclination=minIncl;
		this.maxInclination=maxIncl;
		this.inclinationPenalty=penalty;
	}
	
	/**
	 * 
	 * A method that sets the bad tile constraint parameters
	 * @param forbiddenTiles the tiles that are forbidden
	 * @param penalty the penalty to be given to this construction by being in a forbidden tile.
	 */
	public void setTilesConstraint(Tile[] forbiddenTiles,double penalty){
		
		this.forbiddenTiles=forbiddenTiles;
		this.forbiddenTilesPenalty=penalty;
		
	}
	
	
	/**
	 * 
	 * A method that sets the soil constraint parameters
	 * @param forbiddenTypes the types of soil that are forbidden for this construction
	 * @param soilPenalty the penalty to be given to a tile having an inadequate soil type
	 */
	public void setSoilConstraint(SoilType[] forbiddenTypes, double soilPenalty) {
		this.forbiddenSoils=forbiddenTypes;
		this.forbiddenSoilPenalty=soilPenalty;
		
	}

	/**
	 * 
	 * A method that sets all constraint related to forbidden adjacencies
	 * @param forbInstances the construction instances that should not be placed in adjacent tiles
	 * @param forbClassesNames the construction subclasses whose instances should not be placed in adjacent tiles
	 * @param penalty the penalty to be attributed
	 */
	public void setForbiddenAdjacenciesConstraint(Construction[] forbInstances, String[] forbClassesNames,double penalty){
		
		this.forbiddenAdjacentClasses=forbClassesNames;
		this.forbiddenAdjacencies=forbInstances;
		this.forbiddenConstructionPenalty=penalty;
		
	}
	
	/**
	 * A method to set the constraint parameters related to the adjacencies that should be present.
	 * @param adjacentClasses a list of the name of the classes that should be present
	 * @param adjacentInstances a list of the instances that should be present
	 * @param penalty the penalty to be given to every class or instance specified that is not present
	 */
	public void setMustHaveAdjacenciesConstraint(String[] adjacentClasses,Construction[] adjacentInstances, double penalty){
		
		this.mustHaveAdjacenciesInstances=adjacentInstances;
		this.mustHaveAdjacentClasses=adjacentClasses;
		
		this.missingMustHaveAdjacenciePenalty=penalty;
	}

	/**
	 * 
	 * A method to set the constraint parameters related to tile pricing
	 * @param min_price the minimum price allowed
	 * @param max_price the maximum price allowed
	 * @param penalty the penalty to be given in case this constraint is violated
	 */
	public void setPriceConstraint(double min_price,double max_price,double penalty){
		
		this.minPrice=min_price;
		this.maxPrice=max_price;
		this.pricePenalty=penalty;
	}
	
	/**
	 * 
	 * A method that returns the largest index a construction has
	 * @return the largest index a construction has
	 */
	public static int latestConstructionIndex(){
		
		return indexForNextConstruction-1;
	}

	/**
	 * 
	 * A property to hold the chromossome representation for the object
	 * 
	 * */
	private int chromoRepresentation;

	/**
	 * 
	 * A method that should calculate how good a tile is for a given type of
	 * construction.
	 * 
	 * @return A float ranging from 0 to 1. A zero represents that the tile
	 *         doesn't suit this construction.
	 * 
	 * */
	abstract public double affinityToTileInState(Tile tile, State state);

	/**
	 * 
	 * A simple constructor. {@link #maxArea} is initialized with {@link Integer#MAX_VALUE} and {@link #minArea} is initialized to zero
	 * 
	 * @param theName
	 *            an alias for the construction. This will be used in the state
	 *            visual representation.
	 */

	public Construction(String theName) {

		this.name = theName;
		chromoRepresentation = (1 << indexForNextConstruction++);
		constructions.put(this.chromoRepresentation, this);
	}
	


	/**
	 * 
	 * A method to pass a Construction object to a Cromossome representation
	 * 
	 * */
	public int toCromossome() {
		return this.chromoRepresentation;
	}

	/**
	 * 
	 * A method to get the construction that has a given chormossome sequence
	 * 
	 * @param chromossome
	 *            the chromossome sequence whose construction is to be retrieved
	 * @return the construction with the passed chromossome
	 */
	static public Construction constructionWithCromossome(int chromossome) {

		Construction c = constructions.get(chromossome);
		if (c == null) {

			c = nullConstruction();
		}
		return c;

	}

	
	/**
	 * 
	 * 
	 * A method that creates an anonimous construction subclass instance with all default parameters for constraints
	 * @param name the name of the construction
	 * @return the construction to be returned
	 */
	static public Construction anonymousConstruction(String name){
		
		return new Construction(name) {

			@Override
			public double affinityToTileInState(Tile tile,State s) {

				double currentAffinity = 1.0;

	
				currentAffinity-=this.defaultBadTilePenalty(tile);
				currentAffinity-=this.defaultAreaPenalty(tile);
				currentAffinity-=this.defaultSoilTilePenalty(tile);
				
				
				currentAffinity-=this.defaultPenaltyForAdjacentConstruction(tile.adjacencies(), s);
				
				currentAffinity-=this.defaultMustHaveAdjacenciesPenalty(tile.adjacencies(), s);
				
				currentAffinity-=this.defaultPricePenalty(tile);
				
				if(currentAffinity<0.0)currentAffinity=0.0;
				
				

				return currentAffinity;
			}
		};
	}
	
	
	
	/**
	 * 
	 * A method that removes all previously used constructions and resets the
	 * chromossome representations
	 * 
	 */
	static public void resetConstructions() {

		constructions.clear();
		indexForNextConstruction = 0;
	}

	/**
	 * 
	 * A method that return the standard penalty to be given to an adjacent construction passed. Either by being of a disallowed class or a disallowed instance.
	 * @param c
	 * @return
	 */
	protected double defaultPenaltyForAdjacentConstruction(Tile[] adjacentTiles, State s){
		
		double penalty=0.0;
		
		for(Tile t:adjacentTiles){
			
			Construction c=s.constructionForTile(t);
			
			Class<?> theClass=c.getClass();
			boolean classIsForbidden=false;
			Class<?> forbiddenClass = null;
			
			for(String className: this.forbiddenAdjacentClasses){
				
				try {forbiddenClass=Class.forName(className);} catch (ClassNotFoundException e) {}
				
				classIsForbidden=forbiddenClass.isAssignableFrom(theClass);
				if(classIsForbidden){
					penalty+=forbiddenConstructionPenalty;
					break;
				}
				
			}
			
			for(Construction forbiddenConstruction: this.forbiddenAdjacencies){
				
				if(c==forbiddenConstruction){
					penalty+=forbiddenConstructionPenalty;
					break;
				}
				
			}
			
		}
		
		
		
		return penalty;
		
		
	}

	
	/**
	 * 
	 * A method that returns the standard penalty to a tile based on his area and the bounds for this Construction
	 * @param t the tile to be evaluated
	 * @return the penalty to be applied to this Tile
	 */
	protected double defaultAreaPenalty(Tile t){
		if(t.getArea()>maxArea || t.getArea()<minArea) return areaPenalty;
		return 0;
	}
	
	/**
	 * 
	 * A method that returns the default inclination penalty to be given to this construction.
	 * @param t the tile whose inclination is to be tested.
	 * @return the inclination to be applied.
	 */
	protected double defaultInclinationPenalty(Tile t){
		
		double tileInclination=t.getMaxInclination();
		
		if(tileInclination>this.maxInclination || tileInclination<this.minInclination)return this.inclinationPenalty;
		return 0;
	}
	
	/**
	 * 
	 * A method that applies the bad tile penalty
	 * @param t the tile to be tested
	 * @return the penalty to be given
	 */
	protected double defaultBadTilePenalty(Tile t){
		for (Tile forbidden : this.forbiddenTiles) {
			if(forbidden==t)return this.forbiddenTilesPenalty;
		}
		return 0;
	}
	
	/**
	 * 
	 * A method that applies the soil penalty
	 * @param t the tile to be tested
	 * @return the penalty to be applied to this construction.
	 */
	protected double defaultSoilTilePenalty(Tile t){
		
		for (SoilType type : this.forbiddenSoils) {
			if(type==t.getSoilType())return this.forbiddenSoilPenalty;
		}
		return 0;
		
	}
	
	
	/**
	 * 
	 * 
	 * A method that applies the constraint related to the requirement of having some constructions in adjacent tiles
	 * @param adjacentTiles a list of the adjacent tiles
	 * @param s the state for the constraint to be evaluated
	 * @return the value of the penalty to be given to this construction
	 */
	protected double defaultMustHaveAdjacenciesPenalty(Tile[] adjacentTiles,State s){
		
		boolean instancesMatches[]=new boolean[this.mustHaveAdjacenciesInstances.length];//initialized to false
		boolean classesMatches[]=new boolean[this.mustHaveAdjacentClasses.length];
		
		double valueToReturn=0.0;
		
		for (Tile tile : adjacentTiles) {
			Construction c=s.constructionForTile(tile);
			for(int i=0;i<mustHaveAdjacenciesInstances.length;i++){
				if(c==mustHaveAdjacenciesInstances[i])instancesMatches[i]=true;
			}
			for(int i=0;i<mustHaveAdjacentClasses.length;i++){
				
				Class<?> mustHaveClass=null;
				
				try {mustHaveClass=Class.forName(mustHaveAdjacentClasses[i]);}
				catch (ClassNotFoundException e) {e.printStackTrace();}
				
				if(mustHaveClass.isAssignableFrom(c.getClass()))classesMatches[i]=true;
			}
			
			
			
		}
		
		for (boolean b : classesMatches) {
			if(!b)valueToReturn+=missingMustHaveAdjacenciePenalty;
		}
		for(boolean b: instancesMatches){
			if(!b)valueToReturn+=missingMustHaveAdjacenciePenalty;
		}
		
		return valueToReturn;
	}
	
	protected double defaultPricePenalty(Tile t){
		
		if(t.getPricePerAreaUnit()>maxPrice|| t.getPricePerAreaUnit()<minPrice)return pricePenalty;
		return 0.0;
		
	}
	
	public String name() {

		return this.name;
	}

}