package mainPackage.constructions;

import java.util.HashMap;

import sun.security.util.PendingException;
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
	 * The id of the construction
	 */
	private int id;
	
	/**
	 * 
	 * The penalty to be given to a construction placed near another construction of a forbidden class
	 * 
	 */
	protected double forbiddenClassAdjacentPenalty=0.0;
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
	public Construction[] forbiddenAdjacencies=new Construction[0];

	
	/**
	 * 
	 * The penalty to be given by one of {@link #forbiddenAdjacencies} being adjacent to this construction
	 * 
	 */
	private double forbiddenInstancePenalty=0.0;
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
	
	private static HashMap<Integer, Construction> constructions = new HashMap<Integer, Construction>();

	/**
	 * 
	 * A method that returns the id of the construction
	 * @return the id of the construction
	 */
	public int getID(){
		
		return id;
	}

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
	 * A method that sets the constraint related to the forbidden construction classes to be adjacent
	 * @param forbiddenAdjacentClasses
	 * @param penalty
	 */
	public void setForbiddenAdjacentClassesConstraint(String[] forbiddenAdjacentClasses,double penalty){
		
		this.forbiddenAdjacentClasses=forbiddenAdjacentClasses;
		this.forbiddenClassAdjacentPenalty=penalty;
	}
	
	/**
	 * 
	 * A method that sets the constraint related to the forbidden construction instances
	 * @param forbConstructions the construction instances that are forbidden to be in the adjacent tiles
	 * @param penalty the penalty to be given in case of a constraint violation
	 */
	public void setForbiddenAdjacentInstancesConstraint(Construction[] forbConstructions,double penalty){
		
		this.forbiddenAdjacencies=forbConstructions;
		this.forbiddenInstancePenalty=penalty;
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
	 * A more complete constructor that initializes the name of the construction as well as both {@link #maxArea} and {@link #minArea}
	 * @param theName the name of the construction
	 * @param max_area the maximum acceptable value for the area for this construction
	 * @param min_area the minimum acceptable value for the area for this construction
	 */
	public Construction(String theName,double max_area, double min_area,double forbClassAdjacentPenalty){
		
		this(theName);
		this.maxArea=max_area;
		this.minArea=min_area;
		this.forbiddenClassAdjacentPenalty=forbClassAdjacentPenalty;
		this.forbiddenAdjacentClasses=new String[0];
		
		
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
	 * A method that returns a custom construction that has the specified constraints
	 * @param name the name of the construction
	 * @param wrongTilePenalty a number between 0 and 1 that defines the penalty to be given to a state having a construction placed in a forbidden tile
	 * @param disallowedTiles an array of the disallowed tiles for this construction
	 * @param areaPenalty a number between 0 and 1 that defines the penalty to be given to a state having a construction in a tile with an incompatible area
	 * @param minArea the minimum allowed area
	 * @param maxArea the maximum allowed area
	 * @param soilPenalty a number between 0 and 1 that defines the penalty to be given to a state having a construction in a tile with a forbidden soil type
	 * @param forbiddenTypes an array of the forbidden soil types
	 * @param forbiddenClassPen the penalty to be given to this construction for sitting near a instance of the forbidden classes
	 * @param forbiddenClasses an array of the classes whose instances should not be in the adjacent tiles
	 * @return a construction that performs according to the specified constraints
	 */
	//TODO maybe more constraints required
	static public Construction constructionWithConstraints(String name,
			final double wrongTilePenalty, final Tile[] disallowedTiles,
			final double areaPenalty, final double minArea, final double maxArea,
			final double soilPenalty, final Tile.SoilType[] forbiddenTypes,
			double forbiddenClassPen,String[] forbiddenClasses,
			double minInclination,double maxInclination, double inclinationPenalty) {

		Construction c=new Construction(name) {

			@Override
			public double affinityToTileInState(Tile tile,State s) {

				double currentAffinity = 1.0;

	
				currentAffinity-=this.defaultBadTilePenalty(tile);
				currentAffinity-=this.defaultAreaPenalty(tile);
				currentAffinity-=this.defaultSoilTilePenalty(tile);
				
				for (Tile adjacentTile : tile.adjacencies()) {
					
					Construction c=s.constructionForTile(adjacentTile);
					currentAffinity-=this.defaultPenaltyForAdjacentConstruction(c);
					
				}
				
				
				
				
				if(currentAffinity<0.0)currentAffinity=0.0;
				
				

				return currentAffinity;
			}
		};

		c.setForbiddenAdjacentClassesConstraint(forbiddenClasses,forbiddenClassPen);
		c.setAreaConstraint(minArea, maxArea, areaPenalty);
		c.setSoilConstraint(forbiddenTypes,soilPenalty);
		c.setInclinationConstrain(minInclination, maxInclination, inclinationPenalty);
		c.setTilesConstraint(disallowedTiles, wrongTilePenalty);
		return c;
	}

	
	//TODO test
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
				
				for (Tile adjacentTile : tile.adjacencies()) {
					
					Construction c=s.constructionForTile(adjacentTile);
					currentAffinity-=this.defaultPenaltyForAdjacentConstruction(c);
					
				}
				
				
				
				
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
	protected double defaultPenaltyForAdjacentConstruction(Construction c){
		
		double penalty=0.0;
		
		Class<?> theClass=c.getClass();
		boolean classIsForbidden=false;
		Class<?> forbiddenClass = null;
		
		for(String className: this.forbiddenAdjacentClasses){
			
			try {forbiddenClass=Class.forName(className);} catch (ClassNotFoundException e) {}
			
			classIsForbidden=forbiddenClass.isAssignableFrom(theClass);
			if(classIsForbidden){
				penalty+=forbiddenClassAdjacentPenalty;
				break;
			}
			
		}
		
		for(Construction forbiddenConstruction: this.forbiddenAdjacencies){
			
			if(c==forbiddenConstruction){
				penalty+=forbiddenInstancePenalty;
				break;
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
	
	protected double defaultSoilTilePenalty(Tile t){
		
		for (SoilType type : this.forbiddenSoils) {
			if(type==t.getSoilType())return this.forbiddenSoilPenalty;
		}
		return 0;
		
	}
	
	public String name() {

		return this.name;
	}

	public double getMinArea(){
		return minArea;
	}
	
	public double getMaxArea(){
		return maxArea;
	}
	
	public double getMinIncl(){
		return minInclination;
	}
	
	public double getMaxIncl(){
		return maxInclination;
	}

	public void setName(String name) {
		this.name = name;
		
	}

	public Double getAreaPenalty() {
		return areaPenalty;
	}

	public Double getInclPenalty() {
		return inclinationPenalty;
	}

	public Double getSoilTypePen() {
		return forbiddenSoilPenalty;
	}
	
}
//TODO use inclination constraint in auto-function