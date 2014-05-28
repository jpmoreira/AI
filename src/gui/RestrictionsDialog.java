package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

import Exceptions.ConstructionException;
import mainPackage.Tile;
import mainPackage.Tile.SoilType;
import mainPackage.constructions.AirportConstruction;
import mainPackage.constructions.Construction;
import mainPackage.constructions.FactoryConstruction;
import mainPackage.constructions.HouseConstruction;
import mainPackage.constructions.ParkConstruction;
import mainPackage.constructions.PrisonConstruction;

/**
 * The Class StateDialog.
 */
public class RestrictionsDialog extends JDialog{

	/** The landuse id. */
	private int landuseID;

	/** The landuses. */
	private Construction[] landuses;

	/** The temp landuse. */
	private Construction tempLanduse;

	/** The num landuses. */
	private int numLanduses;
	
	
	/** The finished. */
	private boolean finished = false;

	/** The canceled. */
	private boolean canceled = false;

	
	private JCheckBox forbAirportCheckBox;
	private JCheckBox forbFactoryCheckBox;
	private JCheckBox forbHouseCheckBox;
	private JCheckBox forbParkCheckBox;
	private JCheckBox forbPrisonCheckBox;
	
	private JCheckBox mustHaveAirportCheckBox;
	private JCheckBox mustHaveFactoryCheckBox;
	private JCheckBox mustHaveHouseCheckBox;
	private JCheckBox mustHaveParkCheckBox;
	private JCheckBox mustHavePrisonCheckBox;
	
	
	
	/** The adj checkboxes. */
	private ArrayList<JCheckBox> forbiddenCheckboxes;
	
	private JPanel forbCheckBoxPanel;

	
	/** Forbidden restrictions label. */
	private JLabel forbiddenLabel;
	
	/** Forbidden restrictions panel. */
	private JPanel forbiddenPanel;
	

	private JLabel forbiddenPenLabel;
	
	private JSlider forbiddenPenSlider;
	
	/**  */
	private Construction[] forbInstances;
	
	/**  */
	private String[] forbClassesNames;	
	
	
	
	
	/** Must Have restrictions label. */
	private JPanel mustHavePanel;
	
	/** Must Have restrictions panel. */
	private JLabel mustHaveLabel;
	
	private JPanel mustHaveCheckBoxPanel;
	
	/** The adjacencies. */
	private ArrayList<JCheckBox> mustHaveCheckboxes;
	
	private JLabel mustHavePenLabel;
	
	private JSlider mustHavePenSlider;
	
	/**  */
	private Construction[]  mustHaveInstances;
	
	/**  */
	private String[] mustHaveClassesNames;
	
	
	
	
	private JLabel landuseIDLabel;

	private JPanel landuseIDPanel;
	
	/** SoilTypes restrictions panel. */
	private JPanel soilTypePanel;
	
	/** SoilTypes restrictions panel. */
	private JLabel soilTypeLabel;
	
	/** SoilTypes restrictions panel. */
	private JLabel soilTypePenLabel;
	
	/** SoilType penalty slider. */
	private JSlider soilTypePenSlider;
	
	/** SoilTypes restrictions panel. */
	private ArrayList<JCheckBox> soilCheckboxes;
	
	/** SoilTypes restrictions panel. */
	private JPanel soilCheckboxesPanel;
	
	/** SoilTypes. */
	private SoilType[] soilTypes; 
	
	/** SoilType penalty panel. */
	private JPanel soilTypePenPanel;
	
	/** The buttons panel. */
	private JPanel buttonsPanel;

	/** The save button. */
	private JButton saveButton;

	/** The cancel button. */
	private JButton cancelButton;

	/** The next button. */
	private JButton nextButton;

	/** The previous button. */
	private JButton previousButton;

	private JPanel forbiddenPenPanel;

	private JPanel mustHavePenPanel;

	private double forbiddenPenalty;

