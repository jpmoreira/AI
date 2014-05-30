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
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import mainPackage.Tile;
import mainPackage.constructions.Construction;

// TODO: Auto-generated Javadoc
/**
 * The Class AdjacenciesDialog.
 */
public class ForbiddenTilesDialog extends JDialog{


	/** The tile id. */
	private int landuseID;

	/** The tiles. */
	private Tile[] tiles;
	
	private Construction[] landuses;

	/** The adj checkboxes. */
	private ArrayList<JCheckBox> adjCheckboxes;

	/** The adjacencies. */
	private ArrayList<Integer> adjacencies;

	/** The num tiles. */
	private int numTiles;

	private double penalty;


	/** The finished. */
	private boolean finished = false;

	/** The canceled. */
	private boolean canceled = false;


	/** The adj panel. */
	private JPanel forbiddenTilesPanel;

	/** The check box label. */
	private JLabel checkBoxLabel;
	
	/** The check box label panel. */
	private JPanel checkBoxLabelPanel;	

	/** The tile id panel. */
	private JPanel landuseIDPanel;
	
	/** The tile id label. */
	private JLabel landuseIDLabel;
	
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


	
	/** Forbidden restrictions panel. */
	private JPanel forbiddenPanel;

	private JLabel forbiddenLabel;
	
	private JSlider forbiddenPenSlider;

	private int numLandUses;
	
	




	/**
	 * Instantiates a new adjacencies dialog.
	 *
	 * @param frame the frame
	 * @param modal the modal
	 * @param myMessage the my message
	 * @param argTiles the arg tiles
	 * @param landuseID the tile id
	 */
	public ForbiddenTilesDialog(JFrame frame, boolean modal, String myMessage, Tile[] argTiles, Construction[] constructions, int id){
		super(frame, modal);

		this.tiles = argTiles;
		this.landuses = constructions;
		this.penalty = landuses[id].getForbiddenTilesPenalty();
		this.landuseID = id;
		this.numTiles = tiles.length;
		this.numLandUses = constructions.length;

		adjCheckboxes = new ArrayList<JCheckBox>();
		adjacencies = new ArrayList<Integer>();
		
		updateForbiddenTiles();

		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		this.setTitle("Forbidden Sites");
		
		createWidgets();
		addWidgets(getContentPane());

		pack();
		setLocationRelativeTo(frame);
		setVisible(true);

	}



	/**
	 * Update adj.
	 */
	private void updateForbiddenTiles() {
		
		for (Tile t: landuses[landuseID].getForbiddenTiles()){
			adjacencies.add(t.getId());
		}
		
	}
	
	
	/**
	 * Checks if is adj.
	 *
	 * @param i the i
	 * @return true, if is adj
	 */
	private boolean isForbidden(int i) {
		for (int j = 0; j < adjacencies.size(); j++){
			if(adjacencies.get(j) == i) return true;
		}
		return false;
	}



	/**
	 * Adds the widgets.
	 *
	 * @param contentPane the content pane
	 */
	private void addWidgets(Container contentPane) {
		
		landuseIDPanel.add(landuseIDLabel);
		contentPane.add(landuseIDPanel);

		checkBoxLabelPanel.add(checkBoxLabel);
		contentPane.add(checkBoxLabelPanel);

		for (int i = 0;i < numTiles; i++){
			forbiddenTilesPanel.add(adjCheckboxes.get(i));

		}
		contentPane.add(forbiddenTilesPanel);
		
		forbiddenPanel.add(forbiddenLabel);
		forbiddenPanel.add(forbiddenPenSlider);
		contentPane.add(forbiddenPanel);
		

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
		
		landuseIDLabel = new JLabel("Landuse ID: " + landuseID + "  Chromosome: " + landuses[landuseID].toCromossome() + " - " + landuses[landuseID].name());

		checkBoxLabel = new JLabel("Lots Adjacencies");
		
		checkBoxLabelPanel = new JPanel();
		checkBoxLabelPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

		forbiddenTilesPanel = new JPanel();
		
		int rows , cols;
		
		if (numTiles%5 > 0){
			rows = numTiles/5+1;
		} else {
			rows = numTiles/5;
		}
		
		if (numTiles > 5) {
			cols = 5;
		} else {
			cols = numTiles;
		}
		

		forbiddenTilesPanel.setLayout(new GridLayout(rows, cols));		

		for (int i = 0 ; i < numTiles; i++){
			JCheckBox tempCheck = new JCheckBox("Lot " + i);
			if (isForbidden(i)){
				tempCheck.setSelected(true);
			}
			adjCheckboxes.add(tempCheck);
		}

		
		
		forbiddenPanel = new JPanel();
		forbiddenPanel.setLayout(new BoxLayout(forbiddenPanel,BoxLayout.Y_AXIS));
		forbiddenPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		forbiddenLabel = new JLabel("Forbidden penalty:");
		forbiddenLabel.setBorder(new EmptyBorder(0, 0, 2, 0));
		forbiddenLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		forbiddenPenSlider = new JSlider(0,100,(int) (penalty*100));
		forbiddenPenSlider.setSnapToTicks(true);
		forbiddenPenSlider.setPaintTicks(true);
		forbiddenPenSlider.setPaintLabels(true);
		forbiddenPenSlider.setMinorTickSpacing(1);
		forbiddenPenSlider.setMajorTickSpacing(10);
		forbiddenPenSlider.setPreferredSize(new Dimension(400,40));
		
		
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING,5,5));

		saveButton = new JButton("Save");
		saveButton.addActionListener(new SaveButtonListener());

		if (getTileID() != numLandUses-1){
			saveButton.setEnabled(false);
		} else{
			saveButton.setEnabled(true);	
		}

		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING,5,5));

		nextButton = new JButton("Next");
		nextButton.addActionListener(new NextButtonListener());

		if (getTileID() == numLandUses-1){
			nextButton.setEnabled(false);
		} else{
			nextButton.setEnabled(true);	
		}

		previousButton = new JButton("Previous");
		previousButton.addActionListener(new PreviousButtonListener());

		if (getTileID() == 0){
			previousButton.setEnabled(false);
		} else{
			previousButton.setEnabled(true);	
		}

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new CancelButtonListener());


	}






	/**
	 * Adds the adj.
	 */
	private void addForbiddenTiles() {
		for (int i = 0; i < numTiles; i++){
			if (adjCheckboxes.get(i).isSelected()){
				adjacencies.add(i);
			}
		}
		
	}






	/**
	 * Gets the adjacencies.
	 *
	 * @return the adjacencies
	 */
	public ArrayList<Integer> getForbiddenTiles() {
		return adjacencies;
	}





	/**
	 * Gets the tile id.
	 *
	 * @return the tile id
	 */
	public int getTileID() {
		return landuseID;
	}








	/**
	 * Checks if is finished.
	 *
	 * @return true, if is finished
	 */
	public boolean isFinished() {
		return finished;
	}



	public double getForbiddenPenaty() {
		return penalty;
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

			addForbiddenTiles();
			
			penalty = ((double) forbiddenPenSlider.getValue())/100;
			
			landuseID--;

			setVisible(false);

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

			addForbiddenTiles();
			
			penalty = ((double) forbiddenPenSlider.getValue())/100;

			landuseID++;

			setVisible(false);


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

			addForbiddenTiles();
			
			penalty = ((double) forbiddenPenSlider.getValue())/100;
			
			finished = true;

			setVisible(false);


		}


	}

}
