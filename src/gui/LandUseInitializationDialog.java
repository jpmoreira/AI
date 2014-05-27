package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Component;

import javax.swing.JRadioButton;

import java.awt.GridLayout;

import javax.swing.JTextField;

import Exceptions.ConstructionException;
import mainPackage.constructions.AirportConstruction;
import mainPackage.constructions.Construction;
import mainPackage.constructions.FactoryConstruction;
import mainPackage.constructions.HouseConstruction;
import mainPackage.constructions.ParkConstruction;
import mainPackage.constructions.PrisonConstruction;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LandUseInitializationDialog extends JDialog {

	private final JPanel rdbPanel = new JPanel();
	private JTextField textFieldName;
	


	private JRadioButton rdbtnOther;
	private JRadioButton rdbtnPrison;;
	private JRadioButton rdbtnPark;
	private JRadioButton rdbtnHouse;
	private JRadioButton rdbtnFactory;
	private JRadioButton rdbtnAirport;

	private JButton saveButton;
	private JButton btnNext;
	private JButton btnPrevious;
	private JButton cancelButton;


	private Construction tempLanduse;
	private Construction[] landuses;
	private int landuseID;
	private int numLanduses;
	private boolean finished = false;
	private boolean canceled = false;
	private ButtonGroup buttonGroup;



	/**
	 * Create the dialog.
	 */
	public LandUseInitializationDialog(JFrame frame, boolean modal, String myMessage, Construction[] landuses, int landuseID){

		super(frame,modal);
		this.landuses = landuses;
		this.setLanduseID(landuseID);
		numLanduses = landuses.length;

		
		getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		this.setTitle("Landuse Initialization");
		{
			JPanel lblPanel = new JPanel();
			lblPanel.setBorder(new EmptyBorder(5, 5, 0, 5));
			getContentPane().add(lblPanel);
			lblPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
			{
				JLabel lblLanduseID = new JLabel("Landuse ID:" + this.landuseID);
				lblPanel.add(lblLanduseID);
			}
		}
		rdbPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
		getContentPane().add(rdbPanel, BorderLayout.CENTER);
		rdbPanel.setLayout(new GridLayout(2, 4, 3, 3));
		{
			rdbtnAirport = new JRadioButton("Airport");
			rdbPanel.add(rdbtnAirport);
		}
		{
			rdbtnFactory = new JRadioButton("Factory");
			rdbPanel.add(rdbtnFactory);
		}
		{
			rdbtnHouse = new JRadioButton("House");
			rdbPanel.add(rdbtnHouse);
		}
		{
			rdbtnPark = new JRadioButton("Park");
			rdbPanel.add(rdbtnPark);
		}
		{
			rdbtnPrison = new JRadioButton("Prison");
			rdbPanel.add(rdbtnPrison);
		}
		{
			rdbtnOther = new JRadioButton("Other");
			rdbtnOther.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					textFieldName.setEnabled(rdbtnOther.isSelected());
				}
			});
			rdbPanel.add(rdbtnOther);
		}
		{
			textFieldName = new JTextField();
			textFieldName.setEnabled(rdbtnOther.isSelected());
			rdbPanel.add(textFieldName);
			textFieldName.setColumns(7);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelButtonPressed();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				btnPrevious = new JButton("Previous");
				btnPrevious.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						previousButtonPressed();
					}
				});
				buttonPane.add(btnPrevious);
			}
			{
				btnNext = new JButton("Next");
				btnNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						nextButtonPressed();
					}
				});
				buttonPane.add(btnNext);
			}
			{
				saveButton = new JButton("Save");
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveButtonPressed();
					}
				});
				buttonPane.add(saveButton);
			}
		}
		
		buttonGroup = new ButtonGroup();
		
		buttonGroup.add(rdbtnAirport);
		buttonGroup.add(rdbtnFactory);
		buttonGroup.add(rdbtnHouse);
		buttonGroup.add(rdbtnPark);
		buttonGroup.add(rdbtnPrison);
		buttonGroup.add(rdbtnOther);
		
		
		if (landuses[landuseID] != null) {
			
			JOptionPane.showMessageDialog(getParent(), "<html>Warning!<br>Landuses already initialized.");
			
			if (landuses[landuseID].getClass().equals(AirportConstruction.class)){
				rdbtnAirport.setSelected(true);
			} else if (landuses[landuseID].getClass().equals(FactoryConstruction.class)){
				rdbtnFactory.setSelected(true);
			} else if (landuses[landuseID].getClass().equals(HouseConstruction.class)){
				rdbtnHouse.setSelected(true);
			} else if (landuses[landuseID].getClass().equals(ParkConstruction.class)){
				rdbtnPark.setSelected(true);
			} else if (landuses[landuseID].getClass().equals(PrisonConstruction.class)){
				rdbtnPrison.setSelected(true);
			} else {
				rdbtnOther.setSelected(true);
				textFieldName.setText(this.landuses[this.landuseID].name());
				textFieldName.setEnabled(true);
			}
		}
		

		if (getLanduseID() != numLanduses-1){
			saveButton.setEnabled(false);
		} else{
			saveButton.setEnabled(true);	
		}

		if (getLanduseID() == numLanduses-1){
			btnNext.setEnabled(false);
		} else{
			btnNext.setEnabled(true);	
		}

		if (getLanduseID() == 0){
			btnPrevious.setEnabled(false);
		} else{
			btnPrevious.setEnabled(true);	
		}

		pack();
		setLocationRelativeTo(frame);
		setVisible(true);

	}



	public int getLanduseID() {
		return landuseID;
	}





	public void setLanduseID(int landuseID) {
		this.landuseID = landuseID;
	}


	public Construction getTempLanduse() {
		// TODO Auto-generated method stub
		return tempLanduse;
	}



	private void initializeLandUse() throws Exception {
		// TODO Auto-generated method stub

		if (rdbtnOther.isSelected()){
			
			if (textFieldName.getText().equals("")) throw new Exception("name");
			
			tempLanduse = Construction.anonymousConstruction(textFieldName.getText());

		} else if (rdbtnAirport.isSelected()){

			tempLanduse = new AirportConstruction();

		} else if (rdbtnFactory.isSelected()){

			tempLanduse = new FactoryConstruction();

		} else if (rdbtnHouse.isSelected()){

			tempLanduse = new HouseConstruction();

		} else if (rdbtnPark.isSelected()){

			tempLanduse = new ParkConstruction();

		} else if (rdbtnPrison.isSelected()){

			tempLanduse = new PrisonConstruction();

		} else {
			throw new Exception("type");
		}
//
//		landuses[getLanduseID()] = tempLanduse;

	}
	
	


	private void editLanduse() throws Exception {
		// TODO Auto-generated method stub
		
		if (rdbtnOther.isSelected()){
			
			if (textFieldName.getText().equals("")) throw new Exception("name");
			
			tempLanduse.setName(textFieldName.getText());
			tempLanduse = (Construction) tempLanduse;

		} else if (rdbtnAirport.isSelected()){

			tempLanduse = (AirportConstruction) tempLanduse;

		} else if (rdbtnFactory.isSelected()){

			tempLanduse = (FactoryConstruction) tempLanduse;

		} else if (rdbtnHouse.isSelected()){

			tempLanduse = (HouseConstruction) tempLanduse;

		} else if (rdbtnPark.isSelected()){

			tempLanduse = (ParkConstruction) tempLanduse;

		} else if (rdbtnPrison.isSelected()){

			tempLanduse = (PrisonConstruction) tempLanduse;

		} else {
			throw new Exception("type");
		}
		
	}



	protected void cancelButtonPressed() {
		// TODO Auto-generated method stub

		canceled = true;

		setVisible(false);

	}



	protected void previousButtonPressed() {
		// TODO Auto-generated method stub

		try {
			
			if (landuses[landuseID] == null){
				initializeLandUse();
			} else {
				editLanduse();
			}

			landuseID--;

			setVisible(false);
		}
		catch (Exception e1){
			if (e1.getMessage().equals("name")) {
				JOptionPane.showMessageDialog(getParent(), "Introduce the name of landuse.");
			} else if (e1.getMessage().equals("type")) {
				JOptionPane.showMessageDialog(getParent(), "Select one landuse type.");
			} else {
				JOptionPane.showMessageDialog(getParent(), "Unknow error");
			}
			
		}

	}


	protected void nextButtonPressed() {
		// TODO Auto-generated method stub


		try {
			
			if (landuses[landuseID] == null){
				initializeLandUse();
			} else {
				editLanduse();
			}

			landuseID++;

			setVisible(false);
		}
		catch (Exception e1){
			if (e1.getMessage().equals("name")) {
				JOptionPane.showMessageDialog(getParent(), "Introduce the name of landuse.");
			} else if (e1.getMessage().equals("type")) {
				JOptionPane.showMessageDialog(getParent(), "Select one landuse type.");
			} else {
				JOptionPane.showMessageDialog(getParent(), "Unknow error");
			}
			
		}
	}


	protected void saveButtonPressed() {
		// TODO Auto-generated method stub
		try {

			if (landuses[landuseID] == null){
				initializeLandUse();
			} else {
				editLanduse();
			}
		
			finished = true;

			setVisible(false);

		}
		catch (Exception e1){
			if (e1.getMessage().equals("name")) {
				JOptionPane.showMessageDialog(getParent(), "Introduce the name of landuse.");
			} else if (e1.getMessage().equals("type")) {
				JOptionPane.showMessageDialog(getParent(), "Select one landuse type.");
			} else {
				JOptionPane.showMessageDialog(getParent(), "Unknow error");
			}
			
		}


	}



	public boolean isFinished() {
		// TODO Auto-generated method stub
		return finished;
	}



	public boolean isCanceled() {
		// TODO Auto-generated method stub
		return canceled;
	}







}