	private double mustHavePenalty;




	
	public RestrictionsDialog(JFrame frame, boolean modal, String myMessage, Construction[] landuses, Tile[] tiles, int landuseID){

		super(frame,modal);
		this.setLanduses(landuses);
		this.setLanduseID(landuseID);
		numLanduses = landuses.length;
		
		tempLanduse = this.landuses[landuseID];
		this.forbiddenPenalty = tempLanduse.getForbiddenPenalty();
		this.mustHavePenalty = tempLanduse.getMustHavePenalty();
		
		soilTypes = SoilType.values();
		
		soilCheckboxes = new ArrayList<JCheckBox>();
		forbiddenCheckboxes = new ArrayList<JCheckBox>();
		mustHaveCheckboxes = new ArrayList<JCheckBox>();
		
		this.forbInstances = landuses[landuseID].getForbiddenConstr();
		this.forbClassesNames = landuses[landuseID].getForbiddenClasses();
		this.mustHaveInstances = landuses[landuseID].getMustHaveConstr();
		this.mustHaveClassesNames = landuses[landuseID].getMustHaveClasses();

		getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		this.setTitle("LandUse Settings");

		createWidgets();
		addWidgets(getContentPane());

		pack();
		setLocationRelativeTo(frame);
		setVisible(true);

	}


