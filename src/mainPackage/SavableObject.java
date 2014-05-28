package mainPackage;

import java.io.Serializable;
import java.util.HashMap;

import mainPackage.constructions.Construction;

@SuppressWarnings("serial")
public class SavableObject implements Serializable{
	
	private GeneticEngine genetic_engine;
	
	private SimulatedAnnealingEngine sim_engine;
	
	private HashMap<Integer, Construction> map;
	
	
	public SavableObject(GeneticEngine eng,SimulatedAnnealingEngine sim_eng,HashMap<Integer, Construction> constructionsMap) {
		setGenetic_engine(eng);
		setSim_engine(sim_eng);
		setMap(constructionsMap);
		
	}


	public GeneticEngine getGenetic_engine() {
		return genetic_engine;
	}


	public void setGenetic_engine(GeneticEngine genetic_engine) {
		this.genetic_engine = genetic_engine;
	}


	public SimulatedAnnealingEngine getSim_engine() {
		return sim_engine;
	}


	public void setSim_engine(SimulatedAnnealingEngine sim_engine) {
		this.sim_engine = sim_engine;
	}


	public HashMap<Integer, Construction> getMap() {
		return map;
	}


	public void setMap(HashMap<Integer, Construction> map) {
		this.map = map;
	}
	
	

}
