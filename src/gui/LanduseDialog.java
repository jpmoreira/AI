package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Exceptions.ConstructionException;
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
	
	/** Area penalty text field. */
	private JTextField areaPenField;
	

	/** The incl panel. */
	private JPanel inclPanel;
	
	/** The min incl label. */
	private JLabel minInclLabel;
	
	/** The min incl field. */
	private JTextField minInclField;
	
	/** The max incl label. */
	private JLabel maxInclLabel;
	
	/** The max incl field. */
	private JTextField maxInclField;
	
	/** Inclination penalty label. */
	private JLabel inclPenLabel;
	
	/** Inclination penalty text field. */
	private JTextField inclPenField;
	

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
		areaPanel.add(areaPenLabel);
		areaPanel.add(areaPenField);
		contentPane.add(areaPanel);
		
		inclPanel.add(minInclLabel);
		inclPanel.add(minInclField);
		inclPanel.add(maxInclLabel);
		inclPanel.add(maxInclField);
		inclPanel.add(inclPenLabel);
		inclPanel.add(inclPenField);
		contentPane.add(inclPanel);
		
		
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
		
		landuseIDLabel = new JLabel("Landuse ID: " + getLanduseID() + "  Chromosome: " + landuses[landuseID].toCromossome());

		
//		centerPanel = new JPanel();
//		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		
		landusePanel = new JPanel();
		landusePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		landuseLabel = new JLabel("Name:");
		landuseField = new JTextField(landuses[landuseID].name(),20);
		
		areaPanel = new JPanel();
		areaPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		minAreaLabel = new JLabel("Area min:");
		maxAreaLabel = new JLabel("Area max:");
		areaPenLabel = new JLabel("Area penalty:");
		
		minAreaField = new JTextField(((Double) landuses[landuseID].getMinArea()).toString(),8);
		maxAreaField = new JTextField(((Double) landuses[landuseID].getMaxArea()).toString(),8);
		areaPenField = new JTextField(((Double) landuses[landuseID].getAreaPenalty()).toString(),8);
		
			
		inclPanel = new JPanel();
		inclPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		minInclLabel = new JLabel("Inclination min:");
		maxInclLabel = new JLabel("Inclination max:");
		inclPenLabel = new JLabel("Inclination penalty:");
		
		minInclField= new JTextField(((Double) landuses[landuseID].getMinIncl()).toString(),8);
		maxInclField = new JTextField(((Double) landuses[landuseID].getMaxIncl()).toString(),8);
		inclPenField = new JTextField(((Double) landuses[landuseID].getInclPenalty()).toString(),8);
		

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
		double tempAreaPen;
		double tempMinIncl;
		double tempMaxIncl;
		double tempInclPen;
		//double TempSoilPenalty;
		
		tempMinArea = Double.parseDouble(minAreaField.getText());
		tempMaxArea = Double.parseDouble(maxAreaField.getText());
		tempAreaPen = Double.parseDouble(areaPenField.getText());
		
		tempMinIncl = Double.parseDouble(minInclField.getText());
		tempMaxIncl = Double.parseDouble(maxInclField.getText());
		tempInclPen = Double.parseDouble(inclPenField.getText());
		
		tempLanduse.setName(landuseField.getText());
		tempLanduse.setAreaConstraint(tempMinArea, tempMaxArea, tempAreaPen);
		tempLanduse.setInclinationConstrain(tempMinIncl, tempMaxIncl, tempInclPen);
		
		//tempLanduse.setSoilConstraint(forbiddenTypes, TempSoilPenalty);
		
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
