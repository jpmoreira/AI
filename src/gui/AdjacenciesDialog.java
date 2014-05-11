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

public class AdjacenciesDialog extends JDialog{


	private int tileID;

	private Tile[] tiles;

	private ArrayList<JCheckBox> adjCheckboxes;

	private ArrayList<Integer> adjacencies;



	private int numTiles;



	private boolean finished = false;

	private boolean canceled = false;


	private JPanel adjPanel;

	private JLabel checkBoxLabel;
	
	private JPanel checkBoxLabelPanel;	

	private JPanel tileIDPanel;
	
	private JLabel tileIDLabel;


	private JPanel buttonsPanel;

	private JButton saveButton;

	private JButton cancelButton;

	private JButton nextButton;

	private JButton previousButton;





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



	private void updateAdj() {
		
		for (Tile tile: tiles[tileID].adjacencies()){
			adjacencies.add(tile.getId());
		}
		
	}
	
	
	private boolean isAdj(int i) {
		for (int j = 0; j < adjacencies.size(); j++){
			if(adjacencies.get(j) == i) return true;
		}
		return false;
	}



	private void addWidgets(Container contentPane) {
		
		tileIDPanel.add(tileIDLabel);
		contentPane.add(tileIDPanel);

		checkBoxLabelPanel.add(checkBoxLabel);
		contentPane.add(checkBoxLabelPanel);

		for (int i = 0;i < numTiles; i++){
			adjPanel.add(adjCheckboxes.get(i));

		}
		contentPane.add(adjPanel);

		buttonsPanel.add(cancelButton);
		buttonsPanel.add(previousButton);
		buttonsPanel.add(nextButton);
		buttonsPanel.add(saveButton);

		contentPane.add(buttonsPanel);

	}








	private void createWidgets() {

		tileIDPanel = new JPanel();
		tileIDPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		tileIDLabel = new JLabel("Tile ID: " + tileID);

		checkBoxLabel = new JLabel("Adjacencies");
		
		checkBoxLabelPanel = new JPanel();
		checkBoxLabelPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

		adjPanel = new JPanel();
		
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
		
		adjPanel.setLayout(new GridLayout(rows, cols));

		for (int i = 0 ; i < numTiles; i++){
			JCheckBox tempCheck = new JCheckBox("Lot " + i);
			if (tileID == i) {
				tempCheck.setEnabled(false);
			} else if (isAdj(i)){
				tempCheck.setSelected(true);
			}

			adjCheckboxes.add(tempCheck);
		}

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






	private void addAdj() {
		for (int i = 0; i < numTiles; i++){
			if (adjCheckboxes.get(i).isSelected()){
				adjacencies.add(i);
			}
		}
		
	}






	public ArrayList<Integer> getAdjacencies() {
		return adjacencies;
	}





	public int getTileID() {
		return tileID;
	}








	public boolean isFinished() {
		return finished;
	}








	public boolean isCanceled() {
		return canceled;
	}


	public class CancelButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			canceled = true;
			setVisible(false);

		}

	}



	public class PreviousButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			addAdj();
			
			tileID--;

			setVisible(false);

		}

	}



	public class NextButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			addAdj();

			tileID++;

			setVisible(false);


		}

	}



	public class SaveButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			finished = true;

			setVisible(false);


		}


	}
}
