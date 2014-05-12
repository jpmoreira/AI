package gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Exceptions.ConstructionException;
import mainPackage.Tile.SoilType;
import mainPackage.constructions.Construction;

// TODO: Auto-generated Javadoc
/**
 * The Class LanduseDialog.
 */
public class LanduseDialog extends JDialog{
	
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

	
	/** The landuse id panel. */
	private JPanel landuseIDPanel;

	/** The landuse id label. */
	private JLabel landuseIDLabel;

	/** The center panel. */
	private JPanel centerPanel;

	/** The landuse panel. */
	private JPanel landusePanel;

	/** The landuse label. */
	private JLabel landuseLabel;

	/** The landuse field. */
	private JTextField landuseField;
	
	
	/** The area panel. */
	private JPanel areaPanel;
	
	/** The min area label. */
	private JLabel minAreaLabel;
	
	/** The min area field. */
	private JTextField minAreaField;
	
	/** The max area label. */
	private JLabel maxAreaLabel;
	
	/** The max area field. */
	private JTextField maxAreaField;
	
	/** Area penalty label. */
	private JLabel areaPenLabel;
	
	/** Area penalty panel */
	private JPanel areaPenPanel;
	
	/** Area penalty text slider. */
	private JSlider areaPenSlider;	

	/** The incl min panel. */
	private JPanel inclMinPanel;
	
	/** The incl max panel. */
	private JPanel inclMaxPanel;
	
	/** The min incl label. */
	private JLabel minInclLabel;
	
	/** The min incl field. */
	private JSlider minInclSlider;
	
	/** The max incl label. */
	private JLabel maxInclLabel;
	
	/** The max incl field. */
	private JSlider maxInclSlider;
	
	/** Inclination penalty label. */
	private JLabel inclPenLabel;
	
	/** Inclination penalty slider. */
	private JSlider inclPenSlider;
	
	/** Inclination penalty panel */
	private JPanel inclPenPanel;
	
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

		
	
