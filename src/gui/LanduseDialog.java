package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Exceptions.ConstructionException;
import mainPackage.constructions.AirportConstruction;
import mainPackage.constructions.Construction;
import mainPackage.constructions.FactoryConstruction;
import mainPackage.constructions.HouseConstruction;
import mainPackage.constructions.ParkConstruction;
import mainPackage.constructions.PrisonConstruction;

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
	private JLabel pricePenLabel;
	
	/** SoilType penalty slider. */
	private JSlider pricePenSlider;
	
	/** SoilType penalty panel. */
	private JPanel pricePenPanel;

	
	private JPanel pricePanel;
	
	private JLabel minPriceLabel;

	private JLabel maxPriceLabel;

	private JTextField minPriceField;

	private JTextField maxPriceField;
	
	
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

	/** Construction type initialization */
	private TypeDialog constructionTypeDialog;




	
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
		
		if (landuses[landuseID] == null) {
			constructionTypeDialog = new TypeDialog(frame, true, "ConstructionType");
			if (canceled) {
				setVisible(false);
			}
		} else {
			this.tempLanduse = landuses[landuseID];
		}

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
		
		pricePanel.add(minPriceLabel);
		pricePanel.add(minPriceField);
		pricePanel.add(maxPriceLabel);
		pricePanel.add(maxPriceField);
		contentPane.add(pricePanel);
			
		pricePenPanel.add(pricePenLabel);
		pricePenPanel.add(pricePenSlider);
		contentPane.add(pricePenPanel);
		
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
		
		FlowLayout flowLeading = new FlowLayout(FlowLayout.LEADING, 5, 5);
		FlowLayout flowCenter = new FlowLayout(FlowLayout.CENTER, 5, 5);
		
		landuseIDPanel = new JPanel();
		landuseIDPanel.setLayout(flowLeading);
		landuseIDPanel.setBorder(new EmptyBorder(10, 0, 5, 0));
		
		landuseIDLabel = new JLabel("Landuse ID: " + getLanduseID() + "  Chromosome: " + landuses[landuseID].toCromossome());
		
		landusePanel = new JPanel();
		landusePanel.setLayout(flowLeading);
		
		landuseLabel = new JLabel("Name:");
		landuseField = new JTextField(landuses[landuseID].name(),20);
		
		areaPanel = new JPanel();
		areaPanel.setLayout(flowLeading);
		areaPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		
		areaPenPanel = new JPanel();
		areaPenPanel.setLayout(flowCenter);
		
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
	
		
		pricePanel = new JPanel();
		pricePanel.setLayout(flowLeading);
		
		minPriceLabel = new JLabel("Min price:");
		maxPriceLabel = new JLabel("Max price:");
		
		minPriceField = new JTextField(((Double) landuses[landuseID].getMinPrice()).toString(),6);
		maxPriceField = new JTextField(((Double) landuses[landuseID].getMaxPrice()).toString(),12);
		
		pricePenLabel = new JLabel("Price penalty:");	
		
		pricePenSlider = new JSlider(0,100,(int)(landuses[landuseID].getSoilTypePen()*100));
		pricePenSlider.setSnapToTicks(true);
		pricePenSlider.setPaintTicks(true);
		pricePenSlider.setPaintLabels(true);
		pricePenSlider.setMinorTickSpacing(1);
		pricePenSlider.setMajorTickSpacing(10);
		pricePenSlider.setPreferredSize(new Dimension(500,40));
		
		pricePenPanel = new JPanel();
		pricePenPanel.setLayout(flowCenter);
		
		
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
	

	/**
	 * Builds the landuse.
	 *
	 * @throws ConstructionException the construction exception
	 */
	private void editLanduse() throws ConstructionException, NumberFormatException {
		
		double tempMinArea;
		double tempMaxArea;
		double tempMinPrice;
		double tempMaxPrice;
		
		tempMinArea = Double.parseDouble(minAreaField.getText());
		tempMaxArea = Double.parseDouble(maxAreaField.getText());
		tempMinPrice = Double.parseDouble(minPriceField.getText());
		tempMaxPrice = Double.parseDouble(maxPriceField.getText());
		
		tempLanduse.setName(landuseField.getText());
		tempLanduse.setAreaConstraint(tempMinArea, tempMaxArea, ((double) areaPenSlider.getValue())/100);
		tempLanduse.setInclinationConstrain(((double) minInclSlider.getValue())/100, ((double) maxInclSlider.getValue())/100, ((double) inclPenSlider.getValue())/100);
		tempLanduse.setPriceConstraint(tempMinPrice, tempMaxPrice, ((double) pricePenSlider.getValue())/100);
		
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
	
	public class TypeDialog extends JDialog{
		
		
		/** Dialog label */
		private JLabel dialogLabel;
		/** Dialog Panel */
		private JPanel dialogPanel;
		
		/** Airport radioButton */
		private JRadioButton airportRadio;
		/** Airport radioButton */
		private JRadioButton factoryRadio;
		/** Airport radioButton */
		private JRadioButton houseRadio;
		/** Airport radioButton */
		private JRadioButton parkRadio;
		/** Airport radioButton */
		private JRadioButton prisonRadio;
		/** Airport radioButton */
		private JRadioButton otherRadio;
		
		/** Constructions Panel */
		private JPanel constructionPanel;
		
		/** The ok input button. */
		private JButton okButton;
		
		/** The cancel input button. */
		private JButton cancelDialogButton;
		
		/** The input buttons panel. */
		private JPanel inputButtonsPanel;
		
		/** Button Group */
		private ButtonGroup buttonGroup;
		private JPanel dialogIDPanel;
		private JLabel dialogIDLabel;
		
		
		public TypeDialog(JFrame frame, boolean modal, String myMessage){
			super(frame,modal);
			
			getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
			this.setTitle("LandUse Settings");

			createTypeWidgets();
			addTypeWidgets(getContentPane());

			pack();
			setLocationRelativeTo(frame);
			setVisible(true);
			
		}

		private void createTypeWidgets() {
			
			dialogIDPanel = new JPanel();
			dialogIDPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
			dialogIDPanel.setBorder(new EmptyBorder(10, 5, 5, 5));
			
			dialogIDLabel = new JLabel("Landuse ID: " + getLanduseID());
			
			dialogPanel = new JPanel();
			dialogPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
			dialogPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			
			dialogLabel = new JLabel("Select the type of construction;");
			
			constructionPanel = new JPanel();
			constructionPanel.setLayout(new GridLayout(2, 3, 5, 5));
			constructionPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			
			airportRadio = new JRadioButton("Airport");
			factoryRadio = new JRadioButton("Factory");
			houseRadio = new JRadioButton("House");
			parkRadio = new JRadioButton("Park");
			prisonRadio = new JRadioButton("Prison");
			otherRadio = new JRadioButton("Other");
			
			buttonGroup = new ButtonGroup();
			
			buttonGroup.add(airportRadio);
			buttonGroup.add(factoryRadio);
			buttonGroup.add(houseRadio);
			buttonGroup.add(parkRadio);
			buttonGroup.add(prisonRadio);
			buttonGroup.add(otherRadio);
			
			// Button Panel
			inputButtonsPanel = new JPanel();
			inputButtonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,5,5));

			// Button - OK
			okButton = new JButton("OK");
			okButton.addActionListener(new OkListener());

			// Button - CANCEL
			cancelDialogButton = new JButton("Cancel");	
			cancelDialogButton.addActionListener(new CancelDialogButton());
		}

		private void addTypeWidgets(Container contentPane) {
			
			dialogIDPanel.add(dialogIDLabel);
			contentPane.add(dialogIDPanel);
			
			dialogPanel.add(dialogLabel);
			contentPane.add(dialogPanel);
			
			constructionPanel.add(airportRadio);
			constructionPanel.add(factoryRadio);
			constructionPanel.add(houseRadio);
			constructionPanel.add(parkRadio);
			constructionPanel.add(prisonRadio);
			constructionPanel.add(otherRadio);
			contentPane.add(constructionPanel);
			
			inputButtonsPanel.add(cancelDialogButton);
			inputButtonsPanel.add(okButton);
			contentPane.add(inputButtonsPanel);
			
		}
		
		
		
		
		public class OkListener implements ActionListener {

				
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (otherRadio.isSelected()){
					
					tempLanduse = Construction.anonymousConstruction("NULL");
					
				} else if (airportRadio.isSelected()){
					
					tempLanduse = new AirportConstruction();
					
				} else if (factoryRadio.isSelected()){
					
					tempLanduse = new FactoryConstruction();
					
				} else if (houseRadio.isSelected()){
					
					tempLanduse = new HouseConstruction();
					
				} else if (parkRadio.isSelected()){
					
					tempLanduse = new ParkConstruction();
					
				} else if (prisonRadio.isSelected()){
					
					tempLanduse = new PrisonConstruction();
					
				} else {
					JOptionPane.showMessageDialog(getParent(), "Select a construction type");
					return;
				}
				
				landuses[landuseID] = tempLanduse;
				
				setVisible(false);
				
			}
		
		}



		public class CancelDialogButton implements ActionListener {

			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				canceled = true;
				setVisible(false);
			}

		}
	}	
}
