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
import mainPackage.Tile;
import mainPackage.constructions.Construction;

public class LanduseDialog extends JDialog{
	
	private int landuseID;

	private Construction[] landuses;

	private Construction tempLanduse;

	private int numLanduses;


	private boolean finished = false;

	private boolean canceled = false;

	
	private JPanel landuseIDPanel;

	private JLabel landuseIDLabel;

	private JPanel centerPanel;

	private JPanel landusePanel;

	private JLabel landuseLabel;

	private JTextField landuseField;
	

	private JPanel buttonsPanel;

	private JButton saveButton;

	private JButton cancelButton;

	private JButton nextButton;

	private JButton previousButton;	
	
	public LanduseDialog(JFrame frame, boolean modal, String myMessage, Construction[] landuses, int landuseID){

		super(frame,modal);
		this.landuses = landuses;
		this.setLanduseID(landuseID);
		numLanduses = landuses.length;


		getContentPane().setLayout(new BorderLayout());

		createWidgets();
		addWidgets(getContentPane());

		pack();
		setLocationRelativeTo(frame);
		setVisible(true);

	}

	private void addWidgets(Container contentPane) {

		landuseIDPanel.add(landuseIDLabel);
		contentPane.add(landuseIDPanel, BorderLayout.NORTH);

		landusePanel.add(landuseLabel);
		landusePanel.add(landuseField);

		centerPanel.add(landusePanel);

		contentPane.add(centerPanel,BorderLayout.CENTER);

		
		
		buttonsPanel.add(cancelButton);
		buttonsPanel.add(previousButton);
		buttonsPanel.add(nextButton);
		buttonsPanel.add(saveButton);
		
		contentPane.add(buttonsPanel, BorderLayout.SOUTH);
		
	}

	private void createWidgets() {
		
		landuseIDPanel = new JPanel();
		landuseIDPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		landuseIDLabel = new JLabel("Landuse ID: " + getLanduseID());
	
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		
		landusePanel = new JPanel();
		landusePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		landuseLabel = new JLabel("Name:");
		
		if (landuses[getLanduseID()] == null){
			
			landuseField = new JTextField("Name",20);
			
		} else {
				
			landuseField = new JTextField(landuses[landuseID].name(),20);
				
		}
		
		
		

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
	

	private void buildLanduse() throws ConstructionException {
		
		tempLanduse = new Construction(landuseField.getText()) {
			
			@Override
			public double affinityToTile(Tile tile) {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		
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
				buildLanduse();
				
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



	public class NextButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				buildLanduse();
				
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



	public class SaveButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			try {
				
				buildLanduse();
				
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



	public Construction getTempLanduse() {
		return tempLanduse;
	}

	public int getLanduseID() {
		return landuseID;
	}

	public void setLanduseID(int landuseID) {
		this.landuseID = landuseID;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public boolean isFinished() {
		return finished;
	}
	
	
}
