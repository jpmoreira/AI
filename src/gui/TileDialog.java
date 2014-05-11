package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mainPackage.Tile;
import mainPackage.Tile.SoilType;

// TODO: Auto-generated Javadoc
/**
 * The Class TileDialog.
 */
public class TileDialog extends JDialog {




	/** The tile id. */
	private int tileID;

	/** The tiles. */
	private Tile[] tiles;

	/** The temp tile. */
	private Tile tempTile;

	/** The num tiles. */
	private int numTiles;


	/** The finished. */
	private boolean finished = false;

	/** The canceled. */
	private boolean canceled = false;

	
	/** The tile id panel. */
	private JPanel tileIDPanel;

	/** The tile id label. */
	private JLabel tileIDLabel;

	/** The center panel. */
	private JPanel centerPanel;

	/** The soil type panel. */
	private JPanel soilTypePanel;

	/** The soil label. */
	private JLabel soilLabel;

	/** The soil combo. */
	private JComboBox soilCombo;

	/** The inclination label. */
	private JLabel inclinationLabel;

	/** The inclination field. */
	private JTextField inclinationField;


	/** The area panel. */
	private JPanel areaPanel;

	/** The area label. */
	private JLabel areaLabel;

	/** The area text. */
	private JTextField areaText;

	/** The price label. */
	private JLabel priceLabel;

	/** The price field. */
	private JTextField priceField;
	

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
	 * Instantiates a new tile dialog.
	 *
	 * @param frame the frame
	 * @param modal the modal
	 * @param myMessage the my message
	 * @param argTiles the arg tiles
	 * @param tileID the tile id
	 */
	public TileDialog(JFrame frame, boolean modal, String myMessage, Tile[] argTiles, int tileID){

		super(frame,modal);
		this.tiles = argTiles;
		this.setTileID(tileID);
		numTiles = tiles.length;
		setTempTile(tiles[tileID]);

		getContentPane().setLayout(new BorderLayout());
		this.setTitle("Sites Settings");

		createWidgets();
		addWidgets(getContentPane());

		pack();
		setLocationRelativeTo(frame);
		setVisible(true);

	}


