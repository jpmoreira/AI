package gui;

import java.awt.Container;
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
import mainPackage.Tile;

// TODO: Auto-generated Javadoc
/**
 * The Class AdjacenciesDialog.
 */
public class AdjacenciesDialog extends JDialog{


	/** The tile id. */
	private int tileID;

	/** The tiles. */
	private Tile[] tiles;

	/** The adj checkboxes. */
	private ArrayList<JCheckBox> adjCheckboxes;

	/** The adjacencies. */
	private ArrayList<Integer> adjacencies;



	/** The num tiles. */
	private int numTiles;



	/** The finished. */
	private boolean finished = false;

	/** The canceled. */
	private boolean canceled = false;


	/** The adj panel. */
	private JPanel adjPanel;

	/** The check box label. */
	private JLabel checkBoxLabel;
	
	/** The check box label panel. */
	private JPanel checkBoxLabelPanel;	

	/** The tile id panel. */
	private JPanel tileIDPanel;
	
	/** The tile id label. */
	private JLabel tileIDLabel;
	
	/** Others adjacencies panels */
	private JPanel othersPanel;
	
	/** Others adjacencies panels */
	private JLabel othersLabel;
	
	private JCheckBox lakeCheckBox;
	
	private JCheckBox highwayCheckBox;
	
	private JCheckBox beachCheckBox;
	
	private JCheckBox riverCheckBox;
	
	private JCheckBox trainStationCheckBox;
	
	private JCheckBox hospitalCheckBox;


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

	private JPanel othersLabelPanel;





	/**
	 * Instantiates a new adjacencies dialog.
	 *
	 * @param frame the frame
	 * @param modal the modal
	 * @param myMessage the my message
	 * @param argTiles the arg tiles
	 * @param tileID the tile id
	 */
	public AdjacenciesDialog(JFrame frame, boolean modal, String myMessage, Tile[] argTiles, int tileID){
		super(frame, modal);

		this.tiles = argTiles;
		this.tileID = tileID;
		numTiles = tiles.length;

		adjCheckboxes = new ArrayList<JCheckBox>();
		adjacencies = new ArrayList<Integer>();
		
		updateAdj();

		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		this.setTitle("Adjacencies Settings");
		
		createWidgets();
		addWidgets(getContentPane());

		pack();
		setLocationRelativeTo(frame);
		setVisible(true);

	}



	/**
	 * Update adj.
	 */
	private void updateAdj() {
		
		for (Tile tile: tiles[tileID].adjacencies()){
			adjacencies.add(tile.getId());
		}
		
	}
	
	
	/**
	 * Checks if is adj.
	 *
	 * @param i the i
	 * @return true, if is adj
	 */
	private boolean isAdj(int i) {
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
		
		tileIDPanel.add(tileIDLabel);
		contentPane.add(tileIDPanel);

		checkBoxLabelPanel.add(checkBoxLabel);
		contentPane.add(checkBoxLabelPanel);

		for (int i = 0;i < numTiles; i++){
			adjPanel.add(adjCheckboxes.get(i));

		}
		contentPane.add(adjPanel);
		
		othersLabelPanel.add(othersLabel);
		contentPane.add(othersLabelPanel);
		
		othersPanel.add(lakeCheckBox);
		othersPanel.add(highwayCheckBox);
		othersPanel.add(hospitalCheckBox);
		othersPanel.add(riverCheckBox);
		othersPanel.add(beachCheckBox);
		othersPanel.add(trainStationCheckBox);
		contentPane.add(othersPanel);
		

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

		tileIDPanel = new JPanel();
		tileIDPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		tileIDLabel = new JLabel("Tile ID: " + tileID);

		checkBoxLabel = new JLabel("Lots Adjacencies");
		
		othersLabel = new JLabel("Others Adjacencies:");
		
		checkBoxLabelPanel = new JPanel();
		checkBoxLabelPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		othersLabelPanel = new JPanel();
		othersLabelPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

		adjPanel = new JPanel();
		othersPanel = new JPanel();
		
		int rows , cols, rowsOthers;
		
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
		
		if (cols < 3){
			rowsOthers = 6/cols;
		} else {
			rowsOthers = 2;
		}
		
		adjPanel.setLayout(new GridLayout(rows, cols));		
		othersPanel.setLayout(new GridLayout(rowsOthers, cols));

		for (int i = 0 ; i < numTiles; i++){
			JCheckBox tempCheck = new JCheckBox("Lot " + i);
			if (tileID == i) {
				tempCheck.setEnabled(false);
			} else if (isAdj(i)){
				tempCheck.setSelected(true);
			}

			adjCheckboxes.add(tempCheck);
		}

		
		//TODO add restrictions to tiles...
		
		lakeCheckBox = new JCheckBox("Lake");
		highwayCheckBox = new JCheckBox("Highway");
		hospitalCheckBox = new JCheckBox("Hospital");
		beachCheckBox = new JCheckBox("Beach");
		riverCheckBox = new JCheckBox("River");
		trainStationCheckBox = new JCheckBox("Train Station");

		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING,5,5));

		saveButton = new JButton("Save");
		saveButton.addActionListener(new SaveButtonListener());

		if (getTileID() != numTiles-1){
			saveButton.setEnabled(false);
		} else{
			saveButton.setEnabled(true);	
		}

		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING,5,5));

		nextButton = new JButton("Next");
		nextButton.addActionListener(new NextButtonListener());

		if (getTileID() == numTiles-1){
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
	private void addAdj() {
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
	public ArrayList<Integer> getAdjacencies() {
		return adjacencies;
	}





	/**
	 * Gets the tile id.
	 *
	 * @return the tile id
	 */
	public int getTileID() {
		return tileID;
	}








	/**
	 * Checks if is finished.
	 *
	 * @return true, if is finished
	 */
	public boolean isFinished() {
		return finished;
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

			addAdj();
			
			tileID--;

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

			addAdj();

			tileID++;

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

			finished = true;

			setVisible(false);


		}


	}
}