	/**
	 * Instantiates a new landuse dialog.
	 *
	 * @param frame the frame
	 * @param modal the modal
	 * @param myMessage the my message
	 * @param landuses the landuses
	 * @param landuseID the landuse id
	 */
	public LanduseDialog(JFrame frame, boolean modal, String myMessage, Construction[] landuses, int landuseID){

		super(frame,modal);
		this.landuses = landuses;
		this.setLanduseID(landuseID);
		numLanduses = landuses.length;
		this.tempLanduse = landuses[landuseID];
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

	/**
	 * Adds the widgets.
	 *
	 * @param contentPane the content pane
	 */
	private void addWidgets(Container contentPane) {

		landuseIDPanel.add(landuseIDLabel);
		contentPane.add(landuseIDPanel);

		landusePanel.add(landuseLabel);
		landusePanel.add(landuseField);
		contentPane.add(landusePanel);
//		centerPanel.add(landusePanel);
//
//		contentPane.add(centerPanel);

		areaPanel.add(minAreaLabel);
		areaPanel.add(minAreaField);
		areaPanel.add(maxAreaLabel);
		areaPanel.add(maxAreaField);
		contentPane.add(areaPanel);
		
		areaPenPanel.add(areaPenLabel);
		areaPenPanel.add(areaPenSlider);
		contentPane.add(areaPenPanel);
		
		inclMinPanel.add(minInclLabel);
		inclMinPanel.add(minInclSlider);
		contentPane.add(inclMinPanel);
		
		inclMaxPanel.add(maxInclLabel);
		inclMaxPanel.add(maxInclSlider);
		contentPane.add(inclMaxPanel);
		
		inclPenPanel.add(inclPenLabel);
		inclPenPanel.add(inclPenSlider);
		contentPane.add(inclPenPanel);
		
		
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

	/**
	 * Creates the widgets.
	 */
	private void createWidgets() {
		
		landuseIDPanel = new JPanel();
		landuseIDPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		landuseIDPanel.setBorder(new EmptyBorder(10, 0, 5, 0));
		
		landuseIDLabel = new JLabel("Landuse ID: " + getLanduseID() + "  Chromosome: " + landuses[landuseID].toCromossome());
		
		landusePanel = new JPanel();
		landusePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		landuseLabel = new JLabel("Name:");
		landuseField = new JTextField(landuses[landuseID].name(),20);
		
		areaPanel = new JPanel();
		areaPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		areaPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		
		areaPenPanel = new JPanel();
		areaPenPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		minAreaLabel = new JLabel("Min area:");
		maxAreaLabel = new JLabel("Max area:");
		areaPenLabel = new JLabel("Area penalty:");
		
		minAreaField = new JTextField(((Double) landuses[landuseID].getMinArea()).toString(),6);
		maxAreaField = new JTextField(((Double) landuses[landuseID].getMaxArea()).toString(),12);
		
		areaPenSlider = new JSlider(0,100,(int)(landuses[landuseID].getAreaPenalty()*100));
		areaPenSlider.setSnapToTicks(true);
		areaPenSlider.setPaintTicks(true);
		areaPenSlider.setPaintLabels(true);
		areaPenSlider.setMinorTickSpacing(1);
		areaPenSlider.setMajorTickSpacing(10);
		areaPenSlider.setPreferredSize(new Dimension(500,40));
		
		
		//TODO add price restrictions
			
		inclMinPanel = new JPanel();
		inclMinPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		inclMinPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		
		inclMaxPanel = new JPanel();
		inclMaxPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		inclPenPanel = new JPanel();
		inclPenPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		minInclLabel = new JLabel("Min inclination:");
		maxInclLabel = new JLabel("Max inclination:");
		inclPenLabel = new JLabel("Inclination penalty:");
		
		minInclSlider = new JSlider(0,100,(int)(landuses[landuseID].getMinIncl()*100));
		minInclSlider.setSnapToTicks(true);
		minInclSlider.setPaintTicks(true);
		minInclSlider.setPaintLabels(true);
		minInclSlider.setMinorTickSpacing(1);
		minInclSlider.setMajorTickSpacing(10);
		minInclSlider.setPreferredSize(new Dimension(500,40));
		
		maxInclSlider = new JSlider(0,100,(int)(landuses[landuseID].getMaxIncl()*100));
		maxInclSlider.setSnapToTicks(true);
		maxInclSlider.setPaintTicks(true);
		maxInclSlider.setPaintLabels(true);
		maxInclSlider.setMinorTickSpacing(1);
		maxInclSlider.setMajorTickSpacing(10);
		maxInclSlider.setPreferredSize(new Dimension(500,40));
		
		inclPenSlider = new JSlider(0,100,(int)(landuses[landuseID].getInclPenalty()*100));
		inclPenSlider.setSnapToTicks(true);
		inclPenSlider.setPaintTicks(true);
		inclPenSlider.setPaintLabels(true);
		inclPenSlider.setMinorTickSpacing(1);
		inclPenSlider.setMajorTickSpacing(10);
		inclPenSlider.setPreferredSize(new Dimension(500,40));
		

		soilTypePanel = new JPanel();
		soilTypePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		soilTypePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		
		soilTypeLabel = new JLabel("SoilTypes Restriction");
		soilTypePenLabel = new JLabel("SoilTypes penalty:");	
		
		soilTypePenSlider = new JSlider(0,100,(int)(landuses[landuseID].getSoilTypePen()*100));
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
	

	private boolean soilTypeIsForbidden(SoilType soilType) {
		SoilType[] tempSoils = tempLanduse.getForbiddenSoil();
		for (int i = 0 ; i < tempSoils.length; i++){
			if (tempSoils[i] == soilType) return true;
		}
		return false;
	}

	/**
	 * Builds the landuse.
	 *
	 * @throws ConstructionException the construction exception
	 */
	private void editLanduse() throws ConstructionException, NumberFormatException {
		
		double tempMinArea;
		double tempMaxArea;
		
		tempMinArea = Double.parseDouble(minAreaField.getText());
		tempMaxArea = Double.parseDouble(maxAreaField.getText());
		
		tempLanduse.setName(landuseField.getText());
		tempLanduse.setAreaConstraint(tempMinArea, tempMaxArea, ((double) areaPenSlider.getValue())/100);
		tempLanduse.setInclinationConstrain(((double) minInclSlider.getValue())/100, ((double) maxInclSlider.getValue())/100, ((double) inclPenSlider.getValue())/100);
		
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
		
		tempLanduse.setSoilConstraint(forbiddenTypes, ((double) soilTypePenSlider.getValue())/100);
		
	}
	
	
	/**
	 * The listener interface for receiving cancelButton events.
	 * The class that is interested in processing a cancelButton
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addCancelButtonListener<code> method. When
	 * the cancelButton event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see CancelButtonEvent
	 */
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



	/**
	 * The listener interface for receiving previousButton events.
	 * The class that is interested in processing a previousButton
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addPreviousButtonListener<code> method. When
	 * the previousButton event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see PreviousButtonEvent
	 */
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



	/**
	 * The listener interface for receiving nextButton events.
	 * The class that is interested in processing a nextButton
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addNextButtonListener<code> method. When
	 * the nextButton event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see NextButtonEvent
	 */
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



	/**
	 * The listener interface for receiving saveButton events.
	 * The class that is interested in processing a saveButton
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addSaveButtonListener<code> method. When
	 * the saveButton event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see SaveButtonEvent
	 */
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



	/**
	 * Gets the temp landuse.
	 *
	 * @return the temp landuse
	 */
	public Construction getTempLanduse() {
		return tempLanduse;
	}

	/**
	 * Gets the landuse id.
	 *
	 * @return the landuse id
	 */
	public int getLanduseID() {
		return landuseID;
	}

	/**
	 * Sets the landuse id.
	 *
	 * @param landuseID the new landuse id
	 */
	public void setLanduseID(int landuseID) {
		this.landuseID = landuseID;
	}

	/**
	 * Checks if is canceled.
	 *
	 * @return true, if is canceled
	 */
	public boolean isCanceled() {
		return canceled;
	}

	/**
	 * Checks if is finished.
	 *
	 * @return true, if is finished
	 */
	public boolean isFinished() {
		return finished;
	}
	
	
}