	/**
	 * Creates the widgets.
	 */
	private void createWidgets() {

		tileIDPanel = new JPanel();
		tileIDPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		
		soilTypePanel = new JPanel();
		soilTypePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		soilLabel = new JLabel("Soil type:");
		
		soilCombo = new JComboBox();
		soilCombo.setModel(new DefaultComboBoxModel(Tile.SoilType.values()));
		
		areaPanel = new JPanel();
		areaPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		inclinationLabel = new JLabel("Inclination:");
		areaLabel = new JLabel("Area:");
		priceLabel = new JLabel("Price/m2:");
		
		
		if (tiles[tileID].getSoilType() == null){
			tileIDLabel = new JLabel("Tile ID: " + tileID);
			
			inclinationField = new JTextField("0",4);
			areaText = new JTextField("0",8);
			priceField = new JTextField("0",8);
			soilCombo.setSelectedIndex(0);
			
		} else {
			
			tileIDLabel = new JLabel("Tile ID: " + tiles[tileID].getId());
			
			soilCombo.setSelectedItem(tiles[tileID].getSoilType());
			
			Integer incl = tiles[tileID].getMaxInclination();		
			inclinationField = new JTextField(incl.toString(),4);
			
			Float tempArea = tiles[tileID].getArea();		
			areaText = new JTextField(tempArea.toString(),4);
			
			Float tempPrice = tiles[tileID].getPricePerAreaUnit();		
			priceField = new JTextField(tempPrice.toString(),4);
			
		}

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
	 * Adds the widgets.
	 *
	 * @param contentPane the content pane
	 */
	private void addWidgets(Container contentPane) {

		tileIDPanel.add(tileIDLabel);
		contentPane.add(tileIDPanel, BorderLayout.NORTH);

		soilTypePanel.add(soilLabel);
		soilTypePanel.add(soilCombo);
		soilTypePanel.add(inclinationLabel);
		soilTypePanel.add(inclinationField);

		areaPanel.add(areaLabel);
		areaPanel.add(areaText);
		areaPanel.add(priceLabel);
		areaPanel.add(priceField);

		centerPanel.add(soilTypePanel);
		centerPanel.add(areaPanel);

		contentPane.add(centerPanel,BorderLayout.CENTER);

		
		
		buttonsPanel.add(cancelButton);
		buttonsPanel.add(previousButton);
		buttonsPanel.add(nextButton);
		buttonsPanel.add(saveButton);
		
		contentPane.add(buttonsPanel, BorderLayout.SOUTH);



	}


	/**
	 * Edits the tile.
	 *
	 * @throws Exception the exception
	 * @throws NumberFormatException the number format exception
	 */
	private void editTile() throws Exception, NumberFormatException {

		SoilType tempSoilType = (SoilType) soilCombo.getSelectedItem();


		float tempArea;
		float tempPrice;
		int tempIncl;


		tempArea = Float.parseFloat(areaText.getText());
		tempPrice = Float.parseFloat(priceField.getText());
		tempIncl = Integer.parseInt(inclinationField.getText());

		if (tempArea < 0) throw new Exception("area");
		if (tempPrice < 0) throw new Exception("price");
		if (tempIncl < 0 || tempIncl > 100) throw new Exception("incl");

		tempTile.setArea(tempArea);
		tempTile.setMaxInclination(tempIncl);
		tempTile.setPricePerAreaUnit(tempPrice);
		tempTile.setSoilType(tempSoilType);
		
		return;
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
	 * Sets the finished.
	 *
	 * @param finished the new finished
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}


	/**
	 * Gets the temp tile.
	 *
	 * @return the temp tile
	 */
	public Tile getTempTile() {
		return tempTile;
	}


	/**
	 * Sets the temp tile.
	 *
	 * @param tempTile the new temp tile
	 */
	public void setTempTile(Tile tempTile) {
		this.tempTile = tempTile;
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
	 * Sets the tile id.
	 *
	 * @param tileID the new tile id
	 */
	public void setTileID(int tileID) {
		this.tileID = tileID;
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
	 * Sets the canceled.
	 *
	 * @param canceled the new canceled
	 */
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
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
				editTile();
				
				setTileID(getTileID() - 1);
				
				setVisible(false);
			}
			catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(getParent(), "Diversity usage factor and probability to rank must Double");
			}
			catch (Exception e1){
				if (e1.getMessage().equals("soil")){
					JOptionPane.showMessageDialog(getParent(), "Select Soil Type");
				} else if (e1.getMessage().equals("area")){
					JOptionPane.showMessageDialog(getParent(), "Area must be  Double");
				} else if (e1.getMessage().equals("incl")){
					JOptionPane.showMessageDialog(getParent(), "Inclination must be an Integer between 0 and 100");
				} else if (e1.getMessage().equals("price")){
					JOptionPane.showMessageDialog(getParent(), "Price must be  Double");
				} else {
					JOptionPane.showMessageDialog(getParent(), "Unknow error");
				}
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
				
				editTile();	
				
				setTileID(getTileID() + 1);

				setVisible(false);
			}
			catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(getParent(), "Diversity usage factor and probability to rank must Double");
			}
			catch (Exception e1){
				if (e1.getMessage().equals("soil")){
					JOptionPane.showMessageDialog(getParent(), "Select Soil Type");
				} else if (e1.getMessage().equals("area")){
					JOptionPane.showMessageDialog(getParent(), "Area must be  Double");
				} else if (e1.getMessage().equals("incl")){
					JOptionPane.showMessageDialog(getParent(), "Inclination must be an Integer between 0 and 100");
				} else if (e1.getMessage().equals("price")){
					JOptionPane.showMessageDialog(getParent(), "Price must be  Double");
				} else {
					JOptionPane.showMessageDialog(getParent(), "Unknow error");
				}
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
				
				editTile();
				
				finished = true;

				setVisible(false);
				
			}
			catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(getParent(), "Diversity usage factor and probability to rank must Double");
			}
			catch (Exception e1){
				if (e1.getMessage().equals("soil")){
					JOptionPane.showMessageDialog(getParent(), "Select Soil Type");
				} else if (e1.getMessage().equals("area")){
					JOptionPane.showMessageDialog(getParent(), "Area must be  Double");
				} else if (e1.getMessage().equals("incl")){
					JOptionPane.showMessageDialog(getParent(), "Inclination must be an Integer between 0 and 100");
				} else if (e1.getMessage().equals("price")){
					JOptionPane.showMessageDialog(getParent(), "Price must be  Double");
				} else {
					JOptionPane.showMessageDialog(getParent(), "Unknow error");
				}
			}

		}
		

	}



	/**
	 * Gets the tiles.
	 *
	 * @return the tiles
	 */
	public Tile[] getTiles() {
		return tiles;
	}



}