	private void createWidgets() {
		
		FlowLayout flowLeading = new FlowLayout(FlowLayout.LEADING, 5, 5);
		FlowLayout flowCenter = new FlowLayout(FlowLayout.CENTER, 5, 5);
		
		landuseIDPanel = new JPanel();
		landuseIDPanel.setLayout(flowLeading);
		landuseIDPanel.setBorder(new EmptyBorder(10, 0, 5, 0));
		
		landuseIDLabel = new JLabel("Landuse ID: " + getLanduseID() + "  Chromosome: " + landuses[landuseID].toCromossome() + " - " + landuses[landuseID].name());

		
		soilTypePanel = new JPanel();
		soilTypePanel.setLayout(flowLeading);
		soilTypePanel.setBorder(new EmptyBorder(10, 5, 5, 5));
		
		soilTypeLabel = new JLabel("SoilTypes Restriction");
		soilTypePenLabel = new JLabel("SoilTypes penalty:");	
		
		soilTypePenSlider = new JSlider(0,100,(int)(getLanduses()[getLanduseID()].getSoilTypePen()*100));
		soilTypePenSlider.setSnapToTicks(true);
		soilTypePenSlider.setPaintTicks(true);
		soilTypePenSlider.setPaintLabels(true);
		soilTypePenSlider.setMinorTickSpacing(1);
		soilTypePenSlider.setMajorTickSpacing(10);
		soilTypePenSlider.setPreferredSize(new Dimension(500,40));
		
		soilTypePenPanel = new JPanel();
		soilTypePenPanel.setLayout(flowCenter);
		
		int soilTypesSize = soilTypes.length;
		
		int rows, cols;
		
		if (soilTypesSize%5 > 0){
			rows = soilTypesSize/5 + 1; 
		} else {
			rows = soilTypesSize/5;
		}
		
		if (soilTypesSize > 5) {
			cols = 5;
		} else {
			cols = soilTypesSize;
		}
		
		soilCheckboxesPanel = new JPanel();
		soilCheckboxesPanel.setLayout(new GridLayout(rows, cols,10,5));
		soilCheckboxesPanel.setBorder(new EmptyBorder(10, 5, 5, 5));
		
		for (int i = 0; i < soilTypesSize; i++){
			JCheckBox tempCheckBox = new JCheckBox(soilTypes[i].name());
			if (soilTypeIsForbidden(soilTypes[i])){
				tempCheckBox.setSelected(true);
			}
			soilCheckboxes.add(tempCheckBox);
		}
		
		// TODO adicionar restrições das construções vizinhas
		
		forbiddenPanel = new JPanel();
		forbiddenPanel.setLayout(flowLeading);
		forbiddenLabel = new JLabel("Forbidden adjacencies");
		
		forbiddenPenPanel = new JPanel();
		forbiddenPenPanel.setLayout(flowCenter);
		forbiddenPenLabel = new JLabel("Forbidden penalty:");
		
		forbiddenPenSlider = new JSlider(0,100,(int) (forbiddenPenalty*100));
		forbiddenPenSlider.setSnapToTicks(true);
		forbiddenPenSlider.setPaintTicks(true);
		forbiddenPenSlider.setPaintLabels(true);
		forbiddenPenSlider.setMinorTickSpacing(1);
		forbiddenPenSlider.setMajorTickSpacing(10);
		forbiddenPenSlider.setPreferredSize(new Dimension(500,40));
		
		forbAirportCheckBox = new JCheckBox("Airport");
		forbFactoryCheckBox = new JCheckBox("Factory");
		forbHouseCheckBox = new JCheckBox("House");
		forbParkCheckBox = new JCheckBox("Park");
		forbPrisonCheckBox = new JCheckBox("Prison");
		
		mustHaveAirportCheckBox = new JCheckBox("Airport");
		mustHaveFactoryCheckBox = new JCheckBox("Factory");
		mustHaveHouseCheckBox = new JCheckBox("House");
		mustHaveParkCheckBox = new JCheckBox("Park");
		mustHavePrisonCheckBox = new JCheckBox("Prison");
		
		forbiddenCheckboxes.add(forbAirportCheckBox);
		forbiddenCheckboxes.add(forbFactoryCheckBox);
		forbiddenCheckboxes.add(forbHouseCheckBox);
		forbiddenCheckboxes.add(forbParkCheckBox);
		forbiddenCheckboxes.add(forbPrisonCheckBox);
		
		updateForbClassName();
		
		mustHaveCheckboxes.add(mustHaveAirportCheckBox);
		mustHaveCheckboxes.add(mustHaveFactoryCheckBox);
		mustHaveCheckboxes.add(mustHaveHouseCheckBox);
		mustHaveCheckboxes.add(mustHaveParkCheckBox);
		mustHaveCheckboxes.add(mustHavePrisonCheckBox);
		
		updateMustHaveClassName();
		
		int constructionsSize = landuses.length;
	
		// TODO ArrayList com o nome das classes existentes (não construction) já no checkboxPanel (ver inicialização das construções)
		// e caso exista um obecto do tipo construction adicionar ao arraylist o nome do objecto... 
	
		for (int i = 0; i < constructionsSize; i++){
			
			String tempName = landuses[i].getClass().getCanonicalName();
			
			if (tempName.equals(Construction.class.getCanonicalName())){
				
				JCheckBox tempCheckBox = new JCheckBox(landuses[i].toCromossome() + "- " + landuses[i].name());
				
				if (landuses[i].toCromossome() == tempLanduse.toCromossome()){
					tempCheckBox.setEnabled(false);
				}
					
				if (isForbidden(landuses[i])){
					tempCheckBox.setSelected(true);
				}
				
				forbiddenCheckboxes.add(tempCheckBox);
				
			}
		
		}


		
		mustHavePanel = new JPanel();
		mustHavePanel.setLayout(flowLeading);
		mustHaveLabel = new JLabel("Must have adjacencies");
		
		mustHavePenPanel = new JPanel();
		mustHavePenPanel.setLayout(flowCenter);
		mustHavePenLabel = new JLabel("Missing must have penalty:");
		
		mustHavePenSlider = new JSlider(0,100,(int)(mustHavePenalty*100));
		mustHavePenSlider.setSnapToTicks(true);
		mustHavePenSlider.setPaintTicks(true);
		mustHavePenSlider.setPaintLabels(true);
		mustHavePenSlider.setMinorTickSpacing(1);
		mustHavePenSlider.setMajorTickSpacing(10);
		mustHavePenSlider.setPreferredSize(new Dimension(500,40));
		
		for (int i = 0; i < constructionsSize; i++){
			
			String tempName = landuses[i].getClass().getCanonicalName();
			
			if (tempName.equals(Construction.class.getCanonicalName())){ 
				JCheckBox tempCheckBox = new JCheckBox(landuses[i].toCromossome() + "- " + landuses[i].name());
				
				if (landuses[i].toCromossome() == tempLanduse.toCromossome()){
					tempCheckBox.setEnabled(false);
				}
				
				if (mustHave(landuses[i])){
					tempCheckBox.setSelected(true);
				}
				mustHaveCheckboxes.add(tempCheckBox);
			}
			
		}
		
		int rows2;	
		if (forbiddenCheckboxes.size()%5 > 0){
			rows2 = constructionsSize/5 + 1; 
		} else {
			rows2 = constructionsSize/5;
		}
		
		forbCheckBoxPanel = new JPanel();
		forbCheckBoxPanel.setLayout(new GridLayout(rows2, cols, 10, 5));
		forbCheckBoxPanel.setBorder(new EmptyBorder(10, 5, 5, 5));
		
		int rows3;
		if (mustHaveCheckboxes.size()%5 > 0){
			rows3 = constructionsSize/5 + 1; 
		} else {
			rows3 = constructionsSize/5;
		}
		
		mustHaveCheckBoxPanel = new JPanel();
		mustHaveCheckBoxPanel.setLayout(new GridLayout(rows3, cols, 10, 5));
		mustHaveCheckBoxPanel.setBorder(new EmptyBorder(10, 5, 5, 5));
		
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(new SaveButtonListener());

		if (getLanduseID() != numLanduses-1){
			saveButton.setEnabled(false);
		} else{
			saveButton.setEnabled(true);	
		}

		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING,5,5));

		nextButton = new JButton("Next");
		nextButton.addActionListener(new NextButtonListener());

		if (getLanduseID() == numLanduses-1){
			nextButton.setEnabled(false);
		} else{
			nextButton.setEnabled(true);	
		}

		previousButton = new JButton("Previous");
		previousButton.addActionListener(new PreviousButtonListener());

		if (getLanduseID() == 0){
			previousButton.setEnabled(false);
		} else{
			previousButton.setEnabled(true);	
		}

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new CancelButtonListener());
		
		
	}


	private void updateMustHaveClassName() {
		
		for (String tempName: mustHaveClassesNames ){
			
			if (tempName.equals(AirportConstruction.class.getCanonicalName())){
				mustHaveAirportCheckBox.setSelected(true);
			} else if (tempName.equals(FactoryConstruction.class.getCanonicalName())){
				mustHaveFactoryCheckBox.setSelected(true);
			} else if (tempName.equals(HouseConstruction.class.getCanonicalName())){
				mustHaveHouseCheckBox.setSelected(true);
			} else if (tempName.equals(ParkConstruction.class.getCanonicalName())){
				mustHaveParkCheckBox.setSelected(true);
			} else if (tempName.equals(PrisonConstruction.class.getCanonicalName())){
				mustHavePrisonCheckBox.setSelected(true);
			}	
			
		}
		
	}


	private void updateForbClassName() {
		
		for (String tempName: forbClassesNames ){
			
			if (tempName.equals(AirportConstruction.class.getCanonicalName())){
				forbAirportCheckBox.setSelected(true);
			} else if (tempName.equals(FactoryConstruction.class.getCanonicalName())){
				forbFactoryCheckBox.setSelected(true);
			} else if (tempName.equals(HouseConstruction.class.getCanonicalName())){
				forbHouseCheckBox.setSelected(true);
			} else if (tempName.equals(ParkConstruction.class.getCanonicalName())){
				forbParkCheckBox.setSelected(true);
			} else if (tempName.equals(PrisonConstruction.class.getCanonicalName())){
				forbPrisonCheckBox.setSelected(true);
			}	
			
		}
		
		
	}

	private void addWidgets(Container contentPane) {
		
		
		landuseIDPanel.add(landuseIDLabel);
		contentPane.add(landuseIDPanel);

		soilTypePanel.add(soilTypeLabel);
		contentPane.add(soilTypePanel);
		
		for (int i =0; i < soilCheckboxes.size(); i++){
			soilCheckboxesPanel.add(soilCheckboxes.get(i));
		}
		contentPane.add(soilCheckboxesPanel);
		
		soilTypePenPanel.add(soilTypePenLabel);
		soilTypePenPanel.add(soilTypePenSlider);
		contentPane.add(soilTypePenPanel);
		
		forbiddenPanel.add(forbiddenLabel);
		contentPane.add(forbiddenPanel);
		
		for (int i = 0; i < forbiddenCheckboxes.size() ; i++){
			forbCheckBoxPanel.add(forbiddenCheckboxes.get(i));
		}
		contentPane.add(forbCheckBoxPanel);
		
		forbiddenPenPanel.add(forbiddenPenLabel);
		forbiddenPenPanel.add(forbiddenPenSlider);
		contentPane.add(forbiddenPenPanel);
	
		
		mustHavePanel.add(mustHaveLabel);
		contentPane.add(mustHavePanel);
		
		for (int i = 0; i < mustHaveCheckboxes.size() ; i++){
			mustHaveCheckBoxPanel.add(mustHaveCheckboxes.get(i));
		}
		contentPane.add(mustHaveCheckBoxPanel);
		
		mustHavePenPanel.add(mustHavePenLabel);
		mustHavePenPanel.add(mustHavePenSlider);
		contentPane.add(mustHavePenPanel);
		
		buttonsPanel.add(cancelButton);
		buttonsPanel.add(previousButton);
		buttonsPanel.add(nextButton);
		buttonsPanel.add(saveButton);
		
		contentPane.add(buttonsPanel);
		
		
	}
	
	
	public int getLanduseID() {
		return landuseID;
	}


	public void setLanduseID(int landuseID) {
		this.landuseID = landuseID;
	}


	public Construction[] getLanduses() {
		return landuses;
	}


	public void setLanduses(Construction[] landuses) {
		this.landuses = landuses;
	}


	public Construction getTempLanduse() {
		return tempLanduse;
	}


	public void setTempLanduse(Construction tempLanduse) {
		this.tempLanduse = tempLanduse;
	}

	public boolean isFinished() {
		return finished;
	}


	public boolean isCanceled() {
		return canceled;
	}

	private boolean soilTypeIsForbidden(SoilType soilType) {
		SoilType[] tempSoils = getTempLanduse().getForbiddenSoil();
		for (int i = 0 ; i < tempSoils.length; i++){
			if (tempSoils[i] == soilType) return true;
		}
		return false;
	}
	
	
	
	private boolean isForbidden(Construction construction) {
		
		for (Construction temp: forbInstances){
			if (temp.toCromossome() == construction.toCromossome()) {
				return true;
			}
		}		
		return false;
	}


	private boolean mustHave(Construction construction) {

		for (Construction temp: mustHaveInstances){
			if (temp.toCromossome() == construction.toCromossome()) {
				return true;
			}
		}		
		return false;
	}
	
	public int getChromosome(String name) {
		
		String separator = "- ";
		String[] parts = name.split(separator);
		String chromo = parts[0];
		
		return Integer.parseInt(chromo);
	}
	
	private void editLanduse() throws ConstructionException, NumberFormatException {
		

		
		ArrayList<SoilType> tempForbiddenTypes = new ArrayList<SoilType>();
		
		for (int i = 0; i < soilTypes.length; i++){
			if (soilCheckboxes.get(i).isSelected()){
				tempForbiddenTypes.add(soilTypes[i]);
			}
		}
		
		SoilType[] forbiddenTypes = new SoilType[tempForbiddenTypes.size()];
		
		for (int i = 0; i < forbiddenTypes.length;i++){
			forbiddenTypes[i] = tempForbiddenTypes.get(i);
		}
		
		getTempLanduse().setSoilConstraint(forbiddenTypes, ((double) soilTypePenSlider.getValue())/100);
		
		forbiddenPenalty = ((double) forbiddenPenSlider.getValue())/100;
		mustHavePenalty = ((double) mustHavePenSlider.getValue())/100;
		// TODO
		
		ArrayList<Construction> auxForbConst = new ArrayList<Construction>();
		ArrayList<String>auxForbClassName = new ArrayList<String>();
		
		for (int i = 0; i < forbiddenCheckboxes.size(); i++){
			
			if (forbiddenCheckboxes.get(i).isSelected()){
				
				if  (i==0) {
					auxForbClassName.add(AirportConstruction.class.getCanonicalName());
				} else if  (i==1) {
					auxForbClassName.add(FactoryConstruction.class.getCanonicalName());
				} else if  (i==2) {
					auxForbClassName.add(HouseConstruction.class.getCanonicalName());
				} else if  (i==3) {
					auxForbClassName.add(ParkConstruction.class.getCanonicalName());
				} else if  (i==4) {
					auxForbClassName.add(PrisonConstruction.class.getCanonicalName());
				} else { 
					
					int tempChromo = getChromosome(forbiddenCheckboxes.get(i).getLabel());
					
					for (int j = 0; j < landuses.length; j++){
						if (landuses[j].toCromossome() == tempChromo){
							auxForbConst.add(landuses[j]);
						}
					}
					
				}
				
			}
			
		}
		
		Construction[] tempForConst = new Construction[auxForbConst.size()];
		for (int i =0 ; i < tempForConst.length; i++){
			tempForConst[i] = auxForbConst.get(i);
		}
		
		String[] tempForClass = new String[auxForbClassName.size()];
		for (int i =0 ; i < tempForClass.length; i++){
			tempForClass[i] = auxForbClassName.get(i);
		}
		
		forbInstances = tempForConst;
		forbClassesNames = tempForClass;
		
		
		ArrayList<Construction> auxMustConst = new ArrayList<Construction>();
		ArrayList<String>auxMustClassName = new ArrayList<String>();
		
		for (int i = 0; i < mustHaveCheckboxes.size(); i++){
			
			if (mustHaveCheckboxes.get(i).isSelected()){
				
				if  (i==0) {
					auxMustClassName.add(AirportConstruction.class.getCanonicalName());
				} else if  (i==1) {
					auxMustClassName.add(FactoryConstruction.class.getCanonicalName());
				} else if  (i==2) {
					auxMustClassName.add(HouseConstruction.class.getCanonicalName());
				} else if  (i==3) {
					auxMustClassName.add(ParkConstruction.class.getCanonicalName());
				} else if  (i==4) {
					auxMustClassName.add(PrisonConstruction.class.getCanonicalName());
				} else { 
					
					int tempChromo = getChromosome(mustHaveCheckboxes.get(i).getLabel());
					
					for (int j = 0; j < landuses.length; j++){
						if (landuses[j].toCromossome() == tempChromo){
							auxMustConst.add(landuses[j]);
						}
					}
					
				}
				
			}
			
		}
		
		Construction[] tempMustConst = new Construction[auxMustConst.size()];
		for (int i =0 ; i < tempMustConst.length; i++){
			tempMustConst[i] = auxMustConst.get(i);
		}
		
		String[] tempMustClass = new String[auxMustClassName.size()];
		for (int i =0 ; i < tempMustClass.length; i++){
			tempMustClass[i] = auxMustClassName.get(i);
		}
		
		mustHaveInstances = tempMustConst;
		mustHaveClassesNames = tempMustClass;
		
	}


	public class CancelButtonListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			canceled = true;
			setVisible(false);

		}

	}




	public class PreviousButtonListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				
				editLanduse();
				
				landuseID--;

				setVisible(false);
			}
			catch(ConstructionException c) {
				JOptionPane.showMessageDialog(getParent(), "Introduce the name of landuse");
			}
			catch (Exception e1){
					JOptionPane.showMessageDialog(getParent(), "Unknow error");
			}
			
		}

	}




	public class NextButtonListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				editLanduse();
				
				landuseID++;

				setVisible(false);
			}
			catch(ConstructionException c) {
				JOptionPane.showMessageDialog(getParent(), "Introduce the name of landuse");
			}
			catch (Exception e1){
					JOptionPane.showMessageDialog(getParent(), "Unknow error");
			}

		}



	}



	public class SaveButtonListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {

			try {
				
				editLanduse();
				
				finished = true;

				setVisible(false);
				
			}
			catch(ConstructionException c) {
				JOptionPane.showMessageDialog(getParent(), "Introduce the name of landuse");
			}
			catch (Exception e1){
					JOptionPane.showMessageDialog(getParent(), "Unknow error");
			}

		}


		

	}



	public Construction[] getForbConst() {
		return forbInstances;
	}


	public String[] getForbClassesNames() {
		return forbClassesNames;
	}


	public String[] getMustHaveClassesNames() {
		return mustHaveClassesNames;
	}


	public Construction[] getMustHaveConst() {
		return mustHaveInstances;
	}


	public double getMustHavePenalty() {
		return mustHavePenalty;
	}


	public double getForbPenalty() {
		return forbiddenPenalty;
	}




	
	
}
