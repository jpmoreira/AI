package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;

import mainPackage.SimulatedAnnealingEngine;
import mainPackage.constructions.AirportConstruction;
import mainPackage.constructions.Construction;
import mainPackage.constructions.FactoryConstruction;
import mainPackage.constructions.HouseConstruction;
import mainPackage.constructions.ParkConstruction;
import mainPackage.constructions.PrisonConstruction;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SolverDialog extends JDialog {
	
	private boolean newSettings;

	
	private int option;


	private final JPanel optionsPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JRadioButton rdbtnSimulatedAnnealing;
	private JRadioButton rdbtnGeneticAlgorithm;
	private JRadioButton rdbtnSimAnnGentAlg;
	private JRadioButton rdbtnGenAlgSimAnn;

	/**
	 * Create the dialog.
	 * @param annealingEngine 
	 */
	public SolverDialog(JFrame frame, boolean modal, String myMessage) {
		
		super(frame,modal);
		
		getContentPane().setLayout(new BorderLayout());
		optionsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(optionsPanel, BorderLayout.CENTER);
		optionsPanel.setLayout(new GridLayout(5, 1, 5, 5));
		{
			JLabel lblNewLabel = new JLabel("Select solver option:");
			optionsPanel.add(lblNewLabel);
		}
		{
			rdbtnSimulatedAnnealing = new JRadioButton("Simulated Annealing");
			buttonGroup.add(rdbtnSimulatedAnnealing);
			rdbtnSimulatedAnnealing.setAlignmentX(Component.CENTER_ALIGNMENT);
			optionsPanel.add(rdbtnSimulatedAnnealing);
		}
		{
			rdbtnGeneticAlgorithm = new JRadioButton("Genetic Algorithm");
			buttonGroup.add(rdbtnGeneticAlgorithm);
			rdbtnGeneticAlgorithm.setAlignmentX(Component.CENTER_ALIGNMENT);
			optionsPanel.add(rdbtnGeneticAlgorithm);
		}
		{
			rdbtnSimAnnGentAlg = new JRadioButton("Simulated Annealing + Genetic Algorithm");
			buttonGroup.add(rdbtnSimAnnGentAlg);
			optionsPanel.add(rdbtnSimAnnGentAlg);
		}
		{
			rdbtnGenAlgSimAnn = new JRadioButton("Genetic Algorithm + Simullated Annealing");
			buttonGroup.add(rdbtnGenAlgSimAnn);
			optionsPanel.add(rdbtnGenAlgSimAnn);
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
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
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
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	protected void cancelButtonPressed() {
		newSettings = false;
		setVisible(false);
		
	}

	protected void okButtonPressed() {
		
		if (rdbtnSimulatedAnnealing.isSelected()){
			
			option = 1;
			
		} else if (rdbtnGeneticAlgorithm.isSelected()){
			
			option = 2;
			
		} else if (rdbtnSimAnnGentAlg.isSelected()){
			
			option = 3;
			
		} else if (rdbtnGenAlgSimAnn.isSelected()){
			
			option = 4;
			
		} else {
			JOptionPane.showMessageDialog(getParent(), "Select an option.");
			return;
		}
		
		newSettings = true;
		setVisible(false);
		
	}

	public int getOption() {
		return this.option;
	}

}
