package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			//LandUseInitializationDialog dialog = new LandUseInitializationDialog();
			//dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			//dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private JRadioButton rdbtnOther;
	private JRadioButton rdbtnPrison;;
	private JRadioButton rdbtnPark;
	private JRadioButton rdbtnHouse;
	private JRadioButton rdbtnFactory;
	private JRadioButton rdbtnAirport;
	
	
	
	private Construction tempLanduse;
	private Construction[] landuses;
	private int landuseID;
	private int numLanduses;
	

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
				JLabel lblLanduseID = new JLabel("Landuse ID:");
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
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelButtonPressed();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JButton btnPrevious = new JButton("Previous");
				btnPrevious.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						previousButtonPressed();
					}
				});
				buttonPane.add(btnPrevious);
			}
			{
				JButton btnNext = new JButton("Next");
				btnNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						nextButtonPressed();
					}
				});
				buttonPane.add(btnNext);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okButtonPressed();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
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



	protected void cancelButtonPressed() {
		// TODO Auto-generated method stub
		
	}



	protected void previousButtonPressed() {
		// TODO Auto-generated method stub
		
		try {
			initializeLandUse();
			
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
	

	
	protected void nextButtonPressed() {
		// TODO Auto-generated method stub
		
		
		try {
			initializeLandUse();
			
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
	

	protected void okButtonPressed() {
		// TODO Auto-generated method stub
		try {
			
			initializeLandUse();
			
			setVisible(false);
			
		}
		catch(ConstructionException c) {
			JOptionPane.showMessageDialog(getParent(), "Introduce the name of landuse");
		}
		catch (Exception e1){
			JOptionPane.showMessageDialog(getParent(), "Unknow error");
		}

		
	}



	private void initializeLandUse() throws ConstructionException, Exception {
		// TODO Auto-generated method stub
		
		if (rdbtnOther.isSelected()){
			
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
			JOptionPane.showMessageDialog(getParent(), "Select a construction type");
			return;
		}
		
		landuses[getLanduseID()] = tempLanduse;
		
	}
}
