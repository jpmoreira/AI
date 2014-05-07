package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
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

public class TileDialog extends JDialog {




	private int tileID;

	private Tile[] tiles;

	private Tile tempTile;

	private int numTiles;


	private boolean finished = false;

	private boolean canceled = false;

	
	private JPanel tileIDPanel;

	private JLabel tileIDLabel;

	private JPanel centerPanel;

	private JPanel soilTypePanel;

	private JLabel soilLabel;

	private JComboBox soilCombo;

	private JLabel inclinationLabel;

	private JTextField inclinationField;


	private JPanel areaPanel;

	private JLabel areaLabel;

	private JTextField areaText;

	private JLabel priceLabel;

	private JTextField priceField;
	

	private JPanel buttonsPanel;

	private JButton saveButton;

	private JButton cancelButton;

	private JButton nextButton;

	private JButton previousButton;	


	public TileDialog(JFrame frame, boolean modal, String myMessage, Tile[] argTiles, int tileID){

		super(frame,modal);
		this.tiles = argTiles;
		this.setTileID(tileID);
		numTiles = tiles.length;


		getContentPane().setLayout(new BorderLayout());

		createWidgets();
		addWidgets(getContentPane());

		pack();
		setLocationRelativeTo(frame);
		setVisible(true);

	}


	private void createWidgets() {

		tileIDPanel = new JPanel();
		tileIDPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		tileIDLabel = new JLabel("Tile ID: " + tileID);
	
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		
		soilTypePanel = new JPanel();
		soilTypePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		soilLabel = new JLabel("Soil type:");
		
		String[] soilTypes = {"Clay", "Peaty", "Sandy", "Silty"};
		soilCombo = new JComboBox(soilTypes);
		soilCombo.setSize(100, 0);
		
		areaPanel = new JPanel();
		areaPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		inclinationLabel = new JLabel("Inclination:");
		areaLabel = new JLabel("Area:");
		priceLabel = new JLabel("Price/m2:");
		
		
		if (tiles[tileID] == null){
			
			inclinationField = new JTextField("0",4);
			areaText = new JTextField("0",8);
			priceField = new JTextField("0",8);
			soilCombo.setSelectedIndex(0);
			
		} else {
			
			int index = 0;
			
			if (tiles[tileID].getSoilType() == SoilType.SoilType_Sandy) index = 0;
			else if (tiles[tileID].getSoilType() == SoilType.SoilType_Silty) index = 1;
			else if (tiles[tileID].getSoilType() == SoilType.SoilType_Clay) index = 2;
			else if (tiles[tileID].getSoilType() == SoilType.SoilType_Peaty) index = 3;
			
			soilCombo.setSelectedIndex(index);
			
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
		//centerPanel.add(inclinationPanel);

		contentPane.add(centerPanel,BorderLayout.CENTER);

		
		
		buttonsPanel.add(cancelButton);
		buttonsPanel.add(previousButton);
		buttonsPanel.add(nextButton);
		buttonsPanel.add(saveButton);
		
		contentPane.add(buttonsPanel, BorderLayout.SOUTH);



	}


	private void buildTile() throws Exception, NumberFormatException {

		SoilType tempSoilType;
		
		if (soilCombo.getSelectedIndex() == 0){
			tempSoilType = SoilType.SoilType_Sandy;
		} else if (soilCombo.getSelectedIndex() == 1) {
			tempSoilType = SoilType.SoilType_Silty;
		} else if (soilCombo.getSelectedIndex() == 2) {
			tempSoilType = SoilType.SoilType_Clay;
		} else if (soilCombo.getSelectedIndex() == 3) {
			tempSoilType = SoilType.SoilType_Peaty;
		} else {
			throw new Exception("soil");
		}


		float tempArea;
		float tempPrice;
		int tempIncl;


		tempArea = Float.parseFloat(areaText.getText());
		tempPrice = Float.parseFloat(priceField.getText());
		tempIncl = Integer.parseInt(inclinationField.getText());

		if (tempArea < 0) throw new Exception("area");
		if (tempPrice < 0) throw new Exception("price");
		if (tempIncl < 0 || tempIncl > 100) throw new Exception("incl");


		setTempTile(new Tile(tempSoilType,tempArea,tempIncl,tempPrice));

		return;
	}


	public boolean isFinished() {
		return finished;
	}


	public void setFinished(boolean finished) {
		this.finished = finished;
	}


	public Tile getTempTile() {
		return tempTile;
	}


	public void setTempTile(Tile tempTile) {
		this.tempTile = tempTile;
	}


	public int getTileID() {
		return tileID;
	}


	public void setTileID(int tileID) {
		this.tileID = tileID;
	}


	public boolean isCanceled() {
		return canceled;
	}


	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
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
			
			try {
				buildTile();
				
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



	public class NextButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				buildTile();
				
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



	public class SaveButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			try {
				
				buildTile();
				
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



}
