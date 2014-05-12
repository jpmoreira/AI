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
import mainPackage.Tile.SoilType;
import mainPackage.constructions.Construction;

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
	
	public RestrictionsDialog(JFrame frame, boolean modal, String myMessage, Construction[] landuses, int landuseID){

		super(frame,modal);
		this.setLanduses(landuses);
		this.setLanduseID(landuseID);
		numLanduses = landuses.length;
		
		tempLanduse = this.landuses[landuseID];
		
		soilTypes = SoilType.values();
		
		soilCheckboxes = new ArrayList<JCheckBox>();

		getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		this.setTitle("LandUse Settings");

		createWidgets();
		addWidgets(getContentPane());

		pack();
		setLocationRelativeTo(frame);
		setVisible(true);

	}


	private void createWidgets() {
		

		soilTypePanel = new JPanel();
		soilTypePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		soilTypePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		
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
		soilTypePenPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
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
		soilCheckboxesPanel.setLayout(new GridLayout(rows, cols));
		
		for (int i = 0; i < soilTypesSize; i++){
			JCheckBox tempCheckBox = new JCheckBox(soilTypes[i].name());
			if (soilTypeIsForbidden(soilTypes[i])){
				tempCheckBox.setSelected(true);
			}
			soilCheckboxes.add(tempCheckBox);
		}
		
		
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
	
	private void addWidgets(Container contentPane) {
		

		soilTypePanel.add(soilTypeLabel);
		contentPane.add(soilTypePanel);
		
		for (int i =0; i < soilCheckboxes.size(); i++){
			soilCheckboxesPanel.add(soilCheckboxes.get(i));
		}
		contentPane.add(soilCheckboxesPanel);
		
		soilTypePenPanel.add(soilTypePenLabel);
		soilTypePenPanel.add(soilTypePenSlider);
		contentPane.add(soilTypePenPanel);
		
		
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




	
	
}
